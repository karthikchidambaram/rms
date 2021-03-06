package com.i2g.rms.service.tablemaintenance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.tablemaintenance.AccidentLocation;
import com.i2g.rms.domain.model.tablemaintenance.AccidentLocationDetail;
import com.i2g.rms.domain.model.tablemaintenance.AccidentType;
import com.i2g.rms.domain.model.tablemaintenance.AssetCategory;
import com.i2g.rms.domain.model.tablemaintenance.BodyPart;
import com.i2g.rms.domain.model.tablemaintenance.BodyPart.BodyPartFrontOrBack;
import com.i2g.rms.domain.model.tablemaintenance.ClaimRequestRegistrationType;
import com.i2g.rms.domain.model.tablemaintenance.ClaimStatus;
import com.i2g.rms.domain.model.tablemaintenance.ClaimType;
import com.i2g.rms.domain.model.tablemaintenance.Department;
import com.i2g.rms.domain.model.tablemaintenance.DistinguishingFeature;
import com.i2g.rms.domain.model.tablemaintenance.DistinguishingFeatureDetail;
import com.i2g.rms.domain.model.tablemaintenance.DocumentCategory;
import com.i2g.rms.domain.model.tablemaintenance.DocumentType;
import com.i2g.rms.domain.model.tablemaintenance.EmployeeType;
import com.i2g.rms.domain.model.tablemaintenance.EntryPoint;
import com.i2g.rms.domain.model.tablemaintenance.EventType;
import com.i2g.rms.domain.model.tablemaintenance.ExternalAgency;
import com.i2g.rms.domain.model.tablemaintenance.GenderType;
import com.i2g.rms.domain.model.tablemaintenance.IncidentCategory;
import com.i2g.rms.domain.model.tablemaintenance.IncidentLocation;
import com.i2g.rms.domain.model.tablemaintenance.IncidentLocationDetail;
import com.i2g.rms.domain.model.tablemaintenance.IncidentType;
import com.i2g.rms.domain.model.tablemaintenance.InjuredPersonType;
import com.i2g.rms.domain.model.tablemaintenance.InjuryCause;
import com.i2g.rms.domain.model.tablemaintenance.InjuryType;
import com.i2g.rms.domain.model.tablemaintenance.InjuryTypeDetail;
import com.i2g.rms.domain.model.tablemaintenance.InjuryTypeDetailSpec;
import com.i2g.rms.domain.model.tablemaintenance.LossType;
import com.i2g.rms.domain.model.tablemaintenance.Organization;
import com.i2g.rms.domain.model.tablemaintenance.PolicyType;
import com.i2g.rms.domain.model.tablemaintenance.Position;
import com.i2g.rms.domain.model.tablemaintenance.PositionLevel;
import com.i2g.rms.domain.model.tablemaintenance.SuspectType;
import com.i2g.rms.domain.model.tablemaintenance.VehicleDamageType;
import com.i2g.rms.domain.model.tablemaintenance.WeaponType;
import com.i2g.rms.domain.model.tablemaintenance.WitnessType;
import com.i2g.rms.persistence.dao.tablemaintenance.AccidentLocationDao;
import com.i2g.rms.persistence.dao.tablemaintenance.AccidentLocationDetailDao;
import com.i2g.rms.persistence.dao.tablemaintenance.AccidentTypeDao;
import com.i2g.rms.persistence.dao.tablemaintenance.AssetCategoryDao;
import com.i2g.rms.persistence.dao.tablemaintenance.BodyPartDao;
import com.i2g.rms.persistence.dao.tablemaintenance.ClaimRequestRegistrationTypeDao;
import com.i2g.rms.persistence.dao.tablemaintenance.ClaimStatusDao;
import com.i2g.rms.persistence.dao.tablemaintenance.ClaimTypeDao;
import com.i2g.rms.persistence.dao.tablemaintenance.DepartmentDao;
import com.i2g.rms.persistence.dao.tablemaintenance.DistinguishingFeatureDao;
import com.i2g.rms.persistence.dao.tablemaintenance.DistinguishingFeatureDetailDao;
import com.i2g.rms.persistence.dao.tablemaintenance.DocumentCategoryDao;
import com.i2g.rms.persistence.dao.tablemaintenance.DocumentTypeDao;
import com.i2g.rms.persistence.dao.tablemaintenance.EmployeeTypeDao;
import com.i2g.rms.persistence.dao.tablemaintenance.EntryPointDao;
import com.i2g.rms.persistence.dao.tablemaintenance.EventTypeDao;
import com.i2g.rms.persistence.dao.tablemaintenance.ExternalAgencyDao;
import com.i2g.rms.persistence.dao.tablemaintenance.GenderTypeDao;
import com.i2g.rms.persistence.dao.tablemaintenance.IncidentCategoryDao;
import com.i2g.rms.persistence.dao.tablemaintenance.IncidentLocationDao;
import com.i2g.rms.persistence.dao.tablemaintenance.IncidentLocationDetailDao;
import com.i2g.rms.persistence.dao.tablemaintenance.IncidentTypeDao;
import com.i2g.rms.persistence.dao.tablemaintenance.InjuredPersonTypeDao;
import com.i2g.rms.persistence.dao.tablemaintenance.InjuryCauseDao;
import com.i2g.rms.persistence.dao.tablemaintenance.InjuryTypeDao;
import com.i2g.rms.persistence.dao.tablemaintenance.InjuryTypeDetailDao;
import com.i2g.rms.persistence.dao.tablemaintenance.InjuryTypeDetailSpecDao;
import com.i2g.rms.persistence.dao.tablemaintenance.LossTypeDao;
import com.i2g.rms.persistence.dao.tablemaintenance.OrganizationDao;
import com.i2g.rms.persistence.dao.tablemaintenance.PolicyTypeDao;
import com.i2g.rms.persistence.dao.tablemaintenance.PositionDao;
import com.i2g.rms.persistence.dao.tablemaintenance.PositionLevelDao;
import com.i2g.rms.persistence.dao.tablemaintenance.SuspectTypeDao;
import com.i2g.rms.persistence.dao.tablemaintenance.TableMaintenanceDao;
import com.i2g.rms.persistence.dao.tablemaintenance.VehicleDamageTypeDao;
import com.i2g.rms.persistence.dao.tablemaintenance.WeaponTypeDao;
import com.i2g.rms.persistence.dao.tablemaintenance.WitnessTypeDao;
import com.i2g.rms.service.AbstractService;

/**
 * Back-end service for all table maintenance operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class TableMaintenaceServiceImpl extends AbstractService implements TableMaintenanceService {

	/** Inject the required table maintenance objects here */
	@Autowired
	private AccidentLocationDao _accidentLocationDao;
	@Autowired
	private AccidentLocationDetailDao _accidentLocationDetailDao;
	@Autowired
	private AssetCategoryDao _assetCategoryDao;
	@Autowired
	private ClaimRequestRegistrationTypeDao _claimRequestRegistrationTypeDao;
	@Autowired
	private ClaimStatusDao _claimStatusDao;
	@Autowired
	private ClaimTypeDao _claimTypeDao;
	@Autowired
	private DistinguishingFeatureDao _distinguishingFeatureDao;
	@Autowired
	private DistinguishingFeatureDetailDao _distinguishingFeatureDetailDao;
	@Autowired
	private EmployeeTypeDao _employeeTypeDao;
	@Autowired
	private EntryPointDao _entryPointDao;
	@Autowired
	private EventTypeDao _eventTypeDao;
	@Autowired
	private ExternalAgencyDao _externalAgencyDao;
	@Autowired
	private IncidentLocationDao _incidentLocationDao;
	@Autowired
	private IncidentLocationDetailDao _incidentLocationDetailDao;
	@Autowired
	private IncidentTypeDao _incidentTypeDao;
	@Autowired
	private InjuryCauseDao _injuryCauseDao;
	@Autowired
	private InjuryTypeDao _injuryTypeDao;
	@Autowired
	private InjuryTypeDetailDao _injuryTypeDetailDao;
	@Autowired
	private InjuryTypeDetailSpecDao _injuryTypeDetailSpecDao;
	@Autowired
	private SuspectTypeDao _suspectTypeDao;
	@Autowired
	private WitnessTypeDao _witnessTypeDao;
	@Autowired
	private WeaponTypeDao _weaponInvolvedDao;
	@Autowired
	private AccidentTypeDao _accidentTypeDao;
	@Autowired
	private BodyPartDao _bodyPartsDao;
	@Autowired
	private DocumentCategoryDao _documentCategoryDao;
	@Autowired
	private DocumentTypeDao _documentTypeDao;
	@Autowired
	private GenderTypeDao _genderTypeDao;
	@Autowired
	private IncidentCategoryDao _incidentCategoryDao;
	@Autowired
	private InjuredPersonTypeDao _injuredPersonTypeDao;
	@Autowired
	private LossTypeDao _lossTypeDao;
	@Autowired
	private PolicyTypeDao _policyTypeDao;
	@Autowired
	private VehicleDamageTypeDao _vehicleDamageTypeDao;
	@Autowired
	private OrganizationDao _organizationDao;
	@Autowired
	private DepartmentDao _departmentDao;
	@Autowired
	private PositionDao _positionDao;
	@Autowired
	private PositionLevelDao _positionLevelDao;

	/** Generic Dao class for all table maintenance activities */
	@Autowired
	private TableMaintenanceDao _tableMaintenanceDao;

	/**
	 * Default constructor
	 */
	private TableMaintenaceServiceImpl() {
	}

	/** Generic approach */
	@Override
	public List<Object[]> get(final String tableName, final String operation) {
		return _tableMaintenanceDao.get(tableName, operation);
	}

	@Override
	public List<Object[]> getByCode(final String tableName, final String operation, final String code) {
		return _tableMaintenanceDao.getByCode(tableName, operation, code);
	}

	@Override
	public int create(final String tableName, final String operation, final String code, final String description) {
		return _tableMaintenanceDao.create(tableName, operation, code, description);
	}

	@Override
	public int update(final String tableName, final String operation, final String code, final String description) {
		return _tableMaintenanceDao.update(tableName, operation, code, description);
	}

	@Override
	public int delete(final String tableName, final String operation, final List<String> codes) {
		return _tableMaintenanceDao.delete(tableName, operation, codes);
	}

	/** Individual approach */

	@Override
	public List<AccidentLocation> getAccidentLocations() {
		return _accidentLocationDao.get();
	}

	@Override
	public AccidentLocation getAccidentLocationByCode(final String code) {
		return _accidentLocationDao.getByCode(code);
	}

	@Override
	public AccidentLocation createAccidentLocation(final String code, final String description) {
		return _accidentLocationDao.create(code, description);
	}

	@Override
	public AccidentLocation updateAccidentLocation(final String code, final String description) {
		return _accidentLocationDao.update(code, description);
	}

	@Override
	public void deleteAccidentLocation(final String code) {
		_accidentLocationDao.delete(code);
	}

	@Override
	public List<AccidentLocationDetail> getAccidentLocationDetails() {
		return _accidentLocationDetailDao.get();
	}

	@Override
	public AccidentLocationDetail getAccidentLocationDetailByCode(final String code) {
		return _accidentLocationDetailDao.getByCode(code);
	}

	@Override
	public List<AccidentLocationDetail> getAccidentLocationDetailsForParent(final AccidentLocation accidentLocation) {
		return _accidentLocationDetailDao.get(accidentLocation);
	}

	@Override
	public AccidentLocationDetail createAccidentLocationDetail(final String code, final String description,
			final AccidentLocation accidentLocation) {
		return _accidentLocationDetailDao.create(code, description, accidentLocation);
	}

	@Override
	public AccidentLocationDetail updateAccidentLocationDetail(final String code, final String description) {
		return _accidentLocationDetailDao.update(code, description);
	}

	@Override
	public void deleteAccidentLocationDetail(final String code) {
		_accidentLocationDetailDao.delete(code);
	}

	@Override
	public List<AssetCategory> getAssetCategories() {
		return _assetCategoryDao.get();
	}

	@Override
	public AssetCategory getAssetCategoryByCode(final String code) {
		return _assetCategoryDao.getByCode(code);
	}

	@Override
	public AssetCategory createAssetCategory(final String code, final String description) {
		return _assetCategoryDao.create(code, description);
	}

	@Override
	public AssetCategory updateAssetCategory(final String code, final String description) {
		return _assetCategoryDao.update(code, description);
	}

	@Override
	public void deleteAssetCategory(final String code) {
		_assetCategoryDao.delete(code);
	}

	@Override
	public List<ClaimRequestRegistrationType> getClaimRequestRegistrationTypes() {
		return _claimRequestRegistrationTypeDao.get();
	}

	@Override
	public ClaimRequestRegistrationType getClaimRequestRegistrationTypeByCode(final String code) {
		return _claimRequestRegistrationTypeDao.getByCode(code);
	}

	@Override
	public ClaimRequestRegistrationType createClaimRequestRegistrationType(final String code,
			final String description) {
		return _claimRequestRegistrationTypeDao.create(code, description);
	}

	@Override
	public ClaimRequestRegistrationType updateClaimRequestRegistrationType(final String code,
			final String description) {
		return _claimRequestRegistrationTypeDao.update(code, description);
	}

	@Override
	public void deleteClaimRequestRegistrationType(final String code) {
		_claimRequestRegistrationTypeDao.delete(code);
	}

	@Override
	public List<ClaimStatus> getClaimStatuses() {
		return _claimStatusDao.get();
	}

	@Override
	public ClaimStatus getClaimStatusByCode(final String code) {
		return _claimStatusDao.getByCode(code);
	}

	@Override
	public ClaimStatus createClaimStatus(final String code, final String description) {
		return _claimStatusDao.create(code, description);
	}

	@Override
	public ClaimStatus updateClaimStatus(final String code, final String description) {
		return _claimStatusDao.update(code, description);
	}

	@Override
	public void deleteClaimStatus(final String code) {
		_claimStatusDao.delete(code);
	}

	@Override
	public List<ClaimType> getClaimTypes() {
		return _claimTypeDao.get();
	}

	@Override
	public ClaimType getClaimTypeByCode(final String code) {
		return _claimTypeDao.getByCode(code);
	}

	@Override
	public ClaimType createClaimType(final String code, final String description) {
		return _claimTypeDao.create(code, description);
	}

	@Override
	public ClaimType updateClaimType(final String code, final String description) {
		return _claimTypeDao.update(code, description);
	}

	@Override
	public void deleteClaimType(final String code) {
		_claimTypeDao.delete(code);
	}

	@Override
	public List<DistinguishingFeature> getDistinguishingFeatures() {
		return _distinguishingFeatureDao.get();
	}

	@Override
	public DistinguishingFeature getDistinguishingFeatureByCode(final String code) {
		return _distinguishingFeatureDao.getByCode(code);
	}

	@Override
	public DistinguishingFeature createDistinguishingFeature(final String code, final String description) {
		return _distinguishingFeatureDao.create(code, description);
	}

	@Override
	public DistinguishingFeature updateDistinguishingFeature(final String code, final String description) {
		return _distinguishingFeatureDao.update(code, description);
	}

	@Override
	public void deleteDistinguishingFeature(final String code) {
		_distinguishingFeatureDao.delete(code);
	}

	@Override
	public List<DistinguishingFeatureDetail> getDistinguishingFeatureDetails() {
		return _distinguishingFeatureDetailDao.get();
	}

	@Override
	public List<DistinguishingFeatureDetail> getDistinguishingFeatureDetailsForParent(
			DistinguishingFeature distinguishingFeature) {
		return _distinguishingFeatureDetailDao.get(distinguishingFeature);
	}

	@Override
	public DistinguishingFeatureDetail getDistinguishingFeatureDetailByCode(final String code) {
		return _distinguishingFeatureDetailDao.getByCode(code);
	}

	@Override
	public DistinguishingFeatureDetail createDistinguishingFeatureDetail(final String code, final String description,
			final DistinguishingFeature distinguishingFeature) {
		return _distinguishingFeatureDetailDao.create(code, description, distinguishingFeature);
	}

	@Override
	public DistinguishingFeatureDetail updateDistinguishingFeatureDetail(final String code, final String description) {
		return _distinguishingFeatureDetailDao.update(code, description);
	}

	@Override
	public void deleteDistinguishingFeatureDetail(final String code) {
		_distinguishingFeatureDetailDao.delete(code);
	}

	@Override
	public List<EmployeeType> getEmployeeTypes() {
		return _employeeTypeDao.get();
	}

	@Override
	public EmployeeType getEmployeeTypeByCode(final String code) {
		return _employeeTypeDao.getByCode(code);
	}

	@Override
	public EmployeeType createEmployeeType(final String code, final String description) {
		return _employeeTypeDao.create(code, description);
	}

	@Override
	public EmployeeType updateEmployeeType(final String code, final String description) {
		return _employeeTypeDao.update(code, description);
	}

	@Override
	public void deleteEmployeeType(final String code) {
		_employeeTypeDao.delete(code);
	}

	@Override
	public List<EntryPoint> getEntryPoints() {
		return _entryPointDao.get();
	}

	@Override
	public EntryPoint getEntryPointByCode(final String code) {
		return _entryPointDao.getByCode(code);
	}

	@Override
	public EntryPoint createEntryPoint(final String code, final String description) {
		return _entryPointDao.create(code, description);
	}

	@Override
	public EntryPoint updateEntryPoint(final String code, final String description) {
		return _entryPointDao.update(code, description);
	}

	@Override
	public void deleteEntryPoint(final String code) {
		_entryPointDao.delete(code);
	}

	@Override
	public List<EventType> getEventTypes() {
		return _eventTypeDao.get();
	}

	@Override
	public EventType getEventTypeByCode(final String code) {
		return _eventTypeDao.getByCode(code);
	}

	@Override
	public EventType createEventType(final String code, final String description) {
		return _eventTypeDao.create(code, description);
	}

	@Override
	public EventType updateEventType(final String code, final String description) {
		return _eventTypeDao.update(code, description);
	}

	@Override
	public void deleteEventType(final String code) {
		_eventTypeDao.delete(code);
	}

	@Override
	public List<ExternalAgency> getExternalAgencies() {
		return _externalAgencyDao.get();
	}

	@Override
	public ExternalAgency getExternalAgencyByCode(final String code) {
		return _externalAgencyDao.getByCode(code);
	}

	@Override
	public ExternalAgency createExternalAgency(final String code, final String description) {
		return _externalAgencyDao.create(code, description);
	}

	@Override
	public ExternalAgency updateExternalAgency(final String code, final String description) {
		return _externalAgencyDao.update(code, description);
	}

	@Override
	public void deleteExternalAgency(final String code) {
		_externalAgencyDao.delete(code);
	}

	@Override
	public List<IncidentLocation> getIncidentLocations() {
		return _incidentLocationDao.get();
	}

	@Override
	public IncidentLocation getIncidentLocationByCode(final String code) {
		return _incidentLocationDao.getByCode(code);
	}

	@Override
	public IncidentLocation createIncidentLocation(final String code, final String description) {
		return _incidentLocationDao.create(code, description);
	}

	@Override
	public IncidentLocation updateIncidentLocation(final String code, final String description) {
		return _incidentLocationDao.update(code, description);
	}

	@Override
	public void deleteIncidentLocation(final String code) {
		_incidentLocationDao.delete(code);
	}

	@Override
	public List<IncidentLocationDetail> getIncidentLocationDetails() {
		return _incidentLocationDetailDao.get();
	}

	@Override
	public IncidentLocationDetail getIncidentLocationDetailByCode(final String code) {
		return _incidentLocationDetailDao.getByCode(code);
	}

	@Override
	public List<IncidentLocationDetail> getIncidentLocationDetailsForParent(final IncidentLocation incidentLocation) {
		return _incidentLocationDetailDao.get(incidentLocation);
	}

	@Override
	public IncidentLocationDetail createIncidentLocationDetail(final String code, final String description,
			final IncidentLocation incidentLocation) {
		return _incidentLocationDetailDao.create(code, description, incidentLocation);
	}

	@Override
	public IncidentLocationDetail updateIncidentLocationDetail(final String code, final String description) {
		return _incidentLocationDetailDao.update(code, description);
	}

	@Override
	public void deleteIncidentLocationDetail(final String code) {
		_incidentLocationDetailDao.delete(code);
	}

	@Override
	public List<IncidentType> getIncidentTypes() {
		return _incidentTypeDao.get();
	}

	@Override
	public IncidentType getIncidentTypeByCode(final String code) {
		return _incidentTypeDao.getByCode(code);
	}

	@Override
	public IncidentType createIncidentType(final String code, final String description) {
		return _incidentTypeDao.create(code, description);
	}

	@Override
	public IncidentType updateIncidentType(final String code, final String description) {
		return _incidentTypeDao.update(code, description);
	}

	@Override
	public void deleteIncidentType(final String code) {
		_incidentTypeDao.delete(code);
	}

	@Override
	public List<InjuryCause> getInjuryCauses() {
		return _injuryCauseDao.get();
	}

	@Override
	public InjuryCause getInjuryCauseByCode(final String code) {
		return _injuryCauseDao.getByCode(code);
	}

	@Override
	public InjuryCause createInjuryCause(final String code, final String description) {
		return _injuryCauseDao.create(code, description);
	}

	@Override
	public InjuryCause updateInjuryCause(final String code, final String description) {
		return _injuryCauseDao.update(code, description);
	}

	@Override
	public void deleteInjuryCause(final String code) {
		_injuryCauseDao.delete(code);
	}

	@Override
	public List<InjuryType> getInjuryTypes() {
		return _injuryTypeDao.get();
	}

	@Override
	public InjuryType getInjuryTypeByCode(final String code) {
		return _injuryTypeDao.getByCode(code);
	}

	@Override
	public InjuryType createInjuryType(final String code, final String description) {
		return _injuryTypeDao.create(code, description);
	}

	@Override
	public InjuryType updateInjuryType(final String code, final String description) {
		return _injuryTypeDao.update(code, description);
	}

	@Override
	public void deleteInjuryType(final String code) {
		_injuryTypeDao.delete(code);
	}

	@Override
	public List<InjuryTypeDetail> getInjuryTypeDetails() {
		return _injuryTypeDetailDao.get();
	}

	@Override
	public List<InjuryTypeDetail> getInjuryTypeDetailsForParent(final InjuryType injuryType) {
		return _injuryTypeDetailDao.get(injuryType);
	}

	@Override
	public InjuryTypeDetail getInjuryTypeDetailByCode(final String code) {
		return _injuryTypeDetailDao.getByCode(code);
	}

	@Override
	public InjuryTypeDetail createInjuryTypeDetail(final String code, final String description,
			final InjuryType injuryType) {
		return _injuryTypeDetailDao.create(code, description, injuryType);
	}

	@Override
	public InjuryTypeDetail updateInjuryTypeDetail(final String code, final String description) {
		return _injuryTypeDetailDao.update(code, description);
	}

	@Override
	public void deleteInjuryTypeDetail(final String code) {
		_injuryTypeDetailDao.delete(code);
	}

	@Override
	public List<InjuryTypeDetailSpec> getInjuryTypeDetailSpecs() {
		return _injuryTypeDetailSpecDao.get();
	}

	@Override
	public List<InjuryTypeDetailSpec> getInjuryTypeDetailSpecsForParent(final InjuryTypeDetail injuryTypeDetail) {
		return _injuryTypeDetailSpecDao.get(injuryTypeDetail);
	}

	@Override
	public InjuryTypeDetailSpec getInjuryTypeDetailSpecByCode(final String code) {
		return _injuryTypeDetailSpecDao.getByCode(code);
	}

	@Override
	public InjuryTypeDetailSpec createInjuryTypeDetailSpec(final String code, final String description,
			final InjuryTypeDetail injuryTypeDetail) {
		return _injuryTypeDetailSpecDao.create(code, description, injuryTypeDetail);
	}

	@Override
	public InjuryTypeDetailSpec updateInjuryTypeDetailSpec(final String code, final String description) {
		return _injuryTypeDetailSpecDao.update(code, description);
	}

	@Override
	public void deleteInjuryTypeDetailSpec(final String code) {
		_injuryTypeDetailSpecDao.delete(code);
	}

	@Override
	public List<SuspectType> getSuspectTypes() {
		return _suspectTypeDao.get();
	}

	@Override
	public SuspectType getSuspectTypeByCode(final String code) {
		return _suspectTypeDao.getByCode(code);
	}

	@Override
	public SuspectType createSuspectType(final String code, final String description) {
		return _suspectTypeDao.create(code, description);
	}

	@Override
	public SuspectType updateSuspectType(final String code, final String description) {
		return _suspectTypeDao.update(code, description);
	}

	@Override
	public void deleteSuspectType(final String code) {
		_suspectTypeDao.delete(code);
	}

	@Override
	public List<WeaponType> getWeaponTypes() {
		return _weaponInvolvedDao.get();
	}

	@Override
	public WeaponType getWeaponTypeByCode(final String code) {
		return _weaponInvolvedDao.getByCode(code);
	}

	@Override
	public WeaponType createWeaponType(final String code, final String description) {
		return _weaponInvolvedDao.create(code, description);
	}

	@Override
	public WeaponType updateWeaponType(final String code, final String description) {
		return _weaponInvolvedDao.update(code, description);
	}

	@Override
	public void deleteWeaponType(final String code) {
		_weaponInvolvedDao.delete(code);
	}

	@Override
	public List<AccidentType> getAccidentTypes() {
		return _accidentTypeDao.get();
	}

	@Override
	public AccidentType getAccidentTypeByCode(final String code) {
		return _accidentTypeDao.getByCode(code);
	}

	@Override
	public AccidentType createAccidentType(final String code, final String description) {
		return _accidentTypeDao.create(code, description);
	}

	@Override
	public AccidentType updateAccidentType(final String code, final String description) {
		return _accidentTypeDao.update(code, description);
	}

	@Override
	public void deleteAccidentType(final String code) {
		_accidentTypeDao.delete(code);
	}

	@Override
	public List<BodyPart> getBodyParts() {
		return _bodyPartsDao.get();
	}

	@Override
	public BodyPart getBodyPartByCode(final String code) {
		return _bodyPartsDao.getByCode(code);
	}

	@Override
	public BodyPart createBodyPart(final String code, final String description) {
		return _bodyPartsDao.create(code, description);
	}

	@Override
	public BodyPart updateBodyPart(final String code, final String description) {
		return _bodyPartsDao.update(code, description);
	}
	
	@Override
	public BodyPart createBodyPart(final String code, final String description, final BodyPartFrontOrBack bodyPartFrontOrBack) {
		return _bodyPartsDao.create(code, description, bodyPartFrontOrBack);
	}

	@Override
	public BodyPart updateBodyPart(final String code, final String description, final BodyPartFrontOrBack bodyPartFrontOrBack) {
		return _bodyPartsDao.update(code, description, bodyPartFrontOrBack);
	}

	@Override
	public void deleteBodyPart(final String code) {
		_bodyPartsDao.delete(code);
	}

	@Override
	public List<DocumentCategory> getDocumentCategories() {
		return _documentCategoryDao.get();
	}

	@Override
	public DocumentCategory getDocumentCategoryByCode(final String code) {
		return _documentCategoryDao.getByCode(code);
	}

	@Override
	public DocumentCategory createDocumentCategory(final String code, final String description) {
		return _documentCategoryDao.create(code, description);
	}

	@Override
	public DocumentCategory updateDocumentCategory(final String code, final String description) {
		return _documentCategoryDao.update(code, description);
	}

	@Override
	public void deleteDocumentCategory(final String code) {
		_documentCategoryDao.delete(code);
	}

	@Override
	public List<DocumentType> getDocumentTypes() {
		return _documentTypeDao.get();
	}

	@Override
	public DocumentType getDocumentTypeByCode(final String code) {
		return _documentTypeDao.getByCode(code);
	}

	@Override
	public DocumentType createDocumentType(final String code, final String description) {
		return _documentTypeDao.create(code, description);
	}

	@Override
	public DocumentType updateDocumentType(final String code, final String description) {
		return _documentTypeDao.update(code, description);
	}

	@Override
	public void deleteDocumentType(final String code) {
		_documentTypeDao.delete(code);
	}

	@Override
	public List<IncidentCategory> getIncidentCategories() {
		return _incidentCategoryDao.get();
	}

	@Override

	public IncidentCategory getIncidentCategoryByCode(final String code) {
		return _incidentCategoryDao.getByCode(code);
	}

	@Override
	public IncidentCategory createIncidentCategory(final String code, final String description) {
		return _incidentCategoryDao.create(code, description);
	}

	@Override
	public IncidentCategory updateIncidentCategory(final String code, final String description) {
		return _incidentCategoryDao.update(code, description);
	}

	@Override
	public void deleteIncidentCategory(final String code) {
		_incidentCategoryDao.delete(code);
	}

	@Override
	public List<GenderType> getGenderTypes() {
		return _genderTypeDao.get();
	}

	@Override
	public GenderType getGenderTypeByCode(final String code) {
		return _genderTypeDao.getByCode(code);
	}

	@Override
	public GenderType createGenderType(final String code, final String description) {
		return _genderTypeDao.create(code, description);
	}

	@Override
	public GenderType updateGenderType(final String code, final String description) {
		return _genderTypeDao.update(code, description);
	}

	@Override
	public void deleteGenderType(final String code) {
		_genderTypeDao.delete(code);
	}

	@Override
	public List<InjuredPersonType> getInjuredPersonTypes() {
		return _injuredPersonTypeDao.get();
	}

	@Override
	public InjuredPersonType getInjuredPersonTypeByCode(final String code) {
		return _injuredPersonTypeDao.getByCode(code);
	}

	@Override
	public InjuredPersonType createInjuredPersonType(final String code, final String description) {
		return _injuredPersonTypeDao.create(code, description);
	}

	@Override
	public InjuredPersonType updateInjuredPersonType(final String code, final String description) {
		return _injuredPersonTypeDao.update(code, description);
	}

	@Override
	public void deleteInjuredPersonType(final String code) {
		_injuredPersonTypeDao.delete(code);
	}

	@Override
	public List<LossType> getLossTypes() {
		return _lossTypeDao.get();
	}

	@Override
	public LossType getLossTypeByCode(final String code) {
		return _lossTypeDao.getByCode(code);
	}

	@Override
	public LossType createLossType(final String code, final String description) {
		return _lossTypeDao.create(code, description);
	}

	@Override
	public LossType updateLossType(final String code, final String description) {
		return _lossTypeDao.update(code, description);
	}

	@Override
	public void deleteLossType(final String code) {
		_lossTypeDao.delete(code);
	}

	@Override
	public List<PolicyType> getPolicyTypes() {
		return _policyTypeDao.get();
	}

	@Override
	public PolicyType getPolicyTypeByCode(final String code) {
		return _policyTypeDao.getByCode(code);
	}

	@Override
	public PolicyType createPolicyType(final String code, final String description) {
		return _policyTypeDao.create(code, description);
	}

	@Override
	public PolicyType updatePolicyType(final String code, final String description) {
		return _policyTypeDao.update(code, description);
	}

	@Override
	public void deletePolicyType(final String code) {
		_policyTypeDao.delete(code);
	}

	@Override
	public List<VehicleDamageType> getVehicleDamageTypes() {
		return _vehicleDamageTypeDao.get();
	}

	@Override
	public VehicleDamageType getVehicleDamageTypeByCode(final String code) {
		return _vehicleDamageTypeDao.getByCode(code);
	}

	@Override
	public VehicleDamageType createVehicleDamageType(final String code, final String description) {
		return _vehicleDamageTypeDao.create(code, description);
	}

	@Override
	public VehicleDamageType updateVehicleDamageType(final String code, final String description) {
		return _vehicleDamageTypeDao.update(code, description);
	}

	@Override
	public void deleteVehicleDamageType(final String code) {
		_vehicleDamageTypeDao.delete(code);
	}

	@Override
	public List<Organization> getOrganizations() {
		return _organizationDao.get();
	}

	@Override
	public Organization getOrganizationByCode(final String code) {
		return _organizationDao.getByCode(code);
	}

	@Override
	public Organization createOrganization(final String code, final String description) {
		return _organizationDao.create(code, description);
	}

	@Override
	public Organization updateOrganization(final String code, final String description) {
		return _organizationDao.update(code, description);
	}

	@Override

	public void deleteOrganization(final String code) {
		_organizationDao.delete(code);
	}

	@Override
	public List<Department> getDepartments() {
		return _departmentDao.get();
	}

	@Override
	public List<Department> getDepartmentsForOrganization(final Organization organization) {
		return _departmentDao.get(organization);
	}

	@Override
	public Department getDepartmentByCode(final String code) {
		return _departmentDao.getByCode(code);
	}

	@Override
	public Department createDepartment(final String code, final String description, final Organization organization) {
		return _departmentDao.create(code, description, organization);
	}

	@Override
	public Department updateDepartment(final String code, final String description) {
		return _departmentDao.update(code, description);
	}

	@Override
	public void deleteDepartment(final String code) {
		_departmentDao.delete(code);
	}

	@Override
	public List<PositionLevel> getPositionLevels() {
		return _positionLevelDao.get();
	}

	@Override
	public PositionLevel getPositionLevelByCode(final String code) {
		return _positionLevelDao.getByCode(code);
	}

	@Override
	public PositionLevel createPositionLevel(final String code, final String description) {
		return _positionLevelDao.create(code, description);
	}

	@Override
	public PositionLevel updatePositionLevel(final String code, final String description) {
		return _positionLevelDao.update(code, description);
	}

	@Override
	public void deletePositionLevel(final String code) {
		_positionLevelDao.delete(code);
	}

	@Override
	public List<Position> getPositions() {
		return _positionDao.get();
	}

	@Override
	public List<Position> getPositionsForOrganization(final Organization organization) {
		return _positionDao.get(organization);
	}

	@Override
	public List<Position> getPositionsForDepartment(final Department department) {
		return _positionDao.get(department);
	}

	@Override
	public List<Position> getPositionsForPositionLevel(final PositionLevel positionLevel) {
		return _positionDao.get(positionLevel);
	}

	@Override
	public Position getPositionByCode(final String code) {
		return _positionDao.getByCode(code);
	}

	@Override
	public Position createPosition(final String code, final String description, final Organization organization) {
		return _positionDao.create(code, description, organization);
	}

	@Override
	public Position createPosition(final String code, final String description, final Department department) {
		return _positionDao.create(code, description, department);
	}

	@Override
	public Position createPosition(final String code, final String description, final PositionLevel positionLevel,
			final Organization organization) {
		return _positionDao.create(code, description, positionLevel, organization);
	}

	@Override
	public Position createPosition(final String code, final String description, final PositionLevel positionLevel,
			final Department department) {
		return _positionDao.create(code, description, positionLevel, department);
	}

	@Override
	public Position updatePosition(final String code, final String description) {
		return _positionDao.update(code, description);
	}

	@Override
	public void deletePosition(final String code) {
		_positionDao.delete(code);
	}

	@Override
	public List<WitnessType> getWitnessTypes() {
		return _witnessTypeDao.get();
	}

	@Override
	public WitnessType getWitnessTypeByCode(final String code) {
		return _witnessTypeDao.getByCode(code);
	}

	@Override
	public WitnessType createWitnessType(final String code, final String description) {
		return _witnessTypeDao.create(code, description);
	}

	@Override
	public WitnessType updateWitnessType(final String code, final String description) {
		return _witnessTypeDao.update(code, description);
	}

	@Override
	public void deleteWitnessType(final String code) {
		_witnessTypeDao.delete(code);
	}
}
