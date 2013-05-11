/*
 * InputService.hpp
 *
 *  Created on: Jan 27, 2013
 *      Author: thomas
 */

#ifndef INPUTSERVICE_HPP_
#define INPUTSERVICE_HPP_

#include "../Observable.hpp"
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

private:

	int serialInterface;
	int serialByteCounter;
	long curRfid;

public:

	InputService();
	void run();

};

class MockInputService : public InputService {

public:
	MockInputService();
	void run();

};

}

#endif /* INPUTSERVICE_HPP_ */
