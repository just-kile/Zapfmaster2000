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

public:

	StartupView(SDL_Surface* surface);
	virtual ~StartupView();

protected:

	void paintView() const;

};

}

#endif /* STARTUPVIEW_HPP_ */
