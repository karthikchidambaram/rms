package com.i2g.rms.rest.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i2g.rms.domain.model.ReportedLoss;
import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.domain.model.YesNoType;
import com.i2g.rms.domain.model.incident.Incident;
import com.i2g.rms.domain.model.tablemaintenance.ExternalAgency;
import com.i2g.rms.rest.model.DeleteRO;
import com.i2g.rms.rest.model.ReportedLossRO;
import com.i2g.rms.rest.model.wrapper.ReportedLossWrapper;
import com.i2g.rms.rest.service.incident.IncidentRestService;
import com.i2g.rms.service.ReportedLossService;
import com.i2g.rms.service.exception.ResourceNotCreatedException;
import com.i2g.rms.service.exception.ResourceNotUpdatedException;
import com.i2g.rms.service.exception.ResourceNotValidException;
import com.i2g.rms.service.incident.IncidentService;
import com.i2g.rms.service.tablemaintenance.TableMaintenanceService;

/**
 * Rest services for reported loss rest controller.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class ReportedLossRestServiceImpl extends AbstractRestService implements ReportedLossRestService {
	
	private final Logger _logger = LoggerFactory.getLogger(ReportedLossRestServiceImpl.class);
	
	@Autowired
	private TableMaintenanceService _tableMaintenanceService;
	@Autowired
	private IncidentRestService _incidentRestService;
	@Autowired
	private IncidentService _incidentService;
	@Autowired
	private ReportedLossService _reportedLossService;
		
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public List<ReportedLossRO> get() {
		List<ReportedLoss> reportedLosses = _reportedLossService.get();
		List<ReportedLossRO> reportedLossROs = (reportedLosses == null || reportedLosses.isEmpty()) ? Collections.emptyList() : _mapperService.map(reportedLosses, ReportedLossRO.class);
		return reportedLossROs;
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public Set<ReportedLossRO> getReportedLossesByIncidentId(final Long incidentId) {
		validateKeyId(incidentId);
		final Incident incident = _incidentService.get(incidentId);
		validateGenericObject(incident);
		Set<ReportedLoss> reportedLosses = incident.getReportedLosses();
		Set<ReportedLossRO> reportedLossROs = (reportedLosses == null || reportedLosses.isEmpty()) ? Collections.emptySet() : _mapperService.map(reportedLosses, ReportedLossRO.class);
		return reportedLossROs;		
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public Set<ReportedLossRO> getReportedLossesByUniqueIncidentId(final String uniqueIncidentId) {
		validateUniqueIncidentId(uniqueIncidentId);
		final Incident incident = _incidentService.getIncidentByUniqueIncidentId(uniqueIncidentId);
		validateGenericObject(incident);
		Set<ReportedLoss> reportedLosses = incident.getReportedLosses();
		Set<ReportedLossRO> reportedLossROs = (reportedLosses == null || reportedLosses.isEmpty()) ? Collections.emptySet() : _mapperService.map(reportedLosses, ReportedLossRO.class);
		return reportedLossROs;		
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public List<ReportedLossRO> getReportedLossTableByIncidentId(final Long incidentId) {
		validateKeyId(incidentId);
		final Incident incident = _incidentService.get(incidentId);
		validateGenericObject(incident);
		final List<ReportedLoss> reportedLosses = _reportedLossService.get(incident);
		List<ReportedLossRO> reportedLossROs = (reportedLosses == null || reportedLosses.isEmpty()) ? Collections.emptyList() : _mapperService.map(reportedLosses, ReportedLossRO.class);
		return reportedLossROs;		
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public List<ReportedLossRO> getReportedLossTableByUniqueIncidentId(final String uniqueIncidentId) {
		validateUniqueIncidentId(uniqueIncidentId);
		final Incident incident = _incidentService.getIncidentByUniqueIncidentId(uniqueIncidentId);
		validateGenericObject(incident);
		final List<ReportedLoss> reportedLosses = _reportedLossService.get(incident);
		List<ReportedLossRO> reportedLossROs = (reportedLosses == null || reportedLosses.isEmpty()) ? Collections.emptyList() : _mapperService.map(reportedLosses, ReportedLossRO.class);
		return reportedLossROs;
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public ReportedLossRO get(final long reportedLossId) {
		if (reportedLossId > 0) {
			final ReportedLoss reportedLoss = _reportedLossService.get(reportedLossId);
			validateGenericObject(reportedLoss);
			return _mapperService.map(reportedLoss, ReportedLossRO.class);
		} else {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.GENERIC_FETCH_FAILED_MESSAGE));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public ReportedLossRO createReportedLoss(final ReportedLossRO reportedLossRO) {
		validateObject(reportedLossRO);
		final Incident incident = getIncident(reportedLossRO);
		validateGenericObject(incident);
		final ReportedLoss reportedLoss = constructNewReportedLoss(incident, reportedLossRO);
		validateGenericObject(reportedLoss);
		final ReportedLoss newReportedLoss = _reportedLossService.createReportedLoss(reportedLoss);
		if (newReportedLoss != null) {
			return _mapperService.map(newReportedLoss, ReportedLossRO.class);
		} else {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CREATE_RECORD));
		}	
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public ReportedLossRO createReportedLossForIncidentId(final Long incidentId, final ReportedLossRO reportedLossRO) {
		validateKeyId(incidentId);
		validateObject(reportedLossRO);
		final Incident incident = _incidentService.get(incidentId);
		validateGenericObject(incident);
		final ReportedLoss reportedLoss = constructNewReportedLoss(incident, reportedLossRO);
		validateGenericObject(reportedLoss);
		final ReportedLoss newReportedLoss = _reportedLossService.createReportedLoss(reportedLoss);
		if (newReportedLoss != null) {
			return _mapperService.map(newReportedLoss, ReportedLossRO.class);
		} else {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CREATE_RECORD));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public ReportedLossRO createReportedLossForUniqueIncidentId(final String uniqueIncidentId, final ReportedLossRO reportedLossRO) {
		validateUniqueIncidentId(uniqueIncidentId);
		validateObject(reportedLossRO);
		final Incident incident = _incidentService.getIncidentByUniqueIncidentId(uniqueIncidentId.trim());
		validateGenericObject(incident);
		final ReportedLoss reportedLoss = constructNewReportedLoss(incident, reportedLossRO);
		validateGenericObject(reportedLoss);
		final ReportedLoss newReportedLoss = _reportedLossService.createReportedLoss(reportedLoss);
		if (newReportedLoss != null) {
			return _mapperService.map(newReportedLoss, ReportedLossRO.class);
		} else {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CREATE_RECORD));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public List<ReportedLossRO> createReportedLosses(final ReportedLossWrapper reportedLossWrapper) {
		validateObject(reportedLossWrapper);
		validateUniqueIncidentId(reportedLossWrapper.getUniqueIncidentId());
		validateCollectionObject(reportedLossWrapper.getReportedLosses());
		final Incident incident = _incidentService.getIncidentByUniqueIncidentId(reportedLossWrapper.getUniqueIncidentId().trim());
		validateGenericObject(incident);
		final Set<ReportedLoss> reportedLosses = _incidentRestService.constructReportedLosses(new HashSet<ReportedLossRO>(reportedLossWrapper.getReportedLosses()), incident);
		if (reportedLosses == null || reportedLosses.isEmpty()) {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CONSTRUCT_OBJECT_FOR_RECORD_CREATION));
		}
		final List<ReportedLoss> newReportedLosses = _reportedLossService.createReportedLosses(reportedLosses); 
		if (newReportedLosses != null && !newReportedLosses.isEmpty()) {
			return _mapperService.map(newReportedLosses, ReportedLossRO.class);
		} else {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CREATE_RECORD));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public ReportedLossRO updateReportedLoss(final ReportedLossRO reportedLossRO) {
		validateObject(reportedLossRO);
		if (reportedLossRO.getId() <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.GENERIC_FETCH_FAILED_MESSAGE));
		}
		final ReportedLoss reportedLoss = constructReportedLoss(reportedLossRO);
		validateGenericObject(reportedLoss);
		final ReportedLoss udpatedReportedLoss = _reportedLossService.updateReportedLoss(reportedLoss);
		if (udpatedReportedLoss != null) {
			return _mapperService.map(udpatedReportedLoss, ReportedLossRO.class);
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public List<ReportedLossRO> updateReportedLosses(final ReportedLossWrapper reportedLossWrapper) {
		validateObject(reportedLossWrapper);
		validateCollectionObject(reportedLossWrapper.getReportedLosses());
		final Set<ReportedLoss> reportedLosses = new HashSet<ReportedLoss>(0);
		for (ReportedLossRO reportedLossRO : reportedLossWrapper.getReportedLosses()) {
			if (reportedLossRO != null) {
				reportedLosses.add(constructReportedLoss(reportedLossRO));
			}
		}
		final List<ReportedLoss> updatedReportedLosses = _reportedLossService.updateReportedLosses(reportedLosses);
		if (updatedReportedLosses != null && !updatedReportedLosses.isEmpty()) {
			return _mapperService.map(updatedReportedLosses, ReportedLossRO.class);
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}		
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public void deleteReportedLoss(final Long reportedLossId) {
		if (reportedLossId == null || reportedLossId <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.INVALID_KEY_PASSED_FOR_DELETE));
		}
		final ReportedLoss reportedLoss = _reportedLossService.get(reportedLossId);
		validateGenericObject(reportedLoss);
		_reportedLossService.deleteReportedLoss(reportedLoss);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public void deleteReportedLosses(final DeleteRO deleteRO) { 
		validateObject(deleteRO);
		final Long[] ids = deleteRO.getIds();	
		if (ids == null || ids.length <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.NOTHING_TO_DELETE));
		}
		Set<ReportedLoss> reportedLosses = new HashSet<ReportedLoss>(0);
		for (int i = 0; i < ids.length; i++) {
			final Long id = ids[i];
			if (id != null && id > 0) {
				final ReportedLoss reportedLoss = _reportedLossService.get(id);
				if (reportedLoss != null) {
					reportedLosses.add(reportedLoss);
				}
			}
		}
		_reportedLossService.deleteReportedLosses(reportedLosses);
	}

	@Override
	public ReportedLoss constructNewReportedLoss(final Incident incident, final ReportedLossRO reportedLossRO) {
		validateObject(incident);
		validateObject(reportedLossRO);
		ReportedLoss reportedLoss = new ReportedLoss.Builder().setStatusFlag(StatusFlag.ACTIVE).setIncident(incident).build();
		return setOtherFieldsForReportedLoss(reportedLoss, reportedLossRO);
	}

	@Override
	public ReportedLoss constructReportedLoss(final ReportedLossRO reportedLossRO) {
		validateObject(reportedLossRO);
		if (reportedLossRO.getId() <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.RECORD_FETCH_FAILED_FOR_UPDATE));
		}
		ReportedLoss reportedLoss = _reportedLossService.get(reportedLossRO.getId());
		return setOtherFieldsForReportedLoss(reportedLoss, reportedLossRO);
	}
	
	@Override
	public ReportedLoss setOtherFieldsForReportedLoss(final ReportedLoss reportedLoss, final ReportedLossRO reportedLossRO) {
		if (reportedLoss != null && reportedLossRO != null) {
			//Set other values
			// Loss type
			if (reportedLossRO.getLossType() != null) {
				if (reportedLossRO.getLossType().getId() != null && !reportedLossRO.getLossType().getId().trim().isEmpty()) {
					reportedLoss.setLossType(_tableMaintenanceService.getLossTypeByCode(reportedLossRO.getLossType().getId().trim()));
				}
			}
			// loss type other
			if (reportedLossRO.getLossTypeOther() != null && !reportedLossRO.getLossTypeOther().trim().isEmpty()) {
				reportedLoss.setLossTypeOther(reportedLossRO.getLossTypeOther().trim());
			}
			// Loss value
			if (reportedLossRO.getLossValue() != null) {
				reportedLoss.setLossValue(reportedLossRO.getLossValue());
			}
			// External agency contacted flag
			YesNoType externalAgencyContacted = YesNoType.N;
			if (reportedLossRO.getExternalAgencyContacted() != null && reportedLossRO.getExternalAgencyContacted().name().equals("Y")) {
				externalAgencyContacted = YesNoType.Y;
			}
			// External agency
			ExternalAgency externalAgency = null;
			if (reportedLossRO.getExternalAgency() != null) {
				if (reportedLossRO.getExternalAgency().getId() != null && !reportedLossRO.getExternalAgency().getId().trim().isEmpty()) {
					externalAgency = _tableMaintenanceService.getExternalAgencyByCode(reportedLossRO.getExternalAgency().getId().trim());
				}
			}
			// external agency other
			if (reportedLossRO.getExternalAgencyTypeOther() != null && !reportedLossRO.getExternalAgencyTypeOther().trim().isEmpty()) {
				reportedLoss.setExternalAgencyTypeOther(reportedLossRO.getExternalAgencyTypeOther().trim());
			}
			// Date-time contacted
			LocalDateTime dateTimeContacted = null;
			if (reportedLossRO.getDateTimeContacted() != null) {
				dateTimeContacted = reportedLossRO.getDateTimeContacted();
			}
			_incidentRestService.validateExternalAgencyAndType(externalAgencyContacted, externalAgency, dateTimeContacted);
			reportedLoss.setExternalAgencyContacted(externalAgencyContacted);
			reportedLoss.setExternalAgency(externalAgency);
			reportedLoss.setDateTimeContacted(dateTimeContacted);

			// Cost estimation
			if (reportedLossRO.getCostEstimation() != null) {
				reportedLoss.setCostEstimation(reportedLossRO.getCostEstimation());
			}			
		}
		return reportedLoss;
	}
	
	private Incident getIncident(final ReportedLossRO reportedLossRO) {
		Incident incident = null;
		if (reportedLossRO != null) {
			if (reportedLossRO.getIncident().getId() > 0) {
				incident = _incidentService.get(reportedLossRO.getIncident().getId());
			} else if (reportedLossRO.getIncident().getUniqueIncidentId() != null && !reportedLossRO.getIncident().getUniqueIncidentId().trim().isEmpty()) {
				incident = _incidentService.getIncidentByUniqueIncidentId(reportedLossRO.getIncident().getUniqueIncidentId().trim());
			}
		}
		return incident;
	}	
}
