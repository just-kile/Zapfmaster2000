package de.justkile.zapfmaster2000.services

import de.justkile.zapfmaster2000.model.ZapfKit
import de.justkile.zapfmaster2000.utils.BlockingDataDelegator
import de.justkile.zapfmaster2000.utils.zapfing.ZapfService
import grails.transaction.Transactional

class ZapfKitService {

    private final def mapZapfKitId2RfidDelegator = [:]
    private final def mapZapfKitId2ZapfService = [:]
    private final List<ZapfKitServiceListener> listeners = []

    /**
     * Returns the next rfid tag that is read by a given ZapfKit.
     *
     * <p>
     *     This is a blocking call.
     * </p>
     *
     * @param the zapfKit to get the data for
     * @return the next rfid tag read by teh given zapfKit.
     */
    long retrieveNextRfidTag(ZapfKit zapfKit) {
        if (!mapZapfKitId2RfidDelegator[zapfKit.id]) {
            mapZapfKitId2RfidDelegator[zapfKit.id] = new BlockingDataDelegator()
        }
        return mapZapfKitId2RfidDelegator[zapfKit.id].waitForData()
    }

    def processRfidTag(ZapfKit zapfKit, long rfidTag) {
        if (mapZapfKitId2RfidDelegator[zapfKit.id]) {
            mapZapfKitId2RfidDelegator[zapfKit.id].pushData(rfidTag)
        }
    }

    ZapfService retrieveZapfService(ZapfKit zapfKit) {
        if (!mapZapfKitId2ZapfService[zapfKit.id]) {
            mapZapfKitId2ZapfService[zapfKit.id] = new ZapfService(zapfKit)
        }
        mapZapfKitId2ZapfService[zapfKit.id]
    }

    @Transactional
    def createZapfKit(ZapfKit zapfKit) {
        println 'creating kit'
        zapfKit.save()
        println 'created kit'
        listeners.each {
            it.onZapfKitCreated zapfKit
        }
    }

    def addListener(ZapfKitServiceListener listener) {
        if (listener) {
            listeners.add(listener)
        }
    }

}
