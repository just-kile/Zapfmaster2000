/*
 * InputService.hpp
 *
 *  Created on: Jan 27, 2013
 *      Author: thomas
 */

#ifndef INPUTSERVICE_HPP_
#define INPUTSERVICE_HPP_

#include <Observable.hpp>
#include <serial/SerialConnector.hpp>
#include <vector>
#include <string>

namespace zm2k {

class MessageProcessor;

class InputServiceListener {

public:

	virtual ~InputServiceListener() {
	}

	virtual void onRfidRead(long rfid) = 0;
	virtual void onTicksRead(int ticks) = 0;

};

class InputService: public Observable<InputServiceListener>,
		public SerialConnectorListener {

private:
	std::string buffer;
	std::vector<MessageProcessor*> processors;

public:
	InputService(SerialConnector& SerialConnector);
	virtual void onCharRead(const char c);

};

}

#endif /* INPUTSERVICE_HPP_ */
