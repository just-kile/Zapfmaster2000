/*
 * Event.cpp
 *
 *  Created on: May 11, 2014
 *      Author: thomas
 */

#include "../../include/statemachine/State.hpp"
#include "../../include/statemachine/StateMachine.hpp"

void State::performTransition(State* nextState) {
	parent->performTransition(nextState);
}

TimerManager* State::getTimerManager() {
	return parent->getTimerManager();
}
