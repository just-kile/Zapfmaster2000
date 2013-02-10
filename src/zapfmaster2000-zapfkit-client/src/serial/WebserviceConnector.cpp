/*
 * WebserviceConnector.cpp
 *
 *  Created on: Feb 3, 2013
 *      Author: Thomas Kipar
 */

#include <sstream>
#include <iostream>
#include <boost/property_tree/json_parser.hpp>
#include "../../include/serial/WebserviceConnector.hpp"

using namespace zm2k;
using namespace std;

size_t write_to_string(void *ptr, size_t size, size_t count, void *stream) {
  ((string*)stream)->append((char*)ptr, 0, size*count);
  return size*count;
}

WebserviceConnector::WebserviceConnector(string pRootPath, string pPassphrase) {
	rootPath = pRootPath;
	passphrase = pPassphrase;

	curl = curl_easy_init();
	if (curl == 0) {
		throw "Could not initialize curl";
	}
}

boost::property_tree::ptree WebserviceConnector::postLogin(string rfidTag) {
	stringstream url;
	string response;
	long httpCode = 0;

	url << rootPath << "rest/box/login?rfid=" << rfidTag << "&passphrase=" << passphrase << endl;
	cout << url.str() << endl;
	curl_easy_setopt(curl, CURLOPT_URL, url.str().c_str());
	curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, write_to_string);
	curl_easy_setopt(curl, CURLOPT_WRITEDATA, &response);
	curl_easy_getinfo(curl, CURLINFO_RESPONSE_CODE, &httpCode);

	CURLcode res = curl_easy_perform(curl);
	if(res != CURLE_OK) {
		throw "Could not post login";
	}

	cout << "got " << response << endl;
	try {
		stringstream ss;
		ss << response;
		boost::property_tree::ptree pt;
		boost::property_tree::read_json(ss, pt);
		return pt; // login succeeded
	} catch (...) {
		// login failed
		throw "Forbidden";
	}

}
boost::property_tree::ptree WebserviceConnector::postTicks(int numTicks) {

	stringstream url;
	string response;
	long httpCode = 0;

	url << rootPath << "rest/box/draw?ticks=" << numTicks << "&passphrase=" << passphrase << endl;
	cout << url.str() << endl;
	curl_easy_setopt(curl, CURLOPT_URL, url.str().c_str());
	curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, write_to_string);
	curl_easy_setopt(curl, CURLOPT_WRITEDATA, &response);
	curl_easy_getinfo(curl, CURLINFO_RESPONSE_CODE, &httpCode);

	CURLcode res = curl_easy_perform(curl);
	if(res != CURLE_OK) {
		throw "Could not post login";
	}

	cout << "got " << response << endl;
	try {
		stringstream ss;
		ss << response;
		boost::property_tree::ptree pt;
		boost::property_tree::read_json(ss, pt);
		return pt; // login succeeded
	} catch (...) {
		// login failed
		throw "Forbidden";
	}

}

