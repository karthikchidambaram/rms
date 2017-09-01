package com.i2g.rms.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.Suspect;
import com.i2g.rms.persistence.dao.SuspectDao;

/**
 * Back-end service for suspect related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class SuspectServiceImpl extends AbstractService implements SuspectService {

	@Autowired
	private SuspectDao _suspectDao;

	private SuspectServiceImpl() {
	}

	@Override
	public List<Suspect> get() {
		return _suspectDao.get();
	}

	@Override
	public Suspect get(final long id) {
		return _suspectDao.get(id);
	}

	@Override
	public Set<Suspect> createNewSuspects(final List<Suspect> suspects) {
		return _suspectDao.createNewSuspects(suspects);
	}	
}
