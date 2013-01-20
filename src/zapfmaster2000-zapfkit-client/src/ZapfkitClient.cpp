/*
 * ZapfkitClient.cpp
 *
 *  Created on: Jan 20, 2013
 *      Author: Thomas Kipar
 */

#include <iostream>
#include "../include/ZapfDisplay.hpp"
#include <SDL/SDL.h>

using namespace zm2k;
using namespace std;

int main() {

	try {
		ZapfDisplay display;
		display.run();
	} catch (const char* exception) {
		cerr << "Caught error: " << exception << endl;
	}

}

