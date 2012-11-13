/**
 */
package de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl;

import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeParticipant;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Challenge Participant</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.ChallengeParticipantImpl#getChallenge <em>Challenge</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.ChallengeParticipantImpl#getTeam <em>Team</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.ChallengeParticipantImpl#isWon <em>Won</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.ChallengeParticipantImpl#getUser <em>User</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ChallengeParticipantImpl extends EObjectImpl implements ChallengeParticipant {
	/**
	 * The default value of the '{@link #getTeam() <em>Team</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTeam()
	 * @generated
	 * @ordered
	 */
	protected static final int TEAM_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getTeam() <em>Team</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTeam()
	 * @generated
	 * @ordered
	 */
	protected int team = TEAM_EDEFAULT;

	/**
	 * The default value of the '{@link #isWon() <em>Won</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isWon()
	 * @generated
	 * @ordered
	 */
	protected static final boolean WON_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isWon() <em>Won</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isWon()
	 * @generated
	 * @ordered
	 */
	protected boolean won = WON_EDEFAULT;

	/**
	 * The cached value of the '{@link #getUser() <em>User</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUser()
	 * @generated
	 * @ordered
	 */
	protected EList<User> user;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ChallengeParticipantImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Zapfmaster2000Package.Literals.CHALLENGE_PARTICIPANT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Challenge getChallenge() {
		if (eContainerFeatureID() != Zapfmaster2000Package.CHALLENGE_PARTICIPANT__CHALLENGE) return null;
		return (Challenge)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetChallenge(Challenge newChallenge, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newChallenge, Zapfmaster2000Package.CHALLENGE_PARTICIPANT__CHALLENGE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChallenge(Challenge newChallenge) {
		if (newChallenge != eInternalContainer() || (eContainerFeatureID() != Zapfmaster2000Package.CHALLENGE_PARTICIPANT__CHALLENGE && newChallenge != null)) {
			if (EcoreUtil.isAncestor(this, newChallenge))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newChallenge != null)
				msgs = ((InternalEObject)newChallenge).eInverseAdd(this, Zapfmaster2000Package.CHALLENGE__PARTICIPANTS, Challenge.class, msgs);
			msgs = basicSetChallenge(newChallenge, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.CHALLENGE_PARTICIPANT__CHALLENGE, newChallenge, newChallenge));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getTeam() {
		return team;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTeam(int newTeam) {
		int oldTeam = team;
		team = newTeam;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.CHALLENGE_PARTICIPANT__TEAM, oldTeam, team));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isWon() {
		return won;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWon(boolean newWon) {
		boolean oldWon = won;
		won = newWon;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.CHALLENGE_PARTICIPANT__WON, oldWon, won));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<User> getUser() {
		if (user == null) {
			user = new EObjectWithInverseResolvingEList.ManyInverse<User>(User.class, this, Zapfmaster2000Package.CHALLENGE_PARTICIPANT__USER, Zapfmaster2000Package.USER__CHALLENGE_PARTICIPATIONS);
		}
		return user;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Zapfmaster2000Package.CHALLENGE_PARTICIPANT__CHALLENGE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetChallenge((Challenge)otherEnd, msgs);
			case Zapfmaster2000Package.CHALLENGE_PARTICIPANT__USER:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getUser()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Zapfmaster2000Package.CHALLENGE_PARTICIPANT__CHALLENGE:
				return basicSetChallenge(null, msgs);
			case Zapfmaster2000Package.CHALLENGE_PARTICIPANT__USER:
				return ((InternalEList<?>)getUser()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case Zapfmaster2000Package.CHALLENGE_PARTICIPANT__CHALLENGE:
				return eInternalContainer().eInverseRemove(this, Zapfmaster2000Package.CHALLENGE__PARTICIPANTS, Challenge.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Zapfmaster2000Package.CHALLENGE_PARTICIPANT__CHALLENGE:
				return getChallenge();
			case Zapfmaster2000Package.CHALLENGE_PARTICIPANT__TEAM:
				return getTeam();
			case Zapfmaster2000Package.CHALLENGE_PARTICIPANT__WON:
				return isWon();
			case Zapfmaster2000Package.CHALLENGE_PARTICIPANT__USER:
				return getUser();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case Zapfmaster2000Package.CHALLENGE_PARTICIPANT__CHALLENGE:
				setChallenge((Challenge)newValue);
				return;
			case Zapfmaster2000Package.CHALLENGE_PARTICIPANT__TEAM:
				setTeam((Integer)newValue);
				return;
			case Zapfmaster2000Package.CHALLENGE_PARTICIPANT__WON:
				setWon((Boolean)newValue);
				return;
			case Zapfmaster2000Package.CHALLENGE_PARTICIPANT__USER:
				getUser().clear();
				getUser().addAll((Collection<? extends User>)newValue);
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
			case Zapfmaster2000Package.CHALLENGE_PARTICIPANT__CHALLENGE:
				setChallenge((Challenge)null);
				return;
			case Zapfmaster2000Package.CHALLENGE_PARTICIPANT__TEAM:
				setTeam(TEAM_EDEFAULT);
				return;
			case Zapfmaster2000Package.CHALLENGE_PARTICIPANT__WON:
				setWon(WON_EDEFAULT);
				return;
			case Zapfmaster2000Package.CHALLENGE_PARTICIPANT__USER:
				getUser().clear();
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
			case Zapfmaster2000Package.CHALLENGE_PARTICIPANT__CHALLENGE:
				return getChallenge() != null;
			case Zapfmaster2000Package.CHALLENGE_PARTICIPANT__TEAM:
				return team != TEAM_EDEFAULT;
			case Zapfmaster2000Package.CHALLENGE_PARTICIPANT__WON:
				return won != WON_EDEFAULT;
			case Zapfmaster2000Package.CHALLENGE_PARTICIPANT__USER:
				return user != null && !user.isEmpty();
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
		result.append(" (team: ");
		result.append(team);
		result.append(", won: ");
		result.append(won);
		result.append(')');
		return result.toString();
	}

} //ChallengeParticipantImpl
