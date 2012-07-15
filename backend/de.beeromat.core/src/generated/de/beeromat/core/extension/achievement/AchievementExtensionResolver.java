package de.beeromat.core.extension.achievement;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.CoreException;
import de.beeromat.core.extension.achievement.AchievementExtension;
import de.beeromat.core.extension.achievement.Achievement;
import de.beeromat.core.achievemnt.AbstractAchievementProcessor;
public class AchievementExtensionResolver {
public AchievementExtension[] resolve() throws CoreException {
IExtensionRegistry registry = Platform.getExtensionRegistry();
IExtensionPoint point = registry.getExtensionPoint("de.beeromat.core.achievement");
List<AchievementExtension> extensions = new ArrayList<AchievementExtension>();
if (point != null) {
for (IExtension extension : point.getExtensions()) {
AchievementExtension extensionModel = new AchievementExtension();
extensions.add(extensionModel);
List<Achievement> listAchievement0 = new ArrayList<Achievement>();
for (IConfigurationElement configElem0: extension.getConfigurationElements()) {
if ("achievement".equals(configElem0.getName())) {
Achievement model0 = new Achievement();
listAchievement0.add(model0);
model0.setName(configElem0.getAttribute("name"));
model0.setDescription(configElem0.getAttribute("description"));
model0.setImagepath(configElem0.getAttribute("imagePath"));
model0.setPublic(configElem0.getAttribute("public"));
model0.setType(configElem0.getAttribute("type"));
model0.setProcessor((AbstractAchievementProcessor) configElem0.createExecutableExtension("processor"));
}
}
extensionModel.setAchievements(listAchievement0.toArray(new Achievement[listAchievement0.size()]));
}
}
return extensions.toArray(new AchievementExtension[extensions.size()]);
}
}
