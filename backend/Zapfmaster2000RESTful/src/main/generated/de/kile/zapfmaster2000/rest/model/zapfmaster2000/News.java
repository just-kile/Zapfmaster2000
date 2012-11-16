/**
 */
package de.kile.zapfmaster2000.rest.model.zapfmaster2000;

import java.util.Date;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>News</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.News#getId <em>Id</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.News#getType <em>Type</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.News#getContents <em>Contents</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.News#getImagePath <em>Image Path</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.News#getAccount <em>Account</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.News#getDate <em>Date</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package#getNews()
 * @model
 * @generated
 */
public interface News extends EObject {
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
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package#getNews_Id()
	 * @model id="true"
	 * @generated
	 */
	long getId();

	/**
	 * Sets the value of the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.News#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(long value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.NewsType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.NewsType
	 * @see #setType(NewsType)
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package#getNews_Type()
	 * @model
	 * @generated
	 */
	NewsType getType();

	/**
	 * Sets the value of the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.News#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.NewsType
	 * @see #getType()
	 * @generated
	 */
	void setType(NewsType value);

	/**
	 * Returns the value of the '<em><b>Contents</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contents</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contents</em>' attribute.
	 * @see #setContents(String)
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package#getNews_Contents()
	 * @model
	 * @generated
	 */
	String getContents();

	/**
	 * Sets the value of the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.News#getContents <em>Contents</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Contents</em>' attribute.
	 * @see #getContents()
	 * @generated
	 */
	void setContents(String value);

	/**
	 * Returns the value of the '<em><b>Image Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Image Path</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Image Path</em>' attribute.
	 * @see #setImagePath(String)
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package#getNews_ImagePath()
	 * @model
	 * @generated
	 */
	String getImagePath();

	/**
	 * Sets the value of the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.News#getImagePath <em>Image Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Image Path</em>' attribute.
	 * @see #getImagePath()
	 * @generated
	 */
	void setImagePath(String value);

	/**
	 * Returns the value of the '<em><b>Account</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account#getNews <em>News</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Account</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Account</em>' container reference.
	 * @see #setAccount(Account)
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package#getNews_Account()
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account#getNews
	 * @model opposite="news" transient="false"
	 * @generated
	 */
	Account getAccount();

	/**
	 * Sets the value of the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.News#getAccount <em>Account</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Account</em>' container reference.
	 * @see #getAccount()
	 * @generated
	 */
	void setAccount(Account value);

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
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package#getNews_Date()
	 * @model
	 * @generated
	 */
	Date getDate();

	/**
	 * Sets the value of the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.News#getDate <em>Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Date</em>' attribute.
	 * @see #getDate()
	 * @generated
	 */
	void setDate(Date value);

} // News
