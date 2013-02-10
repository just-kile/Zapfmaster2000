/*
 * StartupView.hpp
 *
 *  Created on: Jan 20, 2013
 *      Author: thomas
 */

#ifndef STARTUPVIEW_HPP_
#define STARTUPVIEW_HPP_

#include "ZapfView.hpp"

namespace zm2k {

class StartupView : public ZapfView {

private:

	SDL_Surface* justKileImage;

public:

	StartupView();
	virtual ~StartupView();

protected:

	void paintView(SDL_Surface* screen) const;

};

}

#endif /* STARTUPVIEW_HPP_ */
