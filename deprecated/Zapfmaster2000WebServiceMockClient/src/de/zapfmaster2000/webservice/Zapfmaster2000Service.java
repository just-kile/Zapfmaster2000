/**
 * Zapfmaster2000Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.zapfmaster2000.webservice;

public interface Zapfmaster2000Service extends java.rmi.Remote {
    public void login(long pId) throws java.rmi.RemoteException;
    public void draw(int pNumTicks) throws java.rmi.RemoteException;
}
