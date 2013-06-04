/*
 * WebserviceConnector.hpp
 *
 *  Created on: Feb 3, 2013
 *      Author: Thomas Kipar
 */

#ifndef WEBSERVICECONNECTOR_HPP_
#define WEBSERVICECONNECTOR_HPP_

#include <string>
#include <curl/curl.h>
#include <boost/property_tree/ptree.hpp>
#include <SDL/SDL.h>
#include <boost/thread.hpp>
#include <log4cpp/Category.hh>

namespace zm2k {

class WebserviceConnector {

private:

	boost::mutex mutex;
	std::string rootPath;
	std::string passphrase;

	CURL* curl;

	log4cpp::Category& logger;

	WebserviceConnector(WebserviceConnector&);

public:

	WebserviceConnector(std::string pRootPath, std::string pPassphrase);


	boost::property_tree::ptree postLogin(long rfidTag);
	boost::property_tree::ptree postTicks(int numTicks);

	SDL_Surface* retrieveImage(std::string pPath);

};

struct SimpleBuffer {
	SimpleBuffer() : size(0) {
	}

	char buffer[1000000];
	int size;
};

}

#endif /* WEBSERVICECONNECTOR_HPP_ */
