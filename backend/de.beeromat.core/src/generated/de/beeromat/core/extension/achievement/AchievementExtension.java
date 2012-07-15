package de.beeromat.core.extension.achievement;
import de.beeromat.core.extension.achievement.Achievement;
public class AchievementExtension {
private String fPoint;
private String fId;
private String fName;
private Achievement[] fAchievements;
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
public Achievement[] getAchievements() {
return fAchievements;
}
public void setAchievements(Achievement[] pAchievements) {
fAchievements = pAchievements;
}
}
