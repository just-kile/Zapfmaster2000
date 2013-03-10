/*
 * DrawView.hpp
 *
 *  Created on: Feb 9, 2013
 *      Author: thomas
 */

#ifndef DRAWVIEW_HPP_
#define DRAWVIEW_HPP_

#include <string>
#include "ZapfView.hpp"


namespace zm2k {

class DrawView : public ZapfView {

private:

	std::string userName;
	SDL_Surface* userImage;
	double amount;

public:

	DrawView() {
		userImage = 0;
		amount = 0;
	}

	void setUserName(std::string userName) {
		this->userName = userName;
	}

	void setAmount(double amount) {
		this->amount = amount;
	}

	void setUserImage(SDL_Surface* userImage) {
		if (this->userImage != 0 && userImage != this->userImage) {
			delete userImage;
		}
		this->userImage = userImage;
	}

protected:
	virtual void paintView(SDL_Surface* screen) const;

};

}




#endif /* DRAWVIEW_HPP_ */
