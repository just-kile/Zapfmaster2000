//
// AVR Component of Zapfmaster2000 DraftKit
// 
// Based on Arduino 1.0.1 and Sanguino 0101r1
// http://arduino.cc
// http://sanguino.cc
// Written for AtMega 644PA
//
// 
// 
// version: 0.1
// date: 10.12.2012
// author: Daniel Wittekind
//
// adopted from arduino code by Thomas Kipar
//
// Purpose:
// Provide hardware interface for the TowiTek RFID Reader,
// the Swissflow SF-800 Flowmeter and a couple status LEDs.
// The RFID Reader is attached to the input of the second UART of the AtMega 644PA and provides the serial number of the RFID tag, as long as it can read one.
// The Flowmeter is attached to interrupt 2. It pulls its line up and down according to the frequency, at which its propeller spins due to liqiud flowing through it.
// The ticks generated are counted via the interrupt for a second.
// Attached to the first UART is a computer to which the RFID and Flowmeter data is send. In return, it can send messages controlling the status LEDs.
// 

#include <PinChangeInt.h>

// interrupts
const int intFlowmeter = 15;

// PINS
// BAUDRATES
const unsigned long brPC = 57600;
const unsigned long brRFID = 9600;

// MESSAGE CODES
// marks the end of a message in either direction
const char endMessage = '\n';

// TX
// message contains the ticks of the last second
const char ticksMessage = 'T';
// message contains the current rfid tag serial
const char rfidMessage = 'R';

// RX
// message contains login status
const char loginMessage = 'L';
// message contains pouring speed
const char speedMessage = 'S';

// successful login
const byte statusOk = 1;
// unsuccessful login
const byte statusError = 2;
// no current login
const byte statusNone = 3;

// GLOBALS

// initial ticks on flow meter
unsigned int numTicks = 0;
// interval for flow meter frequency
int interval = 1000;
// 
long previousMillis = 0;
// length of RFID Reader serial messages
int rfidLength = 5;

// pin of green led
int ledGreen = 16;

// setup pins and ports
// executed once after startup
void setup() {
  // initialize UART to computer
  Serial.begin(brPC);
  // initialize UART to RFID Reader
  Serial1.begin(brRFID);
  
  // attach interrupt to flow meter digital pin
  // executes count() each time it's triggered
  //attachInterrupt(intFlowmeter, count, FALLING);
  pinMode(intFlowmeter, INPUT);
  digitalWrite(intFlowmeter, HIGH);
  PCintPort::attachInterrupt(intFlowmeter, count, RISING);
  
  // green led
  pinMode(16, OUTPUT);
}

// main loop
void loop() {
 
  // read rfid tag if available and send it to serial (pc)
  // check, whether data is available on Serial1, to which
  // the RFID Reader is attached
  if (Serial1.available() > 4) {
    // send opening byte to computer, indicating rfid tag serial message
    Serial.write(rfidMessage);
    // read five bytes from RFID serial port
    for (int i = 0; i < rfidLength; ++i) {
      // read on byte
      int byte = Serial1.read();
      // send byte to computer
      Serial.write(byte);    
    } 
    // close message to computer with end symbol
    Serial.write(endMessage);
  }
  
  // send draw-count if a interval passed
  // get current time since startup
  unsigned long currentMillis = millis(); 
  // if the predefined interval has passed
  if(currentMillis - previousMillis > interval) {
    // disconnect flow meter from interrupt
    // detachInterrupt(intFlowmeter);   
    PCintPort::detachInterrupt(intFlowmeter);   
    // check, whether any flow was detected
    if (numTicks > 0) {
      // start message to computer with tick message symbol
      Serial.print(ticksMessage);
      // send amount of ticks for last interval
      Serial.print(numTicks);
      // send closing symbol
      Serial.print(endMessage);
    }
    // reset amount of ticks
    numTicks = 0;
    // set new start value for interval counter
    previousMillis = currentMillis;   
    // reattach interrupt
    // attachInterrupt(intFlowmeter, count, FALLING);
    PCintPort::attachInterrupt(intFlowmeter, count, RISING);
  }    
  
  if (Serial.available() > 0) {
    digitalWrite(ledGreen, HIGH);
    Serial.read();
    delay(1000);
    digitalWrite(ledGreen, LOW);
  }
}

// flow meter interrupt function
void count() {
  numTicks++;
  digitalWrite(16, HIGH);
      delay(200);
    digitalWrite(16, LOW);
    delay(200);
}
