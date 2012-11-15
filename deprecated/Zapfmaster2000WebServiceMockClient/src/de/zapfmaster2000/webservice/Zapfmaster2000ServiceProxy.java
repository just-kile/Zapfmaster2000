package de.zapfmaster2000.webservice;

public class Zapfmaster2000ServiceProxy implements de.zapfmaster2000.webservice.Zapfmaster2000Service {
  private String _endpoint = null;
  private de.zapfmaster2000.webservice.Zapfmaster2000Service zapfmaster2000Service = null;
  
  public Zapfmaster2000ServiceProxy() {
    _initZapfmaster2000ServiceProxy();
  }
  
  public Zapfmaster2000ServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initZapfmaster2000ServiceProxy();
  }
  
  private void _initZapfmaster2000ServiceProxy() {
    try {
      zapfmaster2000Service = (new de.zapfmaster2000.webservice.Zapfmaster2000ServiceServiceLocator()).getZapfmaster2000Service();
      if (zapfmaster2000Service != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)zapfmaster2000Service)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)zapfmaster2000Service)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (zapfmaster2000Service != null)
      ((javax.xml.rpc.Stub)zapfmaster2000Service)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public de.zapfmaster2000.webservice.Zapfmaster2000Service getZapfmaster2000Service() {
    if (zapfmaster2000Service == null)
      _initZapfmaster2000ServiceProxy();
    return zapfmaster2000Service;
  }
  
  public void login(long pId) throws java.rmi.RemoteException{
    if (zapfmaster2000Service == null)
      _initZapfmaster2000ServiceProxy();
    zapfmaster2000Service.login(pId);
  }
  
  public void draw(int pNumTicks) throws java.rmi.RemoteException{
    if (zapfmaster2000Service == null)
      _initZapfmaster2000ServiceProxy();
    zapfmaster2000Service.draw(pNumTicks);
  }
  
  
}