#include <Arduino.h>
#include <DSPI.h>
#include "XTREMIS_SD.h"
#include <EEPROM.h>
#include "XTREMIS_Library.h"
#include "XTREMIS_Library_Definitions.h"

// This is assuming a 1024 SPS rate
/*
#define BLOCK_30SEC  3600
#define BLOCK_1MIN   7200
#define BLOCK_5MIN   36000
#define BLOCK_15MIN  108000
#define BLOCK_30MIN  216000
#define BLOCK_1HR    432000
#define BLOCK_2HR    864000
#define BLOCK_4HR    1728000
#define BLOCK_12HR   5184000
#define BLOCK_24HR   10368000
*/
// This is assuming a 1KSPS * factor rate
#define FR 1.0
#define BLOCK_30SEC  3600*FR
#define BLOCK_1MIN   7200*FR
#define BLOCK_5MIN   36000*FR
#define BLOCK_15MIN  108000*FR
#define BLOCK_30MIN  216000*FR
#define BLOCK_1HR    432000*FR
#define BLOCK_2HR    864000*FR
#define BLOCK_4HR    1728000*FR
#define BLOCK_12HR   5184000*FR
#define BLOCK_24HR   10368000*FR

#define OVER_DIM 20 // make room for up to 20 write-time overruns

char fileSize = '0';  // SD file size indicator
int blockCounter = 0;

uint32_t BLOCK_COUNT;
SdFile openfile;  // want to put this before setup...
Sd2Card card(&board.spi,SD_SS);// SPI needs to be init'd before here
SdVolume volume;
SdFile root;
uint8_t* pCache;      // array that points to the block buffer on SD card
uint32_t MICROS_PER_BLOCK = 2000; // block write longer than this will get flaged
uint32_t bgnBlock, endBlock; // file extent bookends
int byteCounter = 0;    // used to hold position in cache
//int blockCounter;       // count up to BLOCK_COUNT with this
boolean openvol;
boolean cardInit = false;
boolean fileIsOpen = false;


struct {
  uint32_t block;   // holds block number that over-ran
  uint32_t micro;  // holds the length of this of over-run
} over[OVER_DIM];

uint32_t overruns;      // count the number of overruns
uint32_t maxWriteTime;  // keep track of longest write time
uint32_t minWriteTime;  // and shortest write time
uint32_t t;        // used to measure total file write time
uint32_t start_time, end_time;
uint32_t sampleCounter;

byte fileTens, fileOnes;  // enumerate succesive files on card and store number in EEPROM
char currentFileName[] = "XTRMS_00.TXT"; // file name will enumerate in hex 00 - FF

prog_char elapsedTime[] PROGMEM = {"\n%Total time mS:\n"};  // 17
prog_char minTime[] PROGMEM = {  "%min Write time uS:\n"};  // 20
prog_char maxTime[] PROGMEM = {  "%max Write time uS:\n"};  // 20
prog_char overNum[] PROGMEM = {  "%Over:\n"};               //  7
prog_char blockTime[] PROGMEM = {  "%block, uS\n"};         // 11    74 chars + 2 32(16) + 2 16(8) = 98 + (n 32x2) up to 24 overruns...
prog_char stopStamp[] PROGMEM = {  "%STOP AT\n"};      // used to stamp SD record when stopped by PC
prog_char startStamp[] PROGMEM = {  "%START AT\n"};    // used to stamp SD record when started by PC

// Booleans Required for SD_Card_Stuff.ino
boolean addAccelToSD = false; // On writeDataToSDcard() call adds Accel data to SD card write
boolean addAuxToSD = false; // On writeDataToSDCard() call adds Aux data to SD card write
boolean SDfileOpen = false; // Set true by SD_Card_Stuff.ino on successful file open

// Prototypes
void setup();
void loop();
char sdProcessChar(char);
boolean setupSDcard(char);
boolean closeSDfile();
void writeDataToSDcard(byte);
void writeCache();
void stampSD(boolean);
void writeFooter();
void convertToHex(long, int, boolean);
void incrementFileCounter();
void readTriggerPins();
void setupTriggerPins();
void writeTriggerPinsOnes();
void writeTriggerPinsZeroes();
void processDataFromArduino();

void setup() {
    // Bring up the openBCI board
    board.beginDebug();

    board.useAccel = true;
    board.useAux = false;

    // to enable timing, set to true
    board.timeSynced = false;

    sampleCounter=0;
    board.powerUpSequence();
}


void loop() {
    // put your main code here, to run repeatedly
    // Check for triggers
    if (board.streaming) {
        if(board.channelDataAvailable){        // wait until data is available
            // read from ADS, store data, set channelDataAvailable to false
             board.updateChannelData();

              // If triggers enabled, record trigger
              if(board.useAux){
                String trigger = board.checkTrigger();
                if(trigger != ""){
                    short triggerNumber = board.triggerToAux(trigger);
                    board.auxData[0] = board.auxData[1] = board.auxData[2] = triggerNumber;
                    addAuxToSD = true;
                }
              }
             if(board.useAccel){
                if(board.accelHasNewData()){
                    board.accelUpdateAxisData();
                    addAccelToSD = true;
                }
             }
             // Verify the SD file is open
            if(SDfileOpen) {
              // Write to the SD card, writes aux data
              writeDataToSDcard(board.sampleCounter);
            }

             // This writes to serial for the ESP8266 to pick up and also increments
             // the sample counter
            if(board.timeSynced){
                board.sendChannelDataWithTimeAndAccel();
            } else {
                if(board.useAccel){
                    // Send standard packet with channel data
                    board.sampleCounter++;
                    /* board.sendChannelDataWithAccel(); */
                } else {
                    board.sampleCounter++;
                    /* board.sendChannelData(); */
                }
            }
            sampleCounter++;
        }
    }

    // COMMENT THIS OUT IF YOU DON'T WANT THE BOARD TO PICK UP WHAT THE ARDUINO IS SAYING
    processDataFromArduino();
}

void processDataFromArduino(){
    // Check serial 0 for new data
    if (board.hasDataSerial0()) {
        // Read one char from the serial 0 port
        char newChar = board.getCharSerial0();
        board.sendChannelData();

        if(board.streaming){
            if(newChar == 's'){
                // Send to the sd library for processing
                sdProcessChar(newChar);
                // Send to the board library
                board.processChar(newChar);
            }
        } else {
            // Send to the sd library for processing
            sdProcessChar(newChar);
            // Send to the board library
            board.processChar(newChar);
        }
    }
}

char sdProcessChar(char character) {
    switch (character) {
        case 'U': // ~31 sec
        case 'T': // ~1 min (mostly for testing)
        case 'A': // 5min
        case 'S': // 15min
        case 'F': // 30min
        case 'G': // 1hr
        case 'H': // 2hr
        case 'J': // 4hr
        case 'K': // 12hr
        case 'L': // 24hr
        case 'a': // 512 blocks
            start_time = millis();
            fileSize = character;
            SDfileOpen = setupSDcard(character);
            break;
        case 'j': // close the file, if it's open
            if(SDfileOpen){
                SDfileOpen = closeSDfile();
                BLOCK_COUNT = 0;
            }
            break;
        case 's':
            if(SDfileOpen) {
                stampSD(ACTIVATE);
            }
            break;
        case 'z':
            if(SDfileOpen) {
                stampSD(DEACTIVATE);
            }
            break;
        default:
            break;
    }
    return character;
}


boolean setupSDcard(char limit){
  if(!cardInit){
      // Wait until card initializes properly
      // this is not good practice but
      // current iteration of the hardware requires 2 tries to work
      while(!card.init(SPI_FULL_SPEED, SD_SS)) {}

      Serial0.println("Wiring is correct and a card is present.");
      cardInit = true;

      if (!volume.init(card)) { // Now we will try to open the 'volume'/'partition' - it should be FAT16 or FAT32
          Serial0.println("Could not find FAT16/FAT32 partition. Make sure you've formatted the card");
          board.sendEOT();
        return fileIsOpen;
      }
   }

  // use limit to determine file size
  switch(limit){
    case 'h':
      BLOCK_COUNT = 50; break;
    case 'a':
      BLOCK_COUNT = 512; break;
    case 'U':
      BLOCK_COUNT = BLOCK_30SEC; break;
    case 'T':
      BLOCK_COUNT = BLOCK_1MIN; break;
    case 'A':
      BLOCK_COUNT = BLOCK_5MIN; break;
    case 'S':
      BLOCK_COUNT = BLOCK_15MIN; break;
    case 'F':
      BLOCK_COUNT = BLOCK_30MIN; break;
    case 'G':
      BLOCK_COUNT = BLOCK_1HR; break;
    case 'H':
      BLOCK_COUNT = BLOCK_2HR; break;
    case 'J':
      BLOCK_COUNT = BLOCK_4HR; break;
    case 'K':
      BLOCK_COUNT = BLOCK_12HR; break;
    case 'L':
      BLOCK_COUNT = BLOCK_24HR; break;
    default:
      if(!board.streaming) {
        Serial0.println("invalid BLOCK count");
        board.sendEOT(); // Write end of transmission because we exit here
      }
      return fileIsOpen;
  }

  incrementFileCounter();
  openvol = root.openRoot(volume);
  openfile.remove(root, currentFileName); // if the file is over-writing, let it!

  if (!openfile.createContiguous(root, currentFileName, BLOCK_COUNT*512UL)) {
    if(!board.streaming) {
      Serial0.print("createfdContiguous fail");
    }
    cardInit = false;
  }//else{Serial0.print("got contiguous file...");delay(1);}
  // get the location of the file's blocks
  if (!openfile.contiguousRange(&bgnBlock, &endBlock)) {
    if(!board.streaming) {
      Serial0.print("get contiguousRange fail");
    }
    cardInit = false;
  }//else{Serial0.print("got file range...");delay(1);}
  // grab the Cache
  pCache = (uint8_t*)volume.cacheClear();
  // tell card to setup for multiple block write with pre-erase
  if (!card.erase(bgnBlock, endBlock)){
    if(!board.streaming) {
      Serial0.println("erase block fail");
    }
    cardInit = false;
  }//else{Serial0.print("erased...");delay(1);}
  if (!card.writeStart(bgnBlock, BLOCK_COUNT)){
    if(!board.streaming) {
      Serial0.println("writeStart fail");
    }
    cardInit = false;
  } else{
    fileIsOpen = true;
    delay(1);
  }
  board.csHigh(SD_SS);  // release the spi
  // initialize write-time overrun error counter and min/max wirte time benchmarks
  overruns = 0;
  maxWriteTime = 0;
  minWriteTime = 65000;
  byteCounter = 0;  // counter from 0 - 512
  blockCounter = 0; // counter from 0 - BLOCK_COUNT;
  if(fileIsOpen == true){  // send corresponding file name to controlling program
    if(!board.streaming) {
      Serial0.print("Corresponding SD file ");
      Serial0.println(currentFileName);
    }
  }
  if(!board.streaming) {
    board.sendEOT();
  }
  t = millis();
  return fileIsOpen;
}

boolean closeSDfile(){
  if(fileIsOpen){
    board.csLow(SD_SS);  // take spi
    card.writeStop();
    openfile.close();
    board.csHigh(SD_SS);  // release the spi
    fileIsOpen = false;
    if(!board.streaming){ // verbosity. this also gets insterted as footer in openFile
        end_time = millis() - start_time;
      Serial0.print("Real Elapsed Time: " ); Serial0.println(end_time);
      Serial0.print("Counter: "); Serial0.println(sampleCounter);
      Serial0.print("Total Elapsed Time: ");Serial0.print(t/1000);Serial0.println(" Sec"); //delay(10);
    //  Serial0.print("Max write time: "); Serial0.print(maxWriteTime); Serial0.println(" uS"); //delay(10);
    //  Serial0.print("Min write time: ");Serial0.print(minWriteTime); Serial0.println(" uS"); //delay(10);
    //  Serial0.print("Overruns: "); Serial0.print(overruns); Serial0.println(); //delay(10);
      Serial0.print("Wrote to file: "); Serial0.println(currentFileName);
      if (overruns) {
        uint8_t n = overruns > OVER_DIM ? OVER_DIM : overruns;
        Serial0.println("fileBlock,micros");
        for (uint8_t i = 0; i < n; i++) {
          Serial0.print(over[i].block); Serial0.print(','); Serial0.println(over[i].micro);
        }
      }
      board.sendEOT();
    }
  }else{
    if(!board.streaming) {
      Serial0.println("No open file to close");
      board.sendEOT();
    }
  }
  // delay(100); // cool down
  return fileIsOpen;
}

void writeDataToSDcard(byte sampleNumber){
  boolean addComma = true;
  // convert 8 bit sampleCounter into HEX
  convertToHex(sampleNumber, 2, addComma);
  // convert 24 bit channelData into HEX
  for (int currentChannel = 0; currentChannel < 8; currentChannel++){
    convertToHex(board.boardChannelDataInt[currentChannel], 5, addComma);
    if(board.daisyPresent == false){
      if(currentChannel == 6){
        addComma = false;
        if(addAuxToSD || addAccelToSD) {
            addComma = true;
        }  // format CSV
      }
    }
  }

  // Time log
  // Convert time in milliseconds to HEX
  convertToHex(millis() - start_time, 5, addComma);

  if(addAuxToSD == true){
    // convert auxData into HEX
    for(int currentChannel = 0; currentChannel <  3; currentChannel++){
      convertToHex(board.auxData[currentChannel], 3, addComma);
      if(currentChannel == 1) addComma = false;
    }
    addAuxToSD = false;
  }// end of aux data log
  else if(addAccelToSD == true){  // if we have accelerometer data to log
    // convert 16 bit accelerometer data into HEX
    for (int currentChannel = 0; currentChannel < 3; currentChannel++){
      convertToHex(board.axisData[currentChannel], 3, addComma);
      if(currentChannel == 1) addComma = false;
    }
    addAccelToSD = false;  // reset addAccel
  }// end of accelerometer data log


}


void writeCache(){
    if(blockCounter > BLOCK_COUNT) return;
    uint32_t tw = micros();  // start block write timer
    board.csLow(SD_SS);  // take spi
    if(!card.writeData(pCache)) {
      if (!board.streaming) {
        Serial0.println("block write fail");
        board.sendEOT();
      }
    }   // write the block
    board.csHigh(SD_SS);  // release spi
    tw = micros() - tw;      // stop block write timer
    if (tw > maxWriteTime) maxWriteTime = tw;  // check for max write time
    if (tw < minWriteTime) minWriteTime = tw;  // check for min write time
    if (tw > MICROS_PER_BLOCK) {      // check for overrun
      if (overruns < OVER_DIM) {
        over[overruns].block = blockCounter;
        over[overruns].micro = tw;
      }
      overruns++;
    }
    byteCounter = 0; // reset 512 byte counter for next block
    blockCounter++;    // increment BLOCK counter
    if(blockCounter == BLOCK_COUNT-1){
      t = millis() - t;
      board.streamStop();
    //   stopRunning();
//      board.disable_accel();
      writeFooter();
    }
    if(blockCounter == BLOCK_COUNT){
      closeSDfile();
      BLOCK_COUNT = 0;
    }  // we did it!
}


void incrementFileCounter(){
  fileTens = EEPROM.read(0);
  fileOnes = EEPROM.read(1);
  // if it's the first time writing to EEPROM, seed the file number to '00'
  if(fileTens == 0xFF | fileOnes == 0xFF){
    fileTens = fileOnes = '0';
  }
  fileOnes++;   // increment the file name
  if (fileOnes == ':'){fileOnes = 'A';}
  if (fileOnes > 'F'){
    fileOnes = '0';         // hexify
    fileTens++;
    if(fileTens == ':'){fileTens = 'A';}
    if(fileTens > 'F'){fileTens = '0';fileOnes = '1';}
  }
  EEPROM.write(0,fileTens);     // store current file number in eeprom
  EEPROM.write(1,fileOnes);
  currentFileName[5] = fileTens;
  currentFileName[6] = fileOnes;
//  // send corresponding file name to controlling program
//  Serial0.print("Corresponding SD file ");Serial0.println(currentFileName);
}

void stampSD(boolean state){
  unsigned long time = millis();
  if(state){
    for(int i=0; i<10; i++){
      pCache[byteCounter] = pgm_read_byte_near(startStamp+i);
      byteCounter++;
      if(byteCounter == 512){
        writeCache();
      }
    }
  }
  else{
    for(int i=0; i<9; i++){
      pCache[byteCounter] = pgm_read_byte_near(stopStamp+i);
      byteCounter++;
      if(byteCounter == 512){
        writeCache();
      }
    }
  }
  convertToHex(time, 7, false);
}

void writeFooter(){
  for(int i=0; i<17; i++){
    pCache[byteCounter] = pgm_read_byte_near(elapsedTime+i);
    byteCounter++;
  }
  convertToHex(t, 7, false);

  for(int i=0; i<20; i++){
    pCache[byteCounter] = pgm_read_byte_near(minTime+i);
    byteCounter++;
  }
  convertToHex(minWriteTime, 7, false);

  for(int i=0; i<20; i++){
    pCache[byteCounter] = pgm_read_byte_near(maxTime+i);
    byteCounter++;
  }
  convertToHex(maxWriteTime, 7, false);

  for(int i=0; i<7; i++){
    pCache[byteCounter] = pgm_read_byte_near(overNum+i);
    byteCounter++;
  }
  convertToHex(overruns, 7, false);
  for(int i=0; i<11; i++){
    pCache[byteCounter] = pgm_read_byte_near(blockTime+i);
    byteCounter++;
  }
  if (overruns) {
    uint8_t n = overruns > OVER_DIM ? OVER_DIM : overruns;
    for (uint8_t i = 0; i < n; i++) {
      convertToHex(over[i].block, 7, true);
      convertToHex(over[i].micro, 7, false);
    }
  }
  for(int i=byteCounter; i<512; i++){
    pCache[i] = NULL;
  }
  writeCache();
}

//    CONVERT RAW BYTE DATA TO HEX FOR SD STORAGE
void convertToHex(long rawData, int numNibbles, boolean useComma){

  for (int currentNibble = numNibbles; currentNibble >= 0; currentNibble--){
    byte nibble = (rawData >> currentNibble*4) & 0x0F;
    if (nibble > 9){
      nibble += 55;  // convert to ASCII A-F
    }
    else{
      nibble += 48;  // convert to ASCII 0-9
    }
    pCache[byteCounter] = nibble;
    byteCounter++;
    if(byteCounter == 512){
      writeCache();
    }
  }
  if(useComma == true){
    pCache[byteCounter] = ',';
  }else{
    pCache[byteCounter] = '\n';
  }
  byteCounter++;
  if(byteCounter == 512){
    writeCache();
  }
}// end of byteToHex converter
