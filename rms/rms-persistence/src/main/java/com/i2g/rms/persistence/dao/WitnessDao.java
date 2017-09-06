package com.i2g.rms.persistence.dao;

import java.util.List;
import java.util.Set;

import com.i2g.rms.domain.model.Witness;

/**
 * Back-end DAO for Witness related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface WitnessDao {	
	
	public List<Witness> get();
	
	public Witness get(final long id);
	
	public Set<Witness> createNewWitnesses(final List<Witness> witnesses);
	
}
