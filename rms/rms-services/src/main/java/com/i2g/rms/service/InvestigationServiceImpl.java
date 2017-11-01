package com.i2g.rms.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.Investigation;
import com.i2g.rms.domain.model.User;
import com.i2g.rms.domain.model.incident.Incident;
import com.i2g.rms.persistence.dao.InvestigationDao;

/**
 * Back-end service for Investigation related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class InvestigationServiceImpl extends AbstractService implements InvestigationService {

	@Autowired
	private InvestigationDao _investigationDao;

	private InvestigationServiceImpl() {
	}

	@Override
	public List<Investigation> get() {
		return _investigationDao.get();
	}

	@Override
	public Investigation get(final long id) {
		return _investigationDao.get(id);
	}
	
	@Override
	public List<Investigation> get(final User investigator) {
		return _investigationDao.get(investigator);
	}

	@Override
	public Investigation createInvestigation(final Investigation investigation) {
		return _investigationDao.createInvestigation(investigation);
	}
	
	@Override
	public Investigation updateInvestigation(final Investigation investigation) {
		return _investigationDao.updateInvestigation(investigation);
	}

	@Override
	public Investigation get(final Incident incident) {
		return _investigationDao.get(incident);
	}

	@Override
	public boolean isInvestigatorAssigned(final long id) {
		return _investigationDao.isInvestigatorAssigned(id);
	}

	@Override
	public void deleteInvestigation(final Investigation investigation) {
		_investigationDao.deleteInvestigation(investigation);	
	}

	@Override
	public void deleteInvestigations(final Set<Investigation> investigations) {
		_investigationDao.deleteInvestigations(investigations);
	}	
}
