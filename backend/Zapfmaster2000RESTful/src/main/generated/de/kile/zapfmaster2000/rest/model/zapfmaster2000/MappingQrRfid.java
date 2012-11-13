/**
 */
package de.kile.zapfmaster2000.rest.model.zapfmaster2000;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mapping Qr Rfid</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.MappingQrRfid#getQrCode <em>Qr Code</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.MappingQrRfid#getRfidTag <em>Rfid Tag</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package#getMappingQrRfid()
 * @model
 * @generated
 */
public interface MappingQrRfid extends EObject {
	/**
	 * Returns the value of the '<em><b>Qr Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Qr Code</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Qr Code</em>' attribute.
	 * @see #setQrCode(long)
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package#getMappingQrRfid_QrCode()
	 * @model
	 * @generated
	 */
	long getQrCode();

	/**
	 * Sets the value of the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.MappingQrRfid#getQrCode <em>Qr Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Qr Code</em>' attribute.
	 * @see #getQrCode()
	 * @generated
	 */
	void setQrCode(long value);

	/**
	 * Returns the value of the '<em><b>Rfid Tag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rfid Tag</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rfid Tag</em>' attribute.
	 * @see #setRfidTag(long)
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package#getMappingQrRfid_RfidTag()
	 * @model
	 * @generated
	 */
	long getRfidTag();

	/**
	 * Sets the value of the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.MappingQrRfid#getRfidTag <em>Rfid Tag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rfid Tag</em>' attribute.
	 * @see #getRfidTag()
	 * @generated
	 */
	void setRfidTag(long value);

} // MappingQrRfid
