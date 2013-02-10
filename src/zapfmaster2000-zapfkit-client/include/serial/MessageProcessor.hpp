/*
 * MessageProcessor.hpp
 *
 *  Created on: Jan 27, 2013
 *      Author: thomas
 */

#ifndef MESSAGEPROCESSOR_HPP_
#define MESSAGEPROCESSOR_HPP_

#include <string>
#include "InputService.hpp"

namespace zm2k {

enum ProcessStatus {
	PARTLY, COMPLETELY, NOT_AT_ALL
};

class MessageProcessor {

public:

	virtual ~MessageProcessor() {
	}

	ProcessStatus canProcess(std::string rawInput);

	std::pair<InputService::notification, int> process(std::string rawInput);

protected:

	virtual int getMinLength() = 0;
	virtual int getMaxLength() = 0;
	virtual char getStartChar() = 0;
	virtual InputService::notification doProcess(std::string contents) = 0;

private:
	std::string findContents(char start, char end, int minLength, int maxLength,
			std::string rawInput);
};

class RfidMessageProcessor: public MessageProcessor {

protected:
	virtual int getMinLength() {
		return 5;
	}

	virtual int getMaxLength() {
		return 10;
	}

	virtual char getStartChar() {
		return 'R';
	}

	virtual InputService::notification doProcess(std::string contents) {

		return boost::bind(&InputServiceListener::onRfidRead, _1, contents);
	}

};

#include <iostream>

class TickMessageProcessor: public MessageProcessor {

protected:
	virtual int getMinLength() {
		return 1;
	}
	virtual int getMaxLength() {
		return 5;
	}

	virtual char getStartChar() {
		return 'T';
	}

	virtual InputService::notification doProcess(std::string contents) {
		return boost::bind(&InputServiceListener::onTicksRead, _1,
				(int) atol(contents.c_str()));
	}

};

}

#endif /* MESSAGEPROCESSOR_HPP_ */
