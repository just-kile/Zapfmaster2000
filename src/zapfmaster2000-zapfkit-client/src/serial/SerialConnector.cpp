/*
 * SerialConnector.cpp
 *
 *  Created on: Jan 27, 2013
 *      Author: thomas
 */

#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <termios.h>
#include <boost/bind.hpp>
#include <errno.h>

#include "../../include/serial/SerialConnector.hpp"

using namespace zm2k;
using namespace std;

SerialConnector::SerialConnector(string pDevice, speed_t pBaudRate) {

	struct termios tio;
	memset(&tio, 0, sizeof(tio));
	tio.c_iflag = 0;
	tio.c_oflag = 0;
	tio.c_cflag = CS8 | CREAD | CLOCAL;
	tio.c_lflag = 0;
	tio.c_cc[VMIN] = 1;
	tio.c_cc[VTIME] = 0;

	tty_fd = open(pDevice.c_str(), O_RDWR);
	if (tty_fd == -1) {
		throw "Could not establish serial connection.";
	}
	cfsetospeed(&tio, pBaudRate);
	cfsetispeed(&tio, pBaudRate);

	tcsetattr(tty_fd, TCSANOW, &tio);
}

SerialConnector::~SerialConnector() {
	close(tty_fd);
}

#include <iostream>

void SerialConnector::run() {
	char c;
	while (true) {
		int ret = read(tty_fd, &c, 1);
		if (ret == -1) {
			if (errno != EAGAIN) {
				throw "Error while reading serial input";
			}
		}
		if (ret > 0) {
			notifyListeners(
					boost::bind(&SerialConnectorListener::onCharRead, _1, c));
		}
	}
}

