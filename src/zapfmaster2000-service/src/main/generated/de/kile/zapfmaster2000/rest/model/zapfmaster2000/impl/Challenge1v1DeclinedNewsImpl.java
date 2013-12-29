/**
 */
package de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1DeclinedNews;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Challenge1v1 Declined News</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Challenge1v1DeclinedNewsImpl#getChallenge <em>Challenge</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class Challenge1v1DeclinedNewsImpl extends NewsImpl implements Challenge1v1DeclinedNews {
	/**
	 * The cached value of the '{@link #getChallenge() <em>Challenge</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChallenge()
	 * @generated
	 * @ordered
	 */
	protected Challenge1v1 challenge;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Challenge1v1DeclinedNewsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Zapfmaster2000Package.Literals.CHALLENGE1V1_DECLINED_NEWS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Challenge1v1 getChallenge() {
		if (challenge != null && challenge.eIsProxy()) {
			InternalEObject oldChallenge = (InternalEObject)challenge;
			challenge = (Challenge1v1)eResolveProxy(oldChallenge);
			if (challenge != oldChallenge) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Zapfmaster2000Package.CHALLENGE1V1_DECLINED_NEWS__CHALLENGE, oldChallenge, challenge));
			}
		}
		return challenge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Challenge1v1 basicGetChallenge() {
		return challenge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChallenge(Challenge1v1 newChallenge) {
		Challenge1v1 oldChallenge = challenge;
		challenge = newChallenge;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.CHALLENGE1V1_DECLINED_NEWS__CHALLENGE, oldChallenge, challenge));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Zapfmaster2000Package.CHALLENGE1V1_DECLINED_NEWS__CHALLENGE:
				if (resolve) return getChallenge();
				return basicGetChallenge();
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
			case Zapfmaster2000Package.CHALLENGE1V1_DECLINED_NEWS__CHALLENGE:
				setChallenge((Challenge1v1)newValue);
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
			case Zapfmaster2000Package.CHALLENGE1V1_DECLINED_NEWS__CHALLENGE:
				setChallenge((Challenge1v1)null);
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
			case Zapfmaster2000Package.CHALLENGE1V1_DECLINED_NEWS__CHALLENGE:
				return challenge != null;
		}
		return super.eIsSet(featureID);
	}

} //Challenge1v1DeclinedNewsImpl
