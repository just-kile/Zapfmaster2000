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
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.BoxImpl#getPassphrase <em>Passphrase</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.BoxImpl#getKegs <em>Kegs</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.BoxImpl#getLocation <em>Location</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.BoxImpl#getTickDisturbanceTerm <em>Tick Disturbance Term</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.BoxImpl#getTickRegressor <em>Tick Regressor</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.BoxImpl#getTickReduction <em>Tick Reduction</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.BoxImpl#getA0 <em>A0</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.BoxImpl#getA1 <em>A1</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.BoxImpl#getA2 <em>A2</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.BoxImpl#isNewCalc <em>New Calc</em>}</li>
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
	 * The cached value of the '{@link #getKegs() <em>Kegs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKegs()
	 * @generated
	 * @ordered
	 */
	protected EList<Keg> kegs;

	/**
	 * The default value of the '{@link #getLocation() <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocation()
	 * @generated
	 * @ordered
	 */
	protected static final String LOCATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLocation() <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocation()
	 * @generated
	 * @ordered
	 */
	protected String location = LOCATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getTickDisturbanceTerm() <em>Tick Disturbance Term</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTickDisturbanceTerm()
	 * @generated
	 * @ordered
	 */
	protected static final double TICK_DISTURBANCE_TERM_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getTickDisturbanceTerm() <em>Tick Disturbance Term</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTickDisturbanceTerm()
	 * @generated
	 * @ordered
	 */
	protected double tickDisturbanceTerm = TICK_DISTURBANCE_TERM_EDEFAULT;

	/**
	 * The default value of the '{@link #getTickRegressor() <em>Tick Regressor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTickRegressor()
	 * @generated
	 * @ordered
	 */
	protected static final double TICK_REGRESSOR_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getTickRegressor() <em>Tick Regressor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTickRegressor()
	 * @generated
	 * @ordered
	 */
	protected double tickRegressor = TICK_REGRESSOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getTickReduction() <em>Tick Reduction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTickReduction()
	 * @generated
	 * @ordered
	 */
	protected static final int TICK_REDUCTION_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getTickReduction() <em>Tick Reduction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTickReduction()
	 * @generated
	 * @ordered
	 */
	protected int tickReduction = TICK_REDUCTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getA0() <em>A0</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getA0()
	 * @generated
	 * @ordered
	 */
	protected static final double A0_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getA0() <em>A0</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getA0()
	 * @generated
	 * @ordered
	 */
	protected double a0 = A0_EDEFAULT;

	/**
	 * The default value of the '{@link #getA1() <em>A1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getA1()
	 * @generated
	 * @ordered
	 */
	protected static final double A1_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getA1() <em>A1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getA1()
	 * @generated
	 * @ordered
	 */
	protected double a1 = A1_EDEFAULT;

	/**
	 * The default value of the '{@link #getA2() <em>A2</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getA2()
	 * @generated
	 * @ordered
	 */
	protected static final double A2_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getA2() <em>A2</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getA2()
	 * @generated
	 * @ordered
	 */
	protected double a2 = A2_EDEFAULT;

	/**
	 * The default value of the '{@link #isNewCalc() <em>New Calc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNewCalc()
	 * @generated
	 * @ordered
	 */
	protected static final boolean NEW_CALC_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isNewCalc() <em>New Calc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNewCalc()
	 * @generated
	 * @ordered
	 */
	protected boolean newCalc = NEW_CALC_EDEFAULT;

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
	public String getLocation() {
		return location;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocation(String newLocation) {
		String oldLocation = location;
		location = newLocation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.BOX__LOCATION, oldLocation, location));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getTickDisturbanceTerm() {
		return tickDisturbanceTerm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTickDisturbanceTerm(double newTickDisturbanceTerm) {
		double oldTickDisturbanceTerm = tickDisturbanceTerm;
		tickDisturbanceTerm = newTickDisturbanceTerm;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.BOX__TICK_DISTURBANCE_TERM, oldTickDisturbanceTerm, tickDisturbanceTerm));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getTickRegressor() {
		return tickRegressor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTickRegressor(double newTickRegressor) {
		double oldTickRegressor = tickRegressor;
		tickRegressor = newTickRegressor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.BOX__TICK_REGRESSOR, oldTickRegressor, tickRegressor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getTickReduction() {
		return tickReduction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTickReduction(int newTickReduction) {
		int oldTickReduction = tickReduction;
		tickReduction = newTickReduction;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.BOX__TICK_REDUCTION, oldTickReduction, tickReduction));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getA0() {
		return a0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setA0(double newA0) {
		double oldA0 = a0;
		a0 = newA0;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.BOX__A0, oldA0, a0));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getA1() {
		return a1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setA1(double newA1) {
		double oldA1 = a1;
		a1 = newA1;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.BOX__A1, oldA1, a1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getA2() {
		return a2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setA2(double newA2) {
		double oldA2 = a2;
		a2 = newA2;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.BOX__A2, oldA2, a2));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isNewCalc() {
		return newCalc;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNewCalc(boolean newNewCalc) {
		boolean oldNewCalc = newCalc;
		newCalc = newNewCalc;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.BOX__NEW_CALC, oldNewCalc, newCalc));
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
			case Zapfmaster2000Package.BOX__PASSPHRASE:
				return getPassphrase();
			case Zapfmaster2000Package.BOX__KEGS:
				return getKegs();
			case Zapfmaster2000Package.BOX__LOCATION:
				return getLocation();
			case Zapfmaster2000Package.BOX__TICK_DISTURBANCE_TERM:
				return getTickDisturbanceTerm();
			case Zapfmaster2000Package.BOX__TICK_REGRESSOR:
				return getTickRegressor();
			case Zapfmaster2000Package.BOX__TICK_REDUCTION:
				return getTickReduction();
			case Zapfmaster2000Package.BOX__A0:
				return getA0();
			case Zapfmaster2000Package.BOX__A1:
				return getA1();
			case Zapfmaster2000Package.BOX__A2:
				return getA2();
			case Zapfmaster2000Package.BOX__NEW_CALC:
				return isNewCalc();
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
			case Zapfmaster2000Package.BOX__PASSPHRASE:
				setPassphrase((String)newValue);
				return;
			case Zapfmaster2000Package.BOX__KEGS:
				getKegs().clear();
				getKegs().addAll((Collection<? extends Keg>)newValue);
				return;
			case Zapfmaster2000Package.BOX__LOCATION:
				setLocation((String)newValue);
				return;
			case Zapfmaster2000Package.BOX__TICK_DISTURBANCE_TERM:
				setTickDisturbanceTerm((Double)newValue);
				return;
			case Zapfmaster2000Package.BOX__TICK_REGRESSOR:
				setTickRegressor((Double)newValue);
				return;
			case Zapfmaster2000Package.BOX__TICK_REDUCTION:
				setTickReduction((Integer)newValue);
				return;
			case Zapfmaster2000Package.BOX__A0:
				setA0((Double)newValue);
				return;
			case Zapfmaster2000Package.BOX__A1:
				setA1((Double)newValue);
				return;
			case Zapfmaster2000Package.BOX__A2:
				setA2((Double)newValue);
				return;
			case Zapfmaster2000Package.BOX__NEW_CALC:
				setNewCalc((Boolean)newValue);
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
			case Zapfmaster2000Package.BOX__PASSPHRASE:
				setPassphrase(PASSPHRASE_EDEFAULT);
				return;
			case Zapfmaster2000Package.BOX__KEGS:
				getKegs().clear();
				return;
			case Zapfmaster2000Package.BOX__LOCATION:
				setLocation(LOCATION_EDEFAULT);
				return;
			case Zapfmaster2000Package.BOX__TICK_DISTURBANCE_TERM:
				setTickDisturbanceTerm(TICK_DISTURBANCE_TERM_EDEFAULT);
				return;
			case Zapfmaster2000Package.BOX__TICK_REGRESSOR:
				setTickRegressor(TICK_REGRESSOR_EDEFAULT);
				return;
			case Zapfmaster2000Package.BOX__TICK_REDUCTION:
				setTickReduction(TICK_REDUCTION_EDEFAULT);
				return;
			case Zapfmaster2000Package.BOX__A0:
				setA0(A0_EDEFAULT);
				return;
			case Zapfmaster2000Package.BOX__A1:
				setA1(A1_EDEFAULT);
				return;
			case Zapfmaster2000Package.BOX__A2:
				setA2(A2_EDEFAULT);
				return;
			case Zapfmaster2000Package.BOX__NEW_CALC:
				setNewCalc(NEW_CALC_EDEFAULT);
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
			case Zapfmaster2000Package.BOX__PASSPHRASE:
				return PASSPHRASE_EDEFAULT == null ? passphrase != null : !PASSPHRASE_EDEFAULT.equals(passphrase);
			case Zapfmaster2000Package.BOX__KEGS:
				return kegs != null && !kegs.isEmpty();
			case Zapfmaster2000Package.BOX__LOCATION:
				return LOCATION_EDEFAULT == null ? location != null : !LOCATION_EDEFAULT.equals(location);
			case Zapfmaster2000Package.BOX__TICK_DISTURBANCE_TERM:
				return tickDisturbanceTerm != TICK_DISTURBANCE_TERM_EDEFAULT;
			case Zapfmaster2000Package.BOX__TICK_REGRESSOR:
				return tickRegressor != TICK_REGRESSOR_EDEFAULT;
			case Zapfmaster2000Package.BOX__TICK_REDUCTION:
				return tickReduction != TICK_REDUCTION_EDEFAULT;
			case Zapfmaster2000Package.BOX__A0:
				return a0 != A0_EDEFAULT;
			case Zapfmaster2000Package.BOX__A1:
				return a1 != A1_EDEFAULT;
			case Zapfmaster2000Package.BOX__A2:
				return a2 != A2_EDEFAULT;
			case Zapfmaster2000Package.BOX__NEW_CALC:
				return newCalc != NEW_CALC_EDEFAULT;
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
		result.append(", location: ");
		result.append(location);
		result.append(", tickDisturbanceTerm: ");
		result.append(tickDisturbanceTerm);
		result.append(", tickRegressor: ");
		result.append(tickRegressor);
		result.append(", tickReduction: ");
		result.append(tickReduction);
		result.append(", a0: ");
		result.append(a0);
		result.append(", a1: ");
		result.append(a1);
		result.append(", a2: ");
		result.append(a2);
		result.append(", newCalc: ");
		result.append(newCalc);
		result.append(')');
		return result.toString();
	}

} //BoxImpl
