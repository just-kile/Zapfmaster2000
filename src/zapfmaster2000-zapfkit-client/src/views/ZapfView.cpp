/*
 * ZapfView.cpp
 *
 *  Created on: Jan 20, 2013
 *      Author: Thomas Kipar
 */

#include <assert.h>
#include "../../include/views/ZapfView.hpp"

using namespace zm2k;

ZapfView::ZapfView(SDL_Surface* surface) {
	assert(surface != 0);
	screen = surface;
	loadImages();
}

ZapfView::~ZapfView() {
	SDL_FreeSurface(backgroundImage);
	SDL_FreeSurface(titleImage);
}


void ZapfView::paint() const {
	paintBackground();
	paintView();
	SDL_Flip(screen);
}

void ZapfView::paintBackground() const {
	SDL_BlitSurface(backgroundImage, NULL, screen, NULL);
	SDL_BlitSurface(titleImage, NULL, screen, NULL);
}

void ZapfView::loadImages() {
	backgroundImage = IMG_Load("resources/background.jpg");
	if (backgroundImage == NULL) {
		throw "Could not load background image";
	}

	titleImage = IMG_Load("resources/zapfmaster.png");
	if (titleImage == NULL) {
		throw "Could not load zapfmaster title image";
	}
}
