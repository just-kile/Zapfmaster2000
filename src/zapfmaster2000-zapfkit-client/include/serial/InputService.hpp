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

class AbstractInputService : public Observable<InputServiceListener> {

public:
	virtual void run() = 0;

	void notifyZapfcount(int count);
	void notifyRfid(long rfid);

};
class InputService: public AbstractInputService {

private:

	int serialInterface;
	int serialByteCounter;
	long curRfid;

public:

	InputService();
	virtual void run();

};

class MockInputService : public AbstractInputService {

public:
	MockInputService();
	virtual void run();
};

}

#endif /* INPUTSERVICE_HPP_ */
