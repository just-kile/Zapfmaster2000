/**
 */
package de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl;

import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeParticipant;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.GainedAchievement;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>User</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.UserImpl#getId <em>Id</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.UserImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.UserImpl#getPassword <em>Password</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.UserImpl#getImagePath <em>Image Path</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.UserImpl#getRfidTag <em>Rfid Tag</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.UserImpl#getSex <em>Sex</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.UserImpl#getWeight <em>Weight</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.UserImpl#getGained <em>Gained</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.UserImpl#getDrawings <em>Drawings</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.UserImpl#getChallengeParticipations <em>Challenge Participations</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.UserImpl#getType <em>Type</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.UserImpl#getAccount <em>Account</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UserImpl extends EObjectImpl implements User {
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
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getPassword() <em>Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPassword()
	 * @generated
	 * @ordered
	 */
	protected static final String PASSWORD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPassword() <em>Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPassword()
	 * @generated
	 * @ordered
	 */
	protected String password = PASSWORD_EDEFAULT;

	/**
	 * The default value of the '{@link #getImagePath() <em>Image Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImagePath()
	 * @generated
	 * @ordered
	 */
	protected static final String IMAGE_PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getImagePath() <em>Image Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImagePath()
	 * @generated
	 * @ordered
	 */
	protected String imagePath = IMAGE_PATH_EDEFAULT;

	/**
	 * The default value of the '{@link #getRfidTag() <em>Rfid Tag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRfidTag()
	 * @generated
	 * @ordered
	 */
	protected static final long RFID_TAG_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getRfidTag() <em>Rfid Tag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRfidTag()
	 * @generated
	 * @ordered
	 */
	protected long rfidTag = RFID_TAG_EDEFAULT;

	/**
	 * The default value of the '{@link #getSex() <em>Sex</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSex()
	 * @generated
	 * @ordered
	 */
	protected static final Sex SEX_EDEFAULT = Sex.MALE;

	/**
	 * The cached value of the '{@link #getSex() <em>Sex</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSex()
	 * @generated
	 * @ordered
	 */
	protected Sex sex = SEX_EDEFAULT;

	/**
	 * The default value of the '{@link #getWeight() <em>Weight</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeight()
	 * @generated
	 * @ordered
	 */
	protected static final int WEIGHT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getWeight() <em>Weight</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeight()
	 * @generated
	 * @ordered
	 */
	protected int weight = WEIGHT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getGained() <em>Gained</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGained()
	 * @generated
	 * @ordered
	 */
	protected EList<GainedAchievement> gained;

	/**
	 * The cached value of the '{@link #getDrawings() <em>Drawings</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDrawings()
	 * @generated
	 * @ordered
	 */
	protected EList<Drawing> drawings;

	/**
	 * The cached value of the '{@link #getChallengeParticipations() <em>Challenge Participations</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChallengeParticipations()
	 * @generated
	 * @ordered
	 */
	protected EList<ChallengeParticipant> challengeParticipations;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final UserType TYPE_EDEFAULT = UserType.USER;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected UserType type = TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAccount() <em>Account</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAccount()
	 * @generated
	 * @ordered
	 */
	protected Account account;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UserImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Zapfmaster2000Package.Literals.USER;
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
			eNotify(new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.USER__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.USER__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPassword(String newPassword) {
		String oldPassword = password;
		password = newPassword;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.USER__PASSWORD, oldPassword, password));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImagePath(String newImagePath) {
		String oldImagePath = imagePath;
		imagePath = newImagePath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.USER__IMAGE_PATH, oldImagePath, imagePath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getRfidTag() {
		return rfidTag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRfidTag(long newRfidTag) {
		long oldRfidTag = rfidTag;
		rfidTag = newRfidTag;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.USER__RFID_TAG, oldRfidTag, rfidTag));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Sex getSex() {
		return sex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSex(Sex newSex) {
		Sex oldSex = sex;
		sex = newSex == null ? SEX_EDEFAULT : newSex;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.USER__SEX, oldSex, sex));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWeight(int newWeight) {
		int oldWeight = weight;
		weight = newWeight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.USER__WEIGHT, oldWeight, weight));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GainedAchievement> getGained() {
		if (gained == null) {
			gained = new EObjectWithInverseResolvingEList<GainedAchievement>(GainedAchievement.class, this, Zapfmaster2000Package.USER__GAINED, Zapfmaster2000Package.GAINED_ACHIEVEMENT__USER);
		}
		return gained;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Drawing> getDrawings() {
		if (drawings == null) {
			drawings = new EObjectContainmentWithInverseEList<Drawing>(Drawing.class, this, Zapfmaster2000Package.USER__DRAWINGS, Zapfmaster2000Package.DRAWING__USER);
		}
		return drawings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ChallengeParticipant> getChallengeParticipations() {
		if (challengeParticipations == null) {
			challengeParticipations = new EObjectWithInverseResolvingEList.ManyInverse<ChallengeParticipant>(ChallengeParticipant.class, this, Zapfmaster2000Package.USER__CHALLENGE_PARTICIPATIONS, Zapfmaster2000Package.CHALLENGE_PARTICIPANT__USER);
		}
		return challengeParticipations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UserType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(UserType newType) {
		UserType oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.USER__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Account getAccount() {
		if (account != null && account.eIsProxy()) {
			InternalEObject oldAccount = (InternalEObject)account;
			account = (Account)eResolveProxy(oldAccount);
			if (account != oldAccount) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Zapfmaster2000Package.USER__ACCOUNT, oldAccount, account));
			}
		}
		return account;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Account basicGetAccount() {
		return account;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAccount(Account newAccount, NotificationChain msgs) {
		Account oldAccount = account;
		account = newAccount;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.USER__ACCOUNT, oldAccount, newAccount);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAccount(Account newAccount) {
		if (newAccount != account) {
			NotificationChain msgs = null;
			if (account != null)
				msgs = ((InternalEObject)account).eInverseRemove(this, Zapfmaster2000Package.ACCOUNT__USERS, Account.class, msgs);
			if (newAccount != null)
				msgs = ((InternalEObject)newAccount).eInverseAdd(this, Zapfmaster2000Package.ACCOUNT__USERS, Account.class, msgs);
			msgs = basicSetAccount(newAccount, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.USER__ACCOUNT, newAccount, newAccount));
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
			case Zapfmaster2000Package.USER__GAINED:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getGained()).basicAdd(otherEnd, msgs);
			case Zapfmaster2000Package.USER__DRAWINGS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDrawings()).basicAdd(otherEnd, msgs);
			case Zapfmaster2000Package.USER__CHALLENGE_PARTICIPATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getChallengeParticipations()).basicAdd(otherEnd, msgs);
			case Zapfmaster2000Package.USER__ACCOUNT:
				if (account != null)
					msgs = ((InternalEObject)account).eInverseRemove(this, Zapfmaster2000Package.ACCOUNT__USERS, Account.class, msgs);
				return basicSetAccount((Account)otherEnd, msgs);
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
			case Zapfmaster2000Package.USER__GAINED:
				return ((InternalEList<?>)getGained()).basicRemove(otherEnd, msgs);
			case Zapfmaster2000Package.USER__DRAWINGS:
				return ((InternalEList<?>)getDrawings()).basicRemove(otherEnd, msgs);
			case Zapfmaster2000Package.USER__CHALLENGE_PARTICIPATIONS:
				return ((InternalEList<?>)getChallengeParticipations()).basicRemove(otherEnd, msgs);
			case Zapfmaster2000Package.USER__ACCOUNT:
				return basicSetAccount(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Zapfmaster2000Package.USER__ID:
				return getId();
			case Zapfmaster2000Package.USER__NAME:
				return getName();
			case Zapfmaster2000Package.USER__PASSWORD:
				return getPassword();
			case Zapfmaster2000Package.USER__IMAGE_PATH:
				return getImagePath();
			case Zapfmaster2000Package.USER__RFID_TAG:
				return getRfidTag();
			case Zapfmaster2000Package.USER__SEX:
				return getSex();
			case Zapfmaster2000Package.USER__WEIGHT:
				return getWeight();
			case Zapfmaster2000Package.USER__GAINED:
				return getGained();
			case Zapfmaster2000Package.USER__DRAWINGS:
				return getDrawings();
			case Zapfmaster2000Package.USER__CHALLENGE_PARTICIPATIONS:
				return getChallengeParticipations();
			case Zapfmaster2000Package.USER__TYPE:
				return getType();
			case Zapfmaster2000Package.USER__ACCOUNT:
				if (resolve) return getAccount();
				return basicGetAccount();
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
			case Zapfmaster2000Package.USER__ID:
				setId((Long)newValue);
				return;
			case Zapfmaster2000Package.USER__NAME:
				setName((String)newValue);
				return;
			case Zapfmaster2000Package.USER__PASSWORD:
				setPassword((String)newValue);
				return;
			case Zapfmaster2000Package.USER__IMAGE_PATH:
				setImagePath((String)newValue);
				return;
			case Zapfmaster2000Package.USER__RFID_TAG:
				setRfidTag((Long)newValue);
				return;
			case Zapfmaster2000Package.USER__SEX:
				setSex((Sex)newValue);
				return;
			case Zapfmaster2000Package.USER__WEIGHT:
				setWeight((Integer)newValue);
				return;
			case Zapfmaster2000Package.USER__GAINED:
				getGained().clear();
				getGained().addAll((Collection<? extends GainedAchievement>)newValue);
				return;
			case Zapfmaster2000Package.USER__DRAWINGS:
				getDrawings().clear();
				getDrawings().addAll((Collection<? extends Drawing>)newValue);
				return;
			case Zapfmaster2000Package.USER__CHALLENGE_PARTICIPATIONS:
				getChallengeParticipations().clear();
				getChallengeParticipations().addAll((Collection<? extends ChallengeParticipant>)newValue);
				return;
			case Zapfmaster2000Package.USER__TYPE:
				setType((UserType)newValue);
				return;
			case Zapfmaster2000Package.USER__ACCOUNT:
				setAccount((Account)newValue);
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
			case Zapfmaster2000Package.USER__ID:
				setId(ID_EDEFAULT);
				return;
			case Zapfmaster2000Package.USER__NAME:
				setName(NAME_EDEFAULT);
				return;
			case Zapfmaster2000Package.USER__PASSWORD:
				setPassword(PASSWORD_EDEFAULT);
				return;
			case Zapfmaster2000Package.USER__IMAGE_PATH:
				setImagePath(IMAGE_PATH_EDEFAULT);
				return;
			case Zapfmaster2000Package.USER__RFID_TAG:
				setRfidTag(RFID_TAG_EDEFAULT);
				return;
			case Zapfmaster2000Package.USER__SEX:
				setSex(SEX_EDEFAULT);
				return;
			case Zapfmaster2000Package.USER__WEIGHT:
				setWeight(WEIGHT_EDEFAULT);
				return;
			case Zapfmaster2000Package.USER__GAINED:
				getGained().clear();
				return;
			case Zapfmaster2000Package.USER__DRAWINGS:
				getDrawings().clear();
				return;
			case Zapfmaster2000Package.USER__CHALLENGE_PARTICIPATIONS:
				getChallengeParticipations().clear();
				return;
			case Zapfmaster2000Package.USER__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case Zapfmaster2000Package.USER__ACCOUNT:
				setAccount((Account)null);
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
			case Zapfmaster2000Package.USER__ID:
				return id != ID_EDEFAULT;
			case Zapfmaster2000Package.USER__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case Zapfmaster2000Package.USER__PASSWORD:
				return PASSWORD_EDEFAULT == null ? password != null : !PASSWORD_EDEFAULT.equals(password);
			case Zapfmaster2000Package.USER__IMAGE_PATH:
				return IMAGE_PATH_EDEFAULT == null ? imagePath != null : !IMAGE_PATH_EDEFAULT.equals(imagePath);
			case Zapfmaster2000Package.USER__RFID_TAG:
				return rfidTag != RFID_TAG_EDEFAULT;
			case Zapfmaster2000Package.USER__SEX:
				return sex != SEX_EDEFAULT;
			case Zapfmaster2000Package.USER__WEIGHT:
				return weight != WEIGHT_EDEFAULT;
			case Zapfmaster2000Package.USER__GAINED:
				return gained != null && !gained.isEmpty();
			case Zapfmaster2000Package.USER__DRAWINGS:
				return drawings != null && !drawings.isEmpty();
			case Zapfmaster2000Package.USER__CHALLENGE_PARTICIPATIONS:
				return challengeParticipations != null && !challengeParticipations.isEmpty();
			case Zapfmaster2000Package.USER__TYPE:
				return type != TYPE_EDEFAULT;
			case Zapfmaster2000Package.USER__ACCOUNT:
				return account != null;
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
		result.append(", name: ");
		result.append(name);
		result.append(", password: ");
		result.append(password);
		result.append(", imagePath: ");
		result.append(imagePath);
		result.append(", rfidTag: ");
		result.append(rfidTag);
		result.append(", sex: ");
		result.append(sex);
		result.append(", weight: ");
		result.append(weight);
		result.append(", type: ");
		result.append(type);
		result.append(')');
		return result.toString();
	}

} //UserImpl
