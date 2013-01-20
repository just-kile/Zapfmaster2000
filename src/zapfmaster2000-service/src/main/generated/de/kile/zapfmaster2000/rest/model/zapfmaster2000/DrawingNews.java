/**
 */
package de.kile.zapfmaster2000.rest.model.zapfmaster2000;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Drawing News</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.DrawingNews#getDrawing <em>Drawing</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package#getDrawingNews()
 * @model
 * @generated
 */
public interface DrawingNews extends News {
	/**
	 * Returns the value of the '<em><b>Drawing</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Drawing</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Drawing</em>' reference.
	 * @see #setDrawing(Drawing)
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package#getDrawingNews_Drawing()
	 * @model
	 * @generated
	 */
	Drawing getDrawing();

	/**
	 * Sets the value of the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.DrawingNews#getDrawing <em>Drawing</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Drawing</em>' reference.
	 * @see #getDrawing()
	 * @generated
	 */
	void setDrawing(Drawing value);

} // DrawingNews
