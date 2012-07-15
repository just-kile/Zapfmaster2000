package de.beeromat.core.extension.config;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.CoreException;
import de.beeromat.core.extension.config.ConfigExtension;
import de.beeromat.core.extension.config.Config;
public class ConfigExtensionResolver {
public ConfigExtension[] resolve() throws CoreException {
IExtensionRegistry registry = Platform.getExtensionRegistry();
IExtensionPoint point = registry.getExtensionPoint("de.beeromat.core.config");
List<ConfigExtension> extensions = new ArrayList<ConfigExtension>();
if (point != null) {
for (IExtension extension : point.getExtensions()) {
ConfigExtension extensionModel = new ConfigExtension();
extensions.add(extensionModel);
List<Config> listConfig0 = new ArrayList<Config>();
for (IConfigurationElement configElem0: extension.getConfigurationElements()) {
if ("Config".equals(configElem0.getName())) {
Config model0 = new Config();
listConfig0.add(model0);
model0.setName(configElem0.getAttribute("Name"));
model0.setDefault(configElem0.getAttribute("Default"));
}
}
extensionModel.setConfigs(listConfig0.toArray(new Config[listConfig0.size()]));
}
}
return extensions.toArray(new ConfigExtension[extensions.size()]);
}
}
