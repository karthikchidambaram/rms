package com.i2g.rms.service;

import java.util.List;

import com.i2g.rms.domain.model.Crime;
import com.i2g.rms.domain.model.incident.Incident;

/**
 * Service interface for all Crime related operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface CrimeService {
	
	public List<Crime> get();
	
	public Crime get(final long id);
	
	public Crime get(final Incident incident);
	
	public Crime createCrime(final Crime crime);
	
	public Crime updateCrime(final Crime crime);
	
}
