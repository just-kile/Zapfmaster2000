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
			case Zapfmaster2000Package.CHALLENGE: return createChallenge();
			case Zapfmaster2000Package.CHALLENGE1V1: return createChallenge1v1();
			case Zapfmaster2000Package.CHALLENGE_PARTICIPANT: return createChallengeParticipant();
			case Zapfmaster2000Package.CONFIG: return createConfig();
			case Zapfmaster2000Package.DRAWING: return createDrawing();
			case Zapfmaster2000Package.GAINED_ACHIEVEMENT: return createGainedAchievement();
			case Zapfmaster2000Package.KEG: return createKeg();
			case Zapfmaster2000Package.NEWS: return createNews();
			case Zapfmaster2000Package.MAPPING_QR_RFID: return createMappingQrRfid();
			case Zapfmaster2000Package.USER: return createUser();
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
			case Zapfmaster2000Package.CHALLENGE_TYPE:
				return createChallengeTypeFromString(eDataType, initialValue);
			case Zapfmaster2000Package.SEX:
				return createSexFromString(eDataType, initialValue);
			case Zapfmaster2000Package.NEWS_TYPE:
				return createNewsTypeFromString(eDataType, initialValue);
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
			case Zapfmaster2000Package.CHALLENGE_TYPE:
				return convertChallengeTypeToString(eDataType, instanceValue);
			case Zapfmaster2000Package.SEX:
				return convertSexToString(eDataType, instanceValue);
			case Zapfmaster2000Package.NEWS_TYPE:
				return convertNewsTypeToString(eDataType, instanceValue);
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
	public Challenge createChallenge() {
		ChallengeImpl challenge = new ChallengeImpl();
		return challenge;
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
	public ChallengeParticipant createChallengeParticipant() {
		ChallengeParticipantImpl challengeParticipant = new ChallengeParticipantImpl();
		return challengeParticipant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Config createConfig() {
		ConfigImpl config = new ConfigImpl();
		return config;
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
	public News createNews() {
		NewsImpl news = new NewsImpl();
		return news;
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
	public NewsType createNewsTypeFromString(EDataType eDataType, String initialValue) {
		NewsType result = NewsType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertNewsTypeToString(EDataType eDataType, Object instanceValue) {
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
