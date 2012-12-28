/**
 */
package de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl;

import de.kile.zapfmaster2000.rest.model.zapfmaster2000.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Zapfmaster2000FactoryImpl extends EFactoryImpl implements Zapfmaster2000Factory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Zapfmaster2000Factory init() {
		try {
			Zapfmaster2000Factory theZapfmaster2000Factory = (Zapfmaster2000Factory)EPackage.Registry.INSTANCE.getEFactory("http://www.kile.de/zapfmaster2000/1.0"); 
			if (theZapfmaster2000Factory != null) {
				return theZapfmaster2000Factory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new Zapfmaster2000FactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Zapfmaster2000FactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case Zapfmaster2000Package.ACHIEVEMENT: return createAchievement();
			case Zapfmaster2000Package.BOX: return createBox();
			case Zapfmaster2000Package.ACCOUNT: return createAccount();
			case Zapfmaster2000Package.CHALLENGE1V1: return createChallenge1v1();
			case Zapfmaster2000Package.DRAWING: return createDrawing();
			case Zapfmaster2000Package.GAINED_ACHIEVEMENT: return createGainedAchievement();
			case Zapfmaster2000Package.KEG: return createKeg();
			case Zapfmaster2000Package.DRAWING_NEWS: return createDrawingNews();
			case Zapfmaster2000Package.ACHIEVEMENT_NEWS: return createAchievementNews();
			case Zapfmaster2000Package.OTHER_NEWS: return createOtherNews();
			case Zapfmaster2000Package.CHALLENGE1V1_STARTED_NEWS: return createChallenge1v1StartedNews();
			case Zapfmaster2000Package.CHALLENGE1V1_DECLINED_NEWS: return createChallenge1v1DeclinedNews();
			case Zapfmaster2000Package.CHALLENGE1V1_DONE_NEWS: return createChallenge1v1DoneNews();
			case Zapfmaster2000Package.MAPPING_QR_RFID: return createMappingQrRfid();
			case Zapfmaster2000Package.USER: return createUser();
			case Zapfmaster2000Package.TOKEN: return createToken();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case Zapfmaster2000Package.SEX:
				return createSexFromString(eDataType, initialValue);
			case Zapfmaster2000Package.CHALLENGE_TYPE:
				return createChallengeTypeFromString(eDataType, initialValue);
			case Zapfmaster2000Package.USER_TYPE:
				return createUserTypeFromString(eDataType, initialValue);
			case Zapfmaster2000Package.CHALLENGE_STATE:
				return createChallengeStateFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case Zapfmaster2000Package.SEX:
				return convertSexToString(eDataType, instanceValue);
			case Zapfmaster2000Package.CHALLENGE_TYPE:
				return convertChallengeTypeToString(eDataType, instanceValue);
			case Zapfmaster2000Package.USER_TYPE:
				return convertUserTypeToString(eDataType, instanceValue);
			case Zapfmaster2000Package.CHALLENGE_STATE:
				return convertChallengeStateToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Achievement createAchievement() {
		AchievementImpl achievement = new AchievementImpl();
		return achievement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Box createBox() {
		BoxImpl box = new BoxImpl();
		return box;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Account createAccount() {
		AccountImpl account = new AccountImpl();
		return account;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Challenge1v1 createChallenge1v1() {
		Challenge1v1Impl challenge1v1 = new Challenge1v1Impl();
		return challenge1v1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Drawing createDrawing() {
		DrawingImpl drawing = new DrawingImpl();
		return drawing;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GainedAchievement createGainedAchievement() {
		GainedAchievementImpl gainedAchievement = new GainedAchievementImpl();
		return gainedAchievement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Keg createKeg() {
		KegImpl keg = new KegImpl();
		return keg;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DrawingNews createDrawingNews() {
		DrawingNewsImpl drawingNews = new DrawingNewsImpl();
		return drawingNews;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AchievementNews createAchievementNews() {
		AchievementNewsImpl achievementNews = new AchievementNewsImpl();
		return achievementNews;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OtherNews createOtherNews() {
		OtherNewsImpl otherNews = new OtherNewsImpl();
		return otherNews;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Challenge1v1StartedNews createChallenge1v1StartedNews() {
		Challenge1v1StartedNewsImpl challenge1v1StartedNews = new Challenge1v1StartedNewsImpl();
		return challenge1v1StartedNews;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Challenge1v1DeclinedNews createChallenge1v1DeclinedNews() {
		Challenge1v1DeclinedNewsImpl challenge1v1DeclinedNews = new Challenge1v1DeclinedNewsImpl();
		return challenge1v1DeclinedNews;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Challenge1v1DoneNews createChallenge1v1DoneNews() {
		Challenge1v1DoneNewsImpl challenge1v1DoneNews = new Challenge1v1DoneNewsImpl();
		return challenge1v1DoneNews;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MappingQrRfid createMappingQrRfid() {
		MappingQrRfidImpl mappingQrRfid = new MappingQrRfidImpl();
		return mappingQrRfid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public User createUser() {
		UserImpl user = new UserImpl();
		return user;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Token createToken() {
		TokenImpl token = new TokenImpl();
		return token;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Sex createSexFromString(EDataType eDataType, String initialValue) {
		Sex result = Sex.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSexToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChallengeType createChallengeTypeFromString(EDataType eDataType, String initialValue) {
		ChallengeType result = ChallengeType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertChallengeTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UserType createUserTypeFromString(EDataType eDataType, String initialValue) {
		UserType result = UserType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertUserTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChallengeState createChallengeStateFromString(EDataType eDataType, String initialValue) {
		ChallengeState result = ChallengeState.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertChallengeStateToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Zapfmaster2000Package getZapfmaster2000Package() {
		return (Zapfmaster2000Package)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static Zapfmaster2000Package getPackage() {
		return Zapfmaster2000Package.eINSTANCE;
	}

} //Zapfmaster2000FactoryImpl
