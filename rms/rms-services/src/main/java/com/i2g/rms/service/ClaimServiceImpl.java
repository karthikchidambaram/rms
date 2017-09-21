package com.i2g.rms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.Claim;
import com.i2g.rms.persistence.dao.ClaimDao;

/**
 * Back-end service for claim related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class ClaimServiceImpl extends AbstractService implements ClaimService {

	@Autowired
	private ClaimDao _claimDao;

	private ClaimServiceImpl() {
	}

	@Override
	public List<Claim> get() {
		return _claimDao.get();
	}

	@Override
	public Claim get(final long id) {
		return _claimDao.get(id);
	}

	@Override
	public Claim create(final Claim claim) {
		return _claimDao.create(claim);
	}	
}
