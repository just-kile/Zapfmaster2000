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

#include <serial/SerialConnector.hpp>

using namespace zm2k;
using namespace std;

SerialConnector::SerialConnector(string pDevice, speed_t pBaudRate) {

	struct termios tio;
	struct termios stdio;

	memset(&stdio, 0, sizeof(stdio));
	stdio.c_iflag = 0;
	stdio.c_oflag = 0;
	stdio.c_cflag = 0;
	stdio.c_lflag = 0;
	stdio.c_cc[VMIN] = 1;
	stdio.c_cc[VTIME] = 0;
	tcsetattr(STDOUT_FILENO, TCSANOW, &stdio);
	tcsetattr(STDOUT_FILENO, TCSAFLUSH, &stdio);
	fcntl(STDIN_FILENO, F_SETFL);

	memset(&tio, 0, sizeof(tio));
	tio.c_iflag = 0;
	tio.c_oflag = 0;
	tio.c_cflag = CS8 | CREAD | CLOCAL;
	tio.c_lflag = 0;
	tio.c_cc[VMIN] = 1;
	tio.c_cc[VTIME] = 5;

	tty_fd = open(pDevice.c_str(), O_RDWR | O_NOCTTY | O_NDELAY);
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

