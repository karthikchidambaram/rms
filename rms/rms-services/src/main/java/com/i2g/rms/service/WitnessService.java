package com.i2g.rms.service;

import java.util.List;
import java.util.Set;

import com.i2g.rms.domain.model.Witness;

/**
 * Service interface for all Witness related operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface WitnessService {
	
	public List<Witness> get();
	
	public Witness get(final long id);
	
	public Set<Witness> createNewWitnesses(final List<Witness> witnesses);
	
}
