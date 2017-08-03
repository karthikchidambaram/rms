package com.i2g.rms.rest.service.incident;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i2g.rms.domain.model.incident.Incident;
import com.i2g.rms.rest.model.incident.IncidentRO;
import com.i2g.rms.rest.service.AbstractRestService;
import com.i2g.rms.rest.service.RestMessage;
import com.i2g.rms.service.exception.ResourceNotValidException;
import com.i2g.rms.service.incident.IncidentService;

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

	protected void validateUniqueIncidentId(final String uniqueIncidentId) {
		if (uniqueIncidentId == null || uniqueIncidentId.trim().isEmpty()) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.UNIQUE_INCIDENT_ID_NULL_OR_EMPTY));
		}
	}
}
