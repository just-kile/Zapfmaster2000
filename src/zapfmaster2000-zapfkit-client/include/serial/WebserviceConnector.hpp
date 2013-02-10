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

namespace zm2k {

class WebserviceConnector {

private:

	std::string rootPath;
	std::string passphrase;

	CURL* curl;

public:

	WebserviceConnector(std::string pRootPath, std::string pPassphrase);

	boost::property_tree::ptree postLogin(std::string rfidTag);
	boost::property_tree::ptree postTicks(int numTicks);

};

}

#endif /* WEBSERVICECONNECTOR_HPP_ */
