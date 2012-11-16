/**
 */
package de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl;

import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg;
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
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Box</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.BoxImpl#getId <em>Id</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.BoxImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.BoxImpl#getAccount <em>Account</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.BoxImpl#getKegs <em>Kegs</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.BoxImpl#getPassphrase <em>Passphrase</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BoxImpl extends EObjectImpl implements Box {
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
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected String version = VERSION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getKegs() <em>Kegs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKegs()
	 * @generated
	 * @ordered
	 */
	protected EList<Keg> kegs;

	/**
	 * The default value of the '{@link #getPassphrase() <em>Passphrase</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPassphrase()
	 * @generated
	 * @ordered
	 */
	protected static final String PASSPHRASE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPassphrase() <em>Passphrase</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPassphrase()
	 * @generated
	 * @ordered
	 */
	protected String passphrase = PASSPHRASE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BoxImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Zapfmaster2000Package.Literals.BOX;
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
			eNotify(new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.BOX__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.BOX__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Account getAccount() {
		if (eContainerFeatureID() != Zapfmaster2000Package.BOX__ACCOUNT) return null;
		return (Account)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAccount(Account newAccount, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newAccount, Zapfmaster2000Package.BOX__ACCOUNT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAccount(Account newAccount) {
		if (newAccount != eInternalContainer() || (eContainerFeatureID() != Zapfmaster2000Package.BOX__ACCOUNT && newAccount != null)) {
			if (EcoreUtil.isAncestor(this, newAccount))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newAccount != null)
				msgs = ((InternalEObject)newAccount).eInverseAdd(this, Zapfmaster2000Package.ACCOUNT__BOXES, Account.class, msgs);
			msgs = basicSetAccount(newAccount, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.BOX__ACCOUNT, newAccount, newAccount));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Keg> getKegs() {
		if (kegs == null) {
			kegs = new EObjectContainmentWithInverseEList<Keg>(Keg.class, this, Zapfmaster2000Package.BOX__KEGS, Zapfmaster2000Package.KEG__BOX);
		}
		return kegs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPassphrase() {
		return passphrase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPassphrase(String newPassphrase) {
		String oldPassphrase = passphrase;
		passphrase = newPassphrase;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.BOX__PASSPHRASE, oldPassphrase, passphrase));
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
			case Zapfmaster2000Package.BOX__ACCOUNT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetAccount((Account)otherEnd, msgs);
			case Zapfmaster2000Package.BOX__KEGS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getKegs()).basicAdd(otherEnd, msgs);
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
			case Zapfmaster2000Package.BOX__ACCOUNT:
				return basicSetAccount(null, msgs);
			case Zapfmaster2000Package.BOX__KEGS:
				return ((InternalEList<?>)getKegs()).basicRemove(otherEnd, msgs);
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
			case Zapfmaster2000Package.BOX__ACCOUNT:
				return eInternalContainer().eInverseRemove(this, Zapfmaster2000Package.ACCOUNT__BOXES, Account.class, msgs);
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
			case Zapfmaster2000Package.BOX__ID:
				return getId();
			case Zapfmaster2000Package.BOX__VERSION:
				return getVersion();
			case Zapfmaster2000Package.BOX__ACCOUNT:
				return getAccount();
			case Zapfmaster2000Package.BOX__KEGS:
				return getKegs();
			case Zapfmaster2000Package.BOX__PASSPHRASE:
				return getPassphrase();
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
			case Zapfmaster2000Package.BOX__ID:
				setId((Long)newValue);
				return;
			case Zapfmaster2000Package.BOX__VERSION:
				setVersion((String)newValue);
				return;
			case Zapfmaster2000Package.BOX__ACCOUNT:
				setAccount((Account)newValue);
				return;
			case Zapfmaster2000Package.BOX__KEGS:
				getKegs().clear();
				getKegs().addAll((Collection<? extends Keg>)newValue);
				return;
			case Zapfmaster2000Package.BOX__PASSPHRASE:
				setPassphrase((String)newValue);
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
			case Zapfmaster2000Package.BOX__ID:
				setId(ID_EDEFAULT);
				return;
			case Zapfmaster2000Package.BOX__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case Zapfmaster2000Package.BOX__ACCOUNT:
				setAccount((Account)null);
				return;
			case Zapfmaster2000Package.BOX__KEGS:
				getKegs().clear();
				return;
			case Zapfmaster2000Package.BOX__PASSPHRASE:
				setPassphrase(PASSPHRASE_EDEFAULT);
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
			case Zapfmaster2000Package.BOX__ID:
				return id != ID_EDEFAULT;
			case Zapfmaster2000Package.BOX__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case Zapfmaster2000Package.BOX__ACCOUNT:
				return getAccount() != null;
			case Zapfmaster2000Package.BOX__KEGS:
				return kegs != null && !kegs.isEmpty();
			case Zapfmaster2000Package.BOX__PASSPHRASE:
				return PASSPHRASE_EDEFAULT == null ? passphrase != null : !PASSPHRASE_EDEFAULT.equals(passphrase);
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
		result.append(", version: ");
		result.append(version);
		result.append(", passphrase: ");
		result.append(passphrase);
		result.append(')');
		return result.toString();
	}

} //BoxImpl
