package com.i2g.rms.service;

import java.util.List;
import java.util.Set;

import com.i2g.rms.domain.model.CrimeSuspect;

/**
 * Service interface for all Crime suspect related operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface CrimeSuspectService {
	
	public List<CrimeSuspect> get();
	
	public CrimeSuspect get(final long id);
	
	public Set<CrimeSuspect> createNewCrimeSuspects(final Set<CrimeSuspect> crimeSuspects);
	
}
