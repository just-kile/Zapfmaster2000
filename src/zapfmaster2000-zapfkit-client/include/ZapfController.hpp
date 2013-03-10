/*
 * ZapfController.hpp
 *
 *  Created on: Jan 27, 2013
 *      Author: thomas
 */

#ifndef ZAPFCONTROLLER_HPP_
#define ZAPFCONTROLLER_HPP_

#include "ZapfDisplay.hpp"
#include "views/StartupView.hpp"
#include "views/IdleView.hpp"
#include "views/DrawView.hpp"
#include "views/UnkownUserView.hpp"
#include "serial/InputService.hpp"
#include "serial/WebserviceConnector.hpp"
#include <boost/thread.hpp>
#include <boost/date_time/posix_time/posix_time.hpp>

namespace zm2k {

class ZapfController: InputServiceListener {

private:
	ZapfDisplay& display;
	StartupView startupView;
	IdleView idleView;
	DrawView drawView;
	UnkownUserView unkownUserView;
	WebserviceConnector connector;

	bool unkownUser;
	std::string currentUser;
	SDL_Surface* userImage;
	double amount;

	boost::thread* controllerThread;
	boost::posix_time::ptime lastRfid;

public:

	ZapfController(ZapfDisplay& display, InputService& input,
			WebserviceConnector &connector);
	~ZapfController();

	virtual void onRfidRead(std::string rfid);
	virtual void onTicksRead(int ticks);

	void setThread(boost::thread* thread) {
		controllerThread = thread;
	}

	void run();

};

}

#endif /* ZAPFCONTROLLER_HPP_ */
