/*
 * InputService.hpp
 *
 *  Created on: Jan 27, 2013
 *      Author: thomas
 */

#ifndef INPUTSERVICE_HPP_
#define INPUTSERVICE_HPP_

#include "../Observable.hpp"
#include "SerialConnector.hpp"
#include <vector>
#include <string>
#include <boost/thread.hpp>

namespace zm2k {

class MessageProcessor;

class InputServiceListener {

public:

	virtual ~InputServiceListener() {
	}

	virtual void onRfidRead(long rfid) = 0;
	virtual void onTicksRead(int ticks) = 0;

};

class InputService: public Observable<InputServiceListener> {

};

class SerialInputService: public InputService, public SerialConnectorListener {

private:
	std::string buffer;
	std::vector<MessageProcessor*> processors;

public:
	SerialInputService(SerialConnector& SerialConnector);
	virtual ~SerialInputService();
	virtual void onCharRead(const char c);
};

class MockInputService : public InputService {

public:
	MockInputService();
	void run();

};

}

#endif /* INPUTSERVICE_HPP_ */
