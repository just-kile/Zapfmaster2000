/*
 * InputService.cpp
 *
 *  Created on: Jan 27, 2013
 *      Author: thomas
 */

#include <vector>
#include <string>
#include <fstream>

#include "../../include/serial/InputService.hpp"
#include "../../include/serial/MessageProcessor.hpp"

using namespace zm2k;
using namespace std;

SerialInputService::SerialInputService(SerialConnector& serialConnector) {
	processors.push_back(new RfidMessageProcessor());
	processors.push_back(new TickMessageProcessor());
	serialConnector.addListener(this);
}

SerialInputService::~SerialInputService() {
}

void SerialInputService::onCharRead(const char c) {

	buffer += c;

	bool didProcessMsg;
	bool needsToDropChar = true;

	while (needsToDropChar && buffer.size() > 0) {

		do {
			didProcessMsg = false;
			for (vector<MessageProcessor*>::iterator it = processors.begin();
					it != processors.end(); ++it) {
				ProcessStatus status = (*it)->canProcess(buffer);
				if (status == COMPLETELY) {
					std::pair<InputService::notification, int> result =
							(*it)->process(buffer);
					notifyListeners(result.first);
					buffer = buffer.substr(result.second,
							buffer.size() - result.second);
					didProcessMsg = true;
				} else if (status == PARTLY) {
					needsToDropChar = false;
				}
			}
		} while (didProcessMsg);

		if (needsToDropChar && buffer.size() > 0) {
			buffer = buffer.substr(1, buffer.size() - 1);
		}

	}
}

MockInputService::MockInputService() {
	cout << "did create mock" << endl;
}

void MockInputService::run() {

	cout << "running mock thread" << endl;

	string cmdTicks = "ticks";
	string cmdLogin = "login";

	while (true) {
		cout << "LOOP" << endl;
		string rawInput;
		getline(cin, rawInput);

		cout << "read input " << rawInput << endl;

		// ticks
		if (rawInput.compare(0, cmdTicks.length(), cmdTicks) == 0
				&& rawInput.length() > cmdTicks.length()) {
			int offset = cmdTicks.length() + 1;
			int ticks =
					atoi(
							rawInput.substr(offset, rawInput.length() - offset).c_str());
			notifyListeners(
					boost::bind(&InputServiceListener::onTicksRead, _1, ticks));
		}

		// login
		if (rawInput.compare(0, cmdLogin.length(), cmdLogin) == 0
				&& rawInput.length() > cmdLogin.length()) {
			int offset = cmdLogin.length() + 1;
			long rfid =
					atol(
							rawInput.substr(offset, rawInput.length() - offset).c_str());
			notifyListeners(
					boost::bind(&InputServiceListener::onRfidRead, _1, rfid));

		}
	}
}
