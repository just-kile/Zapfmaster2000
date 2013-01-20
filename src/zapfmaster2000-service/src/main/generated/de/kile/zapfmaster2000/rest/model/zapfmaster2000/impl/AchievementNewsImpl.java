/**
 */
package de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl;

import de.kile.zapfmaster2000.rest.model.zapfmaster2000.AchievementNews;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.GainedAchievement;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Achievement News</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.AchievementNewsImpl#getGainedAchievment <em>Gained Achievment</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AchievementNewsImpl extends NewsImpl implements AchievementNews {
	/**
	 * The cached value of the '{@link #getGainedAchievment() <em>Gained Achievment</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGainedAchievment()
	 * @generated
	 * @ordered
	 */
	protected GainedAchievement gainedAchievment;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AchievementNewsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Zapfmaster2000Package.Literals.ACHIEVEMENT_NEWS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GainedAchievement getGainedAchievment() {
		if (gainedAchievment != null && gainedAchievment.eIsProxy()) {
			InternalEObject oldGainedAchievment = (InternalEObject)gainedAchievment;
			gainedAchievment = (GainedAchievement)eResolveProxy(oldGainedAchievment);
			if (gainedAchievment != oldGainedAchievment) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Zapfmaster2000Package.ACHIEVEMENT_NEWS__GAINED_ACHIEVMENT, oldGainedAchievment, gainedAchievment));
			}
		}
		return gainedAchievment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GainedAchievement basicGetGainedAchievment() {
		return gainedAchievment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGainedAchievment(GainedAchievement newGainedAchievment) {
		GainedAchievement oldGainedAchievment = gainedAchievment;
		gainedAchievment = newGainedAchievment;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.ACHIEVEMENT_NEWS__GAINED_ACHIEVMENT, oldGainedAchievment, gainedAchievment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Zapfmaster2000Package.ACHIEVEMENT_NEWS__GAINED_ACHIEVMENT:
				if (resolve) return getGainedAchievment();
				return basicGetGainedAchievment();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case Zapfmaster2000Package.ACHIEVEMENT_NEWS__GAINED_ACHIEVMENT:
				setGainedAchievment((GainedAchievement)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case Zapfmaster2000Package.ACHIEVEMENT_NEWS__GAINED_ACHIEVMENT:
				setGainedAchievment((GainedAchievement)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case Zapfmaster2000Package.ACHIEVEMENT_NEWS__GAINED_ACHIEVMENT:
				return gainedAchievment != null;
		}
		return super.eIsSet(featureID);
	}

} //AchievementNewsImpl
