/**
 */
package de.kile.zapfmaster2000.rest.model.zapfmaster2000;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>New Keg News</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.NewKegNews#getKeg <em>Keg</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package#getNewKegNews()
 * @model
 * @generated
 */
public interface NewKegNews extends News {
	/**
	 * Returns the value of the '<em><b>Keg</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Keg</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Keg</em>' reference.
	 * @see #setKeg(Keg)
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package#getNewKegNews_Keg()
	 * @model
	 * @generated
	 */
	Keg getKeg();

	/**
	 * Sets the value of the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.NewKegNews#getKeg <em>Keg</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Keg</em>' reference.
	 * @see #getKeg()
	 * @generated
	 */
	void setKeg(Keg value);

} // NewKegNews
