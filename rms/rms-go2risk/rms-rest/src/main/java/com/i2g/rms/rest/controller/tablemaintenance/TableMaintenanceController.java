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
	public List<AccidentLocationDetailsRO> getAccidentLocationDetails() {
		return _tableMaintenanceRestService.getAccidentLocationDetails();
	}

	@RequestMapping(value = RequestMappingConstants.GET_ACCIDENT_LOCATION_DETAILS_BY_CODE, method = RequestMethod.GET)
	public AccidentLocationDetailsRO getAccidentLocationDetailsByCode(@PathVariable final String code) {
		return _tableMaintenanceRestService.getAccidentLocationDetailsByCode(code);
	}

	@RequestMapping(value = RequestMappingConstants.CREATE_ACCIDENT_LOCATION_DETAILS, method = RequestMethod.POST)
	public AccidentLocationDetailsRO createAccidentLocationDetails(final @Valid @RequestBody AccidentLocationDetailsRO accidentLocationDetailsRO) {
		return _tableMaintenanceRestService.createAccidentLocationDetails(accidentLocationDetailsRO);
	}

	@RequestMapping(value = RequestMappingConstants.UPDATE_ACCIDENT_LOCATION_DETAILS, method = RequestMethod.PUT)
	public AccidentLocationDetailsRO updateAccidentLocationDetails(final @Valid @RequestBody AccidentLocationDetailsRO accidentLocationDetailsRO) {
		return _tableMaintenanceRestService.updateAccidentLocationDetails(accidentLocationDetailsRO);
	}

	@RequestMapping(value = RequestMappingConstants.DELETE_ACCIDENT_LOCATION_DETAILS, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAccidentLocationDetails(@PathVariable final String code) {
		_tableMaintenanceRestService.deleteAccidentLocationDetails(code);
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
	public List<DistinguishingFeaturesRO> getDistinguishingFeatures() {
		return _tableMaintenanceRestService.getDistinguishingFeatures();
	}

	@RequestMapping(value = RequestMappingConstants.GET_DISTINGUISHING_FEATURES_BY_CODE, method = RequestMethod.GET)
	public DistinguishingFeaturesRO getDistinguishingFeaturesByCode(@PathVariable final String code) {
		return _tableMaintenanceRestService.getDistinguishingFeaturesByCode(code);
	}

	@RequestMapping(value = RequestMappingConstants.CREATE_DISTINGUISHING_FEATURES, method = RequestMethod.POST)
	public DistinguishingFeaturesRO createDistinguishingFeatures(final @Valid @RequestBody DistinguishingFeaturesRO distinguishingFeaturesRO) {
		return _tableMaintenanceRestService.createDistinguishingFeatures(distinguishingFeaturesRO);
	}

	@RequestMapping(value = RequestMappingConstants.UPDATE_DISTINGUISHING_FEATURES, method = RequestMethod.PUT)
	public DistinguishingFeaturesRO updateDistinguishingFeatures(final @Valid @RequestBody DistinguishingFeaturesRO distinguishingFeaturesRO) {
		return _tableMaintenanceRestService.updateDistinguishingFeatures(distinguishingFeaturesRO);
	}

	@RequestMapping(value = RequestMappingConstants.DELETE_DISTINGUISHING_FEATURES, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDistinguishingFeatures(@PathVariable final String code) {
		_tableMaintenanceRestService.deleteDistinguishingFeatures(code);
	}
	
	/** Methods related to Distinguishing Features Detail */
	@RequestMapping(value = RequestMappingConstants.GET_DISTINGUISHING_FEATURES_DETAILS, method = RequestMethod.GET)
	public List<DistinguishingFeaturesDetailRO> getDistinguishingFeaturesDetails() {
		return _tableMaintenanceRestService.getDistinguishingFeaturesDetails();
	}

	@RequestMapping(value = RequestMappingConstants.GET_DISTINGUISHING_FEATURES_DETAILS_BY_CODE, method = RequestMethod.GET)
	public DistinguishingFeaturesDetailRO getDistinguishingFeaturesDetailByCode(@PathVariable final String code) {
		return _tableMaintenanceRestService.getDistinguishingFeaturesDetailByCode(code);
	}

	@RequestMapping(value = RequestMappingConstants.CREATE_DISTINGUISHING_FEATURES_DETAILS, method = RequestMethod.POST)
	public DistinguishingFeaturesDetailRO createDistinguishingFeaturesDetail(final @Valid @RequestBody DistinguishingFeaturesDetailRO distinguishingFeaturesDetailRO) {
		return _tableMaintenanceRestService.createDistinguishingFeaturesDetail(distinguishingFeaturesDetailRO);
	}

	@RequestMapping(value = RequestMappingConstants.UPDATE_DISTINGUISHING_FEATURES_DETAILS, method = RequestMethod.PUT)
	public DistinguishingFeaturesDetailRO updateDistinguishingFeaturesDetail(final @Valid @RequestBody DistinguishingFeaturesDetailRO distinguishingFeaturesDetailRO) {
		return _tableMaintenanceRestService.updateDistinguishingFeaturesDetail(distinguishingFeaturesDetailRO);
	}

	@RequestMapping(value = RequestMappingConstants.DELETE_DISTINGUISHING_FEATURES_DETAILS, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDistinguishingFeaturesDetail(@PathVariable final String code) {
		_tableMaintenanceRestService.deleteDistinguishingFeaturesDetail(code);
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
	public List<IncidentLocationDetailsRO> getIncidentLocationDetails() {
		return _tableMaintenanceRestService.getIncidentLocationDetails();
	}

	@RequestMapping(value = RequestMappingConstants.GET_INCIDENT_LOCATION_DETAILS_BY_CODE, method = RequestMethod.GET)
	public IncidentLocationDetailsRO getIncidentLocationDetailsByCode(@PathVariable final String code) {
		return _tableMaintenanceRestService.getIncidentLocationDetailsByCode(code);
	}

	@RequestMapping(value = RequestMappingConstants.CREATE_INCIDENT_LOCATION_DETAILS, method = RequestMethod.POST)
	public IncidentLocationDetailsRO createIncidentLocationDetails(final @Valid @RequestBody IncidentLocationDetailsRO incidentLocationDetailsRO) {
		return _tableMaintenanceRestService.createIncidentLocationDetails(incidentLocationDetailsRO);
	}

	@RequestMapping(value = RequestMappingConstants.UPDATE_INCIDENT_LOCATION_DETAILS, method = RequestMethod.PUT)
	public IncidentLocationDetailsRO updateIncidentLocationDetails(final @Valid @RequestBody IncidentLocationDetailsRO incidentLocationDetailsRO) {
		return _tableMaintenanceRestService.updateIncidentLocationDetails(incidentLocationDetailsRO);
	}

	@RequestMapping(value = RequestMappingConstants.DELETE_INCIDENT_LOCATION_DETAILS, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteIncidentLocationDetails(@PathVariable final String code) {
		_tableMaintenanceRestService.deleteIncidentLocationDetails(code);
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
	
	/** Methods related to Injury Type Details */
	@RequestMapping(value = RequestMappingConstants.GET_INJURY_TYPE_DETAILS, method = RequestMethod.GET)
	public List<InjuryTypeDetailsRO> getInjuryTypeDetails() {
		return _tableMaintenanceRestService.getInjuryTypeDetails();
	}

	@RequestMapping(value = RequestMappingConstants.GET_INJURY_TYPE_DETAILS_BY_CODE, method = RequestMethod.GET)
	public InjuryTypeDetailsRO getInjuryTypeDetailsByCode(@PathVariable final String code) {
		return _tableMaintenanceRestService.getInjuryTypeDetailsByCode(code);
	}

	@RequestMapping(value = RequestMappingConstants.CREATE_INJURY_TYPE_DETAILS, method = RequestMethod.POST)
	public InjuryTypeDetailsRO createInjuryTypeDetails(final @Valid @RequestBody InjuryTypeDetailsRO injuryTypeDetailsRO) {
		return _tableMaintenanceRestService.createInjuryTypeDetails(injuryTypeDetailsRO);
	}

	@RequestMapping(value = RequestMappingConstants.UPDATE_INJURY_TYPE_DETAILS, method = RequestMethod.PUT)
	public InjuryTypeDetailsRO updateInjuryTypeDetails(final @Valid @RequestBody InjuryTypeDetailsRO injuryTypeDetailsRO) {
		return _tableMaintenanceRestService.updateInjuryTypeDetails(injuryTypeDetailsRO);
	}

	@RequestMapping(value = RequestMappingConstants.DELETE_INJURY_TYPE_DETAILS, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteInjuryTypeDetails(@PathVariable final String code) {
		_tableMaintenanceRestService.deleteInjuryTypeDetails(code);
	}
	
	/** Methods related to Injury Type Details Specification */
	@RequestMapping(value = RequestMappingConstants.GET_INJURY_TYPE_DETAILS_SPECS, method = RequestMethod.GET)
	public List<InjuryTypeDetailsSpecRO> getInjuryTypeDetailsSpecs() {
		return _tableMaintenanceRestService.getInjuryTypeDetailsSpecs();
	}

	@RequestMapping(value = RequestMappingConstants.GET_INJURY_TYPE_DETAILS_SPEC_BY_CODE, method = RequestMethod.GET)
	public InjuryTypeDetailsSpecRO getInjuryTypeDetailsSpecByCode(@PathVariable final String code) {
		return _tableMaintenanceRestService.getInjuryTypeDetailsSpecByCode(code);
	}

	@RequestMapping(value = RequestMappingConstants.CREATE_INJURY_TYPE_DETAILS_SPEC, method = RequestMethod.POST)
	public InjuryTypeDetailsSpecRO createInjuryTypeDetailsSpec(final @Valid @RequestBody InjuryTypeDetailsSpecRO injuryTypeDetailsSpecRO) {
		return _tableMaintenanceRestService.createInjuryTypeDetailsSpec(injuryTypeDetailsSpecRO);
	}

	@RequestMapping(value = RequestMappingConstants.UPDATE_INJURY_TYPE_DETAILS_SPEC, method = RequestMethod.PUT)
	public InjuryTypeDetailsSpecRO updateInjuryTypeDetailsSpec(final @Valid @RequestBody InjuryTypeDetailsSpecRO injuryTypeDetailsSpecRO) {
		return _tableMaintenanceRestService.updateInjuryTypeDetailsSpec(injuryTypeDetailsSpecRO);
	}

	@RequestMapping(value = RequestMappingConstants.DELETE_INJURY_TYPE_DETAILS_SPEC, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteInjuryTypeDetailsSpec(@PathVariable final String code) {
		_tableMaintenanceRestService.deleteInjuryTypeDetailsSpec(code);
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
}
