/*
 * InputService.cpp
 *
 *  Created on: Jan 27, 2013
 *      Author: thomas
 */

#include <vector>
#include <string>
#include <fstream>
#include <wiringSerial.h>

#include "../../include/serial/InputService.hpp"

using namespace zm2k;

InputService::InputService() {

	serialInterface = serialOpen("/dev/ttyAMA0", 9600);

	if (serialInterface != -1) {
		throw "Could not open serial port.";
	}
	curRfid = 0;
	serialByteCounter = 0;
}

void InputService::run() {
	while (true) {
		int newChar = serialGetchar(serialInterface);

		if (newChar != -1) {
			curRfid <<= 8;
			curRfid += newChar;

			serialByteCounter++;

			if (serialByteCounter == 5) {

				notifyListeners(
						boost::bind(&InputServiceListener::onRfidRead, _1,
								curRfid));

				serialByteCounter = 0;
				curRfid = 0;
			}
		} else {
			serialByteCounter = 0;
			curRfid = 0;
		}

	}
}


MockInputService::MockInputService() {
	throw "not implemented yet";
}

void MockInputService::run() {

}
