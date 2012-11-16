/**
 */
package de.kile.zapfmaster2000.rest.model.zapfmaster2000;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Challenge Participant</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeParticipant#getId <em>Id</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeParticipant#getChallenge <em>Challenge</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeParticipant#getTeam <em>Team</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeParticipant#isWon <em>Won</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeParticipant#getUser <em>User</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package#getChallengeParticipant()
 * @model
 * @generated
 */
public interface ChallengeParticipant extends EObject {
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
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package#getChallengeParticipant_Id()
	 * @model id="true"
	 * @generated
	 */
	long getId();

	/**
	 * Sets the value of the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeParticipant#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(long value);

	/**
	 * Returns the value of the '<em><b>Challenge</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge#getParticipants <em>Participants</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Challenge</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Challenge</em>' container reference.
	 * @see #setChallenge(Challenge)
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package#getChallengeParticipant_Challenge()
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge#getParticipants
	 * @model opposite="participants" transient="false"
	 * @generated
	 */
	Challenge getChallenge();

	/**
	 * Sets the value of the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeParticipant#getChallenge <em>Challenge</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Challenge</em>' container reference.
	 * @see #getChallenge()
	 * @generated
	 */
	void setChallenge(Challenge value);

	/**
	 * Returns the value of the '<em><b>Team</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Team</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Team</em>' attribute.
	 * @see #setTeam(int)
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package#getChallengeParticipant_Team()
	 * @model
	 * @generated
	 */
	int getTeam();

	/**
	 * Sets the value of the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeParticipant#getTeam <em>Team</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Team</em>' attribute.
	 * @see #getTeam()
	 * @generated
	 */
	void setTeam(int value);

	/**
	 * Returns the value of the '<em><b>Won</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Won</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Won</em>' attribute.
	 * @see #setWon(boolean)
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package#getChallengeParticipant_Won()
	 * @model
	 * @generated
	 */
	boolean isWon();

	/**
	 * Sets the value of the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeParticipant#isWon <em>Won</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Won</em>' attribute.
	 * @see #isWon()
	 * @generated
	 */
	void setWon(boolean value);

	/**
	 * Returns the value of the '<em><b>User</b></em>' reference list.
	 * The list contents are of type {@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.User}.
	 * It is bidirectional and its opposite is '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.User#getChallengeParticipations <em>Challenge Participations</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User</em>' reference list.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package#getChallengeParticipant_User()
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.User#getChallengeParticipations
	 * @model opposite="challengeParticipations"
	 * @generated
	 */
	EList<User> getUser();

} // ChallengeParticipant
