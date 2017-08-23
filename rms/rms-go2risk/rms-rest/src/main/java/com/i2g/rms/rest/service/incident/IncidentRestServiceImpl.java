package com.i2g.rms.rest.service.incident;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.domain.model.User;
import com.i2g.rms.domain.model.YesNoType;
import com.i2g.rms.domain.model.incident.Incident;
import com.i2g.rms.domain.model.incident.IncidentStatus;
import com.i2g.rms.rest.model.UserRO;
import com.i2g.rms.rest.model.incident.IncidentRO;
import com.i2g.rms.rest.service.AbstractRestService;
import com.i2g.rms.rest.service.RestMessage;
import com.i2g.rms.service.UserService;
import com.i2g.rms.service.exception.ResourceNotCreatedException;
import com.i2g.rms.service.incident.IncidentService;
import com.i2g.rms.util.ApplicationUtilService;

/**
 * Rest services for password history rest controller.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class IncidentRestServiceImpl extends AbstractRestService implements IncidentRestService {

	private final Logger _logger = LoggerFactory.getLogger(IncidentRestServiceImpl.class);

	@Autowired
	private IncidentService _incidentService;
	@Autowired
	private UserService _userService;
	@Autowired
	private ApplicationUtilService _applicationUtilService;

	@Override
	@Transactional(readOnly = true)
	public List<IncidentRO> get() {
		List<Incident> incidents = _incidentService.get();
		List<IncidentRO> incidentROs = incidents.isEmpty() ? Collections.emptyList()
				: _mapperService.map(incidents, IncidentRO.class);
		return incidentROs;
	}

	@Override
	@Transactional(readOnly = true)
	public IncidentRO getIncidentByUniqueIncidentId(final String uniqueIncidentId) {
		validateUniqueIncidentId(uniqueIncidentId);
		return _mapperService.map(_incidentService.getIncidentByUniqueIncidentId(uniqueIncidentId), IncidentRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public IncidentRO logIncident() {
		//Validate user object
		final User user = getUserFromContext();
		validateUserObject(user);
		//Construct incident record
		final Incident incident = new Incident.Builder()
									.setUniqueIncidentId(ApplicationUtilService.getUniqueIncidentId())
									.setIncidentReportedBy(user)
									.setIncidentStatus(IncidentStatus.DRAFT)
									.setStatusFlag(StatusFlag.ACTIVE)
									.setPersonInjured(YesNoType.N)
									.setPropertyDamage(YesNoType.N)
									.setCrimeInvolved(YesNoType.N)
									.setIncidentOpenedDateTime(ApplicationUtilService.getCurrentTimestamp())
									.build();
		//Validate the newly created object
		validateGenericObject(incident);
		Incident newIncident = _incidentService.logIncident(incident);
		if (newIncident != null) {
			return _mapperService.map(newIncident, IncidentRO.class);
		} else {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CREATE_RECORD));
		}
	}

	@Override
	public UserRO addIncident() {
		return _mapperService.map(getUserFromContext(), UserRO.class);
	}
}
