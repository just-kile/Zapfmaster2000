/*
 * ZapfView.cpp
 *
 *  Created on: Jan 20, 2013
 *      Author: Thomas
 */

#include <SDL/SDL_image.h>
#include <SDL/SDL_ttf.h>

namespace zm2k {

/**
 * \brief A zapf view is an abstract class to provided shared functionality for
 * each view for the client, e. g. the drawing of the background.
 */
class ZapfView {

private:

	SDL_Surface* screen;

	SDL_Surface* backgroundImage;
	SDL_Surface* titleImage;

	TTF_Font* font;

	const SDL_Color fontColor;

public:

	ZapfView(SDL_Surface* surface);

	virtual ~ZapfView();

	/**
	 * \brief Paints whole the view.
	 */
	void paint() const;

protected:

	/**
	 * \brief Paints the foreground of the view.
	 */
	virtual void paintView() const = 0;

	void drawText(char* text, int x, int y) const;

	SDL_Surface* getScreen() const {
		return screen;
	}

private:

	/**
	 * \brief Paints the background of the view.
	 */
	void paintBackground() const;

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

