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
#include <log4cpp/Category.hh>
#include <log4cpp/PropertyConfigurator.hh>

#include "../include/ZapfDisplay.hpp"
#include "../include/serial/InputService.hpp"
#include "../include/ZapfController.hpp"
#include "../include/Files.hpp"

using namespace zm2k;
using namespace std;

log4cpp::Category* logger;
AbstractInputService* service = 0;
ZapfDisplay display;
ZapfController* controller;

void inputThread() {
	try {
		logger->info("running input service");
		service->run();
	} catch (const char* exception) {
		logger->fatal("Caught error: %s", exception);
		logger->fatal("SDL says: %d", SDL_GetError());
		logger->fatal("errno: %d", strerror(errno));
		exit(-1);
	}
}

void displayThread() {
	try {
		controller->run();
	} catch (const char* exception) {
		logger->fatal("Caught error: %s", exception);
		logger->fatal("SDL says: %d", SDL_GetError());
		logger->fatal("errno: %d", strerror(errno));
		exit(-1);
	}
}

// Exit properly on signal
void sighandle(int sig) {
	SDL_Quit();
	exit(-1);
}

int main(int argc, const char* argv[]) {

	// load properties
	boost::property_tree::ptree ptree;
	boost::property_tree::read_xml(toAbsPath("resources/config.xml"), ptree);

	log4cpp::PropertyConfigurator::configure(
			toAbsPath("resources/log4cpp.properties"));

	logger = &log4cpp::Category::getRoot();
	logger->info("Zapfmaster 2000 Zapfkit Client is starting up");

	WebserviceConnector webservieConnector(
			ptree.get<string>("config.webservice.path"),
			ptree.get<string>("config.webservice.passphrase"));

	try {
		if (argc > 1) {
			string mockArg = "mock";
			if (mockArg.compare(argv[1]) == 0) {
				logger->info("creating mock service");
				service = new MockInputService();
			}
		}

		if (!service) {
			logger->info("created input service");
			service = new InputService();
		}

		controller = new ZapfController(display, *service, &webservieConnector);
		logger->info("running thread");
		boost::thread t1(inputThread);
		boost::thread t2(displayThread);
		controller->setThread(&t2);

		signal(SIGINT, &sighandle);

		logger->info("Zapfkit client is up an running.");
		t1.join();
		t2.join();
	} catch (const char* exception) {
		logger->fatal("Caught error: %s", exception);
		logger->fatal("SDL says: %d", SDL_GetError());
		logger->fatal("errno: %d", strerror(errno));
		exit(-1);
	}

}

