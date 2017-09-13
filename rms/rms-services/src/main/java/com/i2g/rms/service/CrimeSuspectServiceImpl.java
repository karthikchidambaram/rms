package com.i2g.rms.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.CrimeSuspect;
import com.i2g.rms.persistence.dao.CrimeSuspectDao;

/**
 * Back-end service for Crime suspect related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class CrimeSuspectServiceImpl extends AbstractService implements CrimeSuspectService {

	@Autowired
	private CrimeSuspectDao _crimeSuspectDao;

	private CrimeSuspectServiceImpl() {
	}

	@Override
	public List<CrimeSuspect> get() {
		return _crimeSuspectDao.get();
	}

	@Override
	public CrimeSuspect get(final long id) {
		return _crimeSuspectDao.get(id);
	}

	@Override
	public Set<CrimeSuspect> createNewCrimeSuspects(final Set<CrimeSuspect> crimeSuspects) {
		return _crimeSuspectDao.createNewCrimeSuspects(crimeSuspects);
	}
}
