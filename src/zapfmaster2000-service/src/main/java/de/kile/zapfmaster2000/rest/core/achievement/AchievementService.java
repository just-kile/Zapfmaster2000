package de.kile.zapfmaster2000.rest.core.achievement;

/**
 * The achievement service checks and grants achievements for all user
 * activities.
 * 
 * @author Thomas Kipar
 */
public interface AchievementService {
	
	public void addListener(AchievementServiceListener pListener);
	
	public void removeListener(AchievementServiceListener pListener);

}
