/*
 * ZapfDisplay.cpp
 *
 *  Created on: Jan 20, 2013
 *      Author: Thomas Kipar
 */

#include <SDL/SDL.h>
#include "../include/ZapfDisplay.hpp"
#include "../include/views/StartupView.hpp"

using namespace zm2k;

ZapfDisplay::ZapfDisplay() {
	initSDL();
}

ZapfDisplay::~ZapfDisplay() {
	SDL_Quit();
}

void ZapfDisplay::run() {

	// 1st: Show startup view
	StartupView view(screen);
	view.paint();
	SDL_Delay(5000);
}

void ZapfDisplay::initSDL() {
	screen = NULL;
	SDL_Init(SDL_INIT_VIDEO);
	const SDL_VideoInfo * videoInfo = SDL_GetVideoInfo();

	int systemX = videoInfo->current_w;
	int systemY = videoInfo->current_h;
	int systemZ = videoInfo->vfmt->BitsPerPixel;

	//Set up screen
	screen = SDL_SetVideoMode(systemX, systemY, systemZ,
			SDL_SWSURFACE | SDL_FULLSCREEN);

	if (!screen) {
		throw "SDL_SetVideoMode failed";
	}
}
