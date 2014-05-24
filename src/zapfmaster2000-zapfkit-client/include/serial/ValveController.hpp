/*
 * ValveController.hpp
 *
 *  Created on: May 24, 2014
 *      Author: thomas
 */

#ifndef VALVECONTROLLER_HPP_
#define VALVECONTROLLER_HPP_


#include <sstream>
#include <stdlib.h>

enum ValveState {
	OPEN, CLOSE
};

class ValveController {

public:

	void switchValve(ValveState state) {
		std::stringstream ss;
		ss << "resources/switchValve.sh ";
		if (state == OPEN) {
			ss << "open";
		} else {
			ss << "close";
		}
		std::system(ss.str().c_str());
	}

};



#endif /* VALVECONTROLLER_HPP_ */
