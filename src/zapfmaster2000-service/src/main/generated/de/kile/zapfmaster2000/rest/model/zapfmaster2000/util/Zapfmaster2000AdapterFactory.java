/**
 */
package de.kile.zapfmaster2000.rest.model.zapfmaster2000.util;

import de.kile.zapfmaster2000.rest.model.zapfmaster2000.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package
 * @generated
 */
public class Zapfmaster2000AdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static Zapfmaster2000Package modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Zapfmaster2000AdapterFactory() {
		if (modelPackage == null) {
			modelPackage = Zapfmaster2000Package.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Zapfmaster2000Switch<Adapter> modelSwitch =
		new Zapfmaster2000Switch<Adapter>() {
			@Override
			public Adapter caseAchievement(Achievement object) {
				return createAchievementAdapter();
			}
			@Override
			public Adapter caseBox(Box object) {
				return createBoxAdapter();
			}
			@Override
			public Adapter caseAccount(Account object) {
				return createAccountAdapter();
			}
			@Override
			public Adapter caseChallenge(Challenge object) {
				return createChallengeAdapter();
			}
			@Override
			public Adapter caseChallenge1v1(Challenge1v1 object) {
				return createChallenge1v1Adapter();
			}
			@Override
			public Adapter caseDrawing(Drawing object) {
				return createDrawingAdapter();
			}
			@Override
			public Adapter caseGainedAchievement(GainedAchievement object) {
				return createGainedAchievementAdapter();
			}
			@Override
			public Adapter caseKeg(Keg object) {
				return createKegAdapter();
			}
			@Override
			public Adapter caseNews(News object) {
				return createNewsAdapter();
			}
			@Override
			public Adapter caseDrawingNews(DrawingNews object) {
				return createDrawingNewsAdapter();
			}
			@Override
			public Adapter caseAchievementNews(AchievementNews object) {
				return createAchievementNewsAdapter();
			}
			@Override
			public Adapter caseOtherNews(OtherNews object) {
				return createOtherNewsAdapter();
			}
			@Override
			public Adapter caseChallenge1v1StartedNews(Challenge1v1StartedNews object) {
				return createChallenge1v1StartedNewsAdapter();
			}
			@Override
			public Adapter caseChallenge1v1DeclinedNews(Challenge1v1DeclinedNews object) {
				return createChallenge1v1DeclinedNewsAdapter();
			}
			@Override
			public Adapter caseChallenge1v1DoneNews(Challenge1v1DoneNews object) {
				return createChallenge1v1DoneNewsAdapter();
			}
			@Override
			public Adapter caseNewKegNews(NewKegNews object) {
				return createNewKegNewsAdapter();
			}
			@Override
			public Adapter caseNewUserNews(NewUserNews object) {
				return createNewUserNewsAdapter();
			}
			@Override
			public Adapter caseMappingQrRfid(MappingQrRfid object) {
				return createMappingQrRfidAdapter();
			}
			@Override
			public Adapter caseUser(User object) {
				return createUserAdapter();
			}
			@Override
			public Adapter caseToken(Token object) {
				return createTokenAdapter();
			}
			@Override
			public Adapter caseImage(Image object) {
				return createImageAdapter();
			}
			@Override
			public Adapter caseTicks(Ticks object) {
				return createTicksAdapter();
			}
			@Override
			public Adapter caseAdmin(Admin object) {
				return createAdminAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Achievement <em>Achievement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Achievement
	 * @generated
	 */
	public Adapter createAchievementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box <em>Box</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box
	 * @generated
	 */
	public Adapter createBoxAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account <em>Account</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account
	 * @generated
	 */
	public Adapter createAccountAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge <em>Challenge</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge
	 * @generated
	 */
	public Adapter createChallengeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1 <em>Challenge1v1</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1
	 * @generated
	 */
	public Adapter createChallenge1v1Adapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing <em>Drawing</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing
	 * @generated
	 */
	public Adapter createDrawingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.GainedAchievement <em>Gained Achievement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.GainedAchievement
	 * @generated
	 */
	public Adapter createGainedAchievementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg <em>Keg</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg
	 * @generated
	 */
	public Adapter createKegAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.News <em>News</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.News
	 * @generated
	 */
	public Adapter createNewsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.DrawingNews <em>Drawing News</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.DrawingNews
	 * @generated
	 */
	public Adapter createDrawingNewsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.AchievementNews <em>Achievement News</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.AchievementNews
	 * @generated
	 */
	public Adapter createAchievementNewsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.OtherNews <em>Other News</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.OtherNews
	 * @generated
	 */
	public Adapter createOtherNewsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1StartedNews <em>Challenge1v1 Started News</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1StartedNews
	 * @generated
	 */
	public Adapter createChallenge1v1StartedNewsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1DeclinedNews <em>Challenge1v1 Declined News</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1DeclinedNews
	 * @generated
	 */
	public Adapter createChallenge1v1DeclinedNewsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1DoneNews <em>Challenge1v1 Done News</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1DoneNews
	 * @generated
	 */
	public Adapter createChallenge1v1DoneNewsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.NewKegNews <em>New Keg News</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.NewKegNews
	 * @generated
	 */
	public Adapter createNewKegNewsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.NewUserNews <em>New User News</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.NewUserNews
	 * @generated
	 */
	public Adapter createNewUserNewsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.MappingQrRfid <em>Mapping Qr Rfid</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.MappingQrRfid
	 * @generated
	 */
	public Adapter createMappingQrRfidAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.User <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.User
	 * @generated
	 */
	public Adapter createUserAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Token <em>Token</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Token
	 * @generated
	 */
	public Adapter createTokenAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Image <em>Image</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Image
	 * @generated
	 */
	public Adapter createImageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Ticks <em>Ticks</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Ticks
	 * @generated
	 */
	public Adapter createTicksAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.Admin <em>Admin</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.kile.zapfmaster2000.rest.model.zapfmaster2000.Admin
	 * @generated
	 */
	public Adapter createAdminAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //Zapfmaster2000AdapterFactory
