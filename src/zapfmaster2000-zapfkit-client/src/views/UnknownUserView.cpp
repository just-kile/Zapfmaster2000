/*
 * UnknownUserView.cpp
 *
 *  Created on: Mar 10, 2013
 *      Author: thomas
 */

#include "../../include/views/UnkownUserView.hpp"

using namespace zm2k;

void UnkownUserView::paintView(SDL_Surface* screen) const {

	drawText("This card is unkown or not", 60, 110, screen);
	drawText("registered for this zapf kit.", 60, 150, screen);
}



