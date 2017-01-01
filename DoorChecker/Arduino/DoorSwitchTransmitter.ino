#include<SPI.h>
#include<RF24.h>

// ce, csn pins

RF24 radio(9,10);
const byte interruptPin = 2;

void setup(void) {
  radio.begin();
  radio.setPALevel(RF24_PA_MAX) ;
  radio.setChannel(0x76);
  radio.openWritingPipe(0xF0F0F0F0E1LL);
  radio.enableDynamicPayloads();
  radio.powerUp();
  pinMode(interruptPin, INPUT_PULLUP);
  attachInterrupt(digitalPinToInterrupt(interruptPin), sendMessage, CHANGE);
  
}

void loop(void) {
  const char text[] = "DoorArduino,Working";
  radio.write(&text, sizeof(text));
  delay(900000);
}
 
void sendMessage() {
  const char openMessage[] = "DoorStatus,Open";
  const char closedMessage[] = "DoorStatus,Closed";
  if (digitalRead(interruptPin) == HIGH) {
    radio.write(&openMessage, sizeof(openMessage));
  } else {
    radio.write(&closedMessage, sizeof(closedMessage));
  }
}
