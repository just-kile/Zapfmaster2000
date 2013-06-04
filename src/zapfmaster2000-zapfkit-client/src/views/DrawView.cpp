/*
 * DrawView.cpp
 *
 *  Created on: Feb 9, 2013
 *      Author: thomas
 */

#include <boost/lexical_cast.hpp>
#include <sstream>
#include <iomanip>
#include "../../include/views/DrawView.hpp"

using namespace zm2k;
using namespace std;

void DrawView::paintView(SDL_Surface* screen) const {

	string msg = string("Welcome ") + userName + string("!");

	drawText(msg.c_str(), 200, 100, screen);
	drawText("You may zapf now.", 200, 160, screen);

	if (amount != 0) {
		std::ostringstream ss;
		ss << "Zapf amount: ";
		ss << std::fixed << std::setprecision(2);
		ss << amount;
		ss << "l";
		std::string amountMsg = ss.str();
		drawText(amountMsg.c_str(), 200, 220, screen);
	}

	if (userImage != 0) {
		SDL_Rect pos = { 15, 50, userImage->w, userImage->h };
		SDL_BlitSurface(userImage, NULL, screen, &pos);
	}
}
