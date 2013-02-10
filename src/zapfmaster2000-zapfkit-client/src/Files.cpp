/*
 * Files.cpp
 *
 *  Created on: Feb 10, 2013
 *      Author: thomas
 */

#include <fstream>
#include "../include/Files.hpp"

using namespace std;

string zm2k::toAbsPath(string path) {

	std::string absPath = string("/usr/share/zapfmaster2000-zapfkit-client/") + path;
	if (ifstream(absPath.c_str())) {
		return absPath;
	}

	return path;
}

