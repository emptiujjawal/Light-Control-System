#include <FirebaseArduino.h>
#include <ESP8266WiFi.h>
#include <string>


int leds[2] = {D0, D2};
int states[2] ={HIGH, LOW};
int totaldevice = 2;

#define firebaseurl "light-control-system.firebaseio.com"
#define authCode "iXTx3Jsq0tJrMpMTItTib8FvIqG2fz4jFS7AVSEl"

#define wifiName "empti"
#define wifiPass "qwerty12"
String chipId ="123";

void setupFirebase(){

  Firebase.begin(firebaseurl, authCode);
  
}

void setupWifi(){

  WiFi.begin(wifiName, wifiPass);

  Serial.println("hey i am connecting....");

  while(WiFi.status()!= WL_CONNECTED ){
    Serial.println(".");
    delay(500); 
  }
  Serial.println();
  Serial.println("I am connected and my ip address:");
  Serial.println(WiFi.localIP());
}


void setup() {
  // put your setup code here, to run once:
  Serial.begin(115200);
  setupWifi();
  setupFirebase();
  setupPinMode();
}
void getData(){

  String path = chipId + "/states";
  FirebaseObject object = Firebase.get(path);

  bool led1 = object.getBool("001");
  bool led2 = object.getBool("002");

  Serial.println("led 1:");
  Serial.println(led1);

  //Serial.println();
  //Serial.println("led 2:");
  //Serial.println(led2);

  //Serial.println();

//write output of high and low for led
  digitalWrite(leds[0],led1);
  
  digitalWrite(leds[1], led2);
  
}

void loop() {
  // put your main code here, to run repeatedly:
  getData();

}

void setupPinMode(){
  //setup Pin mode as output.
  for(int k = 0; k< totaldevice; k++){
    //Serial.print("Setup Output for pin %d",leds[k]);
    pinMode(leds[k],OUTPUT);
  }
  /*pinMode(D0, OUTPUT);
  pinMode(D1, OUTPUT);
  pinMode(D2, OUTPUT);
  */
}



