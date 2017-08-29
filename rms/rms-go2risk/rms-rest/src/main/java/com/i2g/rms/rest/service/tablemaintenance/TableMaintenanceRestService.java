package com.i2g.rms.rest.service.tablemaintenance;

import java.util.List;

import com.i2g.rms.rest.model.tablemaintenance.AccidentLocationDetailRO;
import com.i2g.rms.rest.model.tablemaintenance.AccidentLocationRO;
import com.i2g.rms.rest.model.tablemaintenance.AccidentTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.AssetCategoryRO;
import com.i2g.rms.rest.model.tablemaintenance.BodyPartRO;
import com.i2g.rms.rest.model.tablemaintenance.ClaimRequestRegistrationTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.ClaimStatusRO;
import com.i2g.rms.rest.model.tablemaintenance.ClaimTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.DepartmentRO;
import com.i2g.rms.rest.model.tablemaintenance.DistinguishingFeatureDetailRO;
import com.i2g.rms.rest.model.tablemaintenance.DistinguishingFeatureRO;
import com.i2g.rms.rest.model.tablemaintenance.DocumentCategoryRO;
import com.i2g.rms.rest.model.tablemaintenance.DocumentTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.EmployeeTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.EntryPointRO;
import com.i2g.rms.rest.model.tablemaintenance.EventTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.ExternalAgencyRO;
import com.i2g.rms.rest.model.tablemaintenance.GenderTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.IncidentCategoryRO;
import com.i2g.rms.rest.model.tablemaintenance.IncidentLocationDetailRO;
import com.i2g.rms.rest.model.tablemaintenance.IncidentLocationRO;
import com.i2g.rms.rest.model.tablemaintenance.IncidentTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.InjuredPersonTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.InjuryCauseRO;
import com.i2g.rms.rest.model.tablemaintenance.InjuryTypeDetailRO;
import com.i2g.rms.rest.model.tablemaintenance.InjuryTypeDetailSpecRO;
import com.i2g.rms.rest.model.tablemaintenance.InjuryTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.LossTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.OrganizationRO;
import com.i2g.rms.rest.model.tablemaintenance.PolicyTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.PositionLevelRO;
import com.i2g.rms.rest.model.tablemaintenance.PositionRO;
import com.i2g.rms.rest.model.tablemaintenance.SuspectTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.TableMaintenanceRO;
import com.i2g.rms.rest.model.tablemaintenance.VehicleDamageTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.WeaponTypeRO;

/**
 * Rest Service Interface for all table maintenance objects.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface TableMaintenanceRestService {

	/** Generic method for table maintenance */
	public List<TableMaintenanceRO> get(final String tableName, final String operation);
	public TableMaintenanceRO getByCode(final String tableName, final String operation, final String code);
	public TableMaintenanceRO create(final TableMaintenanceRO tableMaintenanceRO, final String tableName, final String operation);
	public TableMaintenanceRO update(final TableMaintenanceRO tableMaintenanceRO, final String tableName, final String operation);
	public void delete(final TableMaintenanceRO tableMaintenanceRO, final String tableName, final String operation);	
	
	/** Methods related to Accident Location */
	public List<AccidentLocationRO> getAccidentLocations();
	public AccidentLocationRO getAccidentLocationByCode(final String code);
	public AccidentLocationRO createAccidentLocation(final AccidentLocationRO accidentLocationRO);
	public AccidentLocationRO updateAccidentLocation(final AccidentLocationRO accidentLocationRO);
	public void deleteAccidentLocation(final String code);

	/** Methods related to Accident Location Detail */
	public List<AccidentLocationDetailRO> getAccidentLocationDetails();
	public AccidentLocationDetailRO getAccidentLocationDetailByCode(final String code);
	public List<AccidentLocationDetailRO> getAccidentLocationDetailsForParent(final String code);
	public AccidentLocationDetailRO createAccidentLocationDetail(final AccidentLocationDetailRO accidentLocationDetailsRO);
	public AccidentLocationDetailRO updateAccidentLocationDetail(final AccidentLocationDetailRO accidentLocationDetailsRO);
	public void deleteAccidentLocationDetail(final String code);

	/** Methods related to Asset Category */
	public List<AssetCategoryRO> getAssetCategories();
	public AssetCategoryRO getAssetCategoryByCode(final String code);
	public AssetCategoryRO createAssetCategory(final AssetCategoryRO assetCategoryRO);
	public AssetCategoryRO updateAssetCategory(final AssetCategoryRO assetCategoryRO);
	public void deleteAssetCategory(final String code);

	/** Methods related to Claim Request Registration Type */
	public List<ClaimRequestRegistrationTypeRO> getClaimRequestRegistrationTypes();
	public ClaimRequestRegistrationTypeRO getClaimRequestRegistrationTypeByCode(final String code);
	public ClaimRequestRegistrationTypeRO createClaimRequestRegistrationType(final ClaimRequestRegistrationTypeRO claimRequestRegistrationTypeRO);
	public ClaimRequestRegistrationTypeRO updateClaimRequestRegistrationType(final ClaimRequestRegistrationTypeRO claimRequestRegistrationTypeRO);
	public void deleteClaimRequestRegistrationType(final String code);

	/** Methods related to Claim Status */
	public List<ClaimStatusRO> getClaimStatuses();
	public ClaimStatusRO getClaimStatusByCode(final String code);
	public ClaimStatusRO createClaimStatus(final ClaimStatusRO claimStatusRO);
	public ClaimStatusRO updateClaimStatus(final ClaimStatusRO claimStatusRO);
	public void deleteClaimStatus(final String code);

	/** Methods related to Claim Type */
	public List<ClaimTypeRO> getClaimTypes();
	public ClaimTypeRO getClaimTypeByCode(final String code);
	public ClaimTypeRO createClaimType(final ClaimTypeRO claimTypeRO);
	public ClaimTypeRO updateClaimType(final ClaimTypeRO claimTypeRO);
	public void deleteClaimType(final String code);

	/** Methods related to Distinguishing Feature */
	public List<DistinguishingFeatureRO> getDistinguishingFeatures();
	public DistinguishingFeatureRO getDistinguishingFeatureByCode(final String code);
	public DistinguishingFeatureRO createDistinguishingFeature(final DistinguishingFeatureRO distinguishingFeaturesRO);
	public DistinguishingFeatureRO updateDistinguishingFeature(final DistinguishingFeatureRO distinguishingFeaturesRO);
	public void deleteDistinguishingFeature(final String code);

	/** Methods related to Distinguishing Feature Detail */
	public List<DistinguishingFeatureDetailRO> getDistinguishingFeatureDetails();
	public List<DistinguishingFeatureDetailRO> getDistinguishingFeatureDetailsForParent(final String code);
	public DistinguishingFeatureDetailRO getDistinguishingFeatureDetailByCode(final String code);
	public DistinguishingFeatureDetailRO createDistinguishingFeatureDetail(final DistinguishingFeatureDetailRO distinguishingFeaturesDetailRO);
	public DistinguishingFeatureDetailRO updateDistinguishingFeatureDetail(final DistinguishingFeatureDetailRO distinguishingFeaturesDetailRO);
	public void deleteDistinguishingFeatureDetail(final String code);

	/** Methods related to Employee Type */
	public List<EmployeeTypeRO> getEmployeeTypes();
	public EmployeeTypeRO getEmployeeTypeByCode(final String code);
	public EmployeeTypeRO createEmployeeType(final EmployeeTypeRO employeeTypeRO);
	public EmployeeTypeRO updateEmployeeType(final EmployeeTypeRO employeeTypeRO);
	public void deleteEmployeeType(final String code);
	
	/** Methods related to Entry Point */
	public List<EntryPointRO> getEntryPoints();
	public EntryPointRO getEntryPointByCode(final String code);
	public EntryPointRO createEntryPoint(final EntryPointRO entryPointRO);
	public EntryPointRO updateEntryPoint(final EntryPointRO entryPointRO);
	public void deleteEntryPoint(final String code);

	/** Methods related to Event Type */
	public List<EventTypeRO> getEventTypes();
	public EventTypeRO getEventTypeByCode(final String code);
	public EventTypeRO createEventType(final EventTypeRO eventTypeRO);
	public EventTypeRO updateEventType(final EventTypeRO eventTypeRO);
	public void deleteEventType(final String code);

	/** Methods related to External Agency */
	public List<ExternalAgencyRO> getExternalAgencies();
	public ExternalAgencyRO getExternalAgencyByCode(final String code);
	public ExternalAgencyRO createExternalAgency(final ExternalAgencyRO externalAgencyRO);
	public ExternalAgencyRO updateExternalAgency(final ExternalAgencyRO externalAgencyRO);
	public void deleteExternalAgency(final String code);

	/** Methods related to Incident Location */
	public List<IncidentLocationRO> getIncidentLocations();
	public IncidentLocationRO getIncidentLocationByCode(final String code);
	public IncidentLocationRO createIncidentLocation(final IncidentLocationRO incidentLocationRO);
	public IncidentLocationRO updateIncidentLocation(final IncidentLocationRO incidentLocationRO);
	public void deleteIncidentLocation(final String code);

	/** Methods related to Incident Location Detail */
	public List<IncidentLocationDetailRO> getIncidentLocationDetails();
	public IncidentLocationDetailRO getIncidentLocationDetailByCode(final String code);
	public List<IncidentLocationDetailRO> getIncidentLocationDetailsForParent(final String code);
	public IncidentLocationDetailRO createIncidentLocationDetail(final IncidentLocationDetailRO incidentLocationDetailsRO);
	public IncidentLocationDetailRO updateIncidentLocationDetail(final IncidentLocationDetailRO incidentLocationDetailsRO);
	public void deleteIncidentLocationDetail(final String code);

	/** Methods related to Incident Type */
	public List<IncidentTypeRO> getIncidentTypes();
	public IncidentTypeRO getIncidentTypeByCode(final String code);
	public IncidentTypeRO createIncidentType(final IncidentTypeRO incidentTypeRO);
	public IncidentTypeRO updateIncidentType(final IncidentTypeRO incidentTypeRO);
	public void deleteIncidentType(final String code);

	/** Methods related to Injury Cause */
	public List<InjuryCauseRO> getInjuryCauses();
	public InjuryCauseRO getInjuryCauseByCode(final String code);
	public InjuryCauseRO createInjuryCause(final InjuryCauseRO injuryCauseRO);
	public InjuryCauseRO updateInjuryCause(final InjuryCauseRO injuryCauseRO);
	public void deleteInjuryCause(final String code);

	/** Methods related to Injury Type*/
	public List<InjuryTypeRO> getInjuryTypes();
	public InjuryTypeRO getInjuryTypeByCode(final String code);
	public InjuryTypeRO createInjuryType(final InjuryTypeRO injuryTypeRO);
	public InjuryTypeRO updateInjuryType(final InjuryTypeRO injuryTypeRO);
	public void deleteInjuryType(final String code);

	/** Methods related to Injury Type Detail */
	public List<InjuryTypeDetailRO> getInjuryTypeDetails();
	public List<InjuryTypeDetailRO> getInjuryTypeDetailsForParent(final String code);
	public InjuryTypeDetailRO getInjuryTypeDetailByCode(final String code);
	public InjuryTypeDetailRO createInjuryTypeDetail(final InjuryTypeDetailRO injuryTypeDetailsRO);
	public InjuryTypeDetailRO updateInjuryTypeDetail(final InjuryTypeDetailRO injuryTypeDetailsRO);
	public void deleteInjuryTypeDetail(final String code);

	/** Methods related to Injury Type Detail Specification */
	public List<InjuryTypeDetailSpecRO> getInjuryTypeDetailSpecs();
	public List<InjuryTypeDetailSpecRO> getInjuryTypeDetailSpecsForParent(final String code);
	public InjuryTypeDetailSpecRO getInjuryTypeDetailSpecByCode(final String code);
	public InjuryTypeDetailSpecRO createInjuryTypeDetailSpec(final InjuryTypeDetailSpecRO injuryTypeDetailsSpecRO);
	public InjuryTypeDetailSpecRO updateInjuryTypeDetailSpec(final InjuryTypeDetailSpecRO injuryTypeDetailsSpecRO);
	public void deleteInjuryTypeDetailSpec(final String code);

	/** Methods related to Suspect Type */
	public List<SuspectTypeRO> getSuspectTypes();
	public SuspectTypeRO getSuspectTypeByCode(final String code);
	public SuspectTypeRO createSuspectType(final SuspectTypeRO suspectTypeRO);
	public SuspectTypeRO updateSuspectType(final SuspectTypeRO suspectTypeRO);
	public void deleteSuspectType(final String code);

	/** Methods related to Weapon Type */
	public List<WeaponTypeRO> getWeaponTypes();
	public WeaponTypeRO getWeaponTypeByCode(final String code);
	public WeaponTypeRO createWeaponType(final WeaponTypeRO weaponInvolvedRO);
	public WeaponTypeRO updateWeaponType(final WeaponTypeRO weaponInvolvedRO);
	public void deleteWeaponType(final String code);
	
	/** Methods related to Accident Type */
	public List<AccidentTypeRO> getAccidentTypes();
	public AccidentTypeRO getAccidentTypeByCode(final String code);
	public AccidentTypeRO createAccidentType(final AccidentTypeRO accidentTypeRO);
	public AccidentTypeRO updateAccidentType(final AccidentTypeRO accidentTypeRO);
	public void deleteAccidentType(final String code);
	
	/** Methods related to Body Part */
	public List<BodyPartRO> getBodyParts();
	public BodyPartRO getBodyPartByCode(final String code);
	public BodyPartRO createBodyPart(final BodyPartRO bodyPartRO);
	public BodyPartRO updateBodyPart(final BodyPartRO bodyPartRO);
	public void deleteBodyPart(final String code);
	
	/** Methods related to Document Category */
	public List<DocumentCategoryRO> getDocumentCategories();
	public DocumentCategoryRO getDocumentCategoryByCode(final String code);
	public DocumentCategoryRO createDocumentCategory(final DocumentCategoryRO documentCategoryRO);
	public DocumentCategoryRO updateDocumentCategory(final DocumentCategoryRO documentCategoryRO);
	public void deleteDocumentCategory(final String code);
	
	/** Methods related to Document Type */
	public List<DocumentTypeRO> getDocumentTypes();
	public DocumentTypeRO getDocumentTypeByCode(final String code);
	public DocumentTypeRO createDocumentType(final DocumentTypeRO documentTypeRO);
	public DocumentTypeRO updateDocumentType(final DocumentTypeRO documentTypeRO);
	public void deleteDocumentType(final String code);
	
	/** Methods related to Gender Type */
	public List<GenderTypeRO> getGenderTypes();
	public GenderTypeRO getGenderTypeByCode(final String code);
	public GenderTypeRO createGenderType(final GenderTypeRO genderTypeRO);
	public GenderTypeRO updateGenderType(final GenderTypeRO genderTypeRO);
	public void deleteGenderType(final String code);
	
	/** Methods related to Incident Category */
	public List<IncidentCategoryRO> getIncidentCategories();
	public IncidentCategoryRO getIncidentCategoryByCode(final String code);
	public IncidentCategoryRO createIncidentCategory(final IncidentCategoryRO incidentCategoryRO);
	public IncidentCategoryRO updateIncidentCategory(final IncidentCategoryRO incidentCategoryRO);
	public void deleteIncidentCategory(final String code);
	
	/** Methods related to Injured Person Type */
	public List<InjuredPersonTypeRO> getInjuredPersonTypes();
	public InjuredPersonTypeRO getInjuredPersonTypeByCode(final String code);
	public InjuredPersonTypeRO createInjuredPersonType(final InjuredPersonTypeRO injuredPersonTypeRO);
	public InjuredPersonTypeRO updateInjuredPersonType(final InjuredPersonTypeRO injuredPersonTypeRO);
	public void deleteInjuredPersonType(final String code);
	
	/** Methods related to Loss Type */
	public List<LossTypeRO> getLossTypes();
	public LossTypeRO getLossTypeByCode(final String code);
	public LossTypeRO createLossType(final LossTypeRO lossTypeRO);
	public LossTypeRO updateLossType(final LossTypeRO lossTypeRO);
	public void deleteLossType(final String code);
	
	/** Methods related to Policy Type */
	public List<PolicyTypeRO> getPolicyTypes();
	public PolicyTypeRO getPolicyTypeByCode(final String code);
	public PolicyTypeRO createPolicyType(final PolicyTypeRO policyTypeRO);
	public PolicyTypeRO updatePolicyType(final PolicyTypeRO policyTypeRO);
	public void deletePolicyType(final String code);
	
	/** Methods related to Vehicle Damage Type */
	public List<VehicleDamageTypeRO> getVehicleDamageTypes();
	public VehicleDamageTypeRO getVehicleDamageTypeByCode(final String code);
	public VehicleDamageTypeRO createVehicleDamageType(final VehicleDamageTypeRO vehicleDamageTypeRO);
	public VehicleDamageTypeRO updateVehicleDamageType(final VehicleDamageTypeRO vehicleDamageTypeRO);
	public void deleteVehicleDamageType(final String code);	
	
	/** Methods related to Organization */
	public List<OrganizationRO> getOrganizations();
	public OrganizationRO getOrganizationByCode(final String code);
	public OrganizationRO createOrganization(final OrganizationRO organizationRO);
	public OrganizationRO updateOrganization(final OrganizationRO organizationRO);
	public void deleteOrganization(final String code);
	
	/** Methods related to Department */
	public List<DepartmentRO> getDepartments();
	public List<DepartmentRO> getDepartmentsForOrganization(final String code);
	public DepartmentRO getDepartmentByCode(final String code);
	public DepartmentRO createDepartment(final DepartmentRO departmentRO);
	public DepartmentRO updateDepartment(final DepartmentRO departmentRO);
	public void deleteDepartment(final String code);
	
	/** Methods related to Position */
	public List<PositionRO> getPositions();
	public List<PositionRO> getPositionsForOrganization(final String code);
	public List<PositionRO> getPositionsForDepartment(final String code);
	public List<PositionRO> getPositionsForPositionLevel(final String code);
	public PositionRO getPositionByCode(final String code);
	public PositionRO createPosition(final PositionRO positionRO);
	public PositionRO updatePosition(final PositionRO positionRO);
	public void deletePosition(final String code);
	
	/** Methods related to Position Level */
	public List<PositionLevelRO> getPositionLevels();
	public PositionLevelRO getPositionLevelByCode(final String code);
	public PositionLevelRO createPositionLevel(final PositionLevelRO positionLevelRO);
	public PositionLevelRO updatePositionLevel(final PositionLevelRO positionLevelRO);
	public void deletePositionLevel(final String code);
}
