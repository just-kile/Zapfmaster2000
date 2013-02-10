/*
 * ZapfView.cpp
 *
 *  Created on: Jan 20, 2013
 *      Author: Thomas
 */

#ifndef ZAPFVIEW_HPP_
#define ZAPFVIEW_HPP_

#include <SDL/SDL_image.h>
#include <SDL/SDL_ttf.h>

namespace zm2k {

/**
 * \brief A zapf view is an abstract class to provided shared functionality for
 * each view for the client, e. g. the drawing of the background.
 */
class ZapfView {

private:

	SDL_Surface* backgroundImage;
	SDL_Surface* titleImage;

	TTF_Font* font;

	const SDL_Color fontColor;

public:

	ZapfView();

	virtual ~ZapfView();

	/**
	 * \brief Paints whole the view.
	 */
	void paint(SDL_Surface* screen) const;

protected:

	/**
	 * \brief Paints the foreground of the view.
	 */
	virtual void paintView(SDL_Surface* screen) const = 0;

	void drawText(const char* text, int x, int y, SDL_Surface* screen) const;

private:

	/**
	 * \brief Paints the background of the view.
	 */
	void paintBackground(SDL_Surface* screen) const;

	/*
	 * \brief loads the images (for background drawings)
	 */
	void loadImages();

	/*
	 * \breif loads the shared fonts
	 */
	void loadFonts();
};

}

#endif

