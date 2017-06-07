package com.i2g.rms.rest.service.tablemaintenance;

import java.util.List;

import com.i2g.rms.rest.model.tablemaintenance.AccidentLocationDetailsRO;
import com.i2g.rms.rest.model.tablemaintenance.AccidentLocationRO;
import com.i2g.rms.rest.model.tablemaintenance.AssetCategoryRO;
import com.i2g.rms.rest.model.tablemaintenance.ClaimRequestRegistrationTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.ClaimStatusRO;
import com.i2g.rms.rest.model.tablemaintenance.ClaimTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.DistinguishingFeaturesDetailRO;
import com.i2g.rms.rest.model.tablemaintenance.DistinguishingFeaturesRO;
import com.i2g.rms.rest.model.tablemaintenance.EmployeeTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.EntryPointRO;
import com.i2g.rms.rest.model.tablemaintenance.EventTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.ExternalAgencyRO;
import com.i2g.rms.rest.model.tablemaintenance.IncidentLocationDetailsRO;
import com.i2g.rms.rest.model.tablemaintenance.IncidentLocationRO;
import com.i2g.rms.rest.model.tablemaintenance.IncidentTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.InjuryCauseRO;
import com.i2g.rms.rest.model.tablemaintenance.InjuryTypeDetailsRO;
import com.i2g.rms.rest.model.tablemaintenance.InjuryTypeDetailsSpecRO;
import com.i2g.rms.rest.model.tablemaintenance.InjuryTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.SuspectTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.TableMaintenanceRO;
import com.i2g.rms.rest.model.tablemaintenance.WeaponInvolvedRO;

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

	/** Methods related to Accident Location Details */
	public List<AccidentLocationDetailsRO> getAccidentLocationDetails();
	public AccidentLocationDetailsRO getAccidentLocationDetailsByCode(final String code);
	public AccidentLocationDetailsRO createAccidentLocationDetails(final AccidentLocationDetailsRO accidentLocationDetailsRO);
	public AccidentLocationDetailsRO updateAccidentLocationDetails(final AccidentLocationDetailsRO accidentLocationDetailsRO);
	public void deleteAccidentLocationDetails(final String code);

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

	/** Methods related to Distinguishing Features */
	public List<DistinguishingFeaturesRO> getDistinguishingFeatures();
	public DistinguishingFeaturesRO getDistinguishingFeaturesByCode(final String code);
	public DistinguishingFeaturesRO createDistinguishingFeatures(final DistinguishingFeaturesRO distinguishingFeaturesRO);
	public DistinguishingFeaturesRO updateDistinguishingFeatures(final DistinguishingFeaturesRO distinguishingFeaturesRO);
	public void deleteDistinguishingFeatures(final String code);

	/** Methods related to Distinguishing Features Detail */
	public List<DistinguishingFeaturesDetailRO> getDistinguishingFeaturesDetails();
	public DistinguishingFeaturesDetailRO getDistinguishingFeaturesDetailByCode(final String code);
	public DistinguishingFeaturesDetailRO createDistinguishingFeaturesDetail(final DistinguishingFeaturesDetailRO distinguishingFeaturesDetailRO);
	public DistinguishingFeaturesDetailRO updateDistinguishingFeaturesDetail(final DistinguishingFeaturesDetailRO distinguishingFeaturesDetailRO);
	public void deleteDistinguishingFeaturesDetail(final String code);

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

	/** Methods related to Incident Location Details */
	public List<IncidentLocationDetailsRO> getIncidentLocationDetails();
	public IncidentLocationDetailsRO getIncidentLocationDetailsByCode(final String code);
	public IncidentLocationDetailsRO createIncidentLocationDetails(final IncidentLocationDetailsRO incidentLocationDetailsRO);
	public IncidentLocationDetailsRO updateIncidentLocationDetails(final IncidentLocationDetailsRO incidentLocationDetailsRO);
	public void deleteIncidentLocationDetails(final String code);

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

	/** Methods related to Injury Type Details */
	public List<InjuryTypeDetailsRO> getInjuryTypeDetails();
	public InjuryTypeDetailsRO getInjuryTypeDetailsByCode(final String code);
	public InjuryTypeDetailsRO createInjuryTypeDetails(final InjuryTypeDetailsRO injuryTypeDetailsRO);
	public InjuryTypeDetailsRO updateInjuryTypeDetails(final InjuryTypeDetailsRO injuryTypeDetailsRO);
	public void deleteInjuryTypeDetails(final String code);

	/** Methods related to Injury Type Details Spec */
	public List<InjuryTypeDetailsSpecRO> getInjuryTypeDetailsSpecs();
	public InjuryTypeDetailsSpecRO getInjuryTypeDetailsSpecByCode(final String code);
	public InjuryTypeDetailsSpecRO createInjuryTypeDetailsSpec(final InjuryTypeDetailsSpecRO injuryTypeDetailsSpecRO);
	public InjuryTypeDetailsSpecRO updateInjuryTypeDetailsSpec(final InjuryTypeDetailsSpecRO injuryTypeDetailsSpecRO);
	public void deleteInjuryTypeDetailsSpec(final String code);

	/** Methods related to Suspect Type */
	public List<SuspectTypeRO> getSuspectTypes();
	public SuspectTypeRO getSuspectTypeByCode(final String code);
	public SuspectTypeRO createSuspectType(final SuspectTypeRO suspectTypeRO);
	public SuspectTypeRO updateSuspectType(final SuspectTypeRO suspectTypeRO);
	public void deleteSuspectType(final String code);

	/** Methods related to Weapon Involved */
	public List<WeaponInvolvedRO> getWeaponsInvolved();
	public WeaponInvolvedRO getWeaponInvolvedByCode(final String code);
	public WeaponInvolvedRO createWeaponInvolved(final WeaponInvolvedRO weaponInvolvedRO);
	public WeaponInvolvedRO updateWeaponInvolved(final WeaponInvolvedRO weaponInvolvedRO);
	public void deleteWeaponInvolved(final String code);
	
}
