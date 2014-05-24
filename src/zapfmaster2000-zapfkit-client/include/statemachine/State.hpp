/*
 * State.hpp
 *
 *  Created on: May 11, 2014
 *      Author: thomas
 */

#ifndef STATE_HPP_
#define STATE_HPP_

#include "Events.hpp"

class StateMachine;
class TimerManager;

class State {

	friend StateMachine;

	StateMachine* parent;

public:
	virtual void onEnter() {
	}
	virtual void onLeave() {
	}

	virtual void process(const Event& event) = 0;

protected:

	void setParent(StateMachine* parent) {
		this->parent = parent;
	}

	void performTransition(State* nextState);

	TimerManager* getTimerManager();

};

template<typename T>
class EventAcceptor: public State {
protected:
	template<class E>
	void accept(const Event& e) {
		try {
			E castedEvent = dynamic_cast<const E&>(e);
			dynamic_cast<T*>(this)->process(castedEvent);
		} catch (...) {
		}
	}
};

#endif /* STATE_HPP_ */
