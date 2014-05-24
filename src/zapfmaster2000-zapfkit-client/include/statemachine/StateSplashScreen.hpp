/*
 * StateSplashScreen.hpp
 *
 *  Created on: May 24, 2014
 *      Author: thomas
 */

#ifndef STATESPLASHSCREEN_HPP_
#define STATESPLASHSCREEN_HPP_

#include <iostream>
#include "State.hpp"

class StateSplashScreen: public EventAcceptor<StateSplashScreen> {

private:

	const int splashScreenTimer = 0;
	const long slapshScreenDurationMillis = 10 * 1000;

	State* configurationState;

public:

	StateSplashScreen(State* configurationState) :
			configurationState(configurationState) {
	}

	virtual void process(const Event& event) {
		std::cout << "getting event" << std::endl;
		accept<TimeoutEvent>(event);
	}

	void process(const TimeoutEvent& event) {
		std::cout << "got timeout" << std::endl;
		performTransition(configurationState);
	}

	virtual void onEnter() {
		std::cout << "SplashScreen on enter2" << std::endl;
		Event* timeoutEvent = new TimeoutEvent(splashScreenTimer);
		getTimerManager()->startTimer(splashScreenTimer, timeoutEvent,
				slapshScreenDurationMillis);
	}

};

#endif /* STATESPLASHSCREEN_HPP_ */
