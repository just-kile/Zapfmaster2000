/*
 * LEDConnector.cpp

 *
 *  Created on: May 25, 2013
 *      Author: thomas
 */

#include <stdlib.h>
#include <boost/date_time/posix_time/posix_time_types.hpp>
#include <sstream>
#include <wiringPi.h>
#include <iostream>
#include <log4cpp/Category.hh>
#include "../../include/serial/LEDController.hpp"

using namespace zm2k;
using namespace std;

Color currentColor(1, 1, 1);
Color targetColor(0, 0, 0);

const int fadeTime = 500; // in ms
const int fadeInterval = 0;

void changeColor(double r, double g, double b) {
	stringstream ss;
	ss << "resources/changeColor.sh ";
	ss << r << " " << g << " " << b;
	std::system(ss.str().c_str());
}

void fadeThread() {

	while (true) {

		try {
			if (currentColor == targetColor) {
				changeColor(currentColor.r, currentColor.g, currentColor.b);
				boost::this_thread::sleep(boost::posix_time::seconds(100));
			} else {
				// fading is needed
				boost::posix_time::ptime startTime =
						boost::posix_time::microsec_clock::local_time();
				boost::posix_time::time_duration diff;
				while ((diff = boost::posix_time::microsec_clock::local_time()
						- startTime) < boost::posix_time::milliseconds(fadeTime)) {

					double ratio = diff.total_milliseconds()
							/ (double) fadeTime;

					double newR = currentColor.r * (1 - ratio)
							+ targetColor.r * ratio;
					double newG = currentColor.g * (1 - ratio)
							+ targetColor.g * ratio;
					double newB = currentColor.b * (1 - ratio)
							+ targetColor.b * ratio;

					changeColor(newR, newG, newB);
					delay(fadeInterval);
				}
				currentColor = targetColor;
				changeColor(currentColor.r, currentColor.g, currentColor.b);
			}
		} catch (boost::thread_interrupted const&) {
			// we have been interrupted. Fine. Just continue.
		}

	}

}

LEDController::LEDController() {
	thread = new boost::thread(fadeThread);
	changeColor(YELLOW);
}

void LEDController::changeColor(LEDColor newColor) {

	switch (newColor) {
	case GREEN:
		targetColor = Color(0, 1, 0);
		break;
	case YELLOW:
		targetColor = Color(1, 0.35, 0);
		break;
	case RED:
		targetColor = Color(1, 0, 0);
		break;
	}

	thread->interrupt();
}

