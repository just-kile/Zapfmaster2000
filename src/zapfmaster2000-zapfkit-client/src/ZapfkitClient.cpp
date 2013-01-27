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

int main() {

	try {
		//ZapfDisplay display;
		//display.run();

		SerialConnector connector("/dev/ttyUSB0", B9600);
		//addListener(new MyListener2());
		InputService service(connector);
		service.addListener(new MyListener());
		connector.run();

	} catch (const char* exception) {
		cerr << "Caught error: " << exception << endl;
		cerr << "SDL says: " << SDL_GetError() << endl;
		cerr << "errno: " << errno << ", " << strerror(errno) << endl;
	}

}

