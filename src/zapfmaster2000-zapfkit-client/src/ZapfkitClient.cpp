/*
 * ZapfkitClient.cpp
 *
 *  Created on: Jan 20, 2013
 *      Author: Thomas Kipar
 */

#include <iostream>
#include "../include/ZapfDisplay.hpp"
#include <SDL/SDL.h>
#include <errno.h>
#include <serial/InputService.hpp>
#include <boost/thread.hpp>
#include <ZapfController.hpp>

using namespace zm2k;
using namespace std;

class MyListener2: public SerialConnectorListener {

public:
	void onCharRead(const char c) {
		cout << "read char: " << c << endl;
	}
};

class MyListener: public InputServiceListener {

public:
	virtual void onRfidRead(long rfid) {
		cout << "read rfid: " << rfid << endl;
	}
	virtual void onTicksRead(int ticks) {
		cout << "read ticks: " << ticks << endl;
	}
};

SerialConnector connector("/dev/ttyUSB0", B9600);
InputService service(connector);
ZapfDisplay display;
ZapfController controller(display, service);

void inputThread() {
	try {
		connector.run();
	} catch (const char* exception) {
		cerr << "Caught error: " << exception << endl;
		cerr << "SDL says: " << SDL_GetError() << endl;
		cerr << "errno: " << errno << ", " << strerror(errno) << endl;
		exit(-1);
	}
}

void displayThread() {
	try {
		controller.run();
	} catch (const char* exception) {
		cerr << "Caught error: " << exception << endl;
		cerr << "SDL says: " << SDL_GetError() << endl;
		cerr << "errno: " << errno << ", " << strerror(errno) << endl;
		exit(-1);
	}
}
int main() {

	try {


		service.addListener(new MyListener());

		boost::thread t1(inputThread);
		boost::thread t2(displayThread);
		controller.setThread(&t2);
		t1.join();
		t2.join();

	} catch (const char* exception) {
		cerr << "Caught error: " << exception << endl;
		cerr << "SDL says: " << SDL_GetError() << endl;
		cerr << "errno: " << errno << ", " << strerror(errno) << endl;
	}

}

