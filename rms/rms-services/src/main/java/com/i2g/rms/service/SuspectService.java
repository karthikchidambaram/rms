package com.i2g.rms.service;

import java.util.List;
import java.util.Set;

import com.i2g.rms.domain.model.Suspect;

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
	
	public Set<Suspect> createNewSuspects(final Set<Suspect> suspects);
	
}
