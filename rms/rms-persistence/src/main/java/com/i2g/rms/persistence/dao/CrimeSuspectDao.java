package com.i2g.rms.persistence.dao;

import java.util.List;
import java.util.Set;

import com.i2g.rms.domain.model.CrimeSuspect;

/**
 * Back-end DAO for Suspect related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface CrimeSuspectDao {	
	
	public List<CrimeSuspect> get();
	
	public CrimeSuspect get(final long id);
	
	public Set<CrimeSuspect> createNewCrimeSuspects(final Set<CrimeSuspect> crimeSuspects);
	
}
