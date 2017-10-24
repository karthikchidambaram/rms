package com.i2g.rms.rest.controller.incident;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.i2g.rms.domain.model.incident.Incident;
import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.controller.AbstractRestController;
import com.i2g.rms.rest.model.DeleteRO;
import com.i2g.rms.rest.model.DocumentViewRO;
import com.i2g.rms.rest.model.SuspectRO;
import com.i2g.rms.rest.model.UserRO;
import com.i2g.rms.rest.model.incident.AccidentDetailRO;
import com.i2g.rms.rest.model.incident.AssetDetailRO;
import com.i2g.rms.rest.model.incident.ClaimDetailRO;
import com.i2g.rms.rest.model.incident.CrimeDetailRO;
import com.i2g.rms.rest.model.incident.IncidentDetailRO;
import com.i2g.rms.rest.model.incident.IncidentRO;
import com.i2g.rms.rest.model.incident.InvestigationDetailRO;
import com.i2g.rms.rest.model.incident.LogIncidentRO;
import com.i2g.rms.rest.model.wrapper.SuspectWrapper;
import com.i2g.rms.rest.search.Searchable;
import com.i2g.rms.rest.service.incident.DocumentRestService;
import com.i2g.rms.rest.service.incident.IncidentRestService;

/**
 * Rest controller for all incident related activities.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@RestController
public class IncidentController extends AbstractRestController {
	
	@Autowired
	private IncidentRestService _incidentRestService;
	@Autowired
	private DocumentRestService _documentRestService;
	
	/** Get flows */
	@RequestMapping(value = RequestMappingConstants.GET_INCIDENTS, method = RequestMethod.GET)
	@Searchable(sourceType = IncidentRO.class, value = Incident.class)
	public List<IncidentRO> get() {
		return _incidentRestService.get();
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_INCIDENT_BY_UNIQUE_INCIDENT_ID, method = RequestMethod.GET)
	public IncidentRO getIncidentByUniqueIncidentId(@PathVariable final String uniqueIncidentId) {
		return _incidentRestService.getIncidentByUniqueIncidentId(uniqueIncidentId);
	}
	
	/** Add flows */
	@RequestMapping(value = RequestMappingConstants.ADD_INCIDENT, method = RequestMethod.GET)
	public UserRO addIncident() {
		return _incidentRestService.addIncident();
	}
	
	@RequestMapping(value = RequestMappingConstants.LOG_INCIDENT, method = RequestMethod.POST)
	public IncidentRO logIncident(final @Valid @RequestBody LogIncidentRO logIncidentRO) {
		return _incidentRestService.logIncident(logIncidentRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_INCIDENT_DETAILS, method = RequestMethod.POST)
	public IncidentRO addIncidentDetail(final @Valid @RequestBody IncidentDetailRO incidentDetailRO) {
		return _incidentRestService.addIncidentDetail(incidentDetailRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_ACCIDENT_DETAILS, method = RequestMethod.POST)
	public IncidentRO addAccidentDetail(final @Valid @RequestBody AccidentDetailRO accidentDetailRO) {
		return _incidentRestService.addAccidentDetail(accidentDetailRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_ASSET_DETAILS, method = RequestMethod.POST)
	public IncidentRO addAssetDetail(final @Valid @RequestBody AssetDetailRO assetDetailRO) {
		return _incidentRestService.addAssetDetail(assetDetailRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_CRIME_DETAILS, method = RequestMethod.POST)
	public IncidentRO addCrimeDetail(final @Valid @RequestBody CrimeDetailRO crimeDetailRO) {
		return _incidentRestService.addCrimeDetail(crimeDetailRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_CLAIM_DETAILS, method = RequestMethod.POST)
	public IncidentRO addClaimDetail(final @Valid @RequestBody ClaimDetailRO claimDetailRO) {
		return _incidentRestService.addClaimDetail(claimDetailRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_INVESTIGATION_DETAILS, method = RequestMethod.POST)
	public IncidentRO addInvestigationDetail(final @Valid @RequestBody InvestigationDetailRO investigationDetailRO) {
		return _incidentRestService.addInvestigationDetail(investigationDetailRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_OR_UPDATE_INVESTIGATION_DETAILS, method = RequestMethod.POST)
	public IncidentRO addOrUpdateInvestigationDetail(final @Valid @RequestBody InvestigationDetailRO investigationDetailRO) {
		return _incidentRestService.addOrUpdateInvestigationDetail(investigationDetailRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_SUPPORTING_DOCUMENTS, method = RequestMethod.POST)
	public List<DocumentViewRO> addSupportingDocuments(
				@RequestParam("uniqueIncidentId") final String uniqueIncidentId,
				@RequestParam("fileDescription") final String[] fileDescriptions,
				@RequestParam("file") final MultipartFile[] files
				) {
		return _documentRestService.saveDocuments(uniqueIncidentId, fileDescriptions, files);
	}
	
	/** update flows */
	@RequestMapping(value = RequestMappingConstants.UPDATE_LOG_INCIDENT, method = RequestMethod.POST)
	public IncidentRO updateLogIncident(final @Valid @RequestBody LogIncidentRO logIncidentRO) {
		return _incidentRestService.updateLogIncident(logIncidentRO);
	}
	
	/** add or update flows */
	@RequestMapping(value = RequestMappingConstants.ADD_OR_UPDATE_LOG_INCIDENT, method = RequestMethod.POST)
	public IncidentRO addOrUpdateLogIncident(final @Valid @RequestBody LogIncidentRO logIncidentRO) {
		return _incidentRestService.addOrUpdateLogIncident(logIncidentRO);
	}
	
	/** alternate flows */
	@RequestMapping(value = RequestMappingConstants.ADD_SUSPECT_FOR_INCIDENT, method = RequestMethod.PUT)
	public IncidentRO addSuspectForIncident(@PathVariable final String uniqueIncidentId, @Valid @RequestBody final SuspectRO suspectRO) {
		return _incidentRestService.addSuspectForIncident(uniqueIncidentId, suspectRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_SUSPECTS_FOR_INCIDENT, method = RequestMethod.PUT)
	public IncidentRO addSuspectsForIncident(@Valid @RequestBody final SuspectWrapper suspectWrapper) {
		return _incidentRestService.addSuspectsForIncident(suspectWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_EXISTING_SUSPECT_FOR_INCIDENT, method = RequestMethod.PUT)
	public IncidentRO addExistingSuspectForIncident(@PathVariable final String uniqueIncidentId, @PathVariable final Long suspectId) {
		return _incidentRestService.addExistingSuspectForIncident(uniqueIncidentId, suspectId);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_EXISTING_SUSPECTS_FOR_INCIDENT, method = RequestMethod.PUT)
	public IncidentRO addExistingSuspectsForIncident(@Valid @RequestBody final SuspectWrapper suspectWrapper) {
		return _incidentRestService.addExistingSuspectsForIncident(suspectWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_EMPLOYEE_SUSPECT_FOR_INCIDENT_BY_ID, method = RequestMethod.PUT)
	public IncidentRO addEmployeeSuspectForIncidentById(@PathVariable final String uniqueIncidentId, @PathVariable final Long employeeId) {
		return _incidentRestService.addEmployeeSuspectForIncidentById(uniqueIncidentId, employeeId);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_EMPLOYEE_SUSPECT_FOR_INCIDENT_BY_LOGIN_ID, method = RequestMethod.PUT)
	public IncidentRO addEmployeeSuspectForIncidentByLoginId(@PathVariable final String uniqueIncidentId, @PathVariable final String employeeLoginId) {
		return _incidentRestService.addEmployeeSuspectForIncidentByLoginId(uniqueIncidentId, employeeLoginId);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_EMPLOYEE_SUSPECTS_FOR_INCIDENT_BY_IDS, method = RequestMethod.PUT)
	public IncidentRO addEmployeeSuspectsForIncidentByIds(@Valid @RequestBody final SuspectWrapper suspectWrapper) {
		return _incidentRestService.addEmployeeSuspectsForIncidentByIds(suspectWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.ADD_EMPLOYEE_SUSPECTS_FOR_INCIDENT_BY_LOGIN_IDS, method = RequestMethod.PUT)
	public IncidentRO addEmployeeSuspectsForIncidentByLoginIds(@Valid @RequestBody final SuspectWrapper suspectWrapper) {
		return _incidentRestService.addEmployeeSuspectsForIncidentByLoginIds(suspectWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.REMOVE_SUSPECT_FROM_INCIDENT, method = RequestMethod.DELETE)
	public IncidentRO removeSuspectFromIncident(@PathVariable final String uniqueIncidentId, @PathVariable final Long suspectId) {
		return _incidentRestService.removeSuspectFromIncident(uniqueIncidentId, suspectId);
	}
	
	@RequestMapping(value = RequestMappingConstants.REMOVE_SUSPECTS_FROM_INCIDENT, method = RequestMethod.DELETE)
	public IncidentRO removeSuspectsFromIncident(@Valid @RequestBody final SuspectWrapper suspectWrapper) {
		return _incidentRestService.removeSuspectsFromIncident(suspectWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.REMOVE_EMPLOYEE_SUSPECT_FROM_INCIDENT, method = RequestMethod.DELETE)
	public IncidentRO removeEmployeeSuspectFromIncident(@PathVariable final String uniqueIncidentId, @PathVariable final Long userId) {
		return _incidentRestService.removeEmployeeSuspectFromIncident(uniqueIncidentId, userId);
	}
	
	@RequestMapping(value = RequestMappingConstants.REMOVE_EMPLOYEE_SUSPECTS_FROM_INCIDENT, method = RequestMethod.DELETE)
	public IncidentRO removeEmployeeSuspectsFromIncident(@PathVariable final String uniqueIncidentId, @Valid @RequestBody final DeleteRO deleteRO) {
		return _incidentRestService.removeEmployeeSuspectsFromIncident(uniqueIncidentId, deleteRO);
	}
}
