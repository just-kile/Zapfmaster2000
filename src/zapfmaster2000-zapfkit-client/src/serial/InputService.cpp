/*
 * InputService.cpp
 *
 *  Created on: Jan 27, 2013
 *      Author: thomas
 */

#include <serial/InputService.hpp>
#include <serial/MessageProcessor.hpp>
#include <vector>

using namespace zm2k;
using namespace std;

InputService::InputService(SerialConnector& serialConnector) {
	processors.push_back(new RfidMessageProcessor());
	processors.push_back(new TickMessageProcessor());
	serialConnector.addListener(this);
}

#include <iostream>
void InputService::onCharRead(const char c) {

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
