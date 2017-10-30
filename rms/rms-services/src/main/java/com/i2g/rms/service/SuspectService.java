package com.i2g.rms.service;

import java.util.List;
import java.util.Set;

import com.i2g.rms.domain.model.Suspect;
import com.i2g.rms.domain.model.tablemaintenance.DistinguishingFeatureDetail;

/**
 * Service interface for all suspect related operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface SuspectService {
	
	public List<Suspect> get();
	
	public Suspect get(final long id);
	
	public Suspect createNewSuspect(final Suspect suspect);
	
	public List<Suspect> createNewSuspects(final Set<Suspect> suspects);
	
	public Suspect updateSuspect(final Suspect suspect);
	
	public List<Suspect> updateSuspects(final Set<Suspect> suspects);
	
	public void removeDistinguishingFeatureDetailsFromSuspect(final Suspect suspect, final Set<DistinguishingFeatureDetail> distinguishingFeatureDetails);
	
}
