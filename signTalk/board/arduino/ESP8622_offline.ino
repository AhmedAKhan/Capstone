/** Transparent WiFi to UART Server. 
 */

#include <ESP8266WiFi.h>

const char* ssid = "SM-G930W82074";
const char* password = "hhog9680";

// Only set as True when the PIC32MX250F128B firmware is blank, otherwise
// there is a risk of sending accidental commands to the MCU.
#define DEBUG false

// Create an instance of the server
// specify the port to listen on as an argument
WiFiServer server(9999);

/*
 * Function to set up static IP for XTREMIS.
 * Makes things easier when trying to figure out
 * IP address.
 */
void SetUpStaticIP(){
   // Set up a static IP to make life easier
  //IPAddress ip(192, 168, 11, 125);
  IPAddress ip(192,168, 43, 50); // phone ip
  
  // set gateway to match our network.
  // We will need to change this if we use a different router. 
  //IPAddress gateway(192, 168, 11, 1);
  IPAddress gateway(192, 168, 43, 1); // phone 
  if(DEBUG){
    Serial.print("Setting static IP to: ");
    Serial.println(ip);
  }
  // set subnet mask to match the network we're on
  IPAddress subnet(255, 255, 255, 0); 
  WiFi.config(ip, gateway, subnet);

 // WiFi.setOutputPower(0.0);

}

void setup() {
  // put your setup code here, to run once:
  Serial.begin(115200);
  delay(10);
  Serial.print("Setting up.");
  // Let's set up a static IP address to make life easier
  SetUpStaticIP();
  
  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    if(DEBUG){
      Serial.print(".");
    }
  }
  if(DEBUG){
    Serial.println("");
    Serial.println("WiFi connected");
  }

  // Start the server
  server.begin();
  if(DEBUG){
    Serial.println("Server Started.");
    // Print IP Address
    Serial.print("IP Addr: "); Serial.println(WiFi.localIP());
  }
}

void loop() {
  // put your main code here, to run repeatedly:
  WiFiClient client = server.available();
  if(!client){
    return;
  } 
  
  if(DEBUG){
    Serial.println("Client connected!");
  }
  client.println("**ESP8266** Ready.");
  while(client.connected()){
    // Read first line of request
    if(client.available()){
      String req = client.readStringUntil('\r');
      if(req.length() > 0){
        client.print("**ESP8266** Command Received."); 
        client.flush();
        
        // Write command to serial so that PIC32MX250F128B picks it up
  
        Serial.print(req);
        int len = req.length();
        
        if(req[len-1] == 'N'){      // if last letter N (this is for testing only)
          client.print("Powering off WiFi for data collection");
          WiFi.mode(WIFI_OFF);
          // wait for 2 minutes
          delay(120000);   
          WiFi.mode(WIFI_STA);
        }
        
      }
    }

    //client.println("starting to read serial data");
    while (Serial.available() > 0){
      //  Serial.write(Serial.read());
      client.print("serial data: ");
      client.println(Serial.read());
    }
    //client.flush();
  }
}

