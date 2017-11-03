package com.i2g.rms.rest.service;

import java.util.List;
import java.util.Set;

import com.i2g.rms.domain.model.Witness;
import com.i2g.rms.rest.model.WitnessRO;
import com.i2g.rms.rest.model.lookup.WitnessTableRO;
import com.i2g.rms.rest.model.wrapper.DistinguishingFeatureDetailWrapper;
import com.i2g.rms.rest.model.wrapper.WitnessWrapper;

/**
 * Rest Service Interface for witness rest services.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface WitnessRestService {

	public List<WitnessRO> get();

	public WitnessRO get(final long witnessId);
	
	public List<WitnessTableRO> getWitnessTableByAccidentId(final Long accidentId);
	
	public List<WitnessTableRO> getWitnessTableByCrimeId(final Long crimeId);
	
	public WitnessRO createWitness(final WitnessRO witnessRO);

	public Set<WitnessRO> createWitnesses(final WitnessWrapper witnessWrapper);

	public WitnessRO udpateWitness(final WitnessRO witnessRO);

	public Set<WitnessRO> udpateWitnesses(final WitnessWrapper witnessWrapper);

	public void removeDistinguishingFeatureDetailsFromWitness(final DistinguishingFeatureDetailWrapper distinguishingFeatureDetailWrapper);

	/** methods exposed to other services */
	
	public Witness constructNewWitness(final WitnessRO witnessRO);
	
	public Witness constructWitness(final WitnessRO witnessRO);
	
	public Witness setOtherFieldsForWitness(final Witness witness, final WitnessRO witnessRO);
	
}
