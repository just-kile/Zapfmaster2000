/*
 * StateSplashScreen.hpp
 *
 *  Created on: May 24, 2014
 *      Author: thomas
 */

#ifndef STATECONFIGURATION_HPP_
#define STATECONFIGURATION_HPP_

#include <iostream>
#include "State.hpp"

class StateConfiguration: public EventAcceptor<StateSplashScreen> {

public:

	virtual void process(const Event& event) {
	}

	virtual void onEnter() {
		std::cout << "Configuration on enter" << std::endl;
	}

};

#endif /* STATECONFIGURATION_HPP_ */
