/*
 * TimerManager.cpp
 *
 *  Created on: May 11, 2014
 *      Author: thomas
 */

#include <iostream>
#include "../../include/statemachine/StateMachine.hpp"
#include "../../include/statemachine/TimerManager.hpp"
#include "../../include/Threads.hpp"

using namespace boost;
using namespace std;

Timer TimerManager::createTimer(Event* event,
		const boost::posix_time::ptime& timeout) {
	Timer timer;
	timer.event = event;
	timer.timeout = timeout;
	return timer;
}

void TimerManager::startTimer(int timerId, Event* event, long millis) {
	mutex::scoped_lock lock(mapMutex);
	boost::posix_time::ptime timeout(
			boost::posix_time::microsec_clock::local_time());
	timeout += posix_time::milliseconds(millis);

	std::cout << "starting timer" << std::endl;
	std::cout << timeout << std::endl;

	Timer timer = createTimer(event, timeout);
	mapId2Timer[timerId] = timer;

	Threads::getInstance().timerThread->interrupt();
}
void TimerManager::stopTimer(int timerId) {
	mapId2Timer.erase(timerId);
	Threads::getInstance().timerThread->interrupt();
}

int TimerManager::findNextTimer() {
	int nextTimerId = -1;
	for (std::map<int, Timer>::iterator it = mapId2Timer.begin();
			it != mapId2Timer.end(); ++it) {
		if (nextTimerId == -1
				|| mapId2Timer[nextTimerId].timeout > it->second.timeout) {
			nextTimerId = it->first;
		}
	}
	return nextTimerId;
}

void TimerManager::scheduleTimer(int timerId) {
	Timer nextTimer = mapId2Timer[timerId];

	std::cout << "scheduling timer" << timerId << std::endl;

	posix_time::time_duration diff = nextTimer.timeout
			- posix_time::ptime(posix_time::microsec_clock::local_time());
	if (diff > posix_time::seconds(0)) {
		this_thread::sleep(diff);
	} else {
		this_thread::disable_interruption di;
		stopTimer(timerId);
		stateMachine.queueEvent(nextTimer.event);
		std::cout << "did sent event" << std::endl;
	}
}

void TimerManager::run() {
	while (true) {
		try {

			while (!mapId2Timer.empty()) {
				int nextTimerId = findNextTimer();
				scheduleTimer(nextTimerId);
			}

			std::cout << "there aint any timers running" << std::endl;
			posix_time::seconds duration = posix_time::seconds(60);
			this_thread::sleep(duration);

		} catch (boost::thread_interrupted const&) {
		}
	}

}

