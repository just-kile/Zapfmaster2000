/**
 */
package de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl;

import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package;

import java.util.Date;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Challenge1v1</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Challenge1v1Impl#getId <em>Id</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Challenge1v1Impl#getChallenge <em>Challenge</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Challenge1v1Impl#getChallengeEnd <em>Challenge End</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class Challenge1v1Impl extends EObjectImpl implements Challenge1v1 {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final long ID_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected long id = ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getChallenge() <em>Challenge</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChallenge()
	 * @generated
	 * @ordered
	 */
	protected Challenge challenge;

	/**
	 * The default value of the '{@link #getChallengeEnd() <em>Challenge End</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChallengeEnd()
	 * @generated
	 * @ordered
	 */
	protected static final Date CHALLENGE_END_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getChallengeEnd() <em>Challenge End</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChallengeEnd()
	 * @generated
	 * @ordered
	 */
	protected Date challengeEnd = CHALLENGE_END_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Challenge1v1Impl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Zapfmaster2000Package.Literals.CHALLENGE1V1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(long newId) {
		long oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.CHALLENGE1V1__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Challenge getChallenge() {
		if (challenge != null && challenge.eIsProxy()) {
			InternalEObject oldChallenge = (InternalEObject)challenge;
			challenge = (Challenge)eResolveProxy(oldChallenge);
			if (challenge != oldChallenge) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Zapfmaster2000Package.CHALLENGE1V1__CHALLENGE, oldChallenge, challenge));
			}
		}
		return challenge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Challenge basicGetChallenge() {
		return challenge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChallenge(Challenge newChallenge) {
		Challenge oldChallenge = challenge;
		challenge = newChallenge;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.CHALLENGE1V1__CHALLENGE, oldChallenge, challenge));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getChallengeEnd() {
		return challengeEnd;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChallengeEnd(Date newChallengeEnd) {
		Date oldChallengeEnd = challengeEnd;
		challengeEnd = newChallengeEnd;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.CHALLENGE1V1__CHALLENGE_END, oldChallengeEnd, challengeEnd));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Zapfmaster2000Package.CHALLENGE1V1__ID:
				return getId();
			case Zapfmaster2000Package.CHALLENGE1V1__CHALLENGE:
				if (resolve) return getChallenge();
				return basicGetChallenge();
			case Zapfmaster2000Package.CHALLENGE1V1__CHALLENGE_END:
				return getChallengeEnd();
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
			case Zapfmaster2000Package.CHALLENGE1V1__ID:
				setId((Long)newValue);
				return;
			case Zapfmaster2000Package.CHALLENGE1V1__CHALLENGE:
				setChallenge((Challenge)newValue);
				return;
			case Zapfmaster2000Package.CHALLENGE1V1__CHALLENGE_END:
				setChallengeEnd((Date)newValue);
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
			case Zapfmaster2000Package.CHALLENGE1V1__ID:
				setId(ID_EDEFAULT);
				return;
			case Zapfmaster2000Package.CHALLENGE1V1__CHALLENGE:
				setChallenge((Challenge)null);
				return;
			case Zapfmaster2000Package.CHALLENGE1V1__CHALLENGE_END:
				setChallengeEnd(CHALLENGE_END_EDEFAULT);
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
			case Zapfmaster2000Package.CHALLENGE1V1__ID:
				return id != ID_EDEFAULT;
			case Zapfmaster2000Package.CHALLENGE1V1__CHALLENGE:
				return challenge != null;
			case Zapfmaster2000Package.CHALLENGE1V1__CHALLENGE_END:
				return CHALLENGE_END_EDEFAULT == null ? challengeEnd != null : !CHALLENGE_END_EDEFAULT.equals(challengeEnd);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (id: ");
		result.append(id);
		result.append(", challengeEnd: ");
		result.append(challengeEnd);
		result.append(')');
		return result.toString();
	}

} //Challenge1v1Impl
