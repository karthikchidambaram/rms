package com.i2g.rms.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.Claim;
import com.i2g.rms.domain.model.User;
import com.i2g.rms.domain.model.incident.Incident;
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
	public Claim get(final Incident incident) {
		return _claimDao.get(incident);
	}

	@Override
	public Claim createClaim(final Claim claim) {
		return _claimDao.createClaim(claim);
	}
	
	@Override
	public Claim updateClaim(final Claim claim) {
		return _claimDao.updateClaim(claim);
	}
	
	@Override
	public void deleteClaim(final Claim claim) {
		_claimDao.deleteClaim(claim);
	}

	@Override
	public List<Claim> get(final User claimHandler) {
		return _claimDao.get(claimHandler);
	}

	@Override
	public void deleteClaims(final Set<Claim> claims) {
		_claimDao.deleteClaims(claims);
	}

	@Override
	public boolean isClaimHandlerAssigned(final long id) {
		return _claimDao.isClaimHandlerAssigned(id);
	}
}
