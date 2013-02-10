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

	double amount;

public:


	void setUserName(std::string userName) {
		this->userName = userName;
	}

	void setAmount(double amount) {
		this->amount = amount;
	}

protected:
	virtual void paintView(SDL_Surface* screen) const;

};

}




#endif /* DRAWVIEW_HPP_ */
