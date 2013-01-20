/*
 * ZapfView.cpp
 *
 *  Created on: Jan 20, 2013
 *      Author: Thomas
 */

#include <SDL/SDL_image.h>

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

private:

	/**
	 * \brief Paints the background of the view.
	 */
	void paintBackground() const;

	/*
	 * \brief loads the images (for background drawings)
	 */
	void loadImages();
};

}

