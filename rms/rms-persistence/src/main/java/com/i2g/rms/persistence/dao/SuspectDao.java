package com.i2g.rms.persistence.dao;

import java.util.List;
import java.util.Set;

import com.i2g.rms.domain.model.Suspect;

/**
 * Back-end DAO for Suspect related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface SuspectDao {	
	
	public List<Suspect> get();
	
	public Suspect get(final long id);
	
	public Set<Suspect> createNewSuspects(final List<Suspect> suspects);
	
}
