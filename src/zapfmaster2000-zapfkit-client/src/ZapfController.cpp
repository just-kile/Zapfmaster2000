/*
 * ZapfController.cpp
 *
 *  Created on: Jan 27, 2013
 *      Author: thomas
 */

#include <unistd.h>
#include "../include/ZapfController.hpp"

#include <boost/thread.hpp>
#include <boost/date_time/posix_time/posix_time_types.hpp>
#include <boost/property_tree/json_parser.hpp>

using namespace zm2k;
using namespace std;

ZapfController::ZapfController(ZapfDisplay& display,
		AbstractInputService& input, WebserviceConnector* connector) :
		display(display), connector(connector), logger(
				log4cpp::Category::getInstance(std::string("ZapfController"))) {
	currentUser = "";
	amount = 0;
	unkownUser = false;
	input.addListener(this);
}

ZapfController::~ZapfController() {

}

#include <iostream>

void ZapfController::run() {
	logger.debug("ZapfController is running.");

	display.paint(startupView);
	sleep(2);

	while (true) {
		try {
			if (unkownUser) {
				unkownUser = false;
				display.paint(unkownUserView);
				ledController.changeColor(RED);
				boost::this_thread::sleep(boost::posix_time::seconds(1));
			} else if (currentUser == "") {
				// idle
				display.paint(idleView);
				ledController.changeColor(YELLOW);
				boost::this_thread::sleep(boost::posix_time::seconds(100));
			} else {
				boost::posix_time::ptime now =
						boost::posix_time::second_clock::local_time();
				boost::posix_time::time_duration logoutTime =
						boost::posix_time::seconds(3);
				boost::posix_time::time_duration diff = now - lastRfid;
				if (diff > logoutTime) {
					// perform logout
					currentUser = "";
					amount = 0;
				} else {
					ledController.changeColor(GREEN);
					drawView.setUserName(currentUser);
					drawView.setAmount(amount);
					drawView.setUserImage(userImage);
					display.paint(drawView);
					boost::posix_time::time_duration sleepTime = logoutTime
							- diff + boost::posix_time::milliseconds(50); // +50 for "safety"
					boost::this_thread::sleep(sleepTime);
				}

			}
		} catch (boost::thread_interrupted const&) {
			// we have been interrupted. Fine. Just continue.
		}
	}

}

void ZapfController::onRfidRead(long rfid) {
	logger.info("Processing rfid %d", rfid);

	lastRfid = boost::posix_time::second_clock::local_time();
	unkownUser = false;
	try {
		boost::property_tree::ptree pt = connector->postLogin(rfid);
		string newUser = pt.get("userName", "");
		if (newUser != currentUser) {
			amount = 0;

			// load the image
			try {
				// TODO: reenable the image
				//userImage = connector->retrieveImage(pt.get("imagePath", ""));
			} catch (const char* e) {
				cerr << e << endl;
				userImage = 0;
			}
		}
		currentUser = newUser;
	} catch (const char* e) {
		unkownUser = true;
		currentUser = ""; // no user
		cerr << e << endl;
	}
	controllerThread->interrupt();
}

void ZapfController::onTicksRead(int ticks) {
	logger.info("Processing ticks %d", ticks);

	lastRfid = boost::posix_time::second_clock::local_time();
	try {
		boost::property_tree::ptree pt = connector->postTicks(ticks);
		amount = pt.get<double>("totalAmount", 0);
	} catch (const char* e) {
		cerr << e << endl;
	}
	controllerThread->interrupt();
}
