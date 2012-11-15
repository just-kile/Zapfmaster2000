package de.beeromat.core.extension.config;
import de.beeromat.core.extension.config.Config;
public class ConfigExtension {
private String fPoint;
private String fId;
private String fName;
private Config[] fConfigs;
public String getPoint() {
return fPoint;
}
public void setPoint(String pPoint) {
fPoint = pPoint;
}
public String getId() {
return fId;
}
public void setId(String pId) {
fId = pId;
}
public String getName() {
return fName;
}
public void setName(String pName) {
fName = pName;
}
public Config[] getConfigs() {
return fConfigs;
}
public void setConfigs(Config[] pConfigs) {
fConfigs = pConfigs;
}
}
