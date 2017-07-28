package com.i2g.rms.service.tablemaintenance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i2g.rms.domain.model.tablemaintenance.AccidentLocation;
import com.i2g.rms.domain.model.tablemaintenance.AccidentLocationDetail;
import com.i2g.rms.domain.model.tablemaintenance.AccidentType;
import com.i2g.rms.domain.model.tablemaintenance.AssetCategory;
import com.i2g.rms.domain.model.tablemaintenance.BodyPart;
import com.i2g.rms.domain.model.tablemaintenance.ClaimRequestRegistrationType;
import com.i2g.rms.domain.model.tablemaintenance.ClaimStatus;
import com.i2g.rms.domain.model.tablemaintenance.ClaimType;
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
import com.i2g.rms.domain.model.tablemaintenance.PolicyType;
import com.i2g.rms.domain.model.tablemaintenance.SuspectType;
import com.i2g.rms.domain.model.tablemaintenance.VehicleDamageType;
import com.i2g.rms.domain.model.tablemaintenance.WeaponType;
import com.i2g.rms.persistence.dao.tablemaintenance.AccidentLocationDao;
import com.i2g.rms.persistence.dao.tablemaintenance.AccidentLocationDetailDao;
import com.i2g.rms.persistence.dao.tablemaintenance.AccidentTypeDao;
import com.i2g.rms.persistence.dao.tablemaintenance.AssetCategoryDao;
import com.i2g.rms.persistence.dao.tablemaintenance.BodyPartDao;
import com.i2g.rms.persistence.dao.tablemaintenance.ClaimRequestRegistrationTypeDao;
import com.i2g.rms.persistence.dao.tablemaintenance.ClaimStatusDao;
import com.i2g.rms.persistence.dao.tablemaintenance.ClaimTypeDao;
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
import com.i2g.rms.persistence.dao.tablemaintenance.PolicyTypeDao;
import com.i2g.rms.persistence.dao.tablemaintenance.SuspectTypeDao;
import com.i2g.rms.persistence.dao.tablemaintenance.TableMaintenanceDao;
import com.i2g.rms.persistence.dao.tablemaintenance.VehicleDamageTypeDao;
import com.i2g.rms.persistence.dao.tablemaintenance.WeaponTypeDao;
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
	
	/** Generic Dao class for all table maintenance activities */
	@Autowired
	private TableMaintenanceDao _tableMaintenanceDao;

	/**
	 * Default constructor
	 */
	private TableMaintenaceServiceImpl() {}
	
	/** Generic approach */
	@Override
	@Transactional
	public List<Object[]> get(final String tableName, final String operation) {
		return _tableMaintenanceDao.get(tableName, operation);
	}
	
	@Override
	@Transactional
	public List<Object[]> getByCode(final String tableName, final String operation, final String code) {
		return _tableMaintenanceDao.getByCode(tableName, operation, code);
	}
	
	@Override
	@Transactional
	public int create(final String tableName, final String operation, final String code, final String description) {
		return _tableMaintenanceDao.create(tableName, operation, code, description);
	}
	
	@Override
	@Transactional
	public int update(final String tableName, final String operation, final String code, final String description) {
		return _tableMaintenanceDao.update(tableName, operation, code, description);
	}
	
	@Override
	@Transactional
	public int delete(final String tableName, final String operation, final List<String> codes) {
		return _tableMaintenanceDao.delete(tableName, operation, codes);
	}
	
	/** Individual approach */
	
	@Override
	public List<AccidentLocation> getAccidentLocations() {
		return _accidentLocationDao.get();
	}

	@Override
	public AccidentLocation getAccidentLocationByCode(String code) {
		return _accidentLocationDao.getByCode(code);
	}

	@Override
	public AccidentLocation createAccidentLocation(String code, String description) {
		return _accidentLocationDao.create(code, description);
	}

	@Override
	public AccidentLocation updateAccidentLocation(String code, String description) {
		return _accidentLocationDao.update(code, description);
	}

	@Override
	public void deleteAccidentLocation(String code) {
		_accidentLocationDao.delete(code);		
	}

	@Override
	public List<AccidentLocationDetail> getAccidentLocationDetails() {
		return _accidentLocationDetailDao.get();
	}

	@Override
	public AccidentLocationDetail getAccidentLocationDetailByCode(String code) {
		return _accidentLocationDetailDao.getByCode(code);
	}

	@Override
	public AccidentLocationDetail createAccidentLocationDetail(String code, String description) {
		return _accidentLocationDetailDao.create(code, description);
	}

	@Override
	public AccidentLocationDetail updateAccidentLocationDetail(String code, String description) {
		return _accidentLocationDetailDao.update(code, description);
	}

	@Override
	public void deleteAccidentLocationDetail(String code) {
		_accidentLocationDetailDao.delete(code);		
	}

	@Override
	@Transactional
	public List<AssetCategory> getAssetCategories() {
		return _assetCategoryDao.get();
	}

	@Override
	@Transactional
	public AssetCategory getAssetCategoryByCode(String code) {
		return _assetCategoryDao.getByCode(code);
	}

	@Override
	@Transactional
	public AssetCategory createAssetCategory(String code, String description) {
		return _assetCategoryDao.create(code, description);
	}

	@Override
	@Transactional
	public AssetCategory updateAssetCategory(String code, String description) {
		return _assetCategoryDao.update(code, description);
	}

	@Override
	@Transactional
	public void deleteAssetCategory(String code) {
		_assetCategoryDao.delete(code);		
	}

	@Override
	@Transactional
	public List<ClaimRequestRegistrationType> getClaimRequestRegistrationTypes() {
		return _claimRequestRegistrationTypeDao.get();
	}

	@Override
	@Transactional
	public ClaimRequestRegistrationType getClaimRequestRegistrationTypeByCode(String code) {
		return _claimRequestRegistrationTypeDao.getByCode(code);
	}

	@Override
	@Transactional
	public ClaimRequestRegistrationType createClaimRequestRegistrationType(String code, String description) {
		return _claimRequestRegistrationTypeDao.create(code, description);
	}

	@Override
	@Transactional
	public ClaimRequestRegistrationType updateClaimRequestRegistrationType(String code, String description) {
		return _claimRequestRegistrationTypeDao.update(code, description);
	}

	@Override
	@Transactional
	public void deleteClaimRequestRegistrationType(String code) {
		_claimRequestRegistrationTypeDao.delete(code);		
	}

	@Override
	@Transactional
	public List<ClaimStatus> getClaimStatuses() {
		return _claimStatusDao.get();
	}

	@Override
	@Transactional
	public ClaimStatus getClaimStatusByCode(String code) {
		return _claimStatusDao.getByCode(code);
	}

	@Override
	@Transactional
	public ClaimStatus createClaimStatus(String code, String description) {
		return _claimStatusDao.create(code, description);
	}

	@Override
	@Transactional
	public ClaimStatus updateClaimStatus(String code, String description) {
		return _claimStatusDao.update(code, description);
	}

	@Override
	@Transactional
	public void deleteClaimStatus(String code) {
		_claimStatusDao.delete(code);		
	}

	@Override
	@Transactional
	public List<ClaimType> getClaimTypes() {
		return _claimTypeDao.get();
	}

	@Override
	@Transactional
	public ClaimType getClaimTypeByCode(String code) {
		return _claimTypeDao.getByCode(code);
	}

	@Override
	@Transactional
	public ClaimType createClaimType(String code, String description) {
		return _claimTypeDao.create(code, description);
	}

	@Override
	@Transactional
	public ClaimType updateClaimType(String code, String description) {
		return _claimTypeDao.update(code, description);
	}

	@Override
	@Transactional
	public void deleteClaimType(String code) {
		_claimTypeDao.delete(code);		
	}

	@Override
	public List<DistinguishingFeature> getDistinguishingFeatures() {
		return _distinguishingFeatureDao.get();
	}

	@Override
	public DistinguishingFeature getDistinguishingFeatureByCode(String code) {
		return _distinguishingFeatureDao.getByCode(code);
	}

	@Override
	public DistinguishingFeature createDistinguishingFeature(String code, String description) {
		return _distinguishingFeatureDao.create(code, description);
	}

	@Override
	public DistinguishingFeature updateDistinguishingFeature(String code, String description) {
		return _distinguishingFeatureDao.update(code, description);
	}

	@Override
	public void deleteDistinguishingFeature(String code) {
		_distinguishingFeatureDao.delete(code);		
	}

	@Override
	public List<DistinguishingFeatureDetail> getDistinguishingFeatureDetails() {
		return _distinguishingFeatureDetailDao.get();
	}

	@Override
	public DistinguishingFeatureDetail getDistinguishingFeatureDetailByCode(String code) {
		return _distinguishingFeatureDetailDao.getByCode(code);
	}

	@Override
	public DistinguishingFeatureDetail createDistinguishingFeatureDetail(String code, String description) {
		return _distinguishingFeatureDetailDao.create(code, description);
	}

	@Override
	public DistinguishingFeatureDetail updateDistinguishingFeatureDetail(String code, String description) {
		return _distinguishingFeatureDetailDao.update(code, description);
	}

	@Override
	public void deleteDistinguishingFeatureDetail(String code) {
		_distinguishingFeatureDetailDao.delete(code);		
	}

	@Override
	@Transactional
	public List<EmployeeType> getEmployeeTypes() {
		return _employeeTypeDao.get();
	}

	@Override
	@Transactional
	public EmployeeType getEmployeeTypeByCode(String code) {
		return _employeeTypeDao.getByCode(code);
	}

	@Override
	@Transactional
	public EmployeeType createEmployeeType(String code, String description) {
		return _employeeTypeDao.create(code, description);
	}

	@Override
	@Transactional
	public EmployeeType updateEmployeeType(String code, String description) {
		return _employeeTypeDao.update(code, description);
	}

	@Override
	@Transactional
	public void deleteEmployeeType(String code) {
		_employeeTypeDao.delete(code);		
	}
	
	@Override
	@Transactional
	public List<EntryPoint> getEntryPoints() {
		return _entryPointDao.get();
	}

	@Override
	@Transactional
	public EntryPoint getEntryPointByCode(final String code) {
		return _entryPointDao.getByCode(code);
	}

	@Override
	@Transactional
	public EntryPoint createEntryPoint(final String code, final String description) {
		return _entryPointDao.create(code, description);
	}

	@Override
	@Transactional
	public EntryPoint updateEntryPoint(final String code, final String description) {
		return _entryPointDao.update(code, description);
	}

	@Override
	@Transactional
	public void deleteEntryPoint(final String code) {
		_entryPointDao.delete(code);
	}

	@Override
	@Transactional
	public List<EventType> getEventTypes() {
		return _eventTypeDao.get();
	}

	@Override
	@Transactional
	public EventType getEventTypeByCode(String code) {
		return _eventTypeDao.getByCode(code);
	}

	@Override
	@Transactional
	public EventType createEventType(String code, String description) {
		return _eventTypeDao.create(code, description);
	}

	@Override
	@Transactional
	public EventType updateEventType(String code, String description) {
		return _eventTypeDao.update(code, description);
	}

	@Override
	@Transactional
	public void deleteEventType(String code) {
		_eventTypeDao.delete(code);		
	}

	@Override
	@Transactional
	public List<ExternalAgency> getExternalAgencies() {
		return _externalAgencyDao.get();
	}

	@Override
	@Transactional
	public ExternalAgency getExternalAgencyByCode(String code) {
		return _externalAgencyDao.getByCode(code);
	}

	@Override
	@Transactional
	public ExternalAgency createExternalAgency(String code, String description) {
		return _externalAgencyDao.create(code, description);
	}

	@Override
	@Transactional
	public ExternalAgency updateExternalAgency(String code, String description) {
		return _externalAgencyDao.update(code, description);
	}

	@Override
	@Transactional
	public void deleteExternalAgency(String code) {
		_externalAgencyDao.delete(code);		
	}

	@Override
	public List<IncidentLocation> getIncidentLocations() {
		return _incidentLocationDao.get();
	}

	@Override
	public IncidentLocation getIncidentLocationByCode(String code) {
		return _incidentLocationDao.getByCode(code);
	}

	@Override
	public IncidentLocation createIncidentLocation(String code, String description) {
		return _incidentLocationDao.create(code, description);
	}

	@Override
	public IncidentLocation updateIncidentLocation(String code, String description) {
		return _incidentLocationDao.update(code, description);
	}

	@Override
	public void deleteIncidentLocation(String code) {
		_incidentLocationDao.delete(code);		
	}

	@Override
	public List<IncidentLocationDetail> getIncidentLocationDetails() {
		return _incidentLocationDetailDao.get();
	}

	@Override
	public IncidentLocationDetail getIncidentLocationDetailByCode(String code) {
		return _incidentLocationDetailDao.getByCode(code);
	}

	@Override
	public IncidentLocationDetail createIncidentLocationDetail(String code, String description) {
		return _incidentLocationDetailDao.create(code, description);
	}

	@Override
	public IncidentLocationDetail updateIncidentLocationDetail(String code, String description) {
		return _incidentLocationDetailDao.update(code, description);
	}

	@Override
	public void deleteIncidentLocationDetail(String code) {
		_incidentLocationDetailDao.delete(code);		
	}

	@Override
	@Transactional
	public List<IncidentType> getIncidentTypes() {
		return _incidentTypeDao.get();
	}

	@Override
	@Transactional
	public IncidentType getIncidentTypeByCode(String code) {
		return _incidentTypeDao.getByCode(code);
	}

	@Override
	@Transactional
	public IncidentType createIncidentType(String code, String description) {
		return _incidentTypeDao.create(code, description);
	}

	@Override
	@Transactional
	public IncidentType updateIncidentType(String code, String description) {
		return _incidentTypeDao.update(code, description);
	}

	@Override
	@Transactional
	public void deleteIncidentType(String code) {
		_incidentTypeDao.delete(code);		
	}

	@Override
	@Transactional
	public List<InjuryCause> getInjuryCauses() {
		return _injuryCauseDao.get();
	}

	@Override
	@Transactional
	public InjuryCause getInjuryCauseByCode(String code) {
		return _injuryCauseDao.getByCode(code);
	}

	@Override
	@Transactional
	public InjuryCause createInjuryCause(String code, String description) {
		return _injuryCauseDao.create(code, description);
	}

	@Override
	@Transactional
	public InjuryCause updateInjuryCause(String code, String description) {
		return _injuryCauseDao.update(code, description);
	}

	@Override
	@Transactional
	public void deleteInjuryCause(String code) {
		_injuryCauseDao.delete(code);		
	}

	@Override
	public List<InjuryType> getInjuryTypes() {
		return _injuryTypeDao.get();
	}

	@Override
	public InjuryType getInjuryTypeByCode(String code) {
		return _injuryTypeDao.getByCode(code);
	}

	@Override
	public InjuryType createInjuryType(String code, String description) {
		return _injuryTypeDao.create(code, description);
	}

	@Override
	public InjuryType updateInjuryType(String code, String description) {
		return _injuryTypeDao.update(code, description);
	}

	@Override
	public void deleteInjuryType(String code) {
		_injuryTypeDao.delete(code);		
	}

	@Override
	public List<InjuryTypeDetail> getInjuryTypeDetails() {
		return _injuryTypeDetailDao.get();
	}

	@Override
	public InjuryTypeDetail getInjuryTypeDetailByCode(String code) {
		return _injuryTypeDetailDao.getByCode(code);
	}

	@Override
	public InjuryTypeDetail createInjuryTypeDetail(String code, String description) {
		return _injuryTypeDetailDao.create(code, description);
	}

	@Override
	public InjuryTypeDetail updateInjuryTypeDetail(String code, String description) {
		return _injuryTypeDetailDao.update(code, description);
	}

	@Override
	public void deleteInjuryTypeDetail(String code) {
		_injuryTypeDetailDao.delete(code);		
	}

	@Override
	public List<InjuryTypeDetailSpec> getInjuryTypeDetailSpecs() {
		return _injuryTypeDetailSpecDao.get();
	}

	@Override
	public InjuryTypeDetailSpec getInjuryTypeDetailSpecByCode(String code) {
		return _injuryTypeDetailSpecDao.getByCode(code);
	}

	@Override
	public InjuryTypeDetailSpec createInjuryTypeDetailSpec(String code, String description) {
		return _injuryTypeDetailSpecDao.create(code, description);
	}

	@Override
	public InjuryTypeDetailSpec updateInjuryTypeDetailSpec(String code, String description) {
		return _injuryTypeDetailSpecDao.update(code, description);
	}

	@Override
	public void deleteInjuryTypeDetailSpec(String code) {
		_injuryTypeDetailSpecDao.delete(code);		
	}

	@Override
	@Transactional
	public List<SuspectType> getSuspectTypes() {
		return _suspectTypeDao.get();
	}

	@Override
	@Transactional
	public SuspectType getSuspectTypeByCode(String code) {
		return _suspectTypeDao.getByCode(code);
	}

	@Override
	@Transactional
	public SuspectType createSuspectType(String code, String description) {
		return _suspectTypeDao.create(code, description);
	}

	@Override
	@Transactional
	public SuspectType updateSuspectType(String code, String description) {
		return _suspectTypeDao.update(code, description);
	}

	@Override
	@Transactional
	public void deleteSuspectType(String code) {
		_suspectTypeDao.delete(code);		
	}

	@Override
	@Transactional
	public List<WeaponType> getWeaponTypes() {
		return _weaponInvolvedDao.get();
	}

	@Override
	@Transactional
	public WeaponType getWeaponTypeByCode(String code) {
		return _weaponInvolvedDao.getByCode(code);
	}

	@Override
	@Transactional
	public WeaponType createWeaponType(String code, String description) {
		return _weaponInvolvedDao.create(code, description);
	}

	@Override
	@Transactional
	public WeaponType updateWeaponType(String code, String description) {
		return _weaponInvolvedDao.update(code, description);
	}

	@Override
	@Transactional
	public void deleteWeaponType(String code) {
		_weaponInvolvedDao.delete(code);		
	}
	
	@Override
	@Transactional
	public List<AccidentType> getAccidentTypes() {
		return _accidentTypeDao.get();
	}

	@Override
	@Transactional
	public AccidentType getAccidentTypeByCode(String code) {
		return _accidentTypeDao.getByCode(code);
	}

	@Override
	@Transactional
	public AccidentType createAccidentType(String code, String description) {
		return _accidentTypeDao.create(code, description);
	}

	@Override
	@Transactional
	public AccidentType updateAccidentType(String code, String description) {
		return _accidentTypeDao.update(code, description);
	}

	@Override
	@Transactional
	public void deleteAccidentType(String code) {
		_accidentTypeDao.delete(code);		
	}

	@Override
	@Transactional
	public List<BodyPart> getBodyParts() {
		return _bodyPartsDao.get();
	}

	@Override
	@Transactional
	public BodyPart getBodyPartByCode(String code) {
		return _bodyPartsDao.getByCode(code);
	}

	@Override
	@Transactional
	public BodyPart createBodyPart(String code, String description) {
		return _bodyPartsDao.create(code, description);
	}

	@Override
	@Transactional
	public BodyPart updateBodyPart(String code, String description) {
		return _bodyPartsDao.update(code, description);
	}

	@Override
	@Transactional
	public void deleteBodyPart(String code) {
		_bodyPartsDao.delete(code);		
	}

	@Override
	@Transactional
	public List<DocumentCategory> getDocumentCategories() {
		return _documentCategoryDao.get();
	}

	@Override
	@Transactional
	public DocumentCategory getDocumentCategoryByCode(String code) {
		return _documentCategoryDao.getByCode(code);
	}

	@Override
	@Transactional
	public DocumentCategory createDocumentCategory(String code, String description) {
		return _documentCategoryDao.create(code, description);
	}

	@Override
	@Transactional
	public DocumentCategory updateDocumentCategory(String code, String description) {
		return _documentCategoryDao.update(code, description);
	}

	@Override
	@Transactional
	public void deleteDocumentCategory(String code) {
		_documentCategoryDao.delete(code);		
	}

	@Override
	@Transactional
	public List<DocumentType> getDocumentTypes() {
		return _documentTypeDao.get();
	}

	@Override
	@Transactional
	public DocumentType getDocumentTypeByCode(String code) {
		return _documentTypeDao.getByCode(code);
	}

	@Override
	@Transactional
	public DocumentType createDocumentType(String code, String description) {
		return _documentTypeDao.create(code, description);
	}

	@Override
	@Transactional
	public DocumentType updateDocumentType(String code, String description) {
		return _documentTypeDao.update(code, description);
	}

	@Override
	@Transactional
	public void deleteDocumentType(String code) {
		_documentTypeDao.delete(code);		
	}

	@Override
	@Transactional
	public List<IncidentCategory> getIncidentCategories() {
		return _incidentCategoryDao.get();
	}

	@Override
	@Transactional
	public IncidentCategory getIncidentCategoryByCode(String code) {
		return _incidentCategoryDao.getByCode(code);
	}

	@Override
	@Transactional
	public IncidentCategory createIncidentCategory(String code, String description) {
		return _incidentCategoryDao.create(code, description);
	}

	@Override
	@Transactional
	public IncidentCategory updateIncidentCategory(String code, String description) {
		return _incidentCategoryDao.update(code, description);
	}

	@Override
	@Transactional
	public void deleteIncidentCategory(String code) {
		_incidentCategoryDao.delete(code);		
	}

	@Override
	@Transactional
	public List<GenderType> getGenderTypes() {
		return _genderTypeDao.get();
	}

	@Override
	@Transactional
	public GenderType getGenderTypeByCode(String code) {
		return _genderTypeDao.getByCode(code);
	}

	@Override
	@Transactional
	public GenderType createGenderType(String code, String description) {
		return _genderTypeDao.create(code, description);
	}

	@Override
	@Transactional
	public GenderType updateGenderType(String code, String description) {
		return _genderTypeDao.update(code, description);
	}

	@Override
	@Transactional
	public void deleteGenderType(String code) {
		_genderTypeDao.delete(code);		
	}

	@Override
	@Transactional
	public List<InjuredPersonType> getInjuredPersonTypes() {
		return _injuredPersonTypeDao.get();
	}

	@Override
	@Transactional
	public InjuredPersonType getInjuredPersonTypeByCode(String code) {
		return _injuredPersonTypeDao.getByCode(code);
	}

	@Override
	@Transactional
	public InjuredPersonType createInjuredPersonType(String code, String description) {
		return _injuredPersonTypeDao.create(code, description);
	}

	@Override
	@Transactional
	public InjuredPersonType updateInjuredPersonType(String code, String description) {
		return _injuredPersonTypeDao.update(code, description);
	}

	@Override
	@Transactional
	public void deleteInjuredPersonType(String code) {
		_injuredPersonTypeDao.delete(code);		
	}

	@Override
	@Transactional
	public List<LossType> getLossTypes() {
		return _lossTypeDao.get();
	}

	@Override
	@Transactional
	public LossType getLossTypeByCode(String code) {
		return _lossTypeDao.getByCode(code);
	}

	@Override
	@Transactional
	public LossType createLossType(String code, String description) {
		return _lossTypeDao.create(code, description);
	}

	@Override
	@Transactional
	public LossType updateLossType(String code, String description) {
		return _lossTypeDao.update(code, description);
	}

	@Override
	@Transactional
	public void deleteLossType(String code) {
		_lossTypeDao.delete(code);		
	}

	@Override
	@Transactional
	public List<PolicyType> getPolicyTypes() {
		return _policyTypeDao.get();
	}

	@Override
	@Transactional
	public PolicyType getPolicyTypeByCode(String code) {
		return _policyTypeDao.getByCode(code);
	}

	@Override
	@Transactional
	public PolicyType createPolicyType(String code, String description) {
		return _policyTypeDao.create(code, description);
	}

	@Override
	@Transactional
	public PolicyType updatePolicyType(String code, String description) {
		return _policyTypeDao.update(code, description);
	}

	@Override
	@Transactional
	public void deletePolicyType(String code) {
		_policyTypeDao.delete(code);		
	}

	@Override
	@Transactional
	public List<VehicleDamageType> getVehicleDamageTypes() {
		return _vehicleDamageTypeDao.get();
	}

	@Override
	@Transactional
	public VehicleDamageType getVehicleDamageTypeByCode(String code) {
		return _vehicleDamageTypeDao.getByCode(code);
	}

	@Override
	@Transactional
	public VehicleDamageType createVehicleDamageType(String code, String description) {
		return _vehicleDamageTypeDao.create(code, description);
	}

	@Override
	@Transactional
	public VehicleDamageType updateVehicleDamageType(String code, String description) {
		return _vehicleDamageTypeDao.update(code, description);
	}

	@Override
	@Transactional
	public void deleteVehicleDamageType(String code) {
		_vehicleDamageTypeDao.delete(code);		
	}
}
