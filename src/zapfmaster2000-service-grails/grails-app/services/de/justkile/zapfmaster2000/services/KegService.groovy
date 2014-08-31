package de.justkile.zapfmaster2000.services

import de.justkile.zapfmaster2000.model.Keg
import de.justkile.zapfmaster2000.model.ZapfKit
import grails.transaction.Transactional
import groovy.beans.ListenerList

/**
 * Created by thomas on 31.08.14.
 */
class KegService {

    @ListenerList
    private final List<KegServiceListener> listeners

    @Transactional
    def switchKeg(ZapfKit zapfKit, String brand, int size) {
        installEndDate(zapfKit)
        def keg = new Keg(
                zapfKit: zapfKit,
                brand: brand,
                size: size
        )
        keg.save()
        fireOnNewKeg(keg)
    }

    private void installEndDate(ZapfKit zapfKit) {
        def activeKeg = Keg.createCriteria().get {
            eq('zapfKit', zapfKit)
            order('startDate', 'desc')
            maxResults(1)
        }
        if (activeKeg) {
            activeKeg.endDate = new Date()
            activeKeg.save()
        }
    }
}
