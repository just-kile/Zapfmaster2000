/*
 * DrawView.hpp
 *
 *  Created on: Feb 9, 2013
 *      Author: thomas
 */

#ifndef UNKNOWNUSERVIEW_HPP_
#define UNKNOWNUSERVIEW_HPP_

#include <string>
#include "ZapfView.hpp"


namespace zm2k {

class UnkownUserView : public ZapfView {

protected:
	virtual void paintView(SDL_Surface* screen) const;

};

}

#endif /* UNKNOWNUSERVIEW_HPP_ */
