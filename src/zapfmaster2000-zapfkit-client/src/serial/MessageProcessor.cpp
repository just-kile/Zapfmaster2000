/*
 * MessageProcessor.cpp
 *
 *  Created on: Jan 27, 2013
 *      Author: thomas
 */

#include "../../include/serial/MessageProcessor.hpp"
#include <algorithm>

using namespace zm2k;
using namespace std;


std::string MessageProcessor::findContents(char start, char end, int minLength,
		int maxLength, std::string rawInput) {

	if (rawInput.size() > 1) {
		if (rawInput[0] == start) {
			for (int i = minLength; i < min(maxLength + 1, (int) rawInput.size());
					++i) {
				if (rawInput[i] == end) {
					return rawInput.substr(1, i - 1);
				}
			}
			throw "End-Tag not found";
		} else {
			throw "Wrong start character";
		}
	} else {
		throw "Input is too short";
	}
}

ProcessStatus MessageProcessor::canProcess(std::string rawInput) {
	if (rawInput.size() > 0) {
		try {
			findContents(getStartChar(), '\n', getMinLength(), getMaxLength(),
					rawInput);
			return COMPLETELY;
		} catch (const char*) {
			// check if NOT_AT_ALL or PARTLY
			if (rawInput.size() > getMaxLength()
					|| rawInput[0] != getStartChar()) {
				return NOT_AT_ALL;
			} else {
				return PARTLY;
			}
		}

	}

	return NOT_AT_ALL;
}

std::pair<InputService::notification, int> MessageProcessor::process(
		std::string rawInput) {

	string contents = findContents(getStartChar(), '\n', getMinLength(),
			getMaxLength(), rawInput);
	InputService::notification notification = doProcess(contents);

	return make_pair(notification, contents.size() + 2); // +2 for start + end cahr
}

