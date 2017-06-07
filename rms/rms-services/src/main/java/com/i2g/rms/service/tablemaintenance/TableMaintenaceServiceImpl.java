package com.i2g.rms.service.tablemaintenance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i2g.rms.domain.model.tablemaintenance.AccidentLocation;
import com.i2g.rms.domain.model.tablemaintenance.AccidentLocationDetails;
import com.i2g.rms.domain.model.tablemaintenance.AssetCategory;
import com.i2g.rms.domain.model.tablemaintenance.ClaimRequestRegistrationType;
import com.i2g.rms.domain.model.tablemaintenance.ClaimStatus;
import com.i2g.rms.domain.model.tablemaintenance.ClaimType;
import com.i2g.rms.domain.model.tablemaintenance.DistinguishingFeatures;
import com.i2g.rms.domain.model.tablemaintenance.DistinguishingFeaturesDetail;
import com.i2g.rms.domain.model.tablemaintenance.EmployeeType;
import com.i2g.rms.domain.model.tablemaintenance.EntryPoint;
import com.i2g.rms.domain.model.tablemaintenance.EventType;
import com.i2g.rms.domain.model.tablemaintenance.ExternalAgency;
import com.i2g.rms.domain.model.tablemaintenance.IncidentLocation;
import com.i2g.rms.domain.model.tablemaintenance.IncidentLocationDetails;
import com.i2g.rms.domain.model.tablemaintenance.IncidentType;
import com.i2g.rms.domain.model.tablemaintenance.InjuryCause;
import com.i2g.rms.domain.model.tablemaintenance.InjuryType;
import com.i2g.rms.domain.model.tablemaintenance.InjuryTypeDetails;
import com.i2g.rms.domain.model.tablemaintenance.InjuryTypeDetailsSpec;
import com.i2g.rms.domain.model.tablemaintenance.SuspectType;
import com.i2g.rms.domain.model.tablemaintenance.WeaponInvolved;
import com.i2g.rms.persistence.dao.tablemaintenance.AccidentLocationDao;
import com.i2g.rms.persistence.dao.tablemaintenance.AccidentLocationDetailsDao;
import com.i2g.rms.persistence.dao.tablemaintenance.AssetCategoryDao;
import com.i2g.rms.persistence.dao.tablemaintenance.ClaimRequestRegistrationTypeDao;
import com.i2g.rms.persistence.dao.tablemaintenance.ClaimStatusDao;
import com.i2g.rms.persistence.dao.tablemaintenance.ClaimTypeDao;
import com.i2g.rms.persistence.dao.tablemaintenance.DistinguishingFeaturesDao;
import com.i2g.rms.persistence.dao.tablemaintenance.DistinguishingFeaturesDetailDao;
import com.i2g.rms.persistence.dao.tablemaintenance.EmployeeTypeDao;
import com.i2g.rms.persistence.dao.tablemaintenance.EntryPointDao;
import com.i2g.rms.persistence.dao.tablemaintenance.EventTypeDao;
import com.i2g.rms.persistence.dao.tablemaintenance.ExternalAgencyDao;
import com.i2g.rms.persistence.dao.tablemaintenance.IncidentLocationDao;
import com.i2g.rms.persistence.dao.tablemaintenance.IncidentLocationDetailsDao;
import com.i2g.rms.persistence.dao.tablemaintenance.IncidentTypeDao;
import com.i2g.rms.persistence.dao.tablemaintenance.InjuryCauseDao;
import com.i2g.rms.persistence.dao.tablemaintenance.InjuryTypeDao;
import com.i2g.rms.persistence.dao.tablemaintenance.InjuryTypeDetailsDao;
import com.i2g.rms.persistence.dao.tablemaintenance.InjuryTypeDetailsSpecDao;
import com.i2g.rms.persistence.dao.tablemaintenance.SuspectTypeDao;
import com.i2g.rms.persistence.dao.tablemaintenance.TableMaintenanceDao;
import com.i2g.rms.persistence.dao.tablemaintenance.WeaponInvolvedDao;
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
	private AccidentLocationDetailsDao _accidentLocationDetailsDao;
	@Autowired
	private AssetCategoryDao _assetCategoryDao;
	@Autowired
	private ClaimRequestRegistrationTypeDao _claimRequestRegistrationTypeDao;
	@Autowired
	private ClaimStatusDao _claimStatusDao;
	@Autowired
	private ClaimTypeDao _claimTypeDao;
	@Autowired
	private DistinguishingFeaturesDao _distinguishingFeaturesDao;
	@Autowired
	private DistinguishingFeaturesDetailDao _distinguishingFeaturesDetailDao;
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
	private IncidentLocationDetailsDao _incidentLocationDetailsDao;
	@Autowired
	private IncidentTypeDao _incidentTypeDao;
	@Autowired
	private InjuryCauseDao _injuryCauseDao;
	@Autowired
	private InjuryTypeDao _injuryTypeDao;
	@Autowired
	private InjuryTypeDetailsDao _injuryTypeDetailsDao;
	@Autowired
	private InjuryTypeDetailsSpecDao _injuryTypeDetailsSpecDao;
	@Autowired
	private SuspectTypeDao _suspectTypeDao;
	@Autowired
	private WeaponInvolvedDao _weaponInvolvedDao;
		
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
	public List<AccidentLocationDetails> getAccidentLocationDetails() {
		return _accidentLocationDetailsDao.get();
	}

	@Override
	public AccidentLocationDetails getAccidentLocationDetailsByCode(String code) {
		return _accidentLocationDetailsDao.getByCode(code);
	}

	@Override
	public AccidentLocationDetails createAccidentLocationDetails(String code, String description) {
		return _accidentLocationDetailsDao.create(code, description);
	}

	@Override
	public AccidentLocationDetails updateAccidentLocationDetails(String code, String description) {
		return _accidentLocationDetailsDao.update(code, description);
	}

	@Override
	public void deleteAccidentLocationDetails(String code) {
		_accidentLocationDetailsDao.delete(code);		
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
	public List<DistinguishingFeatures> getDistinguishingFeatures() {
		return _distinguishingFeaturesDao.get();
	}

	@Override
	public DistinguishingFeatures getDistinguishingFeaturesByCode(String code) {
		return _distinguishingFeaturesDao.getByCode(code);
	}

	@Override
	public DistinguishingFeatures createDistinguishingFeatures(String code, String description) {
		return _distinguishingFeaturesDao.create(code, description);
	}

	@Override
	public DistinguishingFeatures updateDistinguishingFeatures(String code, String description) {
		return _distinguishingFeaturesDao.update(code, description);
	}

	@Override
	public void deleteDistinguishingFeatures(String code) {
		_distinguishingFeaturesDao.delete(code);		
	}

	@Override
	public List<DistinguishingFeaturesDetail> getDistinguishingFeaturesDetails() {
		return _distinguishingFeaturesDetailDao.get();
	}

	@Override
	public DistinguishingFeaturesDetail getDistinguishingFeaturesDetailByCode(String code) {
		return _distinguishingFeaturesDetailDao.getByCode(code);
	}

	@Override
	public DistinguishingFeaturesDetail createDistinguishingFeaturesDetail(String code, String description) {
		return _distinguishingFeaturesDetailDao.create(code, description);
	}

	@Override
	public DistinguishingFeaturesDetail updateDistinguishingFeaturesDetail(String code, String description) {
		return _distinguishingFeaturesDetailDao.update(code, description);
	}

	@Override
	public void deleteDistinguishingFeaturesDetail(String code) {
		_distinguishingFeaturesDetailDao.delete(code);		
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
	public List<IncidentLocationDetails> getIncidentLocationDetails() {
		return _incidentLocationDetailsDao.get();
	}

	@Override
	public IncidentLocationDetails getIncidentLocationDetailsByCode(String code) {
		return _incidentLocationDetailsDao.getByCode(code);
	}

	@Override
	public IncidentLocationDetails createIncidentLocationDetails(String code, String description) {
		return _incidentLocationDetailsDao.create(code, description);
	}

	@Override
	public IncidentLocationDetails updateIncidentLocationDetails(String code, String description) {
		return _incidentLocationDetailsDao.update(code, description);
	}

	@Override
	public void deleteIncidentLocationDetails(String code) {
		_incidentLocationDetailsDao.delete(code);		
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
	public List<InjuryTypeDetails> getInjuryTypeDetails() {
		return _injuryTypeDetailsDao.get();
	}

	@Override
	public InjuryTypeDetails getInjuryTypeDetailsByCode(String code) {
		return _injuryTypeDetailsDao.getByCode(code);
	}

	@Override
	public InjuryTypeDetails createInjuryTypeDetails(String code, String description) {
		return _injuryTypeDetailsDao.create(code, description);
	}

	@Override
	public InjuryTypeDetails updateInjuryTypeDetails(String code, String description) {
		return _injuryTypeDetailsDao.update(code, description);
	}

	@Override
	public void deleteInjuryTypeDetails(String code) {
		_injuryTypeDetailsDao.delete(code);		
	}

	@Override
	public List<InjuryTypeDetailsSpec> getInjuryTypeDetailsSpecs() {
		return _injuryTypeDetailsSpecDao.get();
	}

	@Override
	public InjuryTypeDetailsSpec getInjuryTypeDetailsSpecByCode(String code) {
		return _injuryTypeDetailsSpecDao.getByCode(code);
	}

	@Override
	public InjuryTypeDetailsSpec createInjuryTypeDetailsSpec(String code, String description) {
		return _injuryTypeDetailsSpecDao.create(code, description);
	}

	@Override
	public InjuryTypeDetailsSpec updateInjuryTypeDetailsSpec(String code, String description) {
		return _injuryTypeDetailsSpecDao.update(code, description);
	}

	@Override
	public void deleteInjuryTypeDetailsSpec(String code) {
		_injuryTypeDetailsSpecDao.delete(code);		
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
	public List<WeaponInvolved> getWeaponsInvolved() {
		return _weaponInvolvedDao.get();
	}

	@Override
	@Transactional
	public WeaponInvolved getWeaponInvolvedByCode(String code) {
		return _weaponInvolvedDao.getByCode(code);
	}

	@Override
	@Transactional
	public WeaponInvolved createWeaponInvolved(String code, String description) {
		return _weaponInvolvedDao.create(code, description);
	}

	@Override
	@Transactional
	public WeaponInvolved updateWeaponInvolved(String code, String description) {
		return _weaponInvolvedDao.update(code, description);
	}

	@Override
	@Transactional
	public void deleteWeaponInvolved(String code) {
		_weaponInvolvedDao.delete(code);		
	}
}
