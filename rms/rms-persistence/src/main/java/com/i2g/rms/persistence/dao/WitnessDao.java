package com.i2g.rms.persistence.dao;

import java.util.List;
import java.util.Set;

import com.i2g.rms.domain.model.Witness;
import com.i2g.rms.domain.model.tablemaintenance.DistinguishingFeatureDetail;

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

	public Witness createNewWitness(final Witness witness);

	public Set<Witness> createNewWitnesses(final Set<Witness> witnesses);

	public Witness updateWitness(final Witness witness);

	public Set<Witness> updateWitnesses(final Set<Witness> witnesses);
	
	public void removeDistinguishingFeatureDetailsFromWitness(final Witness witness, final Set<DistinguishingFeatureDetail> distinguishingFeatureDetails);

}
