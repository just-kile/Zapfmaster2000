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

// PINS
// interrupt pin for flow meter
const int intFlowmeter = 2;

// RGB LED Pins
const int pinR = 12;
const int pinG = 13;
const int pinB = 14;

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
// message signals current login status
const char loginMessage = 'L';

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

// keeps track of the last login status
int loginStatus = statusNone;

// pin of green led
int ledGreen = 16;

// holds the last rgb values
int valR = 0;
int valG = 0;
int valB = 0;

// setup pins and ports
// executed once after startup
void setup() {
  // initialize UART to computer
  Serial.begin(brPC);
  // initialize UART to RFID Reader
  Serial1.begin(brRFID);
  
  // attach interrupt to flow meter digital pin
  // executes count() each time it's triggered
  pinMode(intFlowmeter, INPUT);
  digitalWrite(intFlowmeter, HIGH);
  PCintPort::attachInterrupt(intFlowmeter, count, RISING);
  
  // green led
  pinMode(16, OUTPUT);
  
  // set up rgb led ports
  pinMode(pinR, OUTPUT);
  pinMode(pinG, OUTPUT);
  pinMode(pinB, OUTPUT);
  
  // set rgb leds to orange
  turnOrange();
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
    PCintPort::attachInterrupt(intFlowmeter, count, RISING);
  }    
  
  if (Serial.available() > 1) {
    // read first byte of message
    char symbol = (char) Serial.read();
    // interpret first byte
    if (symbol == loginMessage) {
      // read next byte of login message containing login status
      loginStatus = Serial.parseInt();
    } 
  }
  
  showLogin();
}

// flow meter interrupt function
void count() {
  numTicks++;
}

// change rgb colors according to login status
void showLogin() {
  if (loginStatus == statusOk) {
    turnGreen();
  } else if (loginStatus == statusError) {
    turnRed();
  } else if (loginStatus == statusNone) {
    turnOrange();
  }
}
// led color fade functions
// turns rgb leds green
void turnGreen() {
  analogWrite(pinR, 45);
  analogWrite(pinG, 255);
  analogWrite(pinB, 10);
}

// turns rgb leds orange
void turnOrange() {
  analogWrite(pinR, 255);
  analogWrite(pinG, 128);
  analogWrite(pinB, 0);
}

// turns rgb leds red
void turnRed() {
  analogWrite(pinR, 255);
  analogWrite(pinG, 0);
  analogWrite(pinB, 0);
}

// turns rgb leds white
void turnWhite() {
  analogWrite(pinR, 255);
  analogWrite(pinG, 255);
  analogWrite(pinB, 255);
}
