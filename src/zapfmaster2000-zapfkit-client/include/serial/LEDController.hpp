/*
 * LEDController.hpp
 *
 *  Created on: May 25, 2013
 *      Author: thomas
 */

#ifndef LEDCONTROLLER_HPP_
#define LEDCONTROLLER_HPP_

#include <boost/thread.hpp>

namespace zm2k {

enum LEDColor {

	RED, YELLOW, GREEN

};

struct Color {
	double r;
	double g;
	double b;

	Color(double r, double g, double b) {
		this->r = r;
		this->g = g;
		this->b = b;
	}

	bool operator==(Color& other) {
		return r == other.r && g == other.g && b == other.b;
	}
};
class LEDController {

private:

	boost::thread* thread;
	LEDColor currentColor;

public:

	LEDController();

	void changeColor(LEDColor newColor);

};

}

#endif /* LEDCONTROLLER_HPP_ */
