/**
 */
package de.kile.zapfmaster2000.rest.model.zapfmaster2000;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Factory
 * @model kind="package"
 * @generated
 */
public interface Zapfmaster2000Package extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "zapfmaster2000";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.kile.de/zapfmaster2000/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "zm2k";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Zapfmaster2000Package eINSTANCE = de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl.init();

	/**
	 * The meta object id for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.AchievementImpl <em>Achievement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.AchievementImpl
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getAchievement()
	 * @generated
	 */
	int ACHIEVEMENT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACHIEVEMENT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACHIEVEMENT__DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Image Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACHIEVEMENT__IMAGE_PATH = 2;

	/**
	 * The feature id for the '<em><b>Gained</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACHIEVEMENT__GAINED = 3;

	/**
	 * The number of structural features of the '<em>Achievement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACHIEVEMENT_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.BoxImpl <em>Box</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.BoxImpl
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getBox()
	 * @generated
	 */
	int BOX = 1;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOX__VERSION = 0;

	/**
	 * The feature id for the '<em><b>System</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOX__SYSTEM = 1;

	/**
	 * The feature id for the '<em><b>Kegs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOX__KEGS = 2;

	/**
	 * The number of structural features of the '<em>Box</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOX_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.SystemImpl <em>System</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.SystemImpl
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getSystem()
	 * @generated
	 */
	int SYSTEM = 2;

	/**
	 * The feature id for the '<em><b>Boxes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__BOXES = 0;

	/**
	 * The number of structural features of the '<em>System</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.ChallengeImpl <em>Challenge</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.ChallengeImpl
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getChallenge()
	 * @generated
	 */
	int CHALLENGE = 3;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHALLENGE__TYPE = 0;

	/**
	 * The feature id for the '<em><b>Finished</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHALLENGE__FINISHED = 1;

	/**
	 * The feature id for the '<em><b>Start Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHALLENGE__START_TIME = 2;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHALLENGE__PARTICIPANTS = 3;

	/**
	 * The number of structural features of the '<em>Challenge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHALLENGE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Challenge1v1Impl <em>Challenge1v1</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Challenge1v1Impl
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getChallenge1v1()
	 * @generated
	 */
	int CHALLENGE1V1 = 4;

	/**
	 * The feature id for the '<em><b>Challenge</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHALLENGE1V1__CHALLENGE = 0;

	/**
	 * The feature id for the '<em><b>Challenge End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHALLENGE1V1__CHALLENGE_END = 1;

	/**
	 * The number of structural features of the '<em>Challenge1v1</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHALLENGE1V1_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.ChallengeParticipantImpl <em>Challenge Participant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.ChallengeParticipantImpl
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getChallengeParticipant()
	 * @generated
	 */
	int CHALLENGE_PARTICIPANT = 5;

	/**
	 * The feature id for the '<em><b>Challenge</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHALLENGE_PARTICIPANT__CHALLENGE = 0;

	/**
	 * The feature id for the '<em><b>Team</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHALLENGE_PARTICIPANT__TEAM = 1;

	/**
	 * The feature id for the '<em><b>Won</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHALLENGE_PARTICIPANT__WON = 2;

	/**
	 * The feature id for the '<em><b>User</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHALLENGE_PARTICIPANT__USER = 3;

	/**
	 * The number of structural features of the '<em>Challenge Participant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHALLENGE_PARTICIPANT_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.ConfigImpl <em>Config</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.ConfigImpl
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getConfig()
	 * @generated
	 */
	int CONFIG = 6;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIG__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIG__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIG_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.DrawingImpl <em>Drawing</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.DrawingImpl
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getDrawing()
	 * @generated
	 */
	int DRAWING = 7;

	/**
	 * The feature id for the '<em><b>User</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRAWING__USER = 0;

	/**
	 * The feature id for the '<em><b>Keg</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRAWING__KEG = 1;

	/**
	 * The feature id for the '<em><b>Amount</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRAWING__AMOUNT = 2;

	/**
	 * The feature id for the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRAWING__DATE = 3;

	/**
	 * The feature id for the '<em><b>Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRAWING__DURATION = 4;

	/**
	 * The number of structural features of the '<em>Drawing</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRAWING_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.GainedAchievementImpl <em>Gained Achievement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.GainedAchievementImpl
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getGainedAchievement()
	 * @generated
	 */
	int GAINED_ACHIEVEMENT = 8;

	/**
	 * The feature id for the '<em><b>Achievement</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GAINED_ACHIEVEMENT__ACHIEVEMENT = 0;

	/**
	 * The feature id for the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GAINED_ACHIEVEMENT__DATE = 1;

	/**
	 * The feature id for the '<em><b>User</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GAINED_ACHIEVEMENT__USER = 2;

	/**
	 * The number of structural features of the '<em>Gained Achievement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GAINED_ACHIEVEMENT_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.KegImpl <em>Keg</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.KegImpl
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getKeg()
	 * @generated
	 */
	int KEG = 9;

	/**
	 * The feature id for the '<em><b>Brand</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEG__BRAND = 0;

	/**
	 * The feature id for the '<em><b>Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEG__SIZE = 1;

	/**
	 * The feature id for the '<em><b>Start Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEG__START_DATE = 2;

	/**
	 * The feature id for the '<em><b>End Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEG__END_DATE = 3;

	/**
	 * The feature id for the '<em><b>Drawings</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEG__DRAWINGS = 4;

	/**
	 * The feature id for the '<em><b>Box</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEG__BOX = 5;

	/**
	 * The number of structural features of the '<em>Keg</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEG_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.NewsImpl <em>News</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.NewsImpl
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getNews()
	 * @generated
	 */
	int NEWS = 10;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEWS__TYPE = 0;

	/**
	 * The feature id for the '<em><b>Contents</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEWS__CONTENTS = 1;

	/**
	 * The feature id for the '<em><b>Image Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEWS__IMAGE_PATH = 2;

	/**
	 * The number of structural features of the '<em>News</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEWS_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.MappingQrRfidImpl <em>Mapping Qr Rfid</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.MappingQrRfidImpl
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getMappingQrRfid()
	 * @generated
	 */
	int MAPPING_QR_RFID = 11;

	/**
	 * The feature id for the '<em><b>Qr Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAPPING_QR_RFID__QR_CODE = 0;

	/**
	 * The feature id for the '<em><b>Rfid Tag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAPPING_QR_RFID__RFID_TAG = 1;

	/**
	 * The number of structural features of the '<em>Mapping Qr Rfid</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAPPING_QR_RFID_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.UserImpl <em>User</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.UserImpl
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getUser()
	 * @generated
	 */
	int USER = 12;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__NAME = 0;

	/**
	 * The feature id for the '<em><b>Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__PASSWORD = 1;

	/**
	 * The feature id for the '<em><b>Image Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__IMAGE_PATH = 2;

	/**
	 * The feature id for the '<em><b>Rfid Tag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__RFID_TAG = 3;

	/**
	 * The feature id for the '<em><b>Sex</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__SEX = 4;

	/**
	 * The feature id for the '<em><b>Weight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__WEIGHT = 5;

	/**
	 * The feature id for the '<em><b>Gained</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__GAINED = 6;

	/**
	 * The feature id for the '<em><b>Drawings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__DRAWINGS = 7;

	/**
	 * The feature id for the '<em><b>Challenge Participations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__CHALLENGE_PARTICIPATIONS = 8;

	/**
	 * The number of structural features of the '<em>User</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_FEATURE_COUNT = 9;

	/**
	 * The meta object id for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeType <em>Challenge Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeType
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getChallengeType()
	 * @generated
	 */
	int CHALLENGE_TYPE = 13;

	/**
	 * The meta object id for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex <em>Sex</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getSex()
	 * @generated
	 */
	int SEX = 14;

	/**
	 * The meta object id for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.NewsType <em>News Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.NewsType
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getNewsType()
	 * @generated
	 */
	int NEWS_TYPE = 15;


	/**
	 * Returns the meta object for class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Achievement <em>Achievement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Achievement</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Achievement
	 * @generated
	 */
	EClass getAchievement();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Achievement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Achievement#getName()
	 * @see #getAchievement()
	 * @generated
	 */
	EAttribute getAchievement_Name();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Achievement#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Achievement#getDescription()
	 * @see #getAchievement()
	 * @generated
	 */
	EAttribute getAchievement_Description();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Achievement#getImagePath <em>Image Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Image Path</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Achievement#getImagePath()
	 * @see #getAchievement()
	 * @generated
	 */
	EAttribute getAchievement_ImagePath();

	/**
	 * Returns the meta object for the containment reference list '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Achievement#getGained <em>Gained</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Gained</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Achievement#getGained()
	 * @see #getAchievement()
	 * @generated
	 */
	EReference getAchievement_Gained();

	/**
	 * Returns the meta object for class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box <em>Box</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Box</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box
	 * @generated
	 */
	EClass getBox();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box#getVersion()
	 * @see #getBox()
	 * @generated
	 */
	EAttribute getBox_Version();

	/**
	 * Returns the meta object for the container reference '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box#getSystem <em>System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>System</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box#getSystem()
	 * @see #getBox()
	 * @generated
	 */
	EReference getBox_System();

	/**
	 * Returns the meta object for the containment reference list '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box#getKegs <em>Kegs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Kegs</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box#getKegs()
	 * @see #getBox()
	 * @generated
	 */
	EReference getBox_Kegs();

	/**
	 * Returns the meta object for class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.System <em>System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>System</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.System
	 * @generated
	 */
	EClass getSystem();

	/**
	 * Returns the meta object for the containment reference list '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.System#getBoxes <em>Boxes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Boxes</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.System#getBoxes()
	 * @see #getSystem()
	 * @generated
	 */
	EReference getSystem_Boxes();

	/**
	 * Returns the meta object for class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge <em>Challenge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Challenge</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge
	 * @generated
	 */
	EClass getChallenge();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge#getType()
	 * @see #getChallenge()
	 * @generated
	 */
	EAttribute getChallenge_Type();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge#isFinished <em>Finished</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Finished</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge#isFinished()
	 * @see #getChallenge()
	 * @generated
	 */
	EAttribute getChallenge_Finished();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge#getStartTime <em>Start Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start Time</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge#getStartTime()
	 * @see #getChallenge()
	 * @generated
	 */
	EAttribute getChallenge_StartTime();

	/**
	 * Returns the meta object for the containment reference list '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge#getParticipants <em>Participants</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Participants</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge#getParticipants()
	 * @see #getChallenge()
	 * @generated
	 */
	EReference getChallenge_Participants();

	/**
	 * Returns the meta object for class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1 <em>Challenge1v1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Challenge1v1</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1
	 * @generated
	 */
	EClass getChallenge1v1();

	/**
	 * Returns the meta object for the reference '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1#getChallenge <em>Challenge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Challenge</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1#getChallenge()
	 * @see #getChallenge1v1()
	 * @generated
	 */
	EReference getChallenge1v1_Challenge();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1#getChallengeEnd <em>Challenge End</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Challenge End</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1#getChallengeEnd()
	 * @see #getChallenge1v1()
	 * @generated
	 */
	EAttribute getChallenge1v1_ChallengeEnd();

	/**
	 * Returns the meta object for class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeParticipant <em>Challenge Participant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Challenge Participant</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeParticipant
	 * @generated
	 */
	EClass getChallengeParticipant();

	/**
	 * Returns the meta object for the container reference '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeParticipant#getChallenge <em>Challenge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Challenge</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeParticipant#getChallenge()
	 * @see #getChallengeParticipant()
	 * @generated
	 */
	EReference getChallengeParticipant_Challenge();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeParticipant#getTeam <em>Team</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Team</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeParticipant#getTeam()
	 * @see #getChallengeParticipant()
	 * @generated
	 */
	EAttribute getChallengeParticipant_Team();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeParticipant#isWon <em>Won</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Won</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeParticipant#isWon()
	 * @see #getChallengeParticipant()
	 * @generated
	 */
	EAttribute getChallengeParticipant_Won();

	/**
	 * Returns the meta object for the reference list '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeParticipant#getUser <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>User</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeParticipant#getUser()
	 * @see #getChallengeParticipant()
	 * @generated
	 */
	EReference getChallengeParticipant_User();

	/**
	 * Returns the meta object for class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Config <em>Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Config</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Config
	 * @generated
	 */
	EClass getConfig();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Config#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Config#getKey()
	 * @see #getConfig()
	 * @generated
	 */
	EAttribute getConfig_Key();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Config#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Config#getValue()
	 * @see #getConfig()
	 * @generated
	 */
	EAttribute getConfig_Value();

	/**
	 * Returns the meta object for class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing <em>Drawing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Drawing</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing
	 * @generated
	 */
	EClass getDrawing();

	/**
	 * Returns the meta object for the container reference '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing#getUser <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>User</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing#getUser()
	 * @see #getDrawing()
	 * @generated
	 */
	EReference getDrawing_User();

	/**
	 * Returns the meta object for the reference '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing#getKeg <em>Keg</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Keg</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing#getKeg()
	 * @see #getDrawing()
	 * @generated
	 */
	EReference getDrawing_Keg();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing#getAmount <em>Amount</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Amount</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing#getAmount()
	 * @see #getDrawing()
	 * @generated
	 */
	EAttribute getDrawing_Amount();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing#getDate <em>Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Date</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing#getDate()
	 * @see #getDrawing()
	 * @generated
	 */
	EAttribute getDrawing_Date();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing#getDuration <em>Duration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Duration</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing#getDuration()
	 * @see #getDrawing()
	 * @generated
	 */
	EAttribute getDrawing_Duration();

	/**
	 * Returns the meta object for class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.GainedAchievement <em>Gained Achievement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Gained Achievement</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.GainedAchievement
	 * @generated
	 */
	EClass getGainedAchievement();

	/**
	 * Returns the meta object for the container reference '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.GainedAchievement#getAchievement <em>Achievement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Achievement</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.GainedAchievement#getAchievement()
	 * @see #getGainedAchievement()
	 * @generated
	 */
	EReference getGainedAchievement_Achievement();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.GainedAchievement#getDate <em>Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Date</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.GainedAchievement#getDate()
	 * @see #getGainedAchievement()
	 * @generated
	 */
	EAttribute getGainedAchievement_Date();

	/**
	 * Returns the meta object for the reference '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.GainedAchievement#getUser <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>User</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.GainedAchievement#getUser()
	 * @see #getGainedAchievement()
	 * @generated
	 */
	EReference getGainedAchievement_User();

	/**
	 * Returns the meta object for class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg <em>Keg</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Keg</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg
	 * @generated
	 */
	EClass getKeg();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg#getBrand <em>Brand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Brand</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg#getBrand()
	 * @see #getKeg()
	 * @generated
	 */
	EAttribute getKeg_Brand();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg#getSize <em>Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Size</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg#getSize()
	 * @see #getKeg()
	 * @generated
	 */
	EAttribute getKeg_Size();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg#getStartDate <em>Start Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start Date</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg#getStartDate()
	 * @see #getKeg()
	 * @generated
	 */
	EAttribute getKeg_StartDate();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg#getEndDate <em>End Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>End Date</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg#getEndDate()
	 * @see #getKeg()
	 * @generated
	 */
	EAttribute getKeg_EndDate();

	/**
	 * Returns the meta object for the reference list '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg#getDrawings <em>Drawings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Drawings</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg#getDrawings()
	 * @see #getKeg()
	 * @generated
	 */
	EReference getKeg_Drawings();

	/**
	 * Returns the meta object for the container reference '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg#getBox <em>Box</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Box</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg#getBox()
	 * @see #getKeg()
	 * @generated
	 */
	EReference getKeg_Box();

	/**
	 * Returns the meta object for class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.News <em>News</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>News</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.News
	 * @generated
	 */
	EClass getNews();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.News#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.News#getType()
	 * @see #getNews()
	 * @generated
	 */
	EAttribute getNews_Type();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.News#getContents <em>Contents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Contents</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.News#getContents()
	 * @see #getNews()
	 * @generated
	 */
	EAttribute getNews_Contents();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.News#getImagePath <em>Image Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Image Path</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.News#getImagePath()
	 * @see #getNews()
	 * @generated
	 */
	EAttribute getNews_ImagePath();

	/**
	 * Returns the meta object for class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.MappingQrRfid <em>Mapping Qr Rfid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mapping Qr Rfid</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.MappingQrRfid
	 * @generated
	 */
	EClass getMappingQrRfid();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.MappingQrRfid#getQrCode <em>Qr Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Qr Code</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.MappingQrRfid#getQrCode()
	 * @see #getMappingQrRfid()
	 * @generated
	 */
	EAttribute getMappingQrRfid_QrCode();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.MappingQrRfid#getRfidTag <em>Rfid Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rfid Tag</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.MappingQrRfid#getRfidTag()
	 * @see #getMappingQrRfid()
	 * @generated
	 */
	EAttribute getMappingQrRfid_RfidTag();

	/**
	 * Returns the meta object for class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.User <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.User
	 * @generated
	 */
	EClass getUser();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.User#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.User#getName()
	 * @see #getUser()
	 * @generated
	 */
	EAttribute getUser_Name();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.User#getPassword <em>Password</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Password</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.User#getPassword()
	 * @see #getUser()
	 * @generated
	 */
	EAttribute getUser_Password();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.User#getImagePath <em>Image Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Image Path</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.User#getImagePath()
	 * @see #getUser()
	 * @generated
	 */
	EAttribute getUser_ImagePath();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.User#getRfidTag <em>Rfid Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rfid Tag</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.User#getRfidTag()
	 * @see #getUser()
	 * @generated
	 */
	EAttribute getUser_RfidTag();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.User#getSex <em>Sex</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sex</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.User#getSex()
	 * @see #getUser()
	 * @generated
	 */
	EAttribute getUser_Sex();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.User#getWeight <em>Weight</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Weight</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.User#getWeight()
	 * @see #getUser()
	 * @generated
	 */
	EAttribute getUser_Weight();

	/**
	 * Returns the meta object for the reference list '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.User#getGained <em>Gained</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Gained</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.User#getGained()
	 * @see #getUser()
	 * @generated
	 */
	EReference getUser_Gained();

	/**
	 * Returns the meta object for the containment reference list '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.User#getDrawings <em>Drawings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Drawings</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.User#getDrawings()
	 * @see #getUser()
	 * @generated
	 */
	EReference getUser_Drawings();

	/**
	 * Returns the meta object for the reference list '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.User#getChallengeParticipations <em>Challenge Participations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Challenge Participations</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.User#getChallengeParticipations()
	 * @see #getUser()
	 * @generated
	 */
	EReference getUser_ChallengeParticipations();

	/**
	 * Returns the meta object for enum '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeType <em>Challenge Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Challenge Type</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeType
	 * @generated
	 */
	EEnum getChallengeType();

	/**
	 * Returns the meta object for enum '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex <em>Sex</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Sex</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex
	 * @generated
	 */
	EEnum getSex();

	/**
	 * Returns the meta object for enum '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.NewsType <em>News Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>News Type</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.NewsType
	 * @generated
	 */
	EEnum getNewsType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Zapfmaster2000Factory getZapfmaster2000Factory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.AchievementImpl <em>Achievement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.AchievementImpl
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getAchievement()
		 * @generated
		 */
		EClass ACHIEVEMENT = eINSTANCE.getAchievement();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACHIEVEMENT__NAME = eINSTANCE.getAchievement_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACHIEVEMENT__DESCRIPTION = eINSTANCE.getAchievement_Description();

		/**
		 * The meta object literal for the '<em><b>Image Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACHIEVEMENT__IMAGE_PATH = eINSTANCE.getAchievement_ImagePath();

		/**
		 * The meta object literal for the '<em><b>Gained</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACHIEVEMENT__GAINED = eINSTANCE.getAchievement_Gained();

		/**
		 * The meta object literal for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.BoxImpl <em>Box</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.BoxImpl
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getBox()
		 * @generated
		 */
		EClass BOX = eINSTANCE.getBox();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOX__VERSION = eINSTANCE.getBox_Version();

		/**
		 * The meta object literal for the '<em><b>System</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BOX__SYSTEM = eINSTANCE.getBox_System();

		/**
		 * The meta object literal for the '<em><b>Kegs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BOX__KEGS = eINSTANCE.getBox_Kegs();

		/**
		 * The meta object literal for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.SystemImpl <em>System</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.SystemImpl
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getSystem()
		 * @generated
		 */
		EClass SYSTEM = eINSTANCE.getSystem();

		/**
		 * The meta object literal for the '<em><b>Boxes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM__BOXES = eINSTANCE.getSystem_Boxes();

		/**
		 * The meta object literal for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.ChallengeImpl <em>Challenge</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.ChallengeImpl
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getChallenge()
		 * @generated
		 */
		EClass CHALLENGE = eINSTANCE.getChallenge();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHALLENGE__TYPE = eINSTANCE.getChallenge_Type();

		/**
		 * The meta object literal for the '<em><b>Finished</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHALLENGE__FINISHED = eINSTANCE.getChallenge_Finished();

		/**
		 * The meta object literal for the '<em><b>Start Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHALLENGE__START_TIME = eINSTANCE.getChallenge_StartTime();

		/**
		 * The meta object literal for the '<em><b>Participants</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHALLENGE__PARTICIPANTS = eINSTANCE.getChallenge_Participants();

		/**
		 * The meta object literal for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Challenge1v1Impl <em>Challenge1v1</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Challenge1v1Impl
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getChallenge1v1()
		 * @generated
		 */
		EClass CHALLENGE1V1 = eINSTANCE.getChallenge1v1();

		/**
		 * The meta object literal for the '<em><b>Challenge</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHALLENGE1V1__CHALLENGE = eINSTANCE.getChallenge1v1_Challenge();

		/**
		 * The meta object literal for the '<em><b>Challenge End</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHALLENGE1V1__CHALLENGE_END = eINSTANCE.getChallenge1v1_ChallengeEnd();

		/**
		 * The meta object literal for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.ChallengeParticipantImpl <em>Challenge Participant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.ChallengeParticipantImpl
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getChallengeParticipant()
		 * @generated
		 */
		EClass CHALLENGE_PARTICIPANT = eINSTANCE.getChallengeParticipant();

		/**
		 * The meta object literal for the '<em><b>Challenge</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHALLENGE_PARTICIPANT__CHALLENGE = eINSTANCE.getChallengeParticipant_Challenge();

		/**
		 * The meta object literal for the '<em><b>Team</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHALLENGE_PARTICIPANT__TEAM = eINSTANCE.getChallengeParticipant_Team();

		/**
		 * The meta object literal for the '<em><b>Won</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHALLENGE_PARTICIPANT__WON = eINSTANCE.getChallengeParticipant_Won();

		/**
		 * The meta object literal for the '<em><b>User</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHALLENGE_PARTICIPANT__USER = eINSTANCE.getChallengeParticipant_User();

		/**
		 * The meta object literal for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.ConfigImpl <em>Config</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.ConfigImpl
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getConfig()
		 * @generated
		 */
		EClass CONFIG = eINSTANCE.getConfig();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONFIG__KEY = eINSTANCE.getConfig_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONFIG__VALUE = eINSTANCE.getConfig_Value();

		/**
		 * The meta object literal for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.DrawingImpl <em>Drawing</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.DrawingImpl
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getDrawing()
		 * @generated
		 */
		EClass DRAWING = eINSTANCE.getDrawing();

		/**
		 * The meta object literal for the '<em><b>User</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DRAWING__USER = eINSTANCE.getDrawing_User();

		/**
		 * The meta object literal for the '<em><b>Keg</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DRAWING__KEG = eINSTANCE.getDrawing_Keg();

		/**
		 * The meta object literal for the '<em><b>Amount</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DRAWING__AMOUNT = eINSTANCE.getDrawing_Amount();

		/**
		 * The meta object literal for the '<em><b>Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DRAWING__DATE = eINSTANCE.getDrawing_Date();

		/**
		 * The meta object literal for the '<em><b>Duration</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DRAWING__DURATION = eINSTANCE.getDrawing_Duration();

		/**
		 * The meta object literal for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.GainedAchievementImpl <em>Gained Achievement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.GainedAchievementImpl
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getGainedAchievement()
		 * @generated
		 */
		EClass GAINED_ACHIEVEMENT = eINSTANCE.getGainedAchievement();

		/**
		 * The meta object literal for the '<em><b>Achievement</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GAINED_ACHIEVEMENT__ACHIEVEMENT = eINSTANCE.getGainedAchievement_Achievement();

		/**
		 * The meta object literal for the '<em><b>Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GAINED_ACHIEVEMENT__DATE = eINSTANCE.getGainedAchievement_Date();

		/**
		 * The meta object literal for the '<em><b>User</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GAINED_ACHIEVEMENT__USER = eINSTANCE.getGainedAchievement_User();

		/**
		 * The meta object literal for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.KegImpl <em>Keg</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.KegImpl
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getKeg()
		 * @generated
		 */
		EClass KEG = eINSTANCE.getKeg();

		/**
		 * The meta object literal for the '<em><b>Brand</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute KEG__BRAND = eINSTANCE.getKeg_Brand();

		/**
		 * The meta object literal for the '<em><b>Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute KEG__SIZE = eINSTANCE.getKeg_Size();

		/**
		 * The meta object literal for the '<em><b>Start Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute KEG__START_DATE = eINSTANCE.getKeg_StartDate();

		/**
		 * The meta object literal for the '<em><b>End Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute KEG__END_DATE = eINSTANCE.getKeg_EndDate();

		/**
		 * The meta object literal for the '<em><b>Drawings</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference KEG__DRAWINGS = eINSTANCE.getKeg_Drawings();

		/**
		 * The meta object literal for the '<em><b>Box</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference KEG__BOX = eINSTANCE.getKeg_Box();

		/**
		 * The meta object literal for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.NewsImpl <em>News</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.NewsImpl
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getNews()
		 * @generated
		 */
		EClass NEWS = eINSTANCE.getNews();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NEWS__TYPE = eINSTANCE.getNews_Type();

		/**
		 * The meta object literal for the '<em><b>Contents</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NEWS__CONTENTS = eINSTANCE.getNews_Contents();

		/**
		 * The meta object literal for the '<em><b>Image Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NEWS__IMAGE_PATH = eINSTANCE.getNews_ImagePath();

		/**
		 * The meta object literal for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.MappingQrRfidImpl <em>Mapping Qr Rfid</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.MappingQrRfidImpl
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getMappingQrRfid()
		 * @generated
		 */
		EClass MAPPING_QR_RFID = eINSTANCE.getMappingQrRfid();

		/**
		 * The meta object literal for the '<em><b>Qr Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAPPING_QR_RFID__QR_CODE = eINSTANCE.getMappingQrRfid_QrCode();

		/**
		 * The meta object literal for the '<em><b>Rfid Tag</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAPPING_QR_RFID__RFID_TAG = eINSTANCE.getMappingQrRfid_RfidTag();

		/**
		 * The meta object literal for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.UserImpl <em>User</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.UserImpl
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getUser()
		 * @generated
		 */
		EClass USER = eINSTANCE.getUser();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER__NAME = eINSTANCE.getUser_Name();

		/**
		 * The meta object literal for the '<em><b>Password</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER__PASSWORD = eINSTANCE.getUser_Password();

		/**
		 * The meta object literal for the '<em><b>Image Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER__IMAGE_PATH = eINSTANCE.getUser_ImagePath();

		/**
		 * The meta object literal for the '<em><b>Rfid Tag</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER__RFID_TAG = eINSTANCE.getUser_RfidTag();

		/**
		 * The meta object literal for the '<em><b>Sex</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER__SEX = eINSTANCE.getUser_Sex();

		/**
		 * The meta object literal for the '<em><b>Weight</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER__WEIGHT = eINSTANCE.getUser_Weight();

		/**
		 * The meta object literal for the '<em><b>Gained</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER__GAINED = eINSTANCE.getUser_Gained();

		/**
		 * The meta object literal for the '<em><b>Drawings</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER__DRAWINGS = eINSTANCE.getUser_Drawings();

		/**
		 * The meta object literal for the '<em><b>Challenge Participations</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER__CHALLENGE_PARTICIPATIONS = eINSTANCE.getUser_ChallengeParticipations();

		/**
		 * The meta object literal for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeType <em>Challenge Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeType
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getChallengeType()
		 * @generated
		 */
		EEnum CHALLENGE_TYPE = eINSTANCE.getChallengeType();

		/**
		 * The meta object literal for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex <em>Sex</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getSex()
		 * @generated
		 */
		EEnum SEX = eINSTANCE.getSex();

		/**
		 * The meta object literal for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.NewsType <em>News Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.NewsType
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getNewsType()
		 * @generated
		 */
		EEnum NEWS_TYPE = eINSTANCE.getNewsType();

	}

} //Zapfmaster2000Package
