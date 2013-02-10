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

ZapfController::ZapfController(ZapfDisplay& display, InputService& input,
		WebserviceConnector& connector) :
		display(display), connector(connector) {
	currentUser = "";
	amount = 0;
	input.addListener(this);
}

ZapfController::~ZapfController() {

}

#include <iostream>

void ZapfController::run() {
	display.paint(startupView);
	sleep(2);

	while (true) {
		try {
			if (currentUser == "") {
				// idle
				display.paint(idleView);
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
					drawView.setUserName(currentUser);
					drawView.setAmount(amount);
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

void ZapfController::onRfidRead(string rfid) {
	lastRfid = boost::posix_time::second_clock::local_time();
	try {
		boost::property_tree::ptree pt = connector.postLogin(rfid);
		string newUser = pt.get("userName", "");
		if (newUser != currentUser) {
			amount = 0;
		}
		currentUser = newUser;
	} catch (const char* e) {
		currentUser = ""; // no user
		cerr << e << endl;
	}
	controllerThread->interrupt();
}

void ZapfController::onTicksRead(int ticks) {
	lastRfid = boost::posix_time::second_clock::local_time();
	try {
		boost::property_tree::ptree pt = connector.postTicks(ticks);
		amount = pt.get<double>("totalAmount", 0);
	} catch (const char* e) {
		cerr << e << endl;
	}
	controllerThread->interrupt();
}
