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

	std::string absPath = string("/etc/share/zapfmaster2000-zapfkit-client/") + path;
	if (ifstream(path.c_str())) {
		return absPath;
	}

	return path;
}

