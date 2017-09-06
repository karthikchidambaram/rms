package com.i2g.rms.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.Witness;
import com.i2g.rms.persistence.dao.WitnessDao;

/**
 * Back-end service for witness related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class WitnessServiceImpl extends AbstractService implements WitnessService {

	@Autowired
	private WitnessDao _witnessDao;

	private WitnessServiceImpl() {
	}

	@Override
	public List<Witness> get() {
		return _witnessDao.get();
	}

	@Override
	public Witness get(final long id) {
		return _witnessDao.get(id);
	}

	@Override
	public Set<Witness> createNewWitnesses(final List<Witness> witnesses) {
		return _witnessDao.createNewWitnesses(witnesses);
	}
}