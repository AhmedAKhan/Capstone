/*
   insert header here

*/
#ifndef _____XTREMIS__
#define _____XTREMIS__


#include <DSPI.h>
#include <Arduino.h>
#include "XTREMIS_Library_Definitions.h"

void __USER_ISR ADS_DRDY_Service(void);
class XTREMIS_Library {

public:

    // Start up functions
    XTREMIS_Library();
    boolean accelHasNewData(void);
    void    accelUpdateAxisData(void);
    void    accelWriteAxisData(void);
    void    begin(void);
    void    beginDebug(void);
    boolean beginSecondarySerial(void);
    char    getCharSerial0(void);
    char    getCharSerial1(void);
    boolean hasDataSerial0(void);
    boolean hasDataSerial1(void);


    void    writeSerial(char *data, int len);

    boolean isADSDataAvailable(void);



    void writeTimeCurrent(void);
    void writeZeroAux(void);
    void activateAllChannelsToTestCondition(byte testInputCode, byte amplitudeCode, byte freqCode);

    void channelSettingsArraySetForAll(void);
    void channelSettingsArraySetForChannel(byte N);
    void channelSettingsSetForChannel(byte channelNumber, byte powerDown, byte gain, byte inputType, byte bias, byte srb2, byte srb1);

    void leadOffConfigureSignalForAll(byte amplitudeCode, byte freqCode);
    void leadOffConfigureSignalForTargetSS(byte targetSS, byte amplitudeCode, byte freqCode);

    void leadOffSetForAllChannels(void);
    void leadOffSetForChannel(byte channelNumber, byte pInput, byte nInput);

    boolean processChar(char character);
    void processIncomingBoardMode(char character);
    void processIncomingChannelSettings(char character);
    void processIncomingLeadOffSettings(char character);

    void resetChannelSettingsArrayToDefault(byte channelSettingsArray[][XTREMIS_NUMBER_OF_CHANNEL_SETTINGS]);
    void resetLeadOffArrayToDefault(byte leadOffArray[][XTREMIS_NUMBER_OF_LEAD_OFF_SETTINGS]);

    void sendChannelDataWithAccel(void);
    void sendChannelDataWithRawAux(void);
    void sendChannelDataWithTimeAndAccel(void);
    void sendChannelDataWithTimeAndRawAux(void);

    void streamSafeChannelDeactivate(byte channelNumber);
    void streamSafeChannelActivate(byte channelNumber);
    void streamSafeChannelSettingsForChannel(byte channelNumber, byte powerDown, byte gain, byte inputType, byte bias, byte srb2, byte srb1);
    void streamSafeSetAllChannelsToDefault(void);
    void streamSafeReportAllChannelDefaults(void);
    void streamSafeLeadOffSetForChannel(byte channelNumber, byte pInput, byte nInput);
    void streamSafeTimeSendSyncSetPacket(void);
    void streamStart(void);
    void streamStop(void);

    // OLD CODE REVERT
    void writeChannelSettings();
    void writeChannelSettings(byte N);
    void changeChannelLeadOffDetect();
    void changeChannelLeadOffDetect(byte N);
    void configureLeadOffDetection(byte amplitudeCode, byte freqCode);

    boolean waitForNewChannelData(void);

    // Variables
    boolean daisy;
    boolean sniffMode;
    boolean streaming;
    boolean timeSynced;
    boolean sendTimeSyncUpPacket;
    boolean isProcessingIncomingSettingsChannel;
    boolean isProcessingIncomingSettingsLeadOff;
    boolean settingBoardMode;
    volatile boolean channelDataAvailable;

    boolean isProcessingMultibyteMsg(void);
    boolean isValidBoardType(char c);

    uint8_t curBoardMode;

    int numberOfIncomingSettingsProcessedChannel;
    int numberOfIncomingSettingsProcessedLeadOff;
    char streamPacketType;
    char currentChannelSetting;

    // Getters
    char getChannelCommandForAsciiChar(char asciiChar);
    char getConstrainedChannelNumber(byte channelNumber);
    byte getDefaultChannelSettingForSetting(byte setting);
    char getDefaultChannelSettingForSettingAscii(byte setting);
    char getGainForAsciiChar(char asciiChar);
    char getNumberForAsciiChar(char asciiChar);
    char getTargetSSForConstrainedChannelNumber(byte channelNumber);
    char getYesOrNoForAsciiChar(char asciiChar);

    DSPI0 spi;  // use DSPI library

// BOARD
    boolean useAccel;
    boolean useAux;
    void initialize(void);
    void initializeVariables(void);
    void printAllRegisters(void);
    void sendChannelData(void); // send the current data with sample number


// ADS1299
    void initialize_ads(void);
    void setChannelsToDefault(void);
    void reportDefaultChannelSettings(void);
    void printADSregisters(int);
    void WAKEUP(int);  // get out of low power mode
    void STANDBY(int); // go into low power mode
    void RESET(int);   // set all register values to default
    void START(int);   // start data acquisition
    void STOP(int);    // stop data acquisition
    void RDATAC(int);  // go into read data continuous mode
    void SDATAC(int);  // get out of read data continuous mode
    void RDATA(int);   // read data one-shot
    byte RREG(byte,int);            // read one ADS register
    void RREGS(byte,byte,int);      // read multiple ADS registers
    void WREG(byte,byte,int);       // write one ADS register
    void WREGS(byte,byte,int);      // write multiple ADS registers
    byte ADS_getDeviceID(int);
    void printRegisterName(byte);   // used for verbosity
    void printHex(byte);            // used for verbosity
    void updateChannelData(void);   // retrieve data from ADS
    void updateBoardData(void);
    void updateDaisyData(void);
    byte xfer(byte);        // SPI Transfer function
    void resetADS(int);     // reset all the ADS1299's settings
    void startADS(void);
    void stopADS(void);
    void activateChannel(byte);                  // enable the selected channel
    void deactivateChannel(byte);                // disable given channel 1-8(16)
    void configureInternalTestSignal(byte,byte);

    void changeInputType(byte);

    void ADS_writeChannelData(void);
    // void ADS_printDeviceID(int);   //
    boolean smellDaisy(void);
    void removeDaisy(void);
    void attachDaisy(void);
    void writeAuxData(void);

    short auxData[3];           // This is user faceing
    byte regData[24];           // array is used to mirror register data
    byte lastBoardDataRaw[24];
    byte boardChannelDataRaw[24];    // array to hold raw channel data
    byte meanBoardDataRaw[24];
    byte lastDaisyDataRaw[24];
    byte daisyChannelDataRaw[24];
    byte meanDaisyDataRaw[24];
    int boardStat;           // used to hold the status register
    int daisyStat;
    int boardChannelDataInt[8];    // array used when reading channel data as ints
    int lastBoardChannelDataInt[8];
    int meanBoardChannelDataInt[8];
    int daisyChannelDataInt[8];    // array used when reading channel data as ints
    int lastDaisyChannelDataInt[8];
    int meanDaisyChannelDataInt[8];
    int numChannels;
    byte channelSettings[XTREMIS_NUMBER_OF_CHANNELS_DAISY][XTREMIS_NUMBER_OF_CHANNEL_SETTINGS];  // array to hold current channel settings
    byte defaultChannelSettings[XTREMIS_NUMBER_OF_CHANNEL_SETTINGS];  // default channel settings
    byte leadOffSettings[XTREMIS_NUMBER_OF_CHANNELS_DAISY][XTREMIS_NUMBER_OF_LEAD_OFF_SETTINGS];  // used to control on/off of impedance measure for P and N side of each channel
    boolean useInBias[XTREMIS_NUMBER_OF_CHANNELS_DAISY];        // used to remember if we were included in Bias before channel power down
    boolean useSRB2[XTREMIS_NUMBER_OF_CHANNELS_DAISY];
    boolean boardUseSRB1;             // used to keep track of if we are using SRB1
    boolean daisyUseSRB1;
    boolean verbosity;      // turn on/off Serial verbosity
    boolean daisyPresent;
    boolean firstDataPacket;
    int sampleCounter;

// LSM6DS0
    int axisData[3];
    void initialize_accel(void);    // initialize
    void enable_accel(void);  // start acceleromoeter with default settings
    void disable_accel(void); // stop data acquisition and go into low power mode
    byte LSM6DS0_getDeviceID(void);
    byte LSM6DS0_read(byte);     // read a register on LSM6DS0
    void LSM6DS0_write(byte,byte);   // write a register on LSM6DS0
    int LSM6DS0_read16(byte);    // read two bytes, used to get axis data
    int getX(void);
    int getY(void);
    int getZ(void);
    boolean LSM6DS0_DataReady(void); // check LSM6DS0_DRDY pin
    boolean LSM6DS0_DataAvailable(void); // check LSM6DS0 STATUS_REG2
    void LSM6DS0_readAllRegs(void);
    void LSM6DS0_writeAxisData(void);
    void LSM6DS0_writeAxisDataForAxis(uint8_t axis);
    void LSM6DS0_updateAxisData(void);
    float LSM6DS0_getAccScale(void);

    void csLow(int);
    void csHigh(int);

    //
    boolean boardBegin(void);
    boolean boardBeginDebug(void);
    boolean boardBeginDebug(int);
    void powerUpSequence(void);
    void boardReset(void);
    void ledFlash(int numberOfFlashes);
    void sendEOT(void);

    char buffer[1];

// ADS1299
    boolean isRunning;
// LSM6DS0
    int DRDYpinValue;
    int lastDRDYpinValue;

    // Time sync Variables
    unsigned long timeOffset;
    unsigned long timeSetCharArrived;
    unsigned long timeComputer;
    unsigned long timeCurrent;
    // Time sync Methods
    // unsigned long   timeGet(void);
    // void            timeSet(char character);
    void            timeSendSyncSetPacket(void);
    
// Triggers
    //                    D13, PGC  ,BOOT_EN, D18, PGD}
    byte trigger_pins[NUM_TRIG_PINS] = {13, 12   ,17     ,  18, 11};
    byte last_trigger[NUM_TRIG_PINS];
    boolean keyPressed = false;
    boolean printed = false;
    void setupTriggerPins();
    boolean triggerReset();
    boolean triggerChanged();
    void readTriggerPins();
    String printLastTrigger();
    String printTriggerPins();
    void resetTriggers();
    String checkTrigger();
    short triggerToAux(String);
};

// This let's us call into the class from within the library if necessary
extern XTREMIS_Library board;

#endif