/*
 * StartupView.cpp
 *
 *  Created on: Jan 20, 2013
 *      Author: Thomas Kipar
 */

#include "../../include/views/StartupView.hpp"

using namespace zm2k;

StartupView::StartupView(SDL_Surface* surface) : ZapfView(surface) {
	justKileImage = IMG_Load("resources/justKile.png");
	if (justKileImage == NULL) {
		throw "Could not load just kile image";
	}
}

StartupView::~StartupView() {
	SDL_FreeSurface(justKileImage);
}

void StartupView::paintView() const {
	drawText("Brought to you by", 60, 110);

	drawText("Ben Lenser", 120, 140);
	drawText("Daniel Wittekind", 120, 165);
	drawText("Paul Boeck", 120, 190);
	drawText("Thomas Kipar", 120, 215);

	drawText("A product from", 220, 280);
	SDL_Rect position = {360, 280, 479, 319};
	SDL_BlitSurface(justKileImage, NULL, getScreen(), &position);
}





