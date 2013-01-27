/*
 * SerialConnector.hpp
 *
 *  Created on: Jan 27, 2013
 *      Author: thomas
 */

#ifndef SERIALCONNECTOR_HPP_
#define SERIALCONNECTOR_HPP_

#include <string>
#include <termios.h>
#include "../Observable.hpp"

namespace zm2k {

class SerialConnectorListener {

public:

	virtual ~SerialConnectorListener() {
	}

	virtual void onCharRead(const char c) = 0;

};

class SerialConnector: public Observable<SerialConnectorListener> {

private:
	int tty_fd;

public:

	SerialConnector(std::string pDevice, speed_t pBaudRate);
	~SerialConnector();

	void run();

};

}

#endif /* SERIALCONNECTOR_HPP_ */
