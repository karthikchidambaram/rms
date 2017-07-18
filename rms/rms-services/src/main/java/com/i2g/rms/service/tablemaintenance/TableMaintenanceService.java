package com.i2g.rms.service.tablemaintenance;

import java.util.List;

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
import com.i2g.rms.domain.model.tablemaintenance.WeaponType;

/**
 * Service interface for all table maintenance operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface TableMaintenanceService {

	/** Generic methods */
	public List<Object[]> get(final String tableName, final String operation);
	public List<Object[]> getByCode(final String tableName, final String operation, final String code);
	public int create(final String tableName, final String operation, final String code, final String description);
	public int update(final String tableName, final String operation, final String code, final String description);
	public int delete(final String tableName, final String operation, final List<String> codes);

	/** Methods related to Accident Location */
	public List<AccidentLocation> getAccidentLocations();
	public AccidentLocation getAccidentLocationByCode(final String code);
	public AccidentLocation createAccidentLocation(final String code, final String description);
	public AccidentLocation updateAccidentLocation(final String code, final String description);
	public void deleteAccidentLocation(final String code);
	
	/** Methods related to Accident Location Details */
	public List<AccidentLocationDetails> getAccidentLocationDetails();
	public AccidentLocationDetails getAccidentLocationDetailsByCode(final String code);
	public AccidentLocationDetails createAccidentLocationDetails(final String code, final String description);
	public AccidentLocationDetails updateAccidentLocationDetails(final String code, final String description);
	public void deleteAccidentLocationDetails(final String code);
	
	/** Methods related to Asset Category */
	public List<AssetCategory> getAssetCategories();
	public AssetCategory getAssetCategoryByCode(final String code);
	public AssetCategory createAssetCategory(final String code, final String description);
	public AssetCategory updateAssetCategory(final String code, final String description);
	public void deleteAssetCategory(final String code);
	
	/** Methods related to Claim Request Registration Type */
	public List<ClaimRequestRegistrationType> getClaimRequestRegistrationTypes();
	public ClaimRequestRegistrationType getClaimRequestRegistrationTypeByCode(final String code);
	public ClaimRequestRegistrationType createClaimRequestRegistrationType(final String code, final String description);
	public ClaimRequestRegistrationType updateClaimRequestRegistrationType(final String code, final String description);
	public void deleteClaimRequestRegistrationType(final String code);
	
	/** Methods related to Claim Status */
	public List<ClaimStatus> getClaimStatuses();
	public ClaimStatus getClaimStatusByCode(final String code);
	public ClaimStatus createClaimStatus(final String code, final String description);
	public ClaimStatus updateClaimStatus(final String code, final String description);
	public void deleteClaimStatus(final String code);
	
	/** Methods related to Claim Type */
	public List<ClaimType> getClaimTypes();
	public ClaimType getClaimTypeByCode(final String code);
	public ClaimType createClaimType(final String code, final String description);
	public ClaimType updateClaimType(final String code, final String description);
	public void deleteClaimType(final String code);
	
	/** Methods related to Distinguishing Features */
	public List<DistinguishingFeatures> getDistinguishingFeatures();
	public DistinguishingFeatures getDistinguishingFeaturesByCode(final String code);
	public DistinguishingFeatures createDistinguishingFeatures(final String code, final String description);
	public DistinguishingFeatures updateDistinguishingFeatures(final String code, final String description);
	public void deleteDistinguishingFeatures(final String code);
	
	/** Methods related to Distinguishing Features Detail */
	public List<DistinguishingFeaturesDetail> getDistinguishingFeaturesDetails();
	public DistinguishingFeaturesDetail getDistinguishingFeaturesDetailByCode(final String code);
	public DistinguishingFeaturesDetail createDistinguishingFeaturesDetail(final String code, final String description);
	public DistinguishingFeaturesDetail updateDistinguishingFeaturesDetail(final String code, final String description);
	public void deleteDistinguishingFeaturesDetail(final String code);
	
	/** Methods related to Employee Type */
	public List<EmployeeType> getEmployeeTypes();
	public EmployeeType getEmployeeTypeByCode(final String code);
	public EmployeeType createEmployeeType(final String code, final String description);
	public EmployeeType updateEmployeeType(final String code, final String description);
	public void deleteEmployeeType(final String code);
	
	/** Methods related to Entry Point */
	public List<EntryPoint> getEntryPoints();
	public EntryPoint getEntryPointByCode(final String code);
	public EntryPoint createEntryPoint(final String code, final String description);
	public EntryPoint updateEntryPoint(final String code, final String description);
	public void deleteEntryPoint(final String code);
	
	/** Methods related to Event Type */
	public List<EventType> getEventTypes();
	public EventType getEventTypeByCode(final String code);
	public EventType createEventType(final String code, final String description);
	public EventType updateEventType(final String code, final String description);
	public void deleteEventType(final String code);
	
	/** Methods related to External Agency */
	public List<ExternalAgency> getExternalAgencies();
	public ExternalAgency getExternalAgencyByCode(final String code);
	public ExternalAgency createExternalAgency(final String code, final String description);
	public ExternalAgency updateExternalAgency(final String code, final String description);
	public void deleteExternalAgency(final String code);
	
	/** Methods related to Incident Location */
	public List<IncidentLocation> getIncidentLocations();
	public IncidentLocation getIncidentLocationByCode(final String code);
	public IncidentLocation createIncidentLocation(final String code, final String description);
	public IncidentLocation updateIncidentLocation(final String code, final String description);
	public void deleteIncidentLocation(final String code);
	
	/** Methods related to Incident Location Details */
	public List<IncidentLocationDetails> getIncidentLocationDetails();
	public IncidentLocationDetails getIncidentLocationDetailsByCode(final String code);
	public IncidentLocationDetails createIncidentLocationDetails(final String code, final String description);
	public IncidentLocationDetails updateIncidentLocationDetails(final String code, final String description);
	public void deleteIncidentLocationDetails(final String code);
	
	/** Methods related to Incident Type */
	public List<IncidentType> getIncidentTypes();
	public IncidentType getIncidentTypeByCode(final String code);
	public IncidentType createIncidentType(final String code, final String description);
	public IncidentType updateIncidentType(final String code, final String description);
	public void deleteIncidentType(final String code);
	
	/** Methods related to Injury Cause */
	public List<InjuryCause> getInjuryCauses();
	public InjuryCause getInjuryCauseByCode(final String code);
	public InjuryCause createInjuryCause(final String code, final String description);
	public InjuryCause updateInjuryCause(final String code, final String description);
	public void deleteInjuryCause(final String code);
	
	/** Methods related to Injury Type*/
	public List<InjuryType> getInjuryTypes();
	public InjuryType getInjuryTypeByCode(final String code);
	public InjuryType createInjuryType(final String code, final String description);
	public InjuryType updateInjuryType(final String code, final String description);
	public void deleteInjuryType(final String code);
	
	/** Methods related to Injury Type Details */
	public List<InjuryTypeDetails> getInjuryTypeDetails();
	public InjuryTypeDetails getInjuryTypeDetailsByCode(final String code);
	public InjuryTypeDetails createInjuryTypeDetails(final String code, final String description);
	public InjuryTypeDetails updateInjuryTypeDetails(final String code, final String description);
	public void deleteInjuryTypeDetails(final String code);
	
	/** Methods related to Injury Type Details Spec */
	public List<InjuryTypeDetailsSpec> getInjuryTypeDetailsSpecs();
	public InjuryTypeDetailsSpec getInjuryTypeDetailsSpecByCode(final String code);
	public InjuryTypeDetailsSpec createInjuryTypeDetailsSpec(final String code, final String description);
	public InjuryTypeDetailsSpec updateInjuryTypeDetailsSpec(final String code, final String description);
	public void deleteInjuryTypeDetailsSpec(final String code);
	
	/** Methods related to Suspect Type */
	public List<SuspectType> getSuspectTypes();
	public SuspectType getSuspectTypeByCode(final String code);
	public SuspectType createSuspectType(final String code, final String description);
	public SuspectType updateSuspectType(final String code, final String description);
	public void deleteSuspectType(final String code);
	
	/** Methods related to Weapon Type */
	public List<WeaponType> getWeaponTypes();
	public WeaponType getWeaponTypeByCode(final String code);
	public WeaponType createWeaponType(final String code, final String description);
	public WeaponType updateWeaponType(final String code, final String description);
	public void deleteWeaponType(final String code);

}
