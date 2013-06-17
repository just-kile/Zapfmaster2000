//
// ZapfCounter
// AVR Component of Zapfmaster2000 ZapfKit
// using ATtiny85
//
// Interconnection between the Swissflow 800 Flow-Meter and the Raspberry Pi
// Counts the ticks from the Flow-Meter and sends them to the Raspberry Pi
// via I2C. This component is the I2C slave and sends the requested data to the 
// master .
//
// !!! Attention !!!
// The low fuse byte of the ATtiny85 needs to be set to 0xE2 (default is 0x62)
// 
// I2C Protocol:
// x : Payload
// o : message type indicator bits
//
// Reception
// Master -> Slave 
// Bits of every byte:
// xxxx xxoo
//
// Transmission
// Slave -> Master 
// ooxx xxxx
//
// The position of the message type indication is different in receiving direction
// than in transmitting direction.
//
// The two least significant bits of every transmitted byte are used to 
// indicate the message type. The remaining six carry the payload.
// The ticks of the Flow-Meter are stored within an integer (2 bytes) but only
// the three lower nibbles will be send to the I2C master which makes it a 12 bit 
// integer.
// 
// Author: Daniel Wittekind
// C 2013
// 
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.
//
// TinyWireS
// This software uses the TinyWireS implementation by rambo, which has added support
// for onRequest and onReceive
// https://github.com/rambo/TinyWire
//

#include <avr/sleep.h>
#include "TinyWireS.h"

// clear interrupt macro
#ifndef cbi
#define cbi(sfr, bit) (_SFR_BYTE(sfr) &= ~_BV(bit))
#endif
// enable interrupt macro
#ifndef sbi
#define sbi(sfr, bit) (_SFR_BYTE(sfr) |= _BV(bit))
#endif

// I2C address of this I2C slave
int slaveAddr = 0x42;

// indicator LED pin
int pinLed = 1;
// Flow-Meter Tick pin / interrupt
int pinInt = 4;

// ticks counted, rolls over
int ticks = 0;

// ticks counted within interval
int internalTicks = 0;

// interval for which ticks are accumulated, ms
int interval = 500;

// time keeping variables
long previousMillis = 0;
long currentMillis = 0;

// next byte to send via I2C
byte outByte;
// last byte read on I2C
byte inByte;

// protocol codes
// requests from master
// request lower 6 bits of tick count
byte reqTickLow = 0x02;
// request higher 6 bits of tick count
byte reqTickHigh = 0x03;

// response from this slave
// response with lower 6 bits of tick count
byte resTickLow = 0x40;
// response with higher 6 bits of tick count
byte resTickHigh = 0x80;

// called when the I2C master reads the bus
void requestEvent() {
  cbi(GIMSK,PCIE); // Turn off external interrupt
  TinyWireS.send(outByte); // send transmission byte
  sbi(GIMSK,PCIE); // Turn on external interrupt
}

// called when the I2C master writes to the bus
void receiveEvent(uint8_t amount) {
  cbi(GIMSK,PCIE); // Turn off external interrupt
  if (TinyWireS.available())
    inByte = TinyWireS.receive(); // read received byte
  if (inByte == reqTickLow) // master asks for low part of ticks
    outByte = resTickLow | (ticks & 0x003F); // put low part of ticks in transmission byte
  else if (inByte == reqTickHigh) { // master asks for high part of tick
    outByte = resTickHigh | (ticks & 0x0FC0) >> 6; // put high part of ticks in transmission byte
  }
  sbi(GIMSK,PCIE); // Turn on external interrupt
}

// called once at ATtiny boot time
void setup() {
  pinMode(pinLed,OUTPUT); // led pin in output
  pinMode(pinInt,INPUT); // Flow-Meter is input
  
  analogWrite(pinLed,0); // turn of led
  
  TinyWireS.begin(slaveAddr); // initialize I2C communication
  
  TinyWireS.onReceive(receiveEvent); // register function for when the master writes
  TinyWireS.onRequest(requestEvent); // register function for when the master reads
  
  sbi(GIMSK,PCIE);  // Turn on external interrupt
  sbi(PCMSK,PCINT4);  // Enable interrupt on falling edge on pcint4
  // Turn of interrupt on irrelevant pins
  cbi(PCMSK,PCINT2);  
  cbi(PCMSK,PCINT1);
  cbi(PCMSK,PCINT0);
  
  previousMillis = millis(); // save current time
}

// runs endlessly
void loop(){
  currentMillis = millis(); // save current time
  if ( (currentMillis - previousMillis) > interval ) { // has interval passed?
    cbi(GIMSK,PCIE); // Turn off external interrupt
    // set led brightness according to ticks
    if (internalTicks > 0) {
      if (internalTicks < 100)
        analogWrite(pinLed, 50);
      else if (internalTicks < 200)
        analogWrite(pinLed, 100);
      else if (internalTicks < 400)
        analogWrite(pinLed, 150);
      else if (internalTicks < 600)
        analogWrite(pinLed, 210);
      else if (internalTicks >= 800)
        analogWrite(pinLed, 255);
      internalTicks = 0; // reset ticks after interval
    }
    else {
      analogWrite(pinLed, 0); // turn led off, if no ticks were registered
    }
    sbi(GIMSK,PCIE); // Turn on external interrupt
    previousMillis = currentMillis; // set time to last recorded time
  }
}

// called if interrupt is triggered
ISR(PCINT0_vect){
  ticks++; // increase ticks
  internalTicks++; // increase internal ticks
}
