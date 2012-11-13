/**
 */
package de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl;

import de.kile.zapfmaster2000.rest.model.zapfmaster2000.MappingQrRfid;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Mapping Qr Rfid</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.MappingQrRfidImpl#getQrCode <em>Qr Code</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.MappingQrRfidImpl#getRfidTag <em>Rfid Tag</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MappingQrRfidImpl extends EObjectImpl implements MappingQrRfid {
	/**
	 * The default value of the '{@link #getQrCode() <em>Qr Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQrCode()
	 * @generated
	 * @ordered
	 */
	protected static final long QR_CODE_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getQrCode() <em>Qr Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQrCode()
	 * @generated
	 * @ordered
	 */
	protected long qrCode = QR_CODE_EDEFAULT;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MappingQrRfidImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Zapfmaster2000Package.Literals.MAPPING_QR_RFID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getQrCode() {
		return qrCode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQrCode(long newQrCode) {
		long oldQrCode = qrCode;
		qrCode = newQrCode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.MAPPING_QR_RFID__QR_CODE, oldQrCode, qrCode));
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
			eNotify(new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.MAPPING_QR_RFID__RFID_TAG, oldRfidTag, rfidTag));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Zapfmaster2000Package.MAPPING_QR_RFID__QR_CODE:
				return getQrCode();
			case Zapfmaster2000Package.MAPPING_QR_RFID__RFID_TAG:
				return getRfidTag();
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
			case Zapfmaster2000Package.MAPPING_QR_RFID__QR_CODE:
				setQrCode((Long)newValue);
				return;
			case Zapfmaster2000Package.MAPPING_QR_RFID__RFID_TAG:
				setRfidTag((Long)newValue);
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
			case Zapfmaster2000Package.MAPPING_QR_RFID__QR_CODE:
				setQrCode(QR_CODE_EDEFAULT);
				return;
			case Zapfmaster2000Package.MAPPING_QR_RFID__RFID_TAG:
				setRfidTag(RFID_TAG_EDEFAULT);
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
			case Zapfmaster2000Package.MAPPING_QR_RFID__QR_CODE:
				return qrCode != QR_CODE_EDEFAULT;
			case Zapfmaster2000Package.MAPPING_QR_RFID__RFID_TAG:
				return rfidTag != RFID_TAG_EDEFAULT;
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
		result.append(" (qrCode: ");
		result.append(qrCode);
		result.append(", rfidTag: ");
		result.append(rfidTag);
		result.append(')');
		return result.toString();
	}

} //MappingQrRfidImpl
