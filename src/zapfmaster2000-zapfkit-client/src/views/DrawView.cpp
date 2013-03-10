/*
 * DrawView.cpp
 *
 *  Created on: Feb 9, 2013
 *      Author: thomas
 */

#include <boost/lexical_cast.hpp>
#include "../../include/views/DrawView.hpp"

using namespace zm2k;
using namespace std;

void DrawView::paintView(SDL_Surface* screen) const {

	string msg = string("Welcome ") + userName + string("!");

	drawText(msg.c_str(), 200, 100, screen);
	drawText("You may zapf now.", 200, 150, screen);

	if (amount != 0) {
		string amountMsg = string("Zapf amount: ")
				+ boost::lexical_cast<string>(amount);
		drawText(amountMsg.c_str(), 200, 200, screen);
	}

	if (userImage != 0) {
		SDL_Rect pos = {15, 50, userImage->w, userImage->h};
		SDL_BlitSurface(userImage, NULL, screen, &pos);
	}
}
