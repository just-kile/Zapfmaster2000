/**
 */
package de.kile.zapfmaster2000.rest.model.zapfmaster2000.util;

import de.kile.zapfmaster2000.rest.model.zapfmaster2000.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package
 * @generated
 */
public class Zapfmaster2000Switch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static Zapfmaster2000Package modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Zapfmaster2000Switch() {
		if (modelPackage == null) {
			modelPackage = Zapfmaster2000Package.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case Zapfmaster2000Package.ACHIEVEMENT: {
				Achievement achievement = (Achievement)theEObject;
				T result = caseAchievement(achievement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Zapfmaster2000Package.BOX: {
				Box box = (Box)theEObject;
				T result = caseBox(box);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Zapfmaster2000Package.ACCOUNT: {
				Account account = (Account)theEObject;
				T result = caseAccount(account);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Zapfmaster2000Package.CHALLENGE: {
				Challenge challenge = (Challenge)theEObject;
				T result = caseChallenge(challenge);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Zapfmaster2000Package.CHALLENGE1V1: {
				Challenge1v1 challenge1v1 = (Challenge1v1)theEObject;
				T result = caseChallenge1v1(challenge1v1);
				if (result == null) result = caseChallenge(challenge1v1);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Zapfmaster2000Package.DRAWING: {
				Drawing drawing = (Drawing)theEObject;
				T result = caseDrawing(drawing);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Zapfmaster2000Package.GAINED_ACHIEVEMENT: {
				GainedAchievement gainedAchievement = (GainedAchievement)theEObject;
				T result = caseGainedAchievement(gainedAchievement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Zapfmaster2000Package.KEG: {
				Keg keg = (Keg)theEObject;
				T result = caseKeg(keg);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Zapfmaster2000Package.NEWS: {
				News news = (News)theEObject;
				T result = caseNews(news);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Zapfmaster2000Package.DRAWING_NEWS: {
				DrawingNews drawingNews = (DrawingNews)theEObject;
				T result = caseDrawingNews(drawingNews);
				if (result == null) result = caseNews(drawingNews);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Zapfmaster2000Package.ACHIEVEMENT_NEWS: {
				AchievementNews achievementNews = (AchievementNews)theEObject;
				T result = caseAchievementNews(achievementNews);
				if (result == null) result = caseNews(achievementNews);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Zapfmaster2000Package.OTHER_NEWS: {
				OtherNews otherNews = (OtherNews)theEObject;
				T result = caseOtherNews(otherNews);
				if (result == null) result = caseNews(otherNews);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Zapfmaster2000Package.CHALLENGE1V1_STARTED_NEWS: {
				Challenge1v1StartedNews challenge1v1StartedNews = (Challenge1v1StartedNews)theEObject;
				T result = caseChallenge1v1StartedNews(challenge1v1StartedNews);
				if (result == null) result = caseNews(challenge1v1StartedNews);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Zapfmaster2000Package.CHALLENGE1V1_DECLINED_NEWS: {
				Challenge1v1DeclinedNews challenge1v1DeclinedNews = (Challenge1v1DeclinedNews)theEObject;
				T result = caseChallenge1v1DeclinedNews(challenge1v1DeclinedNews);
				if (result == null) result = caseNews(challenge1v1DeclinedNews);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Zapfmaster2000Package.CHALLENGE1V1_DONE_NEWS: {
				Challenge1v1DoneNews challenge1v1DoneNews = (Challenge1v1DoneNews)theEObject;
				T result = caseChallenge1v1DoneNews(challenge1v1DoneNews);
				if (result == null) result = caseNews(challenge1v1DoneNews);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Zapfmaster2000Package.NEW_KEG_NEWS: {
				NewKegNews newKegNews = (NewKegNews)theEObject;
				T result = caseNewKegNews(newKegNews);
				if (result == null) result = caseNews(newKegNews);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Zapfmaster2000Package.MAPPING_QR_RFID: {
				MappingQrRfid mappingQrRfid = (MappingQrRfid)theEObject;
				T result = caseMappingQrRfid(mappingQrRfid);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Zapfmaster2000Package.USER: {
				User user = (User)theEObject;
				T result = caseUser(user);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Zapfmaster2000Package.TOKEN: {
				Token token = (Token)theEObject;
				T result = caseToken(token);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Zapfmaster2000Package.IMAGE: {
				Image image = (Image)theEObject;
				T result = caseImage(image);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Achievement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Achievement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAchievement(Achievement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Box</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Box</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBox(Box object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Account</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Account</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAccount(Account object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Challenge</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Challenge</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseChallenge(Challenge object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Challenge1v1</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Challenge1v1</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseChallenge1v1(Challenge1v1 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Drawing</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Drawing</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDrawing(Drawing object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Gained Achievement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Gained Achievement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGainedAchievement(GainedAchievement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Keg</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Keg</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseKeg(Keg object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>News</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>News</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNews(News object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Drawing News</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Drawing News</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDrawingNews(DrawingNews object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Achievement News</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Achievement News</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAchievementNews(AchievementNews object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Other News</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Other News</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOtherNews(OtherNews object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Challenge1v1 Started News</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Challenge1v1 Started News</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseChallenge1v1StartedNews(Challenge1v1StartedNews object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Challenge1v1 Declined News</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Challenge1v1 Declined News</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseChallenge1v1DeclinedNews(Challenge1v1DeclinedNews object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Challenge1v1 Done News</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Challenge1v1 Done News</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseChallenge1v1DoneNews(Challenge1v1DoneNews object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>New Keg News</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>New Keg News</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNewKegNews(NewKegNews object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Mapping Qr Rfid</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Mapping Qr Rfid</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMappingQrRfid(MappingQrRfid object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>User</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>User</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUser(User object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Token</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Token</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseToken(Token object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Image</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Image</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseImage(Image object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //Zapfmaster2000Switch
