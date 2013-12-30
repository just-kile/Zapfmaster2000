/**
 */
package de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Achievement;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.GainedAchievement;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Achievement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.AchievementImpl#getId <em>Id</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.AchievementImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.AchievementImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.AchievementImpl#getImagePath <em>Image Path</em>}</li>
 *   <li>{@link de.kile.zapfmaster2000.rest.model.zapfmaster2000.impl.AchievementImpl#getGained <em>Gained</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AchievementImpl extends EObjectImpl implements Achievement {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final long ID_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected long id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getImagePath() <em>Image Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImagePath()
	 * @generated
	 * @ordered
	 */
	protected static final String IMAGE_PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getImagePath() <em>Image Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImagePath()
	 * @generated
	 * @ordered
	 */
	protected String imagePath = IMAGE_PATH_EDEFAULT;

	/**
	 * The cached value of the '{@link #getGained() <em>Gained</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGained()
	 * @generated
	 * @ordered
	 */
	protected EList<GainedAchievement> gained;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AchievementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Zapfmaster2000Package.Literals.ACHIEVEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(long newId) {
		long oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.ACHIEVEMENT__ID, oldId, id));
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
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.ACHIEVEMENT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.ACHIEVEMENT__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImagePath(String newImagePath) {
		String oldImagePath = imagePath;
		imagePath = newImagePath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Zapfmaster2000Package.ACHIEVEMENT__IMAGE_PATH, oldImagePath, imagePath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GainedAchievement> getGained() {
		if (gained == null) {
			gained = new EObjectContainmentWithInverseEList<GainedAchievement>(GainedAchievement.class, this, Zapfmaster2000Package.ACHIEVEMENT__GAINED, Zapfmaster2000Package.GAINED_ACHIEVEMENT__ACHIEVEMENT);
		}
		return gained;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Zapfmaster2000Package.ACHIEVEMENT__GAINED:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getGained()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Zapfmaster2000Package.ACHIEVEMENT__GAINED:
				return ((InternalEList<?>)getGained()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Zapfmaster2000Package.ACHIEVEMENT__ID:
				return getId();
			case Zapfmaster2000Package.ACHIEVEMENT__NAME:
				return getName();
			case Zapfmaster2000Package.ACHIEVEMENT__DESCRIPTION:
				return getDescription();
			case Zapfmaster2000Package.ACHIEVEMENT__IMAGE_PATH:
				return getImagePath();
			case Zapfmaster2000Package.ACHIEVEMENT__GAINED:
				return getGained();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case Zapfmaster2000Package.ACHIEVEMENT__ID:
				setId((Long)newValue);
				return;
			case Zapfmaster2000Package.ACHIEVEMENT__NAME:
				setName((String)newValue);
				return;
			case Zapfmaster2000Package.ACHIEVEMENT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case Zapfmaster2000Package.ACHIEVEMENT__IMAGE_PATH:
				setImagePath((String)newValue);
				return;
			case Zapfmaster2000Package.ACHIEVEMENT__GAINED:
				getGained().clear();
				getGained().addAll((Collection<? extends GainedAchievement>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case Zapfmaster2000Package.ACHIEVEMENT__ID:
				setId(ID_EDEFAULT);
				return;
			case Zapfmaster2000Package.ACHIEVEMENT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case Zapfmaster2000Package.ACHIEVEMENT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case Zapfmaster2000Package.ACHIEVEMENT__IMAGE_PATH:
				setImagePath(IMAGE_PATH_EDEFAULT);
				return;
			case Zapfmaster2000Package.ACHIEVEMENT__GAINED:
				getGained().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case Zapfmaster2000Package.ACHIEVEMENT__ID:
				return id != ID_EDEFAULT;
			case Zapfmaster2000Package.ACHIEVEMENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case Zapfmaster2000Package.ACHIEVEMENT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case Zapfmaster2000Package.ACHIEVEMENT__IMAGE_PATH:
				return IMAGE_PATH_EDEFAULT == null ? imagePath != null : !IMAGE_PATH_EDEFAULT.equals(imagePath);
			case Zapfmaster2000Package.ACHIEVEMENT__GAINED:
				return gained != null && !gained.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (id: ");
		result.append(id);
		result.append(", name: ");
		result.append(name);
		result.append(", description: ");
		result.append(description);
		result.append(", imagePath: ");
		result.append(imagePath);
		result.append(')');
		return result.toString();
	}

} //AchievementImpl
