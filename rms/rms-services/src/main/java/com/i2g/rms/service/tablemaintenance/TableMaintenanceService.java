package com.i2g.rms.service.tablemaintenance;

import java.util.List;

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
	
	/** Methods related to Accident Location Detail */
	public List<AccidentLocationDetail> getAccidentLocationDetails();
	public AccidentLocationDetail getAccidentLocationDetailByCode(final String code);
	public AccidentLocationDetail createAccidentLocationDetail(final String code, final String description);
	public AccidentLocationDetail updateAccidentLocationDetail(final String code, final String description);
	public void deleteAccidentLocationDetail(final String code);
	
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
	
	/** Methods related to Distinguishing Feature */
	public List<DistinguishingFeature> getDistinguishingFeatures();
	public DistinguishingFeature getDistinguishingFeatureByCode(final String code);
	public DistinguishingFeature createDistinguishingFeature(final String code, final String description);
	public DistinguishingFeature updateDistinguishingFeature(final String code, final String description);
	public void deleteDistinguishingFeature(final String code);
	
	/** Methods related to Distinguishing Feature Detail */
	public List<DistinguishingFeatureDetail> getDistinguishingFeatureDetails();
	public DistinguishingFeatureDetail getDistinguishingFeatureDetailByCode(final String code);
	public DistinguishingFeatureDetail createDistinguishingFeatureDetail(final String code, final String description);
	public DistinguishingFeatureDetail updateDistinguishingFeatureDetail(final String code, final String description);
	public void deleteDistinguishingFeatureDetail(final String code);
	
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
	
	/** Methods related to Incident Location Detail */
	public List<IncidentLocationDetail> getIncidentLocationDetails();
	public IncidentLocationDetail getIncidentLocationDetailByCode(final String code);
	public IncidentLocationDetail createIncidentLocationDetail(final String code, final String description);
	public IncidentLocationDetail updateIncidentLocationDetail(final String code, final String description);
	public void deleteIncidentLocationDetail(final String code);
	
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
	
	/** Methods related to Injury Type Detail */
	public List<InjuryTypeDetail> getInjuryTypeDetails();
	public InjuryTypeDetail getInjuryTypeDetailByCode(final String code);
	public InjuryTypeDetail createInjuryTypeDetail(final String code, final String description);
	public InjuryTypeDetail updateInjuryTypeDetail(final String code, final String description);
	public void deleteInjuryTypeDetail(final String code);
	
	/** Methods related to Injury Type Detail Spec */
	public List<InjuryTypeDetailSpec> getInjuryTypeDetailSpecs();
	public InjuryTypeDetailSpec getInjuryTypeDetailSpecByCode(final String code);
	public InjuryTypeDetailSpec createInjuryTypeDetailSpec(final String code, final String description);
	public InjuryTypeDetailSpec updateInjuryTypeDetailSpec(final String code, final String description);
	public void deleteInjuryTypeDetailSpec(final String code);
	
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
	
	/** Methods related to Accident Type */
	public List<AccidentType> getAccidentTypes();
	public AccidentType getAccidentTypeByCode(final String code);
	public AccidentType createAccidentType(final String code, final String description);
	public AccidentType updateAccidentType(final String code, final String description);
	public void deleteAccidentType(final String code);
	
	/** Methods related to Body Part */
	public List<BodyPart> getBodyParts();
	public BodyPart getBodyPartByCode(final String code);
	public BodyPart createBodyPart(final String code, final String description);
	public BodyPart updateBodyPart(final String code, final String description);
	public void deleteBodyPart(final String code);
	
	/** Methods related to Document Category */
	public List<DocumentCategory> getDocumentCategories();
	public DocumentCategory getDocumentCategoryByCode(final String code);
	public DocumentCategory createDocumentCategory(final String code, final String description);
	public DocumentCategory updateDocumentCategory(final String code, final String description);
	public void deleteDocumentCategory(final String code);
	
	/** Methods related to Document Type */
	public List<DocumentType> getDocumentTypes();
	public DocumentType getDocumentTypeByCode(final String code);
	public DocumentType createDocumentType(final String code, final String description);
	public DocumentType updateDocumentType(final String code, final String description);
	public void deleteDocumentType(final String code);
	
	/** Methods related to Incident Category */
	public List<IncidentCategory> getIncidentCategories();
	public IncidentCategory getIncidentCategoryByCode(final String code);
	public IncidentCategory createIncidentCategory(final String code, final String description);
	public IncidentCategory updateIncidentCategory(final String code, final String description);
	public void deleteIncidentCategory(final String code);
	
	/** Methods related to Gender Type */
	public List<GenderType> getGenderTypes();
	public GenderType getGenderTypeByCode(final String code);
	public GenderType createGenderType(final String code, final String description);
	public GenderType updateGenderType(final String code, final String description);
	public void deleteGenderType(final String code);
	
	/** Methods related to Injured Person Type */
	public List<InjuredPersonType> getInjuredPersonTypes();
	public InjuredPersonType getInjuredPersonTypeByCode(final String code);
	public InjuredPersonType createInjuredPersonType(final String code, final String description);
	public InjuredPersonType updateInjuredPersonType(final String code, final String description);
	public void deleteInjuredPersonType(final String code);
	
	/** Methods related to Loss Type */
	public List<LossType> getLossTypes();
	public LossType getLossTypeByCode(final String code);
	public LossType createLossType(final String code, final String description);
	public LossType updateLossType(final String code, final String description);
	public void deleteLossType(final String code);
	
	/** Methods related to Policy Type */
	public List<PolicyType> getPolicyTypes();
	public PolicyType getPolicyTypeByCode(final String code);
	public PolicyType createPolicyType(final String code, final String description);
	public PolicyType updatePolicyType(final String code, final String description);
	public void deletePolicyType(final String code);
	
	/** Methods related to Vehicle Damage Type */
	public List<VehicleDamageType> getVehicleDamageTypes();
	public VehicleDamageType getVehicleDamageTypeByCode(final String code);
	public VehicleDamageType createVehicleDamageType(final String code, final String description);
	public VehicleDamageType updateVehicleDamageType(final String code, final String description);
	public void deleteVehicleDamageType(final String code);
}
