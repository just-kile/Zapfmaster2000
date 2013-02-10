/*
 * StartupView.cpp
 *
 *  Created on: Jan 20, 2013
 *      Author: Thomas Kipar
 */

#include "../../include/views/StartupView.hpp"
#include "../../include/Files.hpp"

using namespace zm2k;

StartupView::StartupView() : ZapfView() {
	justKileImage = IMG_Load(toAbsPath("resources/justKile.png").c_str());
	if (justKileImage == NULL) {
		throw "Could not load just kile image";
	}
}

StartupView::~StartupView() {
	SDL_FreeSurface(justKileImage);
}

void StartupView::paintView(SDL_Surface* screen) const {
	drawText("Brought to you by", 60, 110, screen);

	drawText("Ben Lenser", 120, 140, screen);
	drawText("Daniel Wittekind", 120, 165, screen);
	drawText("Paul Boeck", 120, 190, screen);
	drawText("Thomas Kipar", 120, 215, screen);

	drawText("A product from", 220, 280, screen);
	SDL_Rect position = {360, 280, 479, 319};
	SDL_BlitSurface(justKileImage, NULL, screen, &position);
}





