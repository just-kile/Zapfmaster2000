/**
 * Zapfmaster2000ServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.zapfmaster2000.webservice;

public class Zapfmaster2000ServiceServiceLocator extends org.apache.axis.client.Service implements de.zapfmaster2000.webservice.Zapfmaster2000ServiceService {

    public Zapfmaster2000ServiceServiceLocator() {
    }


    public Zapfmaster2000ServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public Zapfmaster2000ServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for Zapfmaster2000Service
    private java.lang.String Zapfmaster2000Service_address = "http://localhost:8080/Zapfmaster2000WebService/services/Zapfmaster2000Service";

    public java.lang.String getZapfmaster2000ServiceAddress() {
        return Zapfmaster2000Service_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String Zapfmaster2000ServiceWSDDServiceName = "Zapfmaster2000Service";

    public java.lang.String getZapfmaster2000ServiceWSDDServiceName() {
        return Zapfmaster2000ServiceWSDDServiceName;
    }

    public void setZapfmaster2000ServiceWSDDServiceName(java.lang.String name) {
        Zapfmaster2000ServiceWSDDServiceName = name;
    }

    public de.zapfmaster2000.webservice.Zapfmaster2000Service getZapfmaster2000Service() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(Zapfmaster2000Service_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getZapfmaster2000Service(endpoint);
    }

    public de.zapfmaster2000.webservice.Zapfmaster2000Service getZapfmaster2000Service(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            de.zapfmaster2000.webservice.Zapfmaster2000ServiceSoapBindingStub _stub = new de.zapfmaster2000.webservice.Zapfmaster2000ServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getZapfmaster2000ServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setZapfmaster2000ServiceEndpointAddress(java.lang.String address) {
        Zapfmaster2000Service_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (de.zapfmaster2000.webservice.Zapfmaster2000Service.class.isAssignableFrom(serviceEndpointInterface)) {
                de.zapfmaster2000.webservice.Zapfmaster2000ServiceSoapBindingStub _stub = new de.zapfmaster2000.webservice.Zapfmaster2000ServiceSoapBindingStub(new java.net.URL(Zapfmaster2000Service_address), this);
                _stub.setPortName(getZapfmaster2000ServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("Zapfmaster2000Service".equals(inputPortName)) {
            return getZapfmaster2000Service();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://webservice.zapfmaster2000.de", "Zapfmaster2000ServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://webservice.zapfmaster2000.de", "Zapfmaster2000Service"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("Zapfmaster2000Service".equals(portName)) {
            setZapfmaster2000ServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
