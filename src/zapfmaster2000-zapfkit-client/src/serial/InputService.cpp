/*
 * InputService.cpp
 *
 *  Created on: Jan 27, 2013
 *      Author: thomas
 */

#include <vector>
#include <string>
#include <fstream>
#include <iostream>
#include <wiringSerial.h>
#include <boost/thread.hpp>

#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <iomanip>
#include <wiringPi.h>
#include <wiringPiI2C.h>

#include "../../include/serial/InputService.hpp"

using namespace zm2k;
using namespace std;

InputService* singleton;


int interface = 0;

char req_tick_low = 0x02;
char req_tick_high = 0x03;

char res_tick_low = 0x40;
char res_tick_high = 0x80;

int readTicks() {
	int result = -1;

	char res_1 = 0x00;
	char res_2 = 0x00;

	cout << "write request low" << endl;
	delay(1);
	wiringPiI2CWrite(interface, req_tick_low);
	cout << "read response 1" << endl;
	delay(1);
	res_1 = wiringPiI2CRead(interface);
	std::cout << "response 1 is " << std::hex << (int) res_1 << std::dec
			<< std::endl;
	cout << "write request high" << endl;
	delay(1);
	wiringPiI2CWrite(interface, req_tick_high);
	cout << "read response 2" << endl;
	delay(1);
	res_2 = wiringPiI2CRead(interface);
	std::cout << "response 2 is " << std::hex << (int) res_2 << std::dec
			<< std::endl;
	if ((res_1 & 0xC0) == res_tick_low) {
		cout << "response 1 is low tick" << endl;
		if ((res_2 & 0xC0) == res_tick_high) {
			cout << "response 2 is high tick" << endl;
			result = (res_2 & 0x3F) << 6 | (res_1 & 0x3F);
		} else
			result = -1;
	} else
		result = -1;

	return result;
}

int processZapfcounterInput() {
	cout << "tach" << endl;

	interface = wiringPiI2CSetup(0x42);

	if (interface != -1)
		cout << "port open" << endl;
	else
		cout << "error" << interface << endl;

	int ticks = 0;
	int attempt_no = 0;

	while (1 == 1) {
		cout << "----------------------------" << endl;
		do {
			delay(2);
			cout << "tick read attempt no " << attempt_no << endl;
			ticks = readTicks();
			attempt_no++;
		} while (ticks == -1);
		attempt_no = 0;
		cout << "ticks: " << ticks << endl;
		singleton->notifyZapfcount(ticks);
		delay(490);
	}

	return 0;
}

InputService::InputService() {
	singleton = this;
	cout << "creating input service..." << endl;

	serialInterface = serialOpen("/dev/ttyAMA0", 9600);

	if (serialInterface == -1) {
		throw "Could not open serial port.";
	}
	curRfid = 0;
	serialByteCounter = 0;

}

void InputService::run() {
	boost::thread t1(processZapfcounterInput);

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

void InputService::notifyZapfcount(int ticks) {
	if (ticks > 0) {
		notifyListeners(
				boost::bind(&InputServiceListener::onTicksRead, _1, ticks));
	}
}

MockInputService::MockInputService() {
	throw "not implemented yet";
}

void MockInputService::run() {

}
