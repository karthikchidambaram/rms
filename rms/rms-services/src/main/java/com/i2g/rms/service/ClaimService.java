package com.i2g.rms.service;

import java.util.List;

import com.i2g.rms.domain.model.Claim;

/**
 * Service interface for all Claim related operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface ClaimService {
	
	public List<Claim> get();
	
	public Claim get(final long id);
	
	public Claim create(final Claim claim);
	
}
