/*
 * StateMachine.hpp
 *
 *  Created on: May 11, 2014
 *      Author: thomas
 */

#ifndef STATEMACHINE_HPP_
#define STATEMACHINE_HPP_

#include <queue>

class State;
class Event;
class TimerManager;

class StateMachine {

	friend State;

	State* currentState;
	std::queue<Event*> eventQueue;
	TimerManager* timerManager;

public:

	StateMachine(State& initialState) {
		currentState = &initialState;
		addState(initialState);
		timerManager = 0;
	}

	void init(TimerManager* timerManager);

	void run();
	void addState(State& state);
	void queueEvent(Event* event);

private:

	void performTransition(State* state);
	TimerManager* getTimerManager();

};

#endif /* STATEMACHINE_HPP_ */
