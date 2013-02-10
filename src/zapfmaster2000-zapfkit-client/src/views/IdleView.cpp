/*
 * IdleView.cpp
 *
 *  Created on: Jan 27, 2013
 *      Author: thomas
 */

#include "../../include/views/IdleView.hpp"

using namespace zm2k;

IdleView::IdleView() : ZapfView() {
}

IdleView::~IdleView() {
}

void IdleView::paintView(SDL_Surface* screen) const {
	drawText("Place your card onto reader", 60, 110, screen);
}
