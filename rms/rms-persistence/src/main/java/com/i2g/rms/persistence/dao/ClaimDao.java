package com.i2g.rms.persistence.dao;

import java.util.List;

import com.i2g.rms.domain.model.Claim;

/**
 * Back-end DAO for Claim related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface ClaimDao {

	public List<Claim> get();

	public Claim get(final long id);

	public Claim create(final Claim claim);

}
