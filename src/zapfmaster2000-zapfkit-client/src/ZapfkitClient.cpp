/*
 * ZapfkitClient.cpp
 *
 *  Created on: Jan 20, 2013
 *      Author: Thomas Kipar
 */

#include <iostream>
#include <SDL/SDL.h>
#include <errno.h>
#include <boost/thread.hpp>
#include <signal.h>
#include <string>
#include <boost/property_tree/ptree.hpp>
#include <boost/property_tree/xml_parser.hpp>

#include "../include/ZapfDisplay.hpp"
#include "../include/serial/InputService.hpp"
#include "../include/ZapfController.hpp"
#include "../include/Files.hpp"

using namespace zm2k;
using namespace std;

class MyListener: public InputServiceListener {

public:
	virtual void onRfidRead(long rfid) {
		cout << "read rfid: " << rfid << endl;
	}
	virtual void onTicksRead(int ticks) {
		cout << "read ticks: " << ticks << endl;
	}
};

AbstractInputService* service = 0;
ZapfDisplay display;
ZapfController* controller;

void inputThread() {
	try {
		cout << "running input service" << endl;
		service->run();
	} catch (const char* exception) {
		cerr << "Caught error: " << exception << endl;
		cerr << "SDL says: " << SDL_GetError() << endl;
		cerr << "errno: " << errno << ", " << strerror(errno) << endl;
		exit(-1);
	}
}

void displayThread() {
	try {
		controller->run();
	} catch (const char* exception) {
		cerr << "Caught error: " << exception << endl;
		cerr << "SDL says: " << SDL_GetError() << endl;
		cerr << "errno: " << errno << ", " << strerror(errno) << endl;
		exit(-1);
	}
}

// Exit properly on signal
void sighandle(int sig) {
	SDL_Quit();
	exit(-1);
}

int main(int argc, const char* argv[]) {

	cout << "The Zapfmaster 2000 Zapfkit Client says: Hi." << endl;

	// load properties
	boost::property_tree::ptree ptree;
	boost::property_tree::read_xml(toAbsPath("resources/config.xml"), ptree);

	WebserviceConnector webservieConnector(
			ptree.get<string>("config.webservice.path"),
			ptree.get<string>("config.webservice.passphrase"));

	try {
		if (argc > 1) {
			string mockArg = "mock";
			if (mockArg.compare(argv[1]) == 0) {
				cout << "creating mock service" << endl;
				service = new MockInputService();
			}
		}

		if (!service) {
			cout << "created input service" << endl;
			service = new InputService();
		}

		service->addListener(new MyListener());

		controller = new ZapfController(display, *service, &webservieConnector);
		cout << "running thread" << endl;
		boost::thread t1(inputThread);
		boost::thread t2(displayThread);
		controller->setThread(&t2);

		signal(SIGINT, &sighandle);

		cout << "joining" << endl;
		t1.join();
		t2.join();
	} catch (const char* exception) {
		cerr << "Caught error: " << exception << endl;
		cerr << "SDL says: " << SDL_GetError() << endl;
		cerr << "errno: " << errno << ", " << strerror(errno) << endl;
		exit(-1);
	}

}

