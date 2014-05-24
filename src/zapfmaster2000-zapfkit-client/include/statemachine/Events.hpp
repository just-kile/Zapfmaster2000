/*
 * Events.hpp
 *
 *  Created on: May 11, 2014
 *      Author: thomas
 */

#ifndef EVENTS_HPP_
#define EVENTS_HPP_

class Event {
private:
	/*
	 * Used to make event classes to be polymorphic.
	 */
	virtual void makePolymorphic() {
	}
};

class TickEvent: public Event {

	const int numTicks;

public:
	TickEvent(int numTicks) :
			numTicks(numTicks) {
	}

	int getNumTicks() {
		return numTicks;
	}
};

class RfidEvent: public Event {

	const long rfidTag;

public:
	RfidEvent(long rfidTag) :
			rfidTag(rfidTag) {
	}

	long getRfidTag() {
		return rfidTag;
	}

};

class TimeoutEvent: public Event {
	const int timerId;

public:
	TimeoutEvent(int timerId) :
			timerId(timerId) {
	}

	long getTimerId() {
		return timerId;
	}
};

#endif /* EVENTS_HPP_ */
