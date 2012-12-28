/**
 */
package de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl;

import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package;

import java.util.Date;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Challenge1v1</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Challenge1v1Impl#getUser1 <em>User1</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Challenge1v1Impl#getUser2 <em>User2</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Challenge1v1Impl#getWinner <em>Winner</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Challenge1v1Impl#getDuration <em>Duration</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class Challenge1v1Impl extends ChallengeImpl implements Challenge1v1 {
	/**
	 * The cached value of the '{@link #getUser1() <em>User1</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUser1()
	 * @generated
	 * @ordered
	 */
	protected User user1;

	/**
	 * The cached value of the '{@link #getUser2() <em>User2</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUser2()
	 * @generated
	 * @ordered
	 */
	protected User user2;

	/**
	 * The cached value of the '{@link #getWinner() <em>Winner</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWinner()
	 * @generated
	 * @ordered
	 */
	protected User winner;

	/**
	 * The default value of the '{@link #getDuration() <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDuration()
	 * @generated
	 * @ordered
	 */
	protected static final int DURATION_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getDuration() <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDuration()
	 * @generated
	 * @ordered
	 */
	protected int duration = DURATION_EDEFAULT;

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
	public User getUser1() {
		if (user1 != null && user1.eIsProxy()) {
			InternalEObject oldUser1 = (InternalEObject)user1;
			user1 = (User)eResolveProxy(oldUser1);
			if (user1 != oldUser1) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Zapfmaster2000Package.CHALLENGE1V1__USER1, oldUser1, user1));
			}
		}
		return user1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public User basicGetUser1() {
		return user1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUser1(User newUser1) {
		User oldUser1 = user1;
		user1 = newUser1;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.CHALLENGE1V1__USER1, oldUser1, user1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public User getUser2() {
		if (user2 != null && user2.eIsProxy()) {
			InternalEObject oldUser2 = (InternalEObject)user2;
			user2 = (User)eResolveProxy(oldUser2);
			if (user2 != oldUser2) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Zapfmaster2000Package.CHALLENGE1V1__USER2, oldUser2, user2));
			}
		}
		return user2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public User basicGetUser2() {
		return user2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUser2(User newUser2) {
		User oldUser2 = user2;
		user2 = newUser2;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.CHALLENGE1V1__USER2, oldUser2, user2));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public User getWinner() {
		if (winner != null && winner.eIsProxy()) {
			InternalEObject oldWinner = (InternalEObject)winner;
			winner = (User)eResolveProxy(oldWinner);
			if (winner != oldWinner) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Zapfmaster2000Package.CHALLENGE1V1__WINNER, oldWinner, winner));
			}
		}
		return winner;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public User basicGetWinner() {
		return winner;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWinner(User newWinner) {
		User oldWinner = winner;
		winner = newWinner;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.CHALLENGE1V1__WINNER, oldWinner, winner));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDuration(int newDuration) {
		int oldDuration = duration;
		duration = newDuration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.CHALLENGE1V1__DURATION, oldDuration, duration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Zapfmaster2000Package.CHALLENGE1V1__USER1:
				if (resolve) return getUser1();
				return basicGetUser1();
			case Zapfmaster2000Package.CHALLENGE1V1__USER2:
				if (resolve) return getUser2();
				return basicGetUser2();
			case Zapfmaster2000Package.CHALLENGE1V1__WINNER:
				if (resolve) return getWinner();
				return basicGetWinner();
			case Zapfmaster2000Package.CHALLENGE1V1__DURATION:
				return getDuration();
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
			case Zapfmaster2000Package.CHALLENGE1V1__USER1:
				setUser1((User)newValue);
				return;
			case Zapfmaster2000Package.CHALLENGE1V1__USER2:
				setUser2((User)newValue);
				return;
			case Zapfmaster2000Package.CHALLENGE1V1__WINNER:
				setWinner((User)newValue);
				return;
			case Zapfmaster2000Package.CHALLENGE1V1__DURATION:
				setDuration((Integer)newValue);
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
			case Zapfmaster2000Package.CHALLENGE1V1__USER1:
				setUser1((User)null);
				return;
			case Zapfmaster2000Package.CHALLENGE1V1__USER2:
				setUser2((User)null);
				return;
			case Zapfmaster2000Package.CHALLENGE1V1__WINNER:
				setWinner((User)null);
				return;
			case Zapfmaster2000Package.CHALLENGE1V1__DURATION:
				setDuration(DURATION_EDEFAULT);
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
			case Zapfmaster2000Package.CHALLENGE1V1__USER1:
				return user1 != null;
			case Zapfmaster2000Package.CHALLENGE1V1__USER2:
				return user2 != null;
			case Zapfmaster2000Package.CHALLENGE1V1__WINNER:
				return winner != null;
			case Zapfmaster2000Package.CHALLENGE1V1__DURATION:
				return duration != DURATION_EDEFAULT;
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
		result.append(" (duration: ");
		result.append(duration);
		result.append(')');
		return result.toString();
	}

} //Challenge1v1Impl
