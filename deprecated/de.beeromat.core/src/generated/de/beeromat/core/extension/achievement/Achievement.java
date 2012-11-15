package de.beeromat.core.extension.achievement;
import de.beeromat.core.achievemnt.AbstractAchievementProcessor;
public class Achievement {
private String fName;
private String fDescription;
private String fImagepath;
private String fPublic;
private String fType;
private AbstractAchievementProcessor fProcessor;
public String getName() {
return fName;
}
public void setName(String pName) {
fName = pName;
}
public String getDescription() {
return fDescription;
}
public void setDescription(String pDescription) {
fDescription = pDescription;
}
public String getImagepath() {
return fImagepath;
}
public void setImagepath(String pImagepath) {
fImagepath = pImagepath;
}
public String getPublic() {
return fPublic;
}
public void setPublic(String pPublic) {
fPublic = pPublic;
}
public String getType() {
return fType;
}
public void setType(String pType) {
fType = pType;
}
public AbstractAchievementProcessor getProcessor() {
return fProcessor;
}
public void setProcessor(AbstractAchievementProcessor pProcessor) {
fProcessor = pProcessor;
}
}
