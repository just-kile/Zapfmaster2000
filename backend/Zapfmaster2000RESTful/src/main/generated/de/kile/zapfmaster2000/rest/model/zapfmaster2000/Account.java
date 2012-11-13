/**
 */
package de.kile.zapfmaster2000.rest.model.zapfmaster2000;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Account</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account#getBoxes <em>Boxes</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package#getAccount()
 * @model
 * @generated
 */
public interface Account extends EObject {
	/**
	 * Returns the value of the '<em><b>Boxes</b></em>' containment reference list.
	 * The list contents are of type {@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box}.
	 * It is bidirectional and its opposite is '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box#getAccount <em>Account</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Boxes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Boxes</em>' containment reference list.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package#getAccount_Boxes()
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box#getAccount
	 * @model opposite="account" containment="true"
	 * @generated
	 */
	EList<Box> getBoxes();

} // Account
