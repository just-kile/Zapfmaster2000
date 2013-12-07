/**
 */
package de.kile.zapfmaster2000.rest.model.zapfmaster2000;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACHIEVEMENT__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACHIEVEMENT__NAME = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACHIEVEMENT__DESCRIPTION = 2;

	/**
	 * The feature id for the '<em><b>Image Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACHIEVEMENT__IMAGE_PATH = 3;

	/**
	 * The feature id for the '<em><b>Gained</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACHIEVEMENT__GAINED = 4;

	/**
	 * The number of structural features of the '<em>Achievement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACHIEVEMENT_FEATURE_COUNT = 5;

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
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOX__ID = 0;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOX__VERSION = 1;

	/**
	 * The feature id for the '<em><b>Account</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOX__ACCOUNT = 2;

	/**
	 * The feature id for the '<em><b>Passphrase</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOX__PASSPHRASE = 3;

	/**
	 * The feature id for the '<em><b>Kegs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOX__KEGS = 4;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOX__LOCATION = 5;

	/**
	 * The feature id for the '<em><b>A0</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOX__A0 = 6;

	/**
	 * The feature id for the '<em><b>A1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOX__A1 = 7;

	/**
	 * The feature id for the '<em><b>A2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOX__A2 = 8;

	/**
	 * The number of structural features of the '<em>Box</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOX_FEATURE_COUNT = 9;

	/**
	 * The meta object id for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.AccountImpl <em>Account</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.AccountImpl
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getAccount()
	 * @generated
	 */
	int ACCOUNT = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCOUNT__ID = 0;

	/**
	 * The feature id for the '<em><b>Boxes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCOUNT__BOXES = 1;

	/**
	 * The feature id for the '<em><b>News</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCOUNT__NEWS = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCOUNT__NAME = 3;

	/**
	 * The feature id for the '<em><b>Users</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCOUNT__USERS = 4;

	/**
	 * The number of structural features of the '<em>Account</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCOUNT_FEATURE_COUNT = 5;

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
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHALLENGE__ID = 0;

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
	 * The feature id for the '<em><b>State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHALLENGE__STATE = 3;

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
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHALLENGE1V1__ID = CHALLENGE__ID;

	/**
	 * The feature id for the '<em><b>Finished</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHALLENGE1V1__FINISHED = CHALLENGE__FINISHED;

	/**
	 * The feature id for the '<em><b>Start Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHALLENGE1V1__START_TIME = CHALLENGE__START_TIME;

	/**
	 * The feature id for the '<em><b>State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHALLENGE1V1__STATE = CHALLENGE__STATE;

	/**
	 * The feature id for the '<em><b>User1</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHALLENGE1V1__USER1 = CHALLENGE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>User2</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHALLENGE1V1__USER2 = CHALLENGE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Winner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHALLENGE1V1__WINNER = CHALLENGE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHALLENGE1V1__DURATION = CHALLENGE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Challenge1v1</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHALLENGE1V1_FEATURE_COUNT = CHALLENGE_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.DrawingImpl <em>Drawing</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.DrawingImpl
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getDrawing()
	 * @generated
	 */
	int DRAWING = 5;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRAWING__ID = 0;

	/**
	 * The feature id for the '<em><b>User</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRAWING__USER = 1;

	/**
	 * The feature id for the '<em><b>Keg</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRAWING__KEG = 2;

	/**
	 * The feature id for the '<em><b>Amount</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRAWING__AMOUNT = 3;

	/**
	 * The feature id for the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRAWING__DATE = 4;

	/**
	 * The feature id for the '<em><b>Ticks</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRAWING__TICKS = 5;

	/**
	 * The number of structural features of the '<em>Drawing</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRAWING_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.GainedAchievementImpl <em>Gained Achievement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.GainedAchievementImpl
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getGainedAchievement()
	 * @generated
	 */
	int GAINED_ACHIEVEMENT = 6;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GAINED_ACHIEVEMENT__ID = 0;

	/**
	 * The feature id for the '<em><b>Achievement</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GAINED_ACHIEVEMENT__ACHIEVEMENT = 1;

	/**
	 * The feature id for the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GAINED_ACHIEVEMENT__DATE = 2;

	/**
	 * The feature id for the '<em><b>User</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GAINED_ACHIEVEMENT__USER = 3;

	/**
	 * The number of structural features of the '<em>Gained Achievement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GAINED_ACHIEVEMENT_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.KegImpl <em>Keg</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.KegImpl
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getKeg()
	 * @generated
	 */
	int KEG = 7;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEG__ID = 0;

	/**
	 * The feature id for the '<em><b>Brand</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEG__BRAND = 1;

	/**
	 * The feature id for the '<em><b>Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEG__SIZE = 2;

	/**
	 * The feature id for the '<em><b>Start Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEG__START_DATE = 3;

	/**
	 * The feature id for the '<em><b>End Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEG__END_DATE = 4;

	/**
	 * The feature id for the '<em><b>Drawings</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEG__DRAWINGS = 5;

	/**
	 * The feature id for the '<em><b>Box</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEG__BOX = 6;

	/**
	 * The number of structural features of the '<em>Keg</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEG_FEATURE_COUNT = 7;

	/**
	 * The meta object id for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.NewsImpl <em>News</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.NewsImpl
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getNews()
	 * @generated
	 */
	int NEWS = 8;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEWS__ID = 0;

	/**
	 * The feature id for the '<em><b>Account</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEWS__ACCOUNT = 1;

	/**
	 * The feature id for the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEWS__DATE = 2;

	/**
	 * The number of structural features of the '<em>News</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEWS_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.DrawingNewsImpl <em>Drawing News</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.DrawingNewsImpl
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getDrawingNews()
	 * @generated
	 */
	int DRAWING_NEWS = 9;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRAWING_NEWS__ID = NEWS__ID;

	/**
	 * The feature id for the '<em><b>Account</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRAWING_NEWS__ACCOUNT = NEWS__ACCOUNT;

	/**
	 * The feature id for the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRAWING_NEWS__DATE = NEWS__DATE;

	/**
	 * The feature id for the '<em><b>Drawing</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRAWING_NEWS__DRAWING = NEWS_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Drawing News</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRAWING_NEWS_FEATURE_COUNT = NEWS_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.AchievementNewsImpl <em>Achievement News</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.AchievementNewsImpl
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getAchievementNews()
	 * @generated
	 */
	int ACHIEVEMENT_NEWS = 10;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACHIEVEMENT_NEWS__ID = NEWS__ID;

	/**
	 * The feature id for the '<em><b>Account</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACHIEVEMENT_NEWS__ACCOUNT = NEWS__ACCOUNT;

	/**
	 * The feature id for the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACHIEVEMENT_NEWS__DATE = NEWS__DATE;

	/**
	 * The feature id for the '<em><b>Gained Achievment</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACHIEVEMENT_NEWS__GAINED_ACHIEVMENT = NEWS_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Achievement News</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACHIEVEMENT_NEWS_FEATURE_COUNT = NEWS_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.OtherNewsImpl <em>Other News</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.OtherNewsImpl
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getOtherNews()
	 * @generated
	 */
	int OTHER_NEWS = 11;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OTHER_NEWS__ID = NEWS__ID;

	/**
	 * The feature id for the '<em><b>Account</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OTHER_NEWS__ACCOUNT = NEWS__ACCOUNT;

	/**
	 * The feature id for the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OTHER_NEWS__DATE = NEWS__DATE;

	/**
	 * The feature id for the '<em><b>Contents</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OTHER_NEWS__CONTENTS = NEWS_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Image Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OTHER_NEWS__IMAGE_PATH = NEWS_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Other News</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OTHER_NEWS_FEATURE_COUNT = NEWS_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Challenge1v1StartedNewsImpl <em>Challenge1v1 Started News</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Challenge1v1StartedNewsImpl
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getChallenge1v1StartedNews()
	 * @generated
	 */
	int CHALLENGE1V1_STARTED_NEWS = 12;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHALLENGE1V1_STARTED_NEWS__ID = NEWS__ID;

	/**
	 * The feature id for the '<em><b>Account</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHALLENGE1V1_STARTED_NEWS__ACCOUNT = NEWS__ACCOUNT;

	/**
	 * The feature id for the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHALLENGE1V1_STARTED_NEWS__DATE = NEWS__DATE;

	/**
	 * The feature id for the '<em><b>Challenge</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHALLENGE1V1_STARTED_NEWS__CHALLENGE = NEWS_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Challenge1v1 Started News</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHALLENGE1V1_STARTED_NEWS_FEATURE_COUNT = NEWS_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Challenge1v1DeclinedNewsImpl <em>Challenge1v1 Declined News</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Challenge1v1DeclinedNewsImpl
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getChallenge1v1DeclinedNews()
	 * @generated
	 */
	int CHALLENGE1V1_DECLINED_NEWS = 13;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHALLENGE1V1_DECLINED_NEWS__ID = NEWS__ID;

	/**
	 * The feature id for the '<em><b>Account</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHALLENGE1V1_DECLINED_NEWS__ACCOUNT = NEWS__ACCOUNT;

	/**
	 * The feature id for the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHALLENGE1V1_DECLINED_NEWS__DATE = NEWS__DATE;

	/**
	 * The feature id for the '<em><b>Challenge</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHALLENGE1V1_DECLINED_NEWS__CHALLENGE = NEWS_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Challenge1v1 Declined News</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHALLENGE1V1_DECLINED_NEWS_FEATURE_COUNT = NEWS_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Challenge1v1DoneNewsImpl <em>Challenge1v1 Done News</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Challenge1v1DoneNewsImpl
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getChallenge1v1DoneNews()
	 * @generated
	 */
	int CHALLENGE1V1_DONE_NEWS = 14;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHALLENGE1V1_DONE_NEWS__ID = NEWS__ID;

	/**
	 * The feature id for the '<em><b>Account</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHALLENGE1V1_DONE_NEWS__ACCOUNT = NEWS__ACCOUNT;

	/**
	 * The feature id for the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHALLENGE1V1_DONE_NEWS__DATE = NEWS__DATE;

	/**
	 * The feature id for the '<em><b>Challenge</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHALLENGE1V1_DONE_NEWS__CHALLENGE = NEWS_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Challenge1v1 Done News</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHALLENGE1V1_DONE_NEWS_FEATURE_COUNT = NEWS_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.NewKegNewsImpl <em>New Keg News</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.NewKegNewsImpl
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getNewKegNews()
	 * @generated
	 */
	int NEW_KEG_NEWS = 15;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_KEG_NEWS__ID = NEWS__ID;

	/**
	 * The feature id for the '<em><b>Account</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_KEG_NEWS__ACCOUNT = NEWS__ACCOUNT;

	/**
	 * The feature id for the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_KEG_NEWS__DATE = NEWS__DATE;

	/**
	 * The feature id for the '<em><b>Keg</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_KEG_NEWS__KEG = NEWS_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>New Keg News</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_KEG_NEWS_FEATURE_COUNT = NEWS_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.NewUserNewsImpl <em>New User News</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.NewUserNewsImpl
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getNewUserNews()
	 * @generated
	 */
	int NEW_USER_NEWS = 16;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_USER_NEWS__ID = NEWS__ID;

	/**
	 * The feature id for the '<em><b>Account</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_USER_NEWS__ACCOUNT = NEWS__ACCOUNT;

	/**
	 * The feature id for the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_USER_NEWS__DATE = NEWS__DATE;

	/**
	 * The feature id for the '<em><b>User</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_USER_NEWS__USER = NEWS_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>New User News</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_USER_NEWS_FEATURE_COUNT = NEWS_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.MappingQrRfidImpl <em>Mapping Qr Rfid</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.MappingQrRfidImpl
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getMappingQrRfid()
	 * @generated
	 */
	int MAPPING_QR_RFID = 17;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAPPING_QR_RFID__ID = 0;

	/**
	 * The feature id for the '<em><b>Qr Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAPPING_QR_RFID__QR_CODE = 1;

	/**
	 * The feature id for the '<em><b>Rfid Tag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAPPING_QR_RFID__RFID_TAG = 2;

	/**
	 * The number of structural features of the '<em>Mapping Qr Rfid</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAPPING_QR_RFID_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.UserImpl <em>User</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.UserImpl
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getUser()
	 * @generated
	 */
	int USER = 18;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__NAME = 1;

	/**
	 * The feature id for the '<em><b>Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__PASSWORD = 2;

	/**
	 * The feature id for the '<em><b>Image Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__IMAGE_PATH = 3;

	/**
	 * The feature id for the '<em><b>Rfid Tag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__RFID_TAG = 4;

	/**
	 * The feature id for the '<em><b>Sex</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__SEX = 5;

	/**
	 * The feature id for the '<em><b>Weight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__WEIGHT = 6;

	/**
	 * The feature id for the '<em><b>Gained</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__GAINED = 7;

	/**
	 * The feature id for the '<em><b>Drawings</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__DRAWINGS = 8;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__TYPE = 9;

	/**
	 * The feature id for the '<em><b>Account</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__ACCOUNT = 10;

	/**
	 * The number of structural features of the '<em>User</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_FEATURE_COUNT = 11;

	/**
	 * The meta object id for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.TokenImpl <em>Token</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.TokenImpl
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getToken()
	 * @generated
	 */
	int TOKEN = 19;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOKEN__ID = 0;

	/**
	 * The feature id for the '<em><b>Token</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOKEN__TOKEN = 1;

	/**
	 * The feature id for the '<em><b>Account</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOKEN__ACCOUNT = 2;

	/**
	 * The feature id for the '<em><b>User</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOKEN__USER = 3;

	/**
	 * The feature id for the '<em><b>Admin</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOKEN__ADMIN = 4;

	/**
	 * The number of structural features of the '<em>Token</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOKEN_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.ImageImpl <em>Image</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.ImageImpl
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getImage()
	 * @generated
	 */
	int IMAGE = 20;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__ID = 0;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__PATH = 1;

	/**
	 * The feature id for the '<em><b>Content Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__CONTENT_TYPE = 2;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__CONTENT = 3;

	/**
	 * The feature id for the '<em><b>Content Big</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__CONTENT_BIG = 4;

	/**
	 * The number of structural features of the '<em>Image</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.TicksImpl <em>Ticks</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.TicksImpl
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getTicks()
	 * @generated
	 */
	int TICKS = 21;

	/**
	 * The feature id for the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TICKS__DATE = 0;

	/**
	 * The feature id for the '<em><b>Ticks</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TICKS__TICKS = 1;

	/**
	 * The number of structural features of the '<em>Ticks</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TICKS_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.AdminImpl <em>Admin</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.AdminImpl
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getAdmin()
	 * @generated
	 */
	int ADMIN = 22;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADMIN__NAME = 0;

	/**
	 * The feature id for the '<em><b>Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADMIN__PASSWORD = 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADMIN__TYPE = 2;

	/**
	 * The feature id for the '<em><b>Account</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADMIN__ACCOUNT = 3;

	/**
	 * The number of structural features of the '<em>Admin</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADMIN_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex <em>Sex</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getSex()
	 * @generated
	 */
	int SEX = 23;

	/**
	 * The meta object id for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeType <em>Challenge Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeType
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getChallengeType()
	 * @generated
	 */
	int CHALLENGE_TYPE = 24;

	/**
	 * The meta object id for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType <em>User Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getUserType()
	 * @generated
	 */
	int USER_TYPE = 25;

	/**
	 * The meta object id for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeState <em>Challenge State</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeState
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getChallengeState()
	 * @generated
	 */
	int CHALLENGE_STATE = 26;


	/**
	 * The meta object id for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.AdminType <em>Admin Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.AdminType
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getAdminType()
	 * @generated
	 */
	int ADMIN_TYPE = 27;

	/**
	 * The meta object id for the '<em>Blob</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.sql.Blob
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getBlob()
	 * @generated
	 */
	int BLOB = 28;


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
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Achievement#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Achievement#getId()
	 * @see #getAchievement()
	 * @generated
	 */
	EAttribute getAchievement_Id();

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
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box#getId()
	 * @see #getBox()
	 * @generated
	 */
	EAttribute getBox_Id();

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
	 * Returns the meta object for the container reference '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box#getAccount <em>Account</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Account</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box#getAccount()
	 * @see #getBox()
	 * @generated
	 */
	EReference getBox_Account();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box#getPassphrase <em>Passphrase</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Passphrase</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box#getPassphrase()
	 * @see #getBox()
	 * @generated
	 */
	EAttribute getBox_Passphrase();

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
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box#getLocation <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Location</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box#getLocation()
	 * @see #getBox()
	 * @generated
	 */
	EAttribute getBox_Location();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box#getA0 <em>A0</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>A0</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box#getA0()
	 * @see #getBox()
	 * @generated
	 */
	EAttribute getBox_A0();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box#getA1 <em>A1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>A1</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box#getA1()
	 * @see #getBox()
	 * @generated
	 */
	EAttribute getBox_A1();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box#getA2 <em>A2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>A2</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box#getA2()
	 * @see #getBox()
	 * @generated
	 */
	EAttribute getBox_A2();

	/**
	 * Returns the meta object for class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account <em>Account</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Account</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account
	 * @generated
	 */
	EClass getAccount();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account#getId()
	 * @see #getAccount()
	 * @generated
	 */
	EAttribute getAccount_Id();

	/**
	 * Returns the meta object for the containment reference list '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account#getBoxes <em>Boxes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Boxes</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account#getBoxes()
	 * @see #getAccount()
	 * @generated
	 */
	EReference getAccount_Boxes();

	/**
	 * Returns the meta object for the containment reference list '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account#getNews <em>News</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>News</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account#getNews()
	 * @see #getAccount()
	 * @generated
	 */
	EReference getAccount_News();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account#getName()
	 * @see #getAccount()
	 * @generated
	 */
	EAttribute getAccount_Name();

	/**
	 * Returns the meta object for the reference list '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account#getUsers <em>Users</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Users</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account#getUsers()
	 * @see #getAccount()
	 * @generated
	 */
	EReference getAccount_Users();

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
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge#getId()
	 * @see #getChallenge()
	 * @generated
	 */
	EAttribute getChallenge_Id();

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
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge#getState <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>State</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge#getState()
	 * @see #getChallenge()
	 * @generated
	 */
	EAttribute getChallenge_State();

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
	 * Returns the meta object for the reference '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1#getUser1 <em>User1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>User1</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1#getUser1()
	 * @see #getChallenge1v1()
	 * @generated
	 */
	EReference getChallenge1v1_User1();

	/**
	 * Returns the meta object for the reference '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1#getUser2 <em>User2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>User2</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1#getUser2()
	 * @see #getChallenge1v1()
	 * @generated
	 */
	EReference getChallenge1v1_User2();

	/**
	 * Returns the meta object for the reference '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1#getWinner <em>Winner</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Winner</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1#getWinner()
	 * @see #getChallenge1v1()
	 * @generated
	 */
	EReference getChallenge1v1_Winner();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1#getDuration <em>Duration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Duration</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1#getDuration()
	 * @see #getChallenge1v1()
	 * @generated
	 */
	EAttribute getChallenge1v1_Duration();

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
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing#getId()
	 * @see #getDrawing()
	 * @generated
	 */
	EAttribute getDrawing_Id();

	/**
	 * Returns the meta object for the reference '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing#getUser <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>User</em>'.
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
	 * Returns the meta object for the containment reference list '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing#getTicks <em>Ticks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Ticks</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing#getTicks()
	 * @see #getDrawing()
	 * @generated
	 */
	EReference getDrawing_Ticks();

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
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.GainedAchievement#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.GainedAchievement#getId()
	 * @see #getGainedAchievement()
	 * @generated
	 */
	EAttribute getGainedAchievement_Id();

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
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg#getId()
	 * @see #getKeg()
	 * @generated
	 */
	EAttribute getKeg_Id();

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
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.News#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.News#getId()
	 * @see #getNews()
	 * @generated
	 */
	EAttribute getNews_Id();

	/**
	 * Returns the meta object for the container reference '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.News#getAccount <em>Account</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Account</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.News#getAccount()
	 * @see #getNews()
	 * @generated
	 */
	EReference getNews_Account();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.News#getDate <em>Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Date</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.News#getDate()
	 * @see #getNews()
	 * @generated
	 */
	EAttribute getNews_Date();

	/**
	 * Returns the meta object for class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.DrawingNews <em>Drawing News</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Drawing News</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.DrawingNews
	 * @generated
	 */
	EClass getDrawingNews();

	/**
	 * Returns the meta object for the reference '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.DrawingNews#getDrawing <em>Drawing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Drawing</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.DrawingNews#getDrawing()
	 * @see #getDrawingNews()
	 * @generated
	 */
	EReference getDrawingNews_Drawing();

	/**
	 * Returns the meta object for class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.AchievementNews <em>Achievement News</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Achievement News</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.AchievementNews
	 * @generated
	 */
	EClass getAchievementNews();

	/**
	 * Returns the meta object for the reference '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.AchievementNews#getGainedAchievment <em>Gained Achievment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Gained Achievment</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.AchievementNews#getGainedAchievment()
	 * @see #getAchievementNews()
	 * @generated
	 */
	EReference getAchievementNews_GainedAchievment();

	/**
	 * Returns the meta object for class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.OtherNews <em>Other News</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Other News</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.OtherNews
	 * @generated
	 */
	EClass getOtherNews();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.OtherNews#getContents <em>Contents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Contents</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.OtherNews#getContents()
	 * @see #getOtherNews()
	 * @generated
	 */
	EAttribute getOtherNews_Contents();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.OtherNews#getImagePath <em>Image Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Image Path</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.OtherNews#getImagePath()
	 * @see #getOtherNews()
	 * @generated
	 */
	EAttribute getOtherNews_ImagePath();

	/**
	 * Returns the meta object for class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1StartedNews <em>Challenge1v1 Started News</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Challenge1v1 Started News</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1StartedNews
	 * @generated
	 */
	EClass getChallenge1v1StartedNews();

	/**
	 * Returns the meta object for the reference '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1StartedNews#getChallenge <em>Challenge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Challenge</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1StartedNews#getChallenge()
	 * @see #getChallenge1v1StartedNews()
	 * @generated
	 */
	EReference getChallenge1v1StartedNews_Challenge();

	/**
	 * Returns the meta object for class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1DeclinedNews <em>Challenge1v1 Declined News</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Challenge1v1 Declined News</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1DeclinedNews
	 * @generated
	 */
	EClass getChallenge1v1DeclinedNews();

	/**
	 * Returns the meta object for the reference '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1DeclinedNews#getChallenge <em>Challenge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Challenge</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1DeclinedNews#getChallenge()
	 * @see #getChallenge1v1DeclinedNews()
	 * @generated
	 */
	EReference getChallenge1v1DeclinedNews_Challenge();

	/**
	 * Returns the meta object for class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1DoneNews <em>Challenge1v1 Done News</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Challenge1v1 Done News</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1DoneNews
	 * @generated
	 */
	EClass getChallenge1v1DoneNews();

	/**
	 * Returns the meta object for the reference '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1DoneNews#getChallenge <em>Challenge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Challenge</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1DoneNews#getChallenge()
	 * @see #getChallenge1v1DoneNews()
	 * @generated
	 */
	EReference getChallenge1v1DoneNews_Challenge();

	/**
	 * Returns the meta object for class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.NewKegNews <em>New Keg News</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>New Keg News</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.NewKegNews
	 * @generated
	 */
	EClass getNewKegNews();

	/**
	 * Returns the meta object for the reference '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.NewKegNews#getKeg <em>Keg</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Keg</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.NewKegNews#getKeg()
	 * @see #getNewKegNews()
	 * @generated
	 */
	EReference getNewKegNews_Keg();

	/**
	 * Returns the meta object for class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.NewUserNews <em>New User News</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>New User News</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.NewUserNews
	 * @generated
	 */
	EClass getNewUserNews();

	/**
	 * Returns the meta object for the reference '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.NewUserNews#getUser <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>User</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.NewUserNews#getUser()
	 * @see #getNewUserNews()
	 * @generated
	 */
	EReference getNewUserNews_User();

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
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.MappingQrRfid#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.MappingQrRfid#getId()
	 * @see #getMappingQrRfid()
	 * @generated
	 */
	EAttribute getMappingQrRfid_Id();

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
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.User#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.User#getId()
	 * @see #getUser()
	 * @generated
	 */
	EAttribute getUser_Id();

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
	 * Returns the meta object for the reference list '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.User#getDrawings <em>Drawings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Drawings</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.User#getDrawings()
	 * @see #getUser()
	 * @generated
	 */
	EReference getUser_Drawings();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.User#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.User#getType()
	 * @see #getUser()
	 * @generated
	 */
	EAttribute getUser_Type();

	/**
	 * Returns the meta object for the reference '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.User#getAccount <em>Account</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Account</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.User#getAccount()
	 * @see #getUser()
	 * @generated
	 */
	EReference getUser_Account();

	/**
	 * Returns the meta object for class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Token <em>Token</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Token</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Token
	 * @generated
	 */
	EClass getToken();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Token#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Token#getId()
	 * @see #getToken()
	 * @generated
	 */
	EAttribute getToken_Id();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Token#getToken <em>Token</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Token</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Token#getToken()
	 * @see #getToken()
	 * @generated
	 */
	EAttribute getToken_Token();

	/**
	 * Returns the meta object for the reference '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Token#getAccount <em>Account</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Account</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Token#getAccount()
	 * @see #getToken()
	 * @generated
	 */
	EReference getToken_Account();

	/**
	 * Returns the meta object for the reference '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Token#getUser <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>User</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Token#getUser()
	 * @see #getToken()
	 * @generated
	 */
	EReference getToken_User();

	/**
	 * Returns the meta object for the reference '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Token#getAdmin <em>Admin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Admin</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Token#getAdmin()
	 * @see #getToken()
	 * @generated
	 */
	EReference getToken_Admin();

	/**
	 * Returns the meta object for class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Image <em>Image</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Image</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Image
	 * @generated
	 */
	EClass getImage();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Image#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Image#getId()
	 * @see #getImage()
	 * @generated
	 */
	EAttribute getImage_Id();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Image#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Path</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Image#getPath()
	 * @see #getImage()
	 * @generated
	 */
	EAttribute getImage_Path();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Image#getContentType <em>Content Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Content Type</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Image#getContentType()
	 * @see #getImage()
	 * @generated
	 */
	EAttribute getImage_ContentType();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Image#getContent <em>Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Content</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Image#getContent()
	 * @see #getImage()
	 * @generated
	 */
	EAttribute getImage_Content();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Image#getContentBig <em>Content Big</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Content Big</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Image#getContentBig()
	 * @see #getImage()
	 * @generated
	 */
	EAttribute getImage_ContentBig();

	/**
	 * Returns the meta object for class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Ticks <em>Ticks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ticks</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Ticks
	 * @generated
	 */
	EClass getTicks();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Ticks#getDate <em>Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Date</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Ticks#getDate()
	 * @see #getTicks()
	 * @generated
	 */
	EAttribute getTicks_Date();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Ticks#getTicks <em>Ticks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ticks</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Ticks#getTicks()
	 * @see #getTicks()
	 * @generated
	 */
	EAttribute getTicks_Ticks();

	/**
	 * Returns the meta object for class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Admin <em>Admin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Admin</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Admin
	 * @generated
	 */
	EClass getAdmin();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Admin#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Admin#getName()
	 * @see #getAdmin()
	 * @generated
	 */
	EAttribute getAdmin_Name();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Admin#getPassword <em>Password</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Password</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Admin#getPassword()
	 * @see #getAdmin()
	 * @generated
	 */
	EAttribute getAdmin_Password();

	/**
	 * Returns the meta object for the attribute '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Admin#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Admin#getType()
	 * @see #getAdmin()
	 * @generated
	 */
	EAttribute getAdmin_Type();

	/**
	 * Returns the meta object for the reference '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Admin#getAccount <em>Account</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Account</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Admin#getAccount()
	 * @see #getAdmin()
	 * @generated
	 */
	EReference getAdmin_Account();

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
	 * Returns the meta object for enum '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeType <em>Challenge Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Challenge Type</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeType
	 * @generated
	 */
	EEnum getChallengeType();

	/**
	 * Returns the meta object for enum '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType <em>User Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>User Type</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType
	 * @generated
	 */
	EEnum getUserType();

	/**
	 * Returns the meta object for enum '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeState <em>Challenge State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Challenge State</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeState
	 * @generated
	 */
	EEnum getChallengeState();

	/**
	 * Returns the meta object for enum '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.AdminType <em>Admin Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Admin Type</em>'.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.AdminType
	 * @generated
	 */
	EEnum getAdminType();

	/**
	 * Returns the meta object for data type '{@link java.sql.Blob <em>Blob</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Blob</em>'.
	 * @see java.sql.Blob
	 * @model instanceClass="java.sql.Blob"
	 * @generated
	 */
	EDataType getBlob();

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
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACHIEVEMENT__ID = eINSTANCE.getAchievement_Id();

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
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOX__ID = eINSTANCE.getBox_Id();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOX__VERSION = eINSTANCE.getBox_Version();

		/**
		 * The meta object literal for the '<em><b>Account</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BOX__ACCOUNT = eINSTANCE.getBox_Account();

		/**
		 * The meta object literal for the '<em><b>Passphrase</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOX__PASSPHRASE = eINSTANCE.getBox_Passphrase();

		/**
		 * The meta object literal for the '<em><b>Kegs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BOX__KEGS = eINSTANCE.getBox_Kegs();

		/**
		 * The meta object literal for the '<em><b>Location</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOX__LOCATION = eINSTANCE.getBox_Location();

		/**
		 * The meta object literal for the '<em><b>A0</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOX__A0 = eINSTANCE.getBox_A0();

		/**
		 * The meta object literal for the '<em><b>A1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOX__A1 = eINSTANCE.getBox_A1();

		/**
		 * The meta object literal for the '<em><b>A2</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOX__A2 = eINSTANCE.getBox_A2();

		/**
		 * The meta object literal for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.AccountImpl <em>Account</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.AccountImpl
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getAccount()
		 * @generated
		 */
		EClass ACCOUNT = eINSTANCE.getAccount();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACCOUNT__ID = eINSTANCE.getAccount_Id();

		/**
		 * The meta object literal for the '<em><b>Boxes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACCOUNT__BOXES = eINSTANCE.getAccount_Boxes();

		/**
		 * The meta object literal for the '<em><b>News</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACCOUNT__NEWS = eINSTANCE.getAccount_News();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACCOUNT__NAME = eINSTANCE.getAccount_Name();

		/**
		 * The meta object literal for the '<em><b>Users</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACCOUNT__USERS = eINSTANCE.getAccount_Users();

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
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHALLENGE__ID = eINSTANCE.getChallenge_Id();

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
		 * The meta object literal for the '<em><b>State</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHALLENGE__STATE = eINSTANCE.getChallenge_State();

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
		 * The meta object literal for the '<em><b>User1</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHALLENGE1V1__USER1 = eINSTANCE.getChallenge1v1_User1();

		/**
		 * The meta object literal for the '<em><b>User2</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHALLENGE1V1__USER2 = eINSTANCE.getChallenge1v1_User2();

		/**
		 * The meta object literal for the '<em><b>Winner</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHALLENGE1V1__WINNER = eINSTANCE.getChallenge1v1_Winner();

		/**
		 * The meta object literal for the '<em><b>Duration</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHALLENGE1V1__DURATION = eINSTANCE.getChallenge1v1_Duration();

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
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DRAWING__ID = eINSTANCE.getDrawing_Id();

		/**
		 * The meta object literal for the '<em><b>User</b></em>' reference feature.
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
		 * The meta object literal for the '<em><b>Ticks</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DRAWING__TICKS = eINSTANCE.getDrawing_Ticks();

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
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GAINED_ACHIEVEMENT__ID = eINSTANCE.getGainedAchievement_Id();

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
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute KEG__ID = eINSTANCE.getKeg_Id();

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
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NEWS__ID = eINSTANCE.getNews_Id();

		/**
		 * The meta object literal for the '<em><b>Account</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NEWS__ACCOUNT = eINSTANCE.getNews_Account();

		/**
		 * The meta object literal for the '<em><b>Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NEWS__DATE = eINSTANCE.getNews_Date();

		/**
		 * The meta object literal for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.DrawingNewsImpl <em>Drawing News</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.DrawingNewsImpl
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getDrawingNews()
		 * @generated
		 */
		EClass DRAWING_NEWS = eINSTANCE.getDrawingNews();

		/**
		 * The meta object literal for the '<em><b>Drawing</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DRAWING_NEWS__DRAWING = eINSTANCE.getDrawingNews_Drawing();

		/**
		 * The meta object literal for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.AchievementNewsImpl <em>Achievement News</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.AchievementNewsImpl
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getAchievementNews()
		 * @generated
		 */
		EClass ACHIEVEMENT_NEWS = eINSTANCE.getAchievementNews();

		/**
		 * The meta object literal for the '<em><b>Gained Achievment</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACHIEVEMENT_NEWS__GAINED_ACHIEVMENT = eINSTANCE.getAchievementNews_GainedAchievment();

		/**
		 * The meta object literal for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.OtherNewsImpl <em>Other News</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.OtherNewsImpl
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getOtherNews()
		 * @generated
		 */
		EClass OTHER_NEWS = eINSTANCE.getOtherNews();

		/**
		 * The meta object literal for the '<em><b>Contents</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OTHER_NEWS__CONTENTS = eINSTANCE.getOtherNews_Contents();

		/**
		 * The meta object literal for the '<em><b>Image Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OTHER_NEWS__IMAGE_PATH = eINSTANCE.getOtherNews_ImagePath();

		/**
		 * The meta object literal for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Challenge1v1StartedNewsImpl <em>Challenge1v1 Started News</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Challenge1v1StartedNewsImpl
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getChallenge1v1StartedNews()
		 * @generated
		 */
		EClass CHALLENGE1V1_STARTED_NEWS = eINSTANCE.getChallenge1v1StartedNews();

		/**
		 * The meta object literal for the '<em><b>Challenge</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHALLENGE1V1_STARTED_NEWS__CHALLENGE = eINSTANCE.getChallenge1v1StartedNews_Challenge();

		/**
		 * The meta object literal for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Challenge1v1DeclinedNewsImpl <em>Challenge1v1 Declined News</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Challenge1v1DeclinedNewsImpl
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getChallenge1v1DeclinedNews()
		 * @generated
		 */
		EClass CHALLENGE1V1_DECLINED_NEWS = eINSTANCE.getChallenge1v1DeclinedNews();

		/**
		 * The meta object literal for the '<em><b>Challenge</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHALLENGE1V1_DECLINED_NEWS__CHALLENGE = eINSTANCE.getChallenge1v1DeclinedNews_Challenge();

		/**
		 * The meta object literal for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Challenge1v1DoneNewsImpl <em>Challenge1v1 Done News</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Challenge1v1DoneNewsImpl
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getChallenge1v1DoneNews()
		 * @generated
		 */
		EClass CHALLENGE1V1_DONE_NEWS = eINSTANCE.getChallenge1v1DoneNews();

		/**
		 * The meta object literal for the '<em><b>Challenge</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHALLENGE1V1_DONE_NEWS__CHALLENGE = eINSTANCE.getChallenge1v1DoneNews_Challenge();

		/**
		 * The meta object literal for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.NewKegNewsImpl <em>New Keg News</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.NewKegNewsImpl
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getNewKegNews()
		 * @generated
		 */
		EClass NEW_KEG_NEWS = eINSTANCE.getNewKegNews();

		/**
		 * The meta object literal for the '<em><b>Keg</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NEW_KEG_NEWS__KEG = eINSTANCE.getNewKegNews_Keg();

		/**
		 * The meta object literal for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.NewUserNewsImpl <em>New User News</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.NewUserNewsImpl
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getNewUserNews()
		 * @generated
		 */
		EClass NEW_USER_NEWS = eINSTANCE.getNewUserNews();

		/**
		 * The meta object literal for the '<em><b>User</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NEW_USER_NEWS__USER = eINSTANCE.getNewUserNews_User();

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
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAPPING_QR_RFID__ID = eINSTANCE.getMappingQrRfid_Id();

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
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER__ID = eINSTANCE.getUser_Id();

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
		 * The meta object literal for the '<em><b>Drawings</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER__DRAWINGS = eINSTANCE.getUser_Drawings();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER__TYPE = eINSTANCE.getUser_Type();

		/**
		 * The meta object literal for the '<em><b>Account</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER__ACCOUNT = eINSTANCE.getUser_Account();

		/**
		 * The meta object literal for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.TokenImpl <em>Token</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.TokenImpl
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getToken()
		 * @generated
		 */
		EClass TOKEN = eINSTANCE.getToken();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOKEN__ID = eINSTANCE.getToken_Id();

		/**
		 * The meta object literal for the '<em><b>Token</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOKEN__TOKEN = eINSTANCE.getToken_Token();

		/**
		 * The meta object literal for the '<em><b>Account</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TOKEN__ACCOUNT = eINSTANCE.getToken_Account();

		/**
		 * The meta object literal for the '<em><b>User</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TOKEN__USER = eINSTANCE.getToken_User();

		/**
		 * The meta object literal for the '<em><b>Admin</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TOKEN__ADMIN = eINSTANCE.getToken_Admin();

		/**
		 * The meta object literal for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.ImageImpl <em>Image</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.ImageImpl
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getImage()
		 * @generated
		 */
		EClass IMAGE = eINSTANCE.getImage();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMAGE__ID = eINSTANCE.getImage_Id();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMAGE__PATH = eINSTANCE.getImage_Path();

		/**
		 * The meta object literal for the '<em><b>Content Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMAGE__CONTENT_TYPE = eINSTANCE.getImage_ContentType();

		/**
		 * The meta object literal for the '<em><b>Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMAGE__CONTENT = eINSTANCE.getImage_Content();

		/**
		 * The meta object literal for the '<em><b>Content Big</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMAGE__CONTENT_BIG = eINSTANCE.getImage_ContentBig();

		/**
		 * The meta object literal for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.TicksImpl <em>Ticks</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.TicksImpl
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getTicks()
		 * @generated
		 */
		EClass TICKS = eINSTANCE.getTicks();

		/**
		 * The meta object literal for the '<em><b>Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TICKS__DATE = eINSTANCE.getTicks_Date();

		/**
		 * The meta object literal for the '<em><b>Ticks</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TICKS__TICKS = eINSTANCE.getTicks_Ticks();

		/**
		 * The meta object literal for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.AdminImpl <em>Admin</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.AdminImpl
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getAdmin()
		 * @generated
		 */
		EClass ADMIN = eINSTANCE.getAdmin();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADMIN__NAME = eINSTANCE.getAdmin_Name();

		/**
		 * The meta object literal for the '<em><b>Password</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADMIN__PASSWORD = eINSTANCE.getAdmin_Password();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADMIN__TYPE = eINSTANCE.getAdmin_Type();

		/**
		 * The meta object literal for the '<em><b>Account</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADMIN__ACCOUNT = eINSTANCE.getAdmin_Account();

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
		 * The meta object literal for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeType <em>Challenge Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeType
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getChallengeType()
		 * @generated
		 */
		EEnum CHALLENGE_TYPE = eINSTANCE.getChallengeType();

		/**
		 * The meta object literal for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType <em>User Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getUserType()
		 * @generated
		 */
		EEnum USER_TYPE = eINSTANCE.getUserType();

		/**
		 * The meta object literal for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeState <em>Challenge State</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeState
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getChallengeState()
		 * @generated
		 */
		EEnum CHALLENGE_STATE = eINSTANCE.getChallengeState();

		/**
		 * The meta object literal for the '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.AdminType <em>Admin Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.AdminType
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getAdminType()
		 * @generated
		 */
		EEnum ADMIN_TYPE = eINSTANCE.getAdminType();

		/**
		 * The meta object literal for the '<em>Blob</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.sql.Blob
		 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.Zapfmaster2000PackageImpl#getBlob()
		 * @generated
		 */
		EDataType BLOB = eINSTANCE.getBlob();

	}

} //Zapfmaster2000Package
