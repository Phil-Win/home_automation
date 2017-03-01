#include<SPI.h>
#include<RF24.h>

// ce, csn pins

RF24 radio(9,10);
const byte interruptPin = 2;
int HeartBeatOn=0;
int MessageOn=0;
const char openMessage[] = "DoorStatus,Open";
const char closedMessage[] = "DoorStatus,Closed";

void setup(void) {
  radio.begin();
  radio.setPALevel(RF24_PA_MIN) ;
  radio.setChannel(0x76);
  radio.openWritingPipe(0xF0F0F0F0E1LL);
  radio.enableDynamicPayloads();
  pinMode(interruptPin, INPUT_PULLUP);
  attachInterrupt(digitalPinToInterrupt(interruptPin), sendMessage, CHANGE);
  
}

void loop(void) {
  radio.powerUp();
  HeartBeatOn=1;
  const char text[] = "DoorArduino,Working";
  radio.write(&text, sizeof(text));
  HeartBeatOn=0;

  if (HeartBeatOn+MessageOn == 0) 
  {
    radio.powerDown();
  }
  delay(900000);
}
 
void sendMessage() {
  radio.powerUp();
  MessageOn=1;
  if (digitalRead(interruptPin) == HIGH) {
    radio.write(&openMessage, sizeof(openMessage));
  } else {
    radio.write(&closedMessage, sizeof(closedMessage));
  }
  MessageOn=0;
  if (HeartBeatOn+MessageOn == 0) 
  {
    radio.powerDown();
  }
}
