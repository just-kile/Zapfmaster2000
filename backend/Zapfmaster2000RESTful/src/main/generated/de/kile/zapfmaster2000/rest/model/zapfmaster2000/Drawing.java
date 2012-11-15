/**
 */
package de.kile.zapfmaster2000.rest.model.zapfmaster2000;

import java.util.Date;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Drawing</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing#getUser <em>User</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing#getKeg <em>Keg</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing#getAmount <em>Amount</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing#getDate <em>Date</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package#getDrawing()
 * @model
 * @generated
 */
public interface Drawing extends EObject {
	/**
	 * Returns the value of the '<em><b>User</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.User#getDrawings <em>Drawings</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User</em>' container reference.
	 * @see #setUser(User)
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package#getDrawing_User()
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.User#getDrawings
	 * @model opposite="drawings" required="true" transient="false"
	 * @generated
	 */
	User getUser();

	/**
	 * Sets the value of the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing#getUser <em>User</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>User</em>' container reference.
	 * @see #getUser()
	 * @generated
	 */
	void setUser(User value);

	/**
	 * Returns the value of the '<em><b>Keg</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg#getDrawings <em>Drawings</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Keg</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Keg</em>' reference.
	 * @see #setKeg(Keg)
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package#getDrawing_Keg()
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg#getDrawings
	 * @model opposite="drawings" required="true"
	 * @generated
	 */
	Keg getKeg();

	/**
	 * Sets the value of the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing#getKeg <em>Keg</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Keg</em>' reference.
	 * @see #getKeg()
	 * @generated
	 */
	void setKeg(Keg value);

	/**
	 * Returns the value of the '<em><b>Amount</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Amount</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Amount</em>' attribute.
	 * @see #setAmount(double)
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package#getDrawing_Amount()
	 * @model
	 * @generated
	 */
	double getAmount();

	/**
	 * Sets the value of the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing#getAmount <em>Amount</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Amount</em>' attribute.
	 * @see #getAmount()
	 * @generated
	 */
	void setAmount(double value);

	/**
	 * Returns the value of the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Date</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Date</em>' attribute.
	 * @see #setDate(Date)
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package#getDrawing_Date()
	 * @model
	 * @generated
	 */
	Date getDate();

	/**
	 * Sets the value of the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing#getDate <em>Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Date</em>' attribute.
	 * @see #getDate()
	 * @generated
	 */
	void setDate(Date value);

} // Drawing
