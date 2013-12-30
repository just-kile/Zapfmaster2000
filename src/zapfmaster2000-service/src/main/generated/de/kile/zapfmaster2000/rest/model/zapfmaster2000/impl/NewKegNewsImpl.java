/**
 */
package de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.NewKegNews;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>New Keg News</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.NewKegNewsImpl#getKeg <em>Keg</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NewKegNewsImpl extends NewsImpl implements NewKegNews {
	/**
	 * The cached value of the '{@link #getKeg() <em>Keg</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKeg()
	 * @generated
	 * @ordered
	 */
	protected Keg keg;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NewKegNewsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Zapfmaster2000Package.Literals.NEW_KEG_NEWS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Keg getKeg() {
		if (keg != null && keg.eIsProxy()) {
			InternalEObject oldKeg = (InternalEObject)keg;
			keg = (Keg)eResolveProxy(oldKeg);
			if (keg != oldKeg) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Zapfmaster2000Package.NEW_KEG_NEWS__KEG, oldKeg, keg));
			}
		}
		return keg;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Keg basicGetKeg() {
		return keg;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKeg(Keg newKeg) {
		Keg oldKeg = keg;
		keg = newKeg;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.NEW_KEG_NEWS__KEG, oldKeg, keg));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Zapfmaster2000Package.NEW_KEG_NEWS__KEG:
				if (resolve) return getKeg();
				return basicGetKeg();
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
			case Zapfmaster2000Package.NEW_KEG_NEWS__KEG:
				setKeg((Keg)newValue);
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
			case Zapfmaster2000Package.NEW_KEG_NEWS__KEG:
				setKeg((Keg)null);
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
			case Zapfmaster2000Package.NEW_KEG_NEWS__KEG:
				return keg != null;
		}
		return super.eIsSet(featureID);
	}

} //NewKegNewsImpl
