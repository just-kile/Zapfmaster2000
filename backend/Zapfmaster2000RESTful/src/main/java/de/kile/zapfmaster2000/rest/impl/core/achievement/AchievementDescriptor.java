package de.kile.zapfmaster2000.rest.impl.core.achievement;

import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.AbstractAchievementProcessor;

public class AchievementDescriptor {

	private String name;

	private String description;

	private String imagePath;

	private Class<? extends AbstractAchievementProcessor> processor;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Class<? extends AbstractAchievementProcessor> getProcessor() {
		return processor;
	}

	public void setProcessor(
			Class<? extends AbstractAchievementProcessor> pProcessor) {
		this.processor = pProcessor;
	}

}
