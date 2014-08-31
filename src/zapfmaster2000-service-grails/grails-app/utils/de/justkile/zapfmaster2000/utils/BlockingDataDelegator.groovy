package de.justkile.zapfmaster2000.utils
/**
 * Created by thomas on 28.08.14.
 */
class BlockingDataDelegator {

    def waitingThreads = []
    def data

    def waitForData() {
        def object = new Object() //Thread.currentThread();
        synchronized (waitingThreads) {
            waitingThreads.add(object)
        }
        synchronized (object) {
            object.wait();
        }
        return data
    }

    def pushData(data) {
        this.data = data
        synchronized (waitingThreads) {
            waitingThreads.each {
                synchronized (it) {
                    it.notify()
                }
            }
            waitingThreads.clear()
        }
    }

}
