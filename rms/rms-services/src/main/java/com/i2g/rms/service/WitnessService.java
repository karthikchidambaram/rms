package com.i2g.rms.service;

import java.util.List;
import java.util.Set;

import com.i2g.rms.domain.model.Witness;
import com.i2g.rms.domain.model.tablemaintenance.DistinguishingFeatureDetail;

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
	
	public Witness createNewWitness(final Witness witness);
	
	public Set<Witness> createNewWitnesses(final Set<Witness> witnesses);
	
	public Witness updateWitness(final Witness witness);

	public Set<Witness> updateWitnesses(final Set<Witness> witnesses);

	public void removeDistinguishingFeatureDetailsFromWitness(final Witness witness, final Set<DistinguishingFeatureDetail> distinguishingFeatureDetails);
	
}
