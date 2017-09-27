package com.i2g.rms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.Investigation;
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
	public Investigation create(final Investigation investigation) {
		return _investigationDao.create(investigation);
	}	
}
