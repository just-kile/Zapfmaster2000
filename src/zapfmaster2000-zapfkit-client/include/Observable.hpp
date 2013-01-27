/*
 * Obervable.hpp
 *
 *  Created on: Jan 27, 2013
 *      Author: thomas
 */

#ifndef OBERVABLE_HPP_
#define OBERVABLE_HPP_

#include <algorithm>
#include <vector>
#include <boost/function.hpp>
#include <boost/bind.hpp>

namespace zm2k {

template<class T>
class Observable {

private:

	std::vector<T*> listeners;

public:

	typedef boost::function<void(T*)> notification;

	void addListener(T* listener) {
		if (listener != 0) {
			listeners.push_back(listener);
		}
	}

	void removeListener(T* listener) {
		typename std::vector<T*>::iterator it = std::find(listeners.begin(),
				listeners.end(), listener);
		if (it != listeners.end()) {
			listeners.erease(it);
		}
	}

protected:

	const void notifyListeners(notification function) {
		std::for_each(listeners.begin(), listeners.end(),
				boost::bind(function, _1));
	}

};

}

#endif /* OBERVABLE_HPP_ */
