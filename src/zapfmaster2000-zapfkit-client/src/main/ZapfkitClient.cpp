/*
 * ZapfkitClient.cpp
 *
 *  Created on: May 11, 2014
 *      Author: thomas
 */

#include <iostream>
#include "../../include/Threads.hpp"
#include "../../include/statemachine/TimerManager.hpp"
#include "../../include/statemachine/StateMachine.hpp"
#include "../../include/statemachine/StateSplashScreen.hpp"
#include "../../include/statemachine/StateConfiguration.hpp"

StateMachine* stateMachine;
TimerManager* timerManager;


void runController() {
	std::cout << "Running Controller Thread." << std::endl;
	stateMachine->run();
}

void runTimerManager() {
	std::cout << "Running Timer Thread." << std::endl;
	timerManager->run();
}

StateMachine* createStateMachine() {
	State* stateConfiguration = new StateConfiguration();
	State* stateSplashScreen = new StateSplashScreen(stateConfiguration);
	StateMachine* sm = new StateMachine(*stateSplashScreen);
	return sm;
}

TimerManager* createTimerManager() {
	TimerManager* timerManager = new TimerManager(*stateMachine);
	return timerManager;
}

void runThreads() {
	boost::thread controllerThread = boost::thread(runController);
	boost::thread timerThread = boost::thread(runTimerManager);

	Threads::getInstance().controllerThread = &controllerThread;
	Threads::getInstance().timerThread = &timerThread;

	controllerThread.join();
	timerThread.join();
}

void instantiateBusinessLogic() {
	stateMachine = createStateMachine();
	timerManager = createTimerManager();
	stateMachine->init(timerManager);
}

int main() {
	instantiateBusinessLogic();
	runThreads();
	return 0;
}



