package com.i2g.rms.service;

import java.util.List;
import java.util.Set;

import com.i2g.rms.domain.model.Claim;
import com.i2g.rms.domain.model.User;
import com.i2g.rms.domain.model.incident.Incident;

/**
 * Service interface for all Claim related operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface ClaimService {
	
	public List<Claim> get();
	
	public List<Claim> get(final User claimHandler);

	public Claim get(final long id);
	
	public Claim get(final Incident incident);

	public Claim createClaim(final Claim claim);
	
	public Claim updateClaim(final Claim claim);
	
	public void deleteClaim(final Claim claim);
	
	public void deleteClaims(final Set<Claim> claims);
	
	public boolean isClaimHandlerAssigned(final long id);
	
}
