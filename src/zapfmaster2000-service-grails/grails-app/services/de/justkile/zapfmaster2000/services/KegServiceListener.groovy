package de.justkile.zapfmaster2000.services

import de.justkile.zapfmaster2000.model.Keg

/**
 * Created by thomas on 31.08.14.
 */
public interface KegServiceListener {

    def onNewKeg(Keg keg)

}