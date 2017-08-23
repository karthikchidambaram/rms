package com.i2g.rms.service.incident;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.incident.Incident;
import com.i2g.rms.persistence.dao.incident.IncidentDao;
import com.i2g.rms.service.AbstractService;

/**
 * Back-end service for Incident related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class IncidentServiceImpl extends AbstractService implements IncidentService {

	@Autowired
	private IncidentDao _incidentDao;

	private IncidentServiceImpl() {
	}

	@Override
	public List<Incident> get() {
		return _incidentDao.get();
	}

	@Override
	public Incident getIncidentByUniqueIncidentId(final String uniqueIncidentId) {
		return _incidentDao.getIncidentByUniqueIncidentId(uniqueIncidentId);
	}

	@Override
	public Incident logIncident(Incident incident) {
		return _incidentDao.logIncident(incident);
	}
}
