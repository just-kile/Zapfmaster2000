/**
 */
package de.kile.zapfmaster2000.rest.model.zapfmaster2000;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Achievement News</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.AchievementNews#getGainedAchievment <em>Gained Achievment</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package#getAchievementNews()
 * @model
 * @generated
 */
public interface AchievementNews extends News {
	/**
	 * Returns the value of the '<em><b>Gained Achievment</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Gained Achievment</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Gained Achievment</em>' reference.
	 * @see #setGainedAchievment(GainedAchievement)
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package#getAchievementNews_GainedAchievment()
	 * @model
	 * @generated
	 */
	GainedAchievement getGainedAchievment();

	/**
	 * Sets the value of the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.AchievementNews#getGainedAchievment <em>Gained Achievment</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Gained Achievment</em>' reference.
	 * @see #getGainedAchievment()
	 * @generated
	 */
	void setGainedAchievment(GainedAchievement value);

} // AchievementNews
