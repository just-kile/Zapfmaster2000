/*
 * ZapfDisplay.hpp
 *
 *  Created on: Jan 20, 2013
 *      Author: Thomas Kipar
 */

#ifndef ZAPFDISPLAY_HPP_
#define ZAPFDISPLAY_HPP_

#include <SDL/SDL.h>

namespace zm2k {

class ZapfView;

/**
 * \brief 	The zapf display manages the whole output to the user on to the screen.
 *
 * 			Basically, this is done by switching to a view that displays all
 * 			the information.
 */
class ZapfDisplay {

private:

	SDL_Surface* screen;

public:

	ZapfDisplay();

	~ZapfDisplay();

	void paint(ZapfView& view);

private:

	void initSDL();

};

}

#endif /* ZAPFDISPLAY_HPP_ */
