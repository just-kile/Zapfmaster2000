/*
 * InputService.cpp
 *
 *  Created on: Jan 27, 2013
 *      Author: thomas
 */

// TODO: This implementation is crap. Needs to be fixed!
#include <vector>
#include <string>
#include <fstream>
#include <iostream>
#include <wiringSerial.h>
#include <boost/thread.hpp>
#include <log4cpp/Category.hh>

#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <iomanip>
#include <wiringPi.h>
#include <wiringPiI2C.h>

#include "../../include/serial/InputService.hpp"

using namespace zm2k;
using namespace std;

AbstractInputService* singleton;

int interface = 0;

// avr overflows after that tick amount
const int ticksMax = 0xFF;

const char req_tick_low = 0x01;
const char req_tick_high = 0x02;
const char req_tick_check = 0x03;

const char res_tick_low = 0x40;
const char res_tick_high = 0x80;

int readTicks() {
	return wiringPiI2CRead(interface);
}

int processZapfcounterInput() {
	log4cpp::Category& logger = log4cpp::Category::getInstance(
			std::string("I2C Input"));

	interface = wiringPiI2CSetup(0x42);

	if (interface != -1) {
		logger.info("I2C port open");
	} else {
		logger.fatal("Could not open I2C port");
		throw "Could not open I2C port";
	}

	int ticks = 0;
	int lastTicks = -1;

	const int interval = 250;
	const int numTicksUntilUpdate = 4;

	lastTicks = readTicks();

	while (true) {

		int totalDelta = 0;

		for (int i = 0; i < numTicksUntilUpdate; ++i) {
			delay(interval);
			ticks = readTicks();

			logger.debug("read ticks: %d ", totalDelta);

			int tickDelta = ticks - lastTicks;
			if (tickDelta < 0) {
				// ticks did overflow
				tickDelta = ticks + (ticksMax - lastTicks);
			}
			lastTicks = ticks;
			totalDelta += tickDelta;
		}

		logger.debug("sending tick update: %d ", totalDelta);
		singleton->notifyZapfcount(totalDelta);
	}

	return 0;
}

InputService::InputService() :
		logger(log4cpp::Category::getInstance(std::string("InputService"))) {
	singleton = this;
	logger.info("creating input service...");

	serialInterface = serialOpen("/dev/ttyAMA0", 9600);

	if (serialInterface == -1) {
		logger.fatal("Could not open serial port");
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

void AbstractInputService::notifyZapfcount(int ticks) {
	if (ticks > 0) {
		notifyListeners(
				boost::bind(&InputServiceListener::onTicksRead, _1, ticks));
	}
}

void AbstractInputService::notifyRfid(long rfid) {
	if (rfid != -1) {
		notifyListeners(
				boost::bind(&InputServiceListener::onRfidRead, _1, rfid));
	}
}

const int rfidPoll = 200;
const int tickPoll = 500;

int rfid = -1;
long ticks = -1;

void sendMockTicks() {
	while (true) {
		delay(tickPoll);
		singleton->notifyZapfcount(ticks);
	}
}

void sendMockRfid() {
	while (true) {
		delay(rfidPoll);
		singleton->notifyRfid(rfid);
	}
}

MockInputService::MockInputService() {
	singleton = this;

}

void MockInputService::run() {
	boost::thread t1(sendMockTicks);
	boost::thread t2(sendMockRfid);

	const string cmdRfidEnd = "end rfid";
	const string cmdTicksEnd = "end ticks";
	const string cmdRfid = "rfid ";
	const string cmdTicks = "ticks ";

	while (true) {
		string command;
		getline(cin, command);

		if (command.compare(0, cmdRfidEnd.size(), cmdRfidEnd) == 0) {
			rfid = -1;
		}

		if (command.compare(0, cmdTicksEnd.size(), cmdTicksEnd) == 0) {
			ticks = -1;
		}

		if (command.compare(0, cmdTicks.size(), cmdTicks) == 0) {
			string rawTicks = command.substr(cmdTicks.size(),
					command.size() - cmdTicks.size());
			ticks = atoi(rawTicks.c_str());
		}

		if (command.compare(0, cmdRfid.size(), cmdRfid) == 0) {
			string rawRfid = command.substr(cmdRfid.size(),
					command.size() - cmdRfid.size());
			rfid = atol(rawRfid.c_str());
		}
	}
}
