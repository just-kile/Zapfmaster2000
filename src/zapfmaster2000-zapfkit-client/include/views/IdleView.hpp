/*
 * IdleView.hpp
 *
 *  Created on: Jan 27, 2013
 *      Author: thomas
 */

#ifndef IDLEVIEW_HPP_
#define IDLEVIEW_HPP_

#include "ZapfView.hpp"

namespace zm2k {

class IdleView : public ZapfView {

private:

public:

	IdleView();
	virtual ~IdleView();

protected:

	void paintView(SDL_Surface* screen) const;

};

}


#endif /* IDLEVIEW_HPP_ */
