/*
 * ZapfView.cpp
 *
 *  Created on: Jan 20, 2013
 *      Author: Thomas Kipar
 */

#include <assert.h>
#include "../../include/views/ZapfView.hpp"
#include "../../include/Files.hpp"

using namespace zm2k;

ZapfView::ZapfView(): fontColor({ 255, 255, 255, 255 }){
	loadImages();
	loadFonts();
}

ZapfView::~ZapfView() {
	SDL_FreeSurface(backgroundImage);
	SDL_FreeSurface(titleImage);
}


void ZapfView::paint(SDL_Surface* surface) const {
	paintBackground(surface);
	paintView(surface);
}

void ZapfView::paintBackground(SDL_Surface* surface) const {
	SDL_BlitSurface(backgroundImage, NULL, surface, NULL);
	int x = surface->w / 2 - titleImage->w / 2;
	SDL_Rect pos = {x, 10, surface->w, surface->h};
	SDL_BlitSurface(titleImage, NULL, surface, &pos);
}

void ZapfView::loadImages() {
	backgroundImage = IMG_Load(toAbsPath("resources/background.jpg").c_str());
	if (backgroundImage == NULL) {
		throw "Could not load background image";
	}

	titleImage = IMG_Load(toAbsPath("resources/zapfmaster.png").c_str());
	if (titleImage == NULL) {
		throw "Could not load zapfmaster title image";
	}
}

void ZapfView::loadFonts() {
	font = TTF_OpenFont(toAbsPath("resources/FreeSans.ttf").c_str(), 20);
	if (font == NULL) {
		throw "Could not load font";
	}
}

void ZapfView::drawText(const char* text, int x, int y, SDL_Surface* surface) const {
	SDL_Surface* textSurface = TTF_RenderText_Solid(font, text, fontColor);
	SDL_Rect position = { x, y, surface->w, surface->h};
	SDL_BlitSurface(textSurface, NULL, surface, &position);
	SDL_FreeSurface(textSurface);
}
