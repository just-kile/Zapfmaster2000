/**
 */
package de.kile.zapfmaster2000.rest.model.zapfmaster2000;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Challenge State</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package#getChallengeState()
 * @model
 * @generated
 */
public enum ChallengeState implements Enumerator {
	/**
	 * The '<em><b>PENDING</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PENDING_VALUE
	 * @generated
	 * @ordered
	 */
	PENDING(0, "PENDING", "PENDING"),

	/**
	 * The '<em><b>DECLINED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DECLINED_VALUE
	 * @generated
	 * @ordered
	 */
	DECLINED(1, "DECLINED", "DECLINED"),

	/**
	 * The '<em><b>RUNNING</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RUNNING_VALUE
	 * @generated
	 * @ordered
	 */
	RUNNING(2, "RUNNING", "RUNNING"),

	/**
	 * The '<em><b>FINISHED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FINISHED_VALUE
	 * @generated
	 * @ordered
	 */
	FINISHED(3, "FINISHED", "FINISHED");

	/**
	 * The '<em><b>PENDING</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PENDING</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PENDING
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int PENDING_VALUE = 0;

	/**
	 * The '<em><b>DECLINED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DECLINED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DECLINED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DECLINED_VALUE = 1;

	/**
	 * The '<em><b>RUNNING</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>RUNNING</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RUNNING
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int RUNNING_VALUE = 2;

	/**
	 * The '<em><b>FINISHED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FINISHED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FINISHED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int FINISHED_VALUE = 3;

	/**
	 * An array of all the '<em><b>Challenge State</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ChallengeState[] VALUES_ARRAY =
		new ChallengeState[] {
			PENDING,
			DECLINED,
			RUNNING,
			FINISHED,
		};

	/**
	 * A public read-only list of all the '<em><b>Challenge State</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ChallengeState> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Challenge State</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ChallengeState get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ChallengeState result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Challenge State</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ChallengeState getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ChallengeState result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Challenge State</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ChallengeState get(int value) {
		switch (value) {
			case PENDING_VALUE: return PENDING;
			case DECLINED_VALUE: return DECLINED;
			case RUNNING_VALUE: return RUNNING;
			case FINISHED_VALUE: return FINISHED;
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
	private ChallengeState(int value, String name, String literal) {
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
	
} //ChallengeState
