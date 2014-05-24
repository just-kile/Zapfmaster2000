/*
 * TimerManager.hpp
 *
 *  Created on: May 11, 2014
 *      Author: thomas
 */

#ifndef TIMERMANAGER_HPP_
#define TIMERMANAGER_HPP_

#include <boost/thread.hpp>
#include <boost/date_time/posix_time/posix_time.hpp>
#include <map>
#include "Events.hpp"

class StateMachine;

struct Timer {

	Event* event;
	boost::posix_time::ptime timeout;

};

class TimerManager {

	boost::mutex mapMutex;
	StateMachine& stateMachine;
	std::map<int, Timer> mapId2Timer;

public:

	TimerManager(StateMachine& stateMachine) :
			stateMachine(stateMachine) {
	}

	void startTimer(int timerId, Event* event, long millis);
	void stopTimer(int timerId);
	void run();

private:

	int findNextTimer();
	void scheduleTimer(int timerId);
	Timer createTimer(Event* event, const boost::posix_time::ptime& timeout);
};

#endif /* TIMERMANAGER_HPP_ */
