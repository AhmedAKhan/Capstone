/*
 * Transparent WiFi to UART Server. 
 * WiSeR Lab
 * 
 * Author: Ala Shaabana
 */

#include <ESP8266WiFi.h>

// Shedden Lab
//const char* ssid = "xtrms";
//const char* password = "xtremis1";

const char* ssid = "SM-G930W82074";
const char* password = "hhog9680";


// Only set as True when the PIC32MX250F128B firmware is blank, otherwise
// there is a risk of sending accidental commands to the MCU.
#define DEBUG true 

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
  IPAddress ip(192, 168, 43, 100);
  // set gateway to match our network.
  // We will need to change this if we use a different router. 
  IPAddress gateway(192, 168, 43, 1);
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
  Serial.begin(500000);
  delay(10);
  if (DEBUG) {
    Serial.print("Setting up.");
  }
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
//     Serial.println("Listening");
     while (Serial.available() > 0){
      Serial.print(Serial.read());
    //    client.write(Serial.read());
      }
}

