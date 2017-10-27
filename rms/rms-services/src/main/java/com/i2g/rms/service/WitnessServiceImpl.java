package com.i2g.rms.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.Witness;
import com.i2g.rms.domain.model.tablemaintenance.DistinguishingFeatureDetail;
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
	public Witness createNewWitness(final Witness witness) {
		return _witnessDao.createNewWitness(witness);
	}

	@Override
	public Set<Witness> createNewWitnesses(final Set<Witness> witnesses) {
		return _witnessDao.createNewWitnesses(witnesses);
	}

	@Override
	public Witness updateWitness(final Witness witness) {
		return _witnessDao.updateWitness(witness);
	}

	@Override
	public Set<Witness> updateWitnesses(final Set<Witness> witnesses) {
		return _witnessDao.updateWitnesses(witnesses);
	}

	@Override
	public void removeDistinguishingFeatureDetailsFromWitness(final Witness witness, final Set<DistinguishingFeatureDetail> distinguishingFeatureDetails) {
		_witnessDao.removeDistinguishingFeatureDetailsFromWitness(witness, distinguishingFeatureDetails);
	}
}
