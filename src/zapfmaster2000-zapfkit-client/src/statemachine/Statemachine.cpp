/*
 * Statemachine.cpp
 *
 *  Created on: May 11, 2014
 *      Author: thomas
 */

#include <boost/thread.hpp>
#include "../../include/Threads.hpp"
#include "../../include/statemachine/State.hpp"
#include "../../include/statemachine/StateMachine.hpp"
#include "../../include/statemachine/TimerManager.hpp"

void StateMachine::run() {
	currentState->onEnter();

	while (true) {
		try {
			while (!eventQueue.empty()) {
				std::cout << "processing event" << std::endl;
				boost::this_thread::disable_interruption di;
				Event* event = eventQueue.front();
				currentState->process(*event);
				delete event;
				eventQueue.pop();
				std::cout << "did process event" << std::endl;
			}

			boost::posix_time::seconds duration = boost::posix_time::seconds(
					60);
			boost::this_thread::sleep(duration);
		} catch (boost::thread_interrupted const&) {
		}
	}
}

void StateMachine::addState(State& state) {
	state.setParent(this);
}

void StateMachine::queueEvent(Event* event) {
	eventQueue.push(event);
	std::cout << "pushed event" << std::endl;
	Threads::getInstance().controllerThread->interrupt();
}

void StateMachine::performTransition(State* state) {
	if (currentState != 0) {
		currentState->onLeave();
	}
	state->onEnter();
	currentState = state;
}

TimerManager* StateMachine::getTimerManager() {
	return timerManager;
}

void StateMachine::init(TimerManager* timerManager) {
	this->timerManager = timerManager;
}

