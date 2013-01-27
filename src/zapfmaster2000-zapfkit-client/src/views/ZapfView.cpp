/*
 * ZapfView.cpp
 *
 *  Created on: Jan 20, 2013
 *      Author: Thomas Kipar
 */

#include <assert.h>
#include "../../include/views/ZapfView.hpp"

using namespace zm2k;

ZapfView::ZapfView(SDL_Surface* surface) : fontColor({ 255, 255, 255, 255 }){
	assert(surface != 0);
	screen = surface;
	loadImages();
	loadFonts();
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
	int x = screen->w / 2 - titleImage->w / 2;
	SDL_Rect pos = {x, 10, screen->w, screen->h};
	SDL_BlitSurface(titleImage, NULL, screen, &pos);
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

void ZapfView::loadFonts() {
	font = TTF_OpenFont("resources/FreeSans.ttf", 20);
	if (font == NULL) {
		throw "Could not load font";
	}
}

void ZapfView::drawText(char* text, int x, int y) const {
	SDL_Surface* textSurface = TTF_RenderText_Solid(font, text, fontColor);
	SDL_Rect position = { x, y, screen->w, screen->h};
	SDL_BlitSurface(textSurface, NULL, screen, &position);
	SDL_FreeSurface(textSurface);
}
