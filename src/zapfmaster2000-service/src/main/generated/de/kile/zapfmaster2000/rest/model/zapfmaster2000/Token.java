/**
 */
package de.kile.zapfmaster2000.rest.model.zapfmaster2000;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Token</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Token#getId <em>Id</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Token#getToken <em>Token</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Token#getAccount <em>Account</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Token#getUser <em>User</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Token#getAdmin <em>Admin</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Token#getGoogleCloudMessagingToken <em>Google Cloud Messaging Token</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package#getToken()
 * @model
 * @generated
 */
public interface Token extends EObject {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(long)
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package#getToken_Id()
	 * @model id="true"
	 * @generated
	 */
	long getId();

	/**
	 * Sets the value of the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Token#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(long value);

	/**
	 * Returns the value of the '<em><b>Token</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Token</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Token</em>' attribute.
	 * @see #setToken(String)
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package#getToken_Token()
	 * @model
	 * @generated
	 */
	String getToken();

	/**
	 * Sets the value of the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Token#getToken <em>Token</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Token</em>' attribute.
	 * @see #getToken()
	 * @generated
	 */
	void setToken(String value);

	/**
	 * Returns the value of the '<em><b>Account</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Account</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Account</em>' reference.
	 * @see #setAccount(Account)
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package#getToken_Account()
	 * @model
	 * @generated
	 */
	Account getAccount();

	/**
	 * Sets the value of the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Token#getAccount <em>Account</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Account</em>' reference.
	 * @see #getAccount()
	 * @generated
	 */
	void setAccount(Account value);

	/**
	 * Returns the value of the '<em><b>User</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User</em>' reference.
	 * @see #setUser(User)
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package#getToken_User()
	 * @model
	 * @generated
	 */
	User getUser();

	/**
	 * Sets the value of the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Token#getUser <em>User</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>User</em>' reference.
	 * @see #getUser()
	 * @generated
	 */
	void setUser(User value);

	/**
	 * Returns the value of the '<em><b>Admin</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Admin</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Admin</em>' reference.
	 * @see #setAdmin(Admin)
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package#getToken_Admin()
	 * @model
	 * @generated
	 */
	Admin getAdmin();

	/**
	 * Sets the value of the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Token#getAdmin <em>Admin</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Admin</em>' reference.
	 * @see #getAdmin()
	 * @generated
	 */
	void setAdmin(Admin value);

	/**
	 * Returns the value of the '<em><b>Google Cloud Messaging Token</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Google Cloud Messaging Token</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Google Cloud Messaging Token</em>' attribute.
	 * @see #setGoogleCloudMessagingToken(String)
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package#getToken_GoogleCloudMessagingToken()
	 * @model
	 * @generated
	 */
	String getGoogleCloudMessagingToken();

	/**
	 * Sets the value of the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Token#getGoogleCloudMessagingToken <em>Google Cloud Messaging Token</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Google Cloud Messaging Token</em>' attribute.
	 * @see #getGoogleCloudMessagingToken()
	 * @generated
	 */
	void setGoogleCloudMessagingToken(String value);

} // Token
