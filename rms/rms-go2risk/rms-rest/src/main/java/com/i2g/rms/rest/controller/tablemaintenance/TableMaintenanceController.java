package com.i2g.rms.rest.controller.tablemaintenance;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.controller.AbstractRestController;
import com.i2g.rms.rest.model.tablemaintenance.AccidentLocationDetailRO;
import com.i2g.rms.rest.model.tablemaintenance.AccidentLocationRO;
import com.i2g.rms.rest.model.tablemaintenance.AccidentTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.AssetCategoryRO;
import com.i2g.rms.rest.model.tablemaintenance.BodyPartRO;
import com.i2g.rms.rest.model.tablemaintenance.ClaimRequestRegistrationTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.ClaimStatusRO;
import com.i2g.rms.rest.model.tablemaintenance.ClaimTypeRO;
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
import com.i2g.rms.rest.model.tablemaintenance.PolicyTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.SuspectTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.TableMaintenanceRO;
import com.i2g.rms.rest.model.tablemaintenance.VehicleDamageTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.WeaponTypeRO;
import com.i2g.rms.rest.service.tablemaintenance.TableMaintenanceRestService;

/**
 * Rest controller for all table maintenance objects read/create/update/delete
 * operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@RestController
public class TableMaintenanceController extends AbstractRestController {
	
	/** Generic rest service for all table maintenance related operations */
	@Autowired
	TableMaintenanceRestService _tableMaintenanceRestService;
	
	/** Generic approach */
	@RequestMapping(value = RequestMappingConstants.GET_TABLE_MAINTENANCE_DATA, method = RequestMethod.GET)
	public List<TableMaintenanceRO> get(@PathVariable final String tableName, @PathVariable final String operation) {
		return _tableMaintenanceRestService.get(tableName, operation);
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_TABLE_MAINTENANCE_DATA_BY_CODE, method = RequestMethod.GET)
	public TableMaintenanceRO getByCode(@PathVariable final String tableName, @PathVariable final String operation, @PathVariable final String code) {
		return _tableMaintenanceRestService.getByCode(tableName, operation, code);
	}
	
	@RequestMapping(value = RequestMappingConstants.CREATE_TABLE_MAINTENANCE_DATA, method = RequestMethod.POST)
	public TableMaintenanceRO create(final @Valid @RequestBody TableMaintenanceRO tableMaintenanceRO, @PathVariable final String tableName, @PathVariable final String operation) {
		return _tableMaintenanceRestService.create(tableMaintenanceRO, tableName, operation);
	}
	
	@RequestMapping(value = RequestMappingConstants.UPDATE_TABLE_MAINTENANCE_DATA, method = RequestMethod.PUT)
	public TableMaintenanceRO update(final @Valid @RequestBody TableMaintenanceRO tableMaintenanceRO, @PathVariable final String tableName, @PathVariable final String operation) {
		return _tableMaintenanceRestService.update(tableMaintenanceRO, tableName, operation);
	}
	
	@RequestMapping(value = RequestMappingConstants.DELETE_TABLE_MAINTENANCE_DATA, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(final @Valid @RequestBody TableMaintenanceRO tableMaintenanceRO, @PathVariable final String tableName, @PathVariable final String operation) {
		_tableMaintenanceRestService.delete(tableMaintenanceRO, tableName, operation);
	}	

	/** Individual approach */
	
	/** Methods related to Accident Location */
	@RequestMapping(value = RequestMappingConstants.GET_ACCIDENT_LOCATIONS, method = RequestMethod.GET)
	public List<AccidentLocationRO> getAccidentLocations() {
		return _tableMaintenanceRestService.getAccidentLocations();
	}

	@RequestMapping(value = RequestMappingConstants.GET_ACCIDENT_LOCATION_BY_CODE, method = RequestMethod.GET)
	public AccidentLocationRO getAccidentLocationByCode(@PathVariable final String code) {
		return _tableMaintenanceRestService.getAccidentLocationByCode(code);
	}

	@RequestMapping(value = RequestMappingConstants.CREATE_ACCIDENT_LOCATION, method = RequestMethod.POST)
	public AccidentLocationRO createAccidentLocation(final @Valid @RequestBody AccidentLocationRO accidentLocationRO) {
		return _tableMaintenanceRestService.createAccidentLocation(accidentLocationRO);
	}

	@RequestMapping(value = RequestMappingConstants.UPDATE_ACCIDENT_LOCATION, method = RequestMethod.PUT)
	public AccidentLocationRO updateAccidentLocation(final @Valid @RequestBody AccidentLocationRO accidentLocationRO) {
		return _tableMaintenanceRestService.updateAccidentLocation(accidentLocationRO);
	}

	@RequestMapping(value = RequestMappingConstants.DELETE_ACCIDENT_LOCATION, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAccidentLocation(@PathVariable final String code) {
		_tableMaintenanceRestService.deleteAccidentLocation(code);
	}
	
	/** Methods related to Accident Location Details */
	@RequestMapping(value = RequestMappingConstants.GET_ACCIDENT_LOCATION_DETAILS, method = RequestMethod.GET)
	public List<AccidentLocationDetailRO> getAccidentLocationDetails() {
		return _tableMaintenanceRestService.getAccidentLocationDetails();
	}

	@RequestMapping(value = RequestMappingConstants.GET_ACCIDENT_LOCATION_DETAIL_BY_CODE, method = RequestMethod.GET)
	public AccidentLocationDetailRO getAccidentLocationDetailByCode(@PathVariable final String code) {
		return _tableMaintenanceRestService.getAccidentLocationDetailByCode(code);
	}

	@RequestMapping(value = RequestMappingConstants.CREATE_ACCIDENT_LOCATION_DETAIL, method = RequestMethod.POST)
	public AccidentLocationDetailRO createAccidentLocationDetail(final @Valid @RequestBody AccidentLocationDetailRO accidentLocationDetailsRO) {
		return _tableMaintenanceRestService.createAccidentLocationDetail(accidentLocationDetailsRO);
	}

	@RequestMapping(value = RequestMappingConstants.UPDATE_ACCIDENT_LOCATION_DETAIL, method = RequestMethod.PUT)
	public AccidentLocationDetailRO updateAccidentLocationDetail(final @Valid @RequestBody AccidentLocationDetailRO accidentLocationDetailsRO) {
		return _tableMaintenanceRestService.updateAccidentLocationDetail(accidentLocationDetailsRO);
	}

	@RequestMapping(value = RequestMappingConstants.DELETE_ACCIDENT_LOCATION_DETAIL, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAccidentLocationDetail(@PathVariable final String code) {
		_tableMaintenanceRestService.deleteAccidentLocationDetail(code);
	}
	
	/** Methods related to Asset Category */
	@RequestMapping(value = RequestMappingConstants.GET_ASSET_CATEGORIES, method = RequestMethod.GET)
	public List<AssetCategoryRO> getAssetCategories() {
		return _tableMaintenanceRestService.getAssetCategories();
	}

	@RequestMapping(value = RequestMappingConstants.GET_ASSET_CATEGORY_BY_CODE, method = RequestMethod.GET)
	public AssetCategoryRO getAssetCategoryByCode(@PathVariable final String code) {
		return _tableMaintenanceRestService.getAssetCategoryByCode(code);
	}

	@RequestMapping(value = RequestMappingConstants.CREATE_ASSET_CATEGORY, method = RequestMethod.POST)
	public AssetCategoryRO createAssetCategory(final @Valid @RequestBody AssetCategoryRO accidentLocationDetailsRO) {
		return _tableMaintenanceRestService.createAssetCategory(accidentLocationDetailsRO);
	}

	@RequestMapping(value = RequestMappingConstants.UPDATE_ASSET_CATEGORY, method = RequestMethod.PUT)
	public AssetCategoryRO updateAssetCategory(final @Valid @RequestBody AssetCategoryRO accidentLocationDetailsRO) {
		return _tableMaintenanceRestService.updateAssetCategory(accidentLocationDetailsRO);
	}

	@RequestMapping(value = RequestMappingConstants.DELETE_ASSET_CATEGORY, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAssetCategory(@PathVariable final String code) {
		_tableMaintenanceRestService.deleteAssetCategory(code);
	}
	
	/** Methods related to Claim Request Registration Type */
	@RequestMapping(value = RequestMappingConstants.GET_CLAIM_REQUEST_REGISTRATION_TYPES, method = RequestMethod.GET)
	public List<ClaimRequestRegistrationTypeRO> getClaimRequestRegistrationTypes() {
		return _tableMaintenanceRestService.getClaimRequestRegistrationTypes();
	}

	@RequestMapping(value = RequestMappingConstants.GET_CLAIM_REQUEST_REGISTRATION_TYPE_BY_CODE, method = RequestMethod.GET)
	public ClaimRequestRegistrationTypeRO getClaimRequestRegistrationTypeByCode(@PathVariable final String code) {
		return _tableMaintenanceRestService.getClaimRequestRegistrationTypeByCode(code);
	}

	@RequestMapping(value = RequestMappingConstants.CREATE_CLAIM_REQUEST_REGISTRATION_TYPE, method = RequestMethod.POST)
	public ClaimRequestRegistrationTypeRO createClaimRequestRegistrationType(final @Valid @RequestBody ClaimRequestRegistrationTypeRO claimRequestRegistrationTypeRO) {
		return _tableMaintenanceRestService.createClaimRequestRegistrationType(claimRequestRegistrationTypeRO);
	}

	@RequestMapping(value = RequestMappingConstants.UPDATE_CLAIM_REQUEST_REGISTRATION_TYPE, method = RequestMethod.PUT)
	public ClaimRequestRegistrationTypeRO updateClaimRequestRegistrationType(final @Valid @RequestBody ClaimRequestRegistrationTypeRO claimRequestRegistrationTypeRO) {
		return _tableMaintenanceRestService.updateClaimRequestRegistrationType(claimRequestRegistrationTypeRO);
	}

	@RequestMapping(value = RequestMappingConstants.DELETE_CLAIM_REQUEST_REGISTRATION_TYPE, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteClaimRequestRegistrationType(@PathVariable final String code) {
		_tableMaintenanceRestService.deleteClaimRequestRegistrationType(code);
	}
	
	/** Methods related to Claim Status */
	@RequestMapping(value = RequestMappingConstants.GET_CLAIM_STATUSES, method = RequestMethod.GET)
	public List<ClaimStatusRO> getClaimStatuses() {
		return _tableMaintenanceRestService.getClaimStatuses();
	}

	@RequestMapping(value = RequestMappingConstants.GET_CLAIM_STATUS_BY_CODE, method = RequestMethod.GET)
	public ClaimStatusRO getClaimStatusByCode(@PathVariable final String code) {
		return _tableMaintenanceRestService.getClaimStatusByCode(code);
	}

	@RequestMapping(value = RequestMappingConstants.CREATE_CLAIM_STATUS, method = RequestMethod.POST)
	public ClaimStatusRO createClaimStatus(final @Valid @RequestBody ClaimStatusRO claimStatusRO) {
		return _tableMaintenanceRestService.createClaimStatus(claimStatusRO);
	}

	@RequestMapping(value = RequestMappingConstants.UPDATE_CLAIM_STATUS, method = RequestMethod.PUT)
	public ClaimStatusRO updateClaimStatus(final @Valid @RequestBody ClaimStatusRO claimStatusRO) {
		return _tableMaintenanceRestService.updateClaimStatus(claimStatusRO);
	}

	@RequestMapping(value = RequestMappingConstants.DELETE_CLAIM_STATUS, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteClaimStatus(@PathVariable final String code) {
		_tableMaintenanceRestService.deleteClaimStatus(code);
	}
	
	/** Methods related to Claim Type */
	@RequestMapping(value = RequestMappingConstants.GET_CLAIM_TYPES, method = RequestMethod.GET)
	public List<ClaimTypeRO> getClaimTypes() {
		return _tableMaintenanceRestService.getClaimTypes();
	}

	@RequestMapping(value = RequestMappingConstants.GET_CLAIM_TYPE_BY_CODE, method = RequestMethod.GET)
	public ClaimTypeRO getClaimTypeByCode(@PathVariable final String code) {
		return _tableMaintenanceRestService.getClaimTypeByCode(code);
	}

	@RequestMapping(value = RequestMappingConstants.CREATE_CLAIM_TYPE, method = RequestMethod.POST)
	public ClaimTypeRO createClaimType(final @Valid @RequestBody ClaimTypeRO claimTypeRO) {
		return _tableMaintenanceRestService.createClaimType(claimTypeRO);
	}

	@RequestMapping(value = RequestMappingConstants.UPDATE_CLAIM_TYPE, method = RequestMethod.PUT)
	public ClaimTypeRO updateClaimType(final @Valid @RequestBody ClaimTypeRO claimTypeRO) {
		return _tableMaintenanceRestService.updateClaimType(claimTypeRO);
	}

	@RequestMapping(value = RequestMappingConstants.DELETE_CLAIM_TYPE, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteClaimType(@PathVariable final String code) {
		_tableMaintenanceRestService.deleteClaimType(code);
	}
	
	/** Methods related to Distinguishing Features */
	@RequestMapping(value = RequestMappingConstants.GET_DISTINGUISHING_FEATURES, method = RequestMethod.GET)
	public List<DistinguishingFeatureRO> getDistinguishingFeatures() {
		return _tableMaintenanceRestService.getDistinguishingFeatures();
	}

	@RequestMapping(value = RequestMappingConstants.GET_DISTINGUISHING_FEATURE_BY_CODE, method = RequestMethod.GET)
	public DistinguishingFeatureRO getDistinguishingFeatureByCode(@PathVariable final String code) {
		return _tableMaintenanceRestService.getDistinguishingFeatureByCode(code);
	}

	@RequestMapping(value = RequestMappingConstants.CREATE_DISTINGUISHING_FEATURE, method = RequestMethod.POST)
	public DistinguishingFeatureRO createDistinguishingFeature(final @Valid @RequestBody DistinguishingFeatureRO distinguishingFeaturesRO) {
		return _tableMaintenanceRestService.createDistinguishingFeature(distinguishingFeaturesRO);
	}

	@RequestMapping(value = RequestMappingConstants.UPDATE_DISTINGUISHING_FEATURE, method = RequestMethod.PUT)
	public DistinguishingFeatureRO updateDistinguishingFeature(final @Valid @RequestBody DistinguishingFeatureRO distinguishingFeaturesRO) {
		return _tableMaintenanceRestService.updateDistinguishingFeature(distinguishingFeaturesRO);
	}

	@RequestMapping(value = RequestMappingConstants.DELETE_DISTINGUISHING_FEATURE, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDistinguishingFeature(@PathVariable final String code) {
		_tableMaintenanceRestService.deleteDistinguishingFeature(code);
	}
	
	/** Methods related to Distinguishing Features Detail */
	@RequestMapping(value = RequestMappingConstants.GET_DISTINGUISHING_FEATURE_DETAILS, method = RequestMethod.GET)
	public List<DistinguishingFeatureDetailRO> getDistinguishingFeatureDetails() {
		return _tableMaintenanceRestService.getDistinguishingFeatureDetails();
	}

	@RequestMapping(value = RequestMappingConstants.GET_DISTINGUISHING_FEATURE_DETAIL_BY_CODE, method = RequestMethod.GET)
	public DistinguishingFeatureDetailRO getDistinguishingFeatureDetailByCode(@PathVariable final String code) {
		return _tableMaintenanceRestService.getDistinguishingFeatureDetailByCode(code);
	}

	@RequestMapping(value = RequestMappingConstants.CREATE_DISTINGUISHING_FEATURE_DETAIL, method = RequestMethod.POST)
	public DistinguishingFeatureDetailRO createDistinguishingFeatureDetail(final @Valid @RequestBody DistinguishingFeatureDetailRO distinguishingFeaturesDetailRO) {
		return _tableMaintenanceRestService.createDistinguishingFeatureDetail(distinguishingFeaturesDetailRO);
	}

	@RequestMapping(value = RequestMappingConstants.UPDATE_DISTINGUISHING_FEATURE_DETAIL, method = RequestMethod.PUT)
	public DistinguishingFeatureDetailRO updateDistinguishingFeatureDetail(final @Valid @RequestBody DistinguishingFeatureDetailRO distinguishingFeaturesDetailRO) {
		return _tableMaintenanceRestService.updateDistinguishingFeatureDetail(distinguishingFeaturesDetailRO);
	}

	@RequestMapping(value = RequestMappingConstants.DELETE_DISTINGUISHING_FEATURE_DETAIL, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDistinguishingFeatureDetail(@PathVariable final String code) {
		_tableMaintenanceRestService.deleteDistinguishingFeatureDetail(code);
	}
	
	/** Methods related to Employee Type */
	@RequestMapping(value = RequestMappingConstants.GET_EMPLOYEE_TYPES, method = RequestMethod.GET)
	public List<EmployeeTypeRO> getEmployeeTypes() {
		return _tableMaintenanceRestService.getEmployeeTypes();
	}

	@RequestMapping(value = RequestMappingConstants.GET_EMPLOYEE_TYPE_BY_CODE, method = RequestMethod.GET)
	public EmployeeTypeRO getEmployeeTypeByCode(@PathVariable final String code) {
		return _tableMaintenanceRestService.getEmployeeTypeByCode(code);
	}

	@RequestMapping(value = RequestMappingConstants.CREATE_EMPLOYEE_TYPE, method = RequestMethod.POST)
	public EmployeeTypeRO createEmployeeType(final @Valid @RequestBody EmployeeTypeRO employeeTypeRO) {
		return _tableMaintenanceRestService.createEmployeeType(employeeTypeRO);
	}

	@RequestMapping(value = RequestMappingConstants.UPDATE_EMPLOYEE_TYPE, method = RequestMethod.PUT)
	public EmployeeTypeRO updateEmployeeType(final @Valid @RequestBody EmployeeTypeRO employeeTypeRO) {
		return _tableMaintenanceRestService.updateEmployeeType(employeeTypeRO);
	}

	@RequestMapping(value = RequestMappingConstants.DELETE_EMPLOYEE_TYPE, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteEmployeeType(@PathVariable final String code) {
		_tableMaintenanceRestService.deleteEmployeeType(code);
	}
	
	/** Methods related to Entry Point */
	@RequestMapping(value = RequestMappingConstants.GET_ENTRY_POINTS, method = RequestMethod.GET)
	public List<EntryPointRO> getEntryPoints() {
		return _tableMaintenanceRestService.getEntryPoints();
	}

	@RequestMapping(value = RequestMappingConstants.GET_ENTRY_POINT_BY_CODE, method = RequestMethod.GET)
	public EntryPointRO getEntryPointByCode(@PathVariable final String code) {
		return _tableMaintenanceRestService.getEntryPointByCode(code);
	}

	@RequestMapping(value = RequestMappingConstants.CREATE_ENTRY_POINT, method = RequestMethod.POST)
	public EntryPointRO createEntryPoint(final @Valid @RequestBody EntryPointRO entryPointRO) {
		return _tableMaintenanceRestService.createEntryPoint(entryPointRO);
	}

	@RequestMapping(value = RequestMappingConstants.UPDATE_ENTRY_POINT, method = RequestMethod.PUT)
	public EntryPointRO updateEntryPoint(final @Valid @RequestBody EntryPointRO entryPointRO) {
		return _tableMaintenanceRestService.updateEntryPoint(entryPointRO);
	}

	@RequestMapping(value = RequestMappingConstants.DELETE_ENTRY_POINT, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteEntryPoint(@PathVariable final String code) {
		_tableMaintenanceRestService.deleteEntryPoint(code);
	}
	
	/** Methods related to Event Type */
	@RequestMapping(value = RequestMappingConstants.GET_EVENT_TYPES, method = RequestMethod.GET)
	public List<EventTypeRO> getEventTypes() {
		return _tableMaintenanceRestService.getEventTypes();
	}

	@RequestMapping(value = RequestMappingConstants.GET_EVENT_TYPE_BY_CODE, method = RequestMethod.GET)
	public EventTypeRO getEventTypeByCode(@PathVariable final String code) {
		return _tableMaintenanceRestService.getEventTypeByCode(code);
	}

	@RequestMapping(value = RequestMappingConstants.CREATE_EVENT_TYPE, method = RequestMethod.POST)
	public EventTypeRO createEventType(final @Valid @RequestBody EventTypeRO eventTypeRO) {
		return _tableMaintenanceRestService.createEventType(eventTypeRO);
	}

	@RequestMapping(value = RequestMappingConstants.UPDATE_EVENT_TYPE, method = RequestMethod.PUT)
	public EventTypeRO updateEventType(final @Valid @RequestBody EventTypeRO eventTypeRO) {
		return _tableMaintenanceRestService.updateEventType(eventTypeRO);
	}

	@RequestMapping(value = RequestMappingConstants.DELETE_EVENT_TYPE, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteEventType(@PathVariable final String code) {
		_tableMaintenanceRestService.deleteEventType(code);
	}
	
	/** Methods related to External Agency */
	@RequestMapping(value = RequestMappingConstants.GET_EXTERNAL_AGENCIES, method = RequestMethod.GET)
	public List<ExternalAgencyRO> getExternalAgencies() {
		return _tableMaintenanceRestService.getExternalAgencies();
	}

	@RequestMapping(value = RequestMappingConstants.GET_EXTERNAL_AGENCY_BY_CODE, method = RequestMethod.GET)
	public ExternalAgencyRO getExternalAgencyByCode(@PathVariable final String code) {
		return _tableMaintenanceRestService.getExternalAgencyByCode(code);
	}

	@RequestMapping(value = RequestMappingConstants.CREATE_EXTERNAL_AGENCY, method = RequestMethod.POST)
	public ExternalAgencyRO createExternalAgency(final @Valid @RequestBody ExternalAgencyRO externalAgencyRO) {
		return _tableMaintenanceRestService.createExternalAgency(externalAgencyRO);
	}

	@RequestMapping(value = RequestMappingConstants.UPDATE_EXTERNAL_AGENCY, method = RequestMethod.PUT)
	public ExternalAgencyRO updateExternalAgency(final @Valid @RequestBody ExternalAgencyRO externalAgencyRO) {
		return _tableMaintenanceRestService.updateExternalAgency(externalAgencyRO);
	}

	@RequestMapping(value = RequestMappingConstants.DELETE_EXTERNAL_AGENCY, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteExternalAgency(@PathVariable final String code) {
		_tableMaintenanceRestService.deleteExternalAgency(code);
	}
	
	/** Methods related to Incident Location */
	@RequestMapping(value = RequestMappingConstants.GET_INCIDENT_LOCATIONS, method = RequestMethod.GET)
	public List<IncidentLocationRO> getIncidentLocations() {
		return _tableMaintenanceRestService.getIncidentLocations();
	}

	@RequestMapping(value = RequestMappingConstants.GET_INCIDENT_LOCATION_BY_CODE, method = RequestMethod.GET)
	public IncidentLocationRO getIncidentLocationByCode(@PathVariable final String code) {
		return _tableMaintenanceRestService.getIncidentLocationByCode(code);
	}

	@RequestMapping(value = RequestMappingConstants.CREATE_INCIDENT_LOCATION, method = RequestMethod.POST)
	public IncidentLocationRO createIncidentLocation(final @Valid @RequestBody IncidentLocationRO incidentLocationRO) {
		return _tableMaintenanceRestService.createIncidentLocation(incidentLocationRO);
	}

	@RequestMapping(value = RequestMappingConstants.UPDATE_INCIDENT_LOCATION, method = RequestMethod.PUT)
	public IncidentLocationRO updateIncidentLocation(final @Valid @RequestBody IncidentLocationRO incidentLocationRO) {
		return _tableMaintenanceRestService.updateIncidentLocation(incidentLocationRO);
	}

	@RequestMapping(value = RequestMappingConstants.DELETE_INCIDENT_LOCATION, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteIncidentLocation(@PathVariable final String code) {
		_tableMaintenanceRestService.deleteIncidentLocation(code);
	}
	
	/** Methods related to Incident Location Details */
	@RequestMapping(value = RequestMappingConstants.GET_INCIDENT_LOCATION_DETAILS, method = RequestMethod.GET)
	public List<IncidentLocationDetailRO> getIncidentLocationDetails() {
		return _tableMaintenanceRestService.getIncidentLocationDetails();
	}

	@RequestMapping(value = RequestMappingConstants.GET_INCIDENT_LOCATION_DETAIL_BY_CODE, method = RequestMethod.GET)
	public IncidentLocationDetailRO getIncidentLocationDetailByCode(@PathVariable final String code) {
		return _tableMaintenanceRestService.getIncidentLocationDetailByCode(code);
	}

	@RequestMapping(value = RequestMappingConstants.CREATE_INCIDENT_LOCATION_DETAIL, method = RequestMethod.POST)
	public IncidentLocationDetailRO createIncidentLocationDetail(final @Valid @RequestBody IncidentLocationDetailRO incidentLocationDetailsRO) {
		return _tableMaintenanceRestService.createIncidentLocationDetail(incidentLocationDetailsRO);
	}

	@RequestMapping(value = RequestMappingConstants.UPDATE_INCIDENT_LOCATION_DETAIL, method = RequestMethod.PUT)
	public IncidentLocationDetailRO updateIncidentLocationDetail(final @Valid @RequestBody IncidentLocationDetailRO incidentLocationDetailsRO) {
		return _tableMaintenanceRestService.updateIncidentLocationDetail(incidentLocationDetailsRO);
	}

	@RequestMapping(value = RequestMappingConstants.DELETE_INCIDENT_LOCATION_DETAIL, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteIncidentLocationDetail(@PathVariable final String code) {
		_tableMaintenanceRestService.deleteIncidentLocationDetail(code);
	}
	
	/** Methods related to Incident Type */
	@RequestMapping(value = RequestMappingConstants.GET_INCIDENT_TYPES, method = RequestMethod.GET)
	public List<IncidentTypeRO> getIncidentTypes() {
		return _tableMaintenanceRestService.getIncidentTypes();
	}

	@RequestMapping(value = RequestMappingConstants.GET_INCIDENT_TYPE_BY_CODE, method = RequestMethod.GET)
	public IncidentTypeRO getIncidentTypeByCode(@PathVariable final String code) {
		return _tableMaintenanceRestService.getIncidentTypeByCode(code);
	}

	@RequestMapping(value = RequestMappingConstants.CREATE_INCIDENT_TYPE, method = RequestMethod.POST)
	public IncidentTypeRO createIncidentType(final @Valid @RequestBody IncidentTypeRO incidentTypeRO) {
		return _tableMaintenanceRestService.createIncidentType(incidentTypeRO);
	}

	@RequestMapping(value = RequestMappingConstants.UPDATE_INCIDENT_TYPE, method = RequestMethod.PUT)
	public IncidentTypeRO updateIncidentType(final @Valid @RequestBody IncidentTypeRO incidentTypeRO) {
		return _tableMaintenanceRestService.updateIncidentType(incidentTypeRO);
	}

	@RequestMapping(value = RequestMappingConstants.DELETE_INCIDENT_TYPE, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteIncidentType(@PathVariable final String code) {
		_tableMaintenanceRestService.deleteIncidentType(code);
	}
	
	/** Methods related to Injury Cause */
	@RequestMapping(value = RequestMappingConstants.GET_INJURY_CAUSES, method = RequestMethod.GET)
	public List<InjuryCauseRO> getInjuryCauses() {
		return _tableMaintenanceRestService.getInjuryCauses();
	}

	@RequestMapping(value = RequestMappingConstants.GET_INJURY_CAUSE_BY_CODE, method = RequestMethod.GET)
	public InjuryCauseRO getInjuryCauseByCode(@PathVariable final String code) {
		return _tableMaintenanceRestService.getInjuryCauseByCode(code);
	}

	@RequestMapping(value = RequestMappingConstants.CREATE_INJURY_CAUSE, method = RequestMethod.POST)
	public InjuryCauseRO createInjuryCause(final @Valid @RequestBody InjuryCauseRO injuryCauseRO) {
		return _tableMaintenanceRestService.createInjuryCause(injuryCauseRO);
	}

	@RequestMapping(value = RequestMappingConstants.UPDATE_INJURY_CAUSE, method = RequestMethod.PUT)
	public InjuryCauseRO updateInjuryCause(final @Valid @RequestBody InjuryCauseRO injuryCauseRO) {
		return _tableMaintenanceRestService.updateInjuryCause(injuryCauseRO);
	}

	@RequestMapping(value = RequestMappingConstants.DELETE_INJURY_CAUSE, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteInjuryCause(@PathVariable final String code) {
		_tableMaintenanceRestService.deleteInjuryCause(code);
	}
	
	/** Methods related to Injury Type */
	@RequestMapping(value = RequestMappingConstants.GET_INJURY_TYPES, method = RequestMethod.GET)
	public List<InjuryTypeRO> getInjuryTypes() {
		return _tableMaintenanceRestService.getInjuryTypes();
	}

	@RequestMapping(value = RequestMappingConstants.GET_INJURY_TYPE_BY_CODE, method = RequestMethod.GET)
	public InjuryTypeRO getInjuryTypeByCode(@PathVariable final String code) {
		return _tableMaintenanceRestService.getInjuryTypeByCode(code);
	}

	@RequestMapping(value = RequestMappingConstants.CREATE_INJURY_TYPE, method = RequestMethod.POST)
	public InjuryTypeRO createInjuryType(final @Valid @RequestBody InjuryTypeRO injuryTypeRO) {
		return _tableMaintenanceRestService.createInjuryType(injuryTypeRO);
	}

	@RequestMapping(value = RequestMappingConstants.UPDATE_INJURY_TYPE, method = RequestMethod.PUT)
	public InjuryTypeRO updateInjuryType(final @Valid @RequestBody InjuryTypeRO injuryTypeRO) {
		return _tableMaintenanceRestService.updateInjuryType(injuryTypeRO);
	}

	@RequestMapping(value = RequestMappingConstants.DELETE_INJURY_TYPE, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteInjuryType(@PathVariable final String code) {
		_tableMaintenanceRestService.deleteInjuryType(code);
	}
	
	/** Methods related to Injury Type Detail */
	@RequestMapping(value = RequestMappingConstants.GET_INJURY_TYPE_DETAILS, method = RequestMethod.GET)
	public List<InjuryTypeDetailRO> getInjuryTypeDetails() {
		return _tableMaintenanceRestService.getInjuryTypeDetails();
	}

	@RequestMapping(value = RequestMappingConstants.GET_INJURY_TYPE_DETAIL_BY_CODE, method = RequestMethod.GET)
	public InjuryTypeDetailRO getInjuryTypeDetailByCode(@PathVariable final String code) {
		return _tableMaintenanceRestService.getInjuryTypeDetailByCode(code);
	}

	@RequestMapping(value = RequestMappingConstants.CREATE_INJURY_TYPE_DETAIL, method = RequestMethod.POST)
	public InjuryTypeDetailRO createInjuryTypeDetail(final @Valid @RequestBody InjuryTypeDetailRO injuryTypeDetailsRO) {
		return _tableMaintenanceRestService.createInjuryTypeDetail(injuryTypeDetailsRO);
	}

	@RequestMapping(value = RequestMappingConstants.UPDATE_INJURY_TYPE_DETAIL, method = RequestMethod.PUT)
	public InjuryTypeDetailRO updateInjuryTypeDetail(final @Valid @RequestBody InjuryTypeDetailRO injuryTypeDetailsRO) {
		return _tableMaintenanceRestService.updateInjuryTypeDetail(injuryTypeDetailsRO);
	}

	@RequestMapping(value = RequestMappingConstants.DELETE_INJURY_TYPE_DETAIL, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteInjuryTypeDetail(@PathVariable final String code) {
		_tableMaintenanceRestService.deleteInjuryTypeDetail(code);
	}
	
	/** Methods related to Injury Type Detail Specification */
	@RequestMapping(value = RequestMappingConstants.GET_INJURY_TYPE_DETAIL_SPECS, method = RequestMethod.GET)
	public List<InjuryTypeDetailSpecRO> getInjuryTypeDetailSpecs() {
		return _tableMaintenanceRestService.getInjuryTypeDetailSpecs();
	}

	@RequestMapping(value = RequestMappingConstants.GET_INJURY_TYPE_DETAIL_SPEC_BY_CODE, method = RequestMethod.GET)
	public InjuryTypeDetailSpecRO getInjuryTypeDetailSpecByCode(@PathVariable final String code) {
		return _tableMaintenanceRestService.getInjuryTypeDetailSpecByCode(code);
	}

	@RequestMapping(value = RequestMappingConstants.CREATE_INJURY_TYPE_DETAIL_SPEC, method = RequestMethod.POST)
	public InjuryTypeDetailSpecRO createInjuryTypeDetailSpec(final @Valid @RequestBody InjuryTypeDetailSpecRO injuryTypeDetailsSpecRO) {
		return _tableMaintenanceRestService.createInjuryTypeDetailSpec(injuryTypeDetailsSpecRO);
	}

	@RequestMapping(value = RequestMappingConstants.UPDATE_INJURY_TYPE_DETAIL_SPEC, method = RequestMethod.PUT)
	public InjuryTypeDetailSpecRO updateInjuryTypeDetailSpec(final @Valid @RequestBody InjuryTypeDetailSpecRO injuryTypeDetailsSpecRO) {
		return _tableMaintenanceRestService.updateInjuryTypeDetailSpec(injuryTypeDetailsSpecRO);
	}

	@RequestMapping(value = RequestMappingConstants.DELETE_INJURY_TYPE_DETAIL_SPEC, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteInjuryTypeDetailSpec(@PathVariable final String code) {
		_tableMaintenanceRestService.deleteInjuryTypeDetailSpec(code);
	}
	
	/** Methods related to Suspect Type */
	@RequestMapping(value = RequestMappingConstants.GET_SUSPECT_TYPES, method = RequestMethod.GET)
	public List<SuspectTypeRO> getSuspectTypes() {
		return _tableMaintenanceRestService.getSuspectTypes();
	}

	@RequestMapping(value = RequestMappingConstants.GET_SUSPECT_TYPE_BY_CODE, method = RequestMethod.GET)
	public SuspectTypeRO getSuspectTypeByCode(@PathVariable final String code) {
		return _tableMaintenanceRestService.getSuspectTypeByCode(code);
	}

	@RequestMapping(value = RequestMappingConstants.CREATE_SUSPECT_TYPE, method = RequestMethod.POST)
	public SuspectTypeRO createSuspectType(final @Valid @RequestBody SuspectTypeRO suspectTypeRO) {
		return _tableMaintenanceRestService.createSuspectType(suspectTypeRO);
	}

	@RequestMapping(value = RequestMappingConstants.UPDATE_SUSPECT_TYPE, method = RequestMethod.PUT)
	public SuspectTypeRO updateSuspectType(final @Valid @RequestBody SuspectTypeRO suspectTypeRO) {
		return _tableMaintenanceRestService.updateSuspectType(suspectTypeRO);
	}

	@RequestMapping(value = RequestMappingConstants.DELETE_SUSPECT_TYPE, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteSuspectType(@PathVariable final String code) {
		_tableMaintenanceRestService.deleteSuspectType(code);
	}
	
	/** Methods related to Weapon Involved */
	@RequestMapping(value = RequestMappingConstants.GET_WEAPON_TYPES, method = RequestMethod.GET)
	public List<WeaponTypeRO> getWeaponTypes() {
		return _tableMaintenanceRestService.getWeaponTypes();
	}

	@RequestMapping(value = RequestMappingConstants.GET_WEAPON_TYPE_BY_CODE, method = RequestMethod.GET)
	public WeaponTypeRO getWeaponTypeByCode(@PathVariable final String code) {
		return _tableMaintenanceRestService.getWeaponTypeByCode(code);
	}

	@RequestMapping(value = RequestMappingConstants.CREATE_WEAPON_TYPE, method = RequestMethod.POST)
	public WeaponTypeRO createWeaponType(final @Valid @RequestBody WeaponTypeRO weaponTypeRO) {
		return _tableMaintenanceRestService.createWeaponType(weaponTypeRO);
	}

	@RequestMapping(value = RequestMappingConstants.UPDATE_WEAPON_TYPE, method = RequestMethod.PUT)
	public WeaponTypeRO updateWeaponType(final @Valid @RequestBody WeaponTypeRO weaponTypeRO) {
		return _tableMaintenanceRestService.updateWeaponType(weaponTypeRO);
	}

	@RequestMapping(value = RequestMappingConstants.DELETE_WEAPON_TYPE, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteWeaponType(@PathVariable final String code) {
		_tableMaintenanceRestService.deleteWeaponType(code);
	}
	
	//
	
	/** Methods related to Accident Type */
	@RequestMapping(value = RequestMappingConstants.GET_ACCIDENT_TYPES, method = RequestMethod.GET)
	public List<AccidentTypeRO> getAccidentTypes() {
		return _tableMaintenanceRestService.getAccidentTypes();
	}

	@RequestMapping(value = RequestMappingConstants.GET_ACCIDENT_TYPE_BY_CODE, method = RequestMethod.GET)
	public AccidentTypeRO getAccidentTypeByCode(@PathVariable final String code) {
		return _tableMaintenanceRestService.getAccidentTypeByCode(code);
	}

	@RequestMapping(value = RequestMappingConstants.CREATE_ACCIDENT_TYPE, method = RequestMethod.POST)
	public AccidentTypeRO createAccidentType(final @Valid @RequestBody AccidentTypeRO accidentTypeRO) {
		return _tableMaintenanceRestService.createAccidentType(accidentTypeRO);
	}

	@RequestMapping(value = RequestMappingConstants.UPDATE_ACCIDENT_TYPE, method = RequestMethod.PUT)
	public AccidentTypeRO updateAccidentType(final @Valid @RequestBody AccidentTypeRO accidentTypeRO) {
		return _tableMaintenanceRestService.updateAccidentType(accidentTypeRO);
	}

	@RequestMapping(value = RequestMappingConstants.DELETE_ACCIDENT_TYPE, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAccidentType(@PathVariable final String code) {
		_tableMaintenanceRestService.deleteAccidentType(code);
	}
	
	/** Methods related to Body Part */
	@RequestMapping(value = RequestMappingConstants.GET_BODY_PARTS, method = RequestMethod.GET)
	public List<BodyPartRO> getBodyParts() {
		return _tableMaintenanceRestService.getBodyParts();
	}

	@RequestMapping(value = RequestMappingConstants.GET_BODY_PART_BY_CODE, method = RequestMethod.GET)
	public BodyPartRO getBodyPartByCode(@PathVariable final String code) {
		return _tableMaintenanceRestService.getBodyPartByCode(code);
	}

	@RequestMapping(value = RequestMappingConstants.CREATE_BODY_PART_TYPE, method = RequestMethod.POST)
	public BodyPartRO createBodyPart(final @Valid @RequestBody BodyPartRO bodyPartRO) {
		return _tableMaintenanceRestService.createBodyPart(bodyPartRO);
	}

	@RequestMapping(value = RequestMappingConstants.UPDATE_BODY_PART_TYPE, method = RequestMethod.PUT)
	public BodyPartRO updateBodyPart(final @Valid @RequestBody BodyPartRO bodyPartRO) {
		return _tableMaintenanceRestService.updateBodyPart(bodyPartRO);
	}

	@RequestMapping(value = RequestMappingConstants.DELETE_BODY_PART_TYPE, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBodyPart(@PathVariable final String code) {
		_tableMaintenanceRestService.deleteBodyPart(code);
	}
	
	/** Methods related to Document Category */
	@RequestMapping(value = RequestMappingConstants.GET_DOCUMENT_CATEGORIES, method = RequestMethod.GET)
	public List<DocumentCategoryRO> getDocumentCategories() {
		return _tableMaintenanceRestService.getDocumentCategories();
	}

	@RequestMapping(value = RequestMappingConstants.GET_DOCUMENT_CATEGORY_BY_CODE, method = RequestMethod.GET)
	public DocumentCategoryRO getDocumentCategoryByCode(@PathVariable final String code) {
		return _tableMaintenanceRestService.getDocumentCategoryByCode(code);
	}

	@RequestMapping(value = RequestMappingConstants.CREATE_DOCUMENT_CATEGORY, method = RequestMethod.POST)
	public DocumentCategoryRO createDocumentCategory(final @Valid @RequestBody DocumentCategoryRO documentCategoryRO) {
		return _tableMaintenanceRestService.createDocumentCategory(documentCategoryRO);
	}

	@RequestMapping(value = RequestMappingConstants.UPDATE_DOCUMENT_CATEGORY, method = RequestMethod.PUT)
	public DocumentCategoryRO updateDocumentCategory(final @Valid @RequestBody DocumentCategoryRO documentCategoryRO) {
		return _tableMaintenanceRestService.updateDocumentCategory(documentCategoryRO);
	}

	@RequestMapping(value = RequestMappingConstants.DELETE_DOCUMENT_CATEGORY, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDocumentCategory(@PathVariable final String code) {
		_tableMaintenanceRestService.deleteDocumentCategory(code);
	}
	
	/** Methods related to Document Type */
	@RequestMapping(value = RequestMappingConstants.GET_DOCUMENT_TYPES, method = RequestMethod.GET)
	public List<DocumentTypeRO> getDocumentTypes() {
		return _tableMaintenanceRestService.getDocumentTypes();
	}

	@RequestMapping(value = RequestMappingConstants.GET_DOCUMENT_TYPE_BY_CODE, method = RequestMethod.GET)
	public DocumentTypeRO getDocumentTypeByCode(@PathVariable final String code) {
		return _tableMaintenanceRestService.getDocumentTypeByCode(code);
	}

	@RequestMapping(value = RequestMappingConstants.CREATE_DOCUMENT_TYPE, method = RequestMethod.POST)
	public DocumentTypeRO createDocumentType(final @Valid @RequestBody DocumentTypeRO documentTypeRO) {
		return _tableMaintenanceRestService.createDocumentType(documentTypeRO);
	}

	@RequestMapping(value = RequestMappingConstants.UPDATE_DOCUMENT_TYPE, method = RequestMethod.PUT)
	public DocumentTypeRO updateDocumentType(final @Valid @RequestBody DocumentTypeRO documentTypeRO) {
		return _tableMaintenanceRestService.updateDocumentType(documentTypeRO);
	}

	@RequestMapping(value = RequestMappingConstants.DELETE_DOCUMENT_TYPE, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDocumentType(@PathVariable final String code) {
		_tableMaintenanceRestService.deleteDocumentType(code);
	}
	
	/** Methods related to Gender Type */
	@RequestMapping(value = RequestMappingConstants.GET_GENDER_TYPES, method = RequestMethod.GET)
	public List<GenderTypeRO> getGenderTypes() {
		return _tableMaintenanceRestService.getGenderTypes();
	}

	@RequestMapping(value = RequestMappingConstants.GET_GENDER_TYPE_BY_CODE, method = RequestMethod.GET)
	public GenderTypeRO getGenderTypeByCode(@PathVariable final String code) {
		return _tableMaintenanceRestService.getGenderTypeByCode(code);
	}

	@RequestMapping(value = RequestMappingConstants.CREATE_GENDER_TYPE, method = RequestMethod.POST)
	public GenderTypeRO createGenderType(final @Valid @RequestBody GenderTypeRO genderTypeRO) {
		return _tableMaintenanceRestService.createGenderType(genderTypeRO);
	}

	@RequestMapping(value = RequestMappingConstants.UPDATE_GENDER_TYPE, method = RequestMethod.PUT)
	public GenderTypeRO updateGenderType(final @Valid @RequestBody GenderTypeRO genderTypeRO) {
		return _tableMaintenanceRestService.updateGenderType(genderTypeRO);
	}

	@RequestMapping(value = RequestMappingConstants.DELETE_GENDER_TYPE, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteGenderType(@PathVariable final String code) {
		_tableMaintenanceRestService.deleteGenderType(code);
	}
	
	/** Methods related to Incident Category */
	@RequestMapping(value = RequestMappingConstants.GET_INCIDENT_CATEGORIES, method = RequestMethod.GET)
	public List<IncidentCategoryRO> getIncidentCategories() {
		return _tableMaintenanceRestService.getIncidentCategories();
	}

	@RequestMapping(value = RequestMappingConstants.GET_INCIDENT_CATEGORY_BY_CODE, method = RequestMethod.GET)
	public IncidentCategoryRO getIncidentCategoryByCode(@PathVariable final String code) {
		return _tableMaintenanceRestService.getIncidentCategoryByCode(code);
	}

	@RequestMapping(value = RequestMappingConstants.CREATE_INCIDENT_CATEGORY, method = RequestMethod.POST)
	public IncidentCategoryRO createIncidentCategory(final @Valid @RequestBody IncidentCategoryRO incidentCategoryRO) {
		return _tableMaintenanceRestService.createIncidentCategory(incidentCategoryRO);
	}

	@RequestMapping(value = RequestMappingConstants.UPDATE_INCIDENT_CATEGORY, method = RequestMethod.PUT)
	public IncidentCategoryRO updateIncidentCategory(final @Valid @RequestBody IncidentCategoryRO incidentCategoryRO) {
		return _tableMaintenanceRestService.updateIncidentCategory(incidentCategoryRO);
	}

	@RequestMapping(value = RequestMappingConstants.DELETE_INCIDENT_CATEGORY, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteIncidentCategory(@PathVariable final String code) {
		_tableMaintenanceRestService.deleteIncidentCategory(code);
	}
	
	/** Methods related to Injured Person Type */
	@RequestMapping(value = RequestMappingConstants.GET_INJURED_PERSON_TYPES, method = RequestMethod.GET)
	public List<InjuredPersonTypeRO> getInjuredPersonTypes() {
		return _tableMaintenanceRestService.getInjuredPersonTypes();
	}

	@RequestMapping(value = RequestMappingConstants.GET_INJURED_PERSON_TYPE_BY_CODE, method = RequestMethod.GET)
	public InjuredPersonTypeRO getInjuredPersonTypeByCode(@PathVariable final String code) {
		return _tableMaintenanceRestService.getInjuredPersonTypeByCode(code);
	}

	@RequestMapping(value = RequestMappingConstants.CREATE_INJURED_PERSON_TYPE, method = RequestMethod.POST)
	public InjuredPersonTypeRO createInjuredPersonType(final @Valid @RequestBody InjuredPersonTypeRO injuredPersonTypeRO) {
		return _tableMaintenanceRestService.createInjuredPersonType(injuredPersonTypeRO);
	}

	@RequestMapping(value = RequestMappingConstants.UPDATE_INJURED_PERSON_TYPE, method = RequestMethod.PUT)
	public InjuredPersonTypeRO updateInjuredPersonType(final @Valid @RequestBody InjuredPersonTypeRO injuredPersonTypeRO) {
		return _tableMaintenanceRestService.updateInjuredPersonType(injuredPersonTypeRO);
	}

	@RequestMapping(value = RequestMappingConstants.DELETE_INJURED_PERSON_TYPE, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteInjuredPersonType(@PathVariable final String code) {
		_tableMaintenanceRestService.deleteInjuredPersonType(code);
	}
	
	/** Methods related to Loss Type */
	@RequestMapping(value = RequestMappingConstants.GET_LOSS_TYPES, method = RequestMethod.GET)
	public List<LossTypeRO> getLossTypes() {
		return _tableMaintenanceRestService.getLossTypes();
	}

	@RequestMapping(value = RequestMappingConstants.GET_LOSS_TYPE_BY_CODE, method = RequestMethod.GET)
	public LossTypeRO getLossTypeByCode(@PathVariable final String code) {
		return _tableMaintenanceRestService.getLossTypeByCode(code);
	}

	@RequestMapping(value = RequestMappingConstants.CREATE_LOSS_TYPE, method = RequestMethod.POST)
	public LossTypeRO createLossType(final @Valid @RequestBody LossTypeRO lossTypeRO) {
		return _tableMaintenanceRestService.createLossType(lossTypeRO);
	}

	@RequestMapping(value = RequestMappingConstants.UPDATE_LOSS_TYPE, method = RequestMethod.PUT)
	public LossTypeRO updateLossType(final @Valid @RequestBody LossTypeRO lossTypeRO) {
		return _tableMaintenanceRestService.updateLossType(lossTypeRO);
	}

	@RequestMapping(value = RequestMappingConstants.DELETE_LOSS_TYPE, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteLossType(@PathVariable final String code) {
		_tableMaintenanceRestService.deleteLossType(code);
	}
	
	/** Methods related to Policy Type */
	@RequestMapping(value = RequestMappingConstants.GET_POLICY_TYPES, method = RequestMethod.GET)
	public List<PolicyTypeRO> getPolicyTypes() {
		return _tableMaintenanceRestService.getPolicyTypes();
	}

	@RequestMapping(value = RequestMappingConstants.GET_POLICY_TYPE_BY_CODE, method = RequestMethod.GET)
	public PolicyTypeRO getPolicyTypeByCode(@PathVariable final String code) {
		return _tableMaintenanceRestService.getPolicyTypeByCode(code);
	}

	@RequestMapping(value = RequestMappingConstants.CREATE_POLICY_TYPE, method = RequestMethod.POST)
	public PolicyTypeRO createPolicyType(final @Valid @RequestBody PolicyTypeRO policyTypeRO) {
		return _tableMaintenanceRestService.createPolicyType(policyTypeRO);
	}

	@RequestMapping(value = RequestMappingConstants.UPDATE_POLICY_TYPE, method = RequestMethod.PUT)
	public PolicyTypeRO updatePolicyType(final @Valid @RequestBody PolicyTypeRO policyTypeRO) {
		return _tableMaintenanceRestService.updatePolicyType(policyTypeRO);
	}

	@RequestMapping(value = RequestMappingConstants.DELETE_POLICY_TYPE, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePolicyType(@PathVariable final String code) {
		_tableMaintenanceRestService.deletePolicyType(code);
	}
	
	/** Methods related to Vehicle Damage Type */
	@RequestMapping(value = RequestMappingConstants.GET_VEHICLE_DAMAGE_TYPES, method = RequestMethod.GET)
	public List<VehicleDamageTypeRO> getVehicleDamageTypes() {
		return _tableMaintenanceRestService.getVehicleDamageTypes();
	}

	@RequestMapping(value = RequestMappingConstants.GET_VEHICLE_DAMAGE_TYPE_BY_CODE, method = RequestMethod.GET)
	public VehicleDamageTypeRO getVehicleDamageTypeByCode(@PathVariable final String code) {
		return _tableMaintenanceRestService.getVehicleDamageTypeByCode(code);
	}

	@RequestMapping(value = RequestMappingConstants.CREATE_VEHICLE_DAMAGE_TYPE, method = RequestMethod.POST)
	public VehicleDamageTypeRO createVehicleDamageType(final @Valid @RequestBody VehicleDamageTypeRO vehicleDamageTypeRO) {
		return _tableMaintenanceRestService.createVehicleDamageType(vehicleDamageTypeRO);
	}

	@RequestMapping(value = RequestMappingConstants.UPDATE_VEHICLE_DAMAGE_TYPE, method = RequestMethod.PUT)
	public VehicleDamageTypeRO updateVehicleDamageType(final @Valid @RequestBody VehicleDamageTypeRO vehicleDamageTypeRO) {
		return _tableMaintenanceRestService.updateVehicleDamageType(vehicleDamageTypeRO);
	}

	@RequestMapping(value = RequestMappingConstants.DELETE_VEHICLE_DAMAGE_TYPE, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteVehicleDamageType(@PathVariable final String code) {
		_tableMaintenanceRestService.deleteVehicleDamageType(code);
	}
}
