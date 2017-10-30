package com.i2g.rms.persistence.dao;

import java.util.List;

import com.i2g.rms.domain.model.Crime;
import com.i2g.rms.domain.model.incident.Incident;

/**
 * Back-end DAO for Crime related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface CrimeDao {
	
	public List<Crime> get();
	
	public Crime get(final long id);
	
	public Crime get(final Incident incident);
	
	public Crime createCrime(final Crime crime);
	
	public Crime updateCrime(final Crime crime);
}
