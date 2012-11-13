/**
 */
package de.kile.zapfmaster2000.rest.model.zapfmaster2000;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>News Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package#getNewsType()
 * @model
 * @generated
 */
public enum NewsType implements Enumerator {
	/**
	 * The '<em><b>Drawing</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DRAWING_VALUE
	 * @generated
	 * @ordered
	 */
	DRAWING(0, "drawing", "drawing"),

	/**
	 * The '<em><b>Achievement</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ACHIEVEMENT_VALUE
	 * @generated
	 * @ordered
	 */
	ACHIEVEMENT(1, "achievement", "achievement"),

	/**
	 * The '<em><b>Other</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OTHER_VALUE
	 * @generated
	 * @ordered
	 */
	OTHER(2, "other", "other"),

	/**
	 * The '<em><b>Challenge startet</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CHALLENGE_STARTET_VALUE
	 * @generated
	 * @ordered
	 */
	CHALLENGE_STARTET(3, "challenge_startet", "challenge_startet"),

	/**
	 * The '<em><b>Challenge done</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CHALLENGE_DONE_VALUE
	 * @generated
	 * @ordered
	 */
	CHALLENGE_DONE(4, "challenge_done", "challenge_done"),

	/**
	 * The '<em><b>Challenge declined</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CHALLENGE_DECLINED_VALUE
	 * @generated
	 * @ordered
	 */
	CHALLENGE_DECLINED(5, "challenge_declined", "challenge_declined");

	/**
	 * The '<em><b>Drawing</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Drawing</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DRAWING
	 * @model name="drawing"
	 * @generated
	 * @ordered
	 */
	public static final int DRAWING_VALUE = 0;

	/**
	 * The '<em><b>Achievement</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Achievement</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ACHIEVEMENT
	 * @model name="achievement"
	 * @generated
	 * @ordered
	 */
	public static final int ACHIEVEMENT_VALUE = 1;

	/**
	 * The '<em><b>Other</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Other</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #OTHER
	 * @model name="other"
	 * @generated
	 * @ordered
	 */
	public static final int OTHER_VALUE = 2;

	/**
	 * The '<em><b>Challenge startet</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Challenge startet</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CHALLENGE_STARTET
	 * @model name="challenge_startet"
	 * @generated
	 * @ordered
	 */
	public static final int CHALLENGE_STARTET_VALUE = 3;

	/**
	 * The '<em><b>Challenge done</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Challenge done</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CHALLENGE_DONE
	 * @model name="challenge_done"
	 * @generated
	 * @ordered
	 */
	public static final int CHALLENGE_DONE_VALUE = 4;

	/**
	 * The '<em><b>Challenge declined</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Challenge declined</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CHALLENGE_DECLINED
	 * @model name="challenge_declined"
	 * @generated
	 * @ordered
	 */
	public static final int CHALLENGE_DECLINED_VALUE = 5;

	/**
	 * An array of all the '<em><b>News Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final NewsType[] VALUES_ARRAY =
		new NewsType[] {
			DRAWING,
			ACHIEVEMENT,
			OTHER,
			CHALLENGE_STARTET,
			CHALLENGE_DONE,
			CHALLENGE_DECLINED,
		};

	/**
	 * A public read-only list of all the '<em><b>News Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<NewsType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>News Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static NewsType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			NewsType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>News Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static NewsType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			NewsType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>News Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static NewsType get(int value) {
		switch (value) {
			case DRAWING_VALUE: return DRAWING;
			case ACHIEVEMENT_VALUE: return ACHIEVEMENT;
			case OTHER_VALUE: return OTHER;
			case CHALLENGE_STARTET_VALUE: return CHALLENGE_STARTET;
			case CHALLENGE_DONE_VALUE: return CHALLENGE_DONE;
			case CHALLENGE_DECLINED_VALUE: return CHALLENGE_DECLINED;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private NewsType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //NewsType
