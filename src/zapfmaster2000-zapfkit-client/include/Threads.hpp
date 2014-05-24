/*
 * Threads.hpp
 *
 *  Created on: May 11, 2014
 *      Author: thomas
 */

#ifndef THREADS_HPP_
#define THREADS_HPP_

#include <boost/thread.hpp>
#include <boost/thread/mutex.hpp>

class Threads {
public:

	boost::thread* controllerThread;
	boost::thread* timerThread;

	static Threads& getInstance() {
		boost::mutex::scoped_lock lock(mutex);
		static Threads instance;
		return instance;
	}

private:
	static boost::mutex mutex;

	Threads() :
			controllerThread(0) {
	}
	~Threads() {
	}

	Threads(const Threads&);
	const Threads& operator=(const Threads&);
};

#endif /* THREADS_HPP_ */
