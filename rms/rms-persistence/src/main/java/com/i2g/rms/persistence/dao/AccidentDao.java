package com.i2g.rms.persistence.dao;

import java.util.List;

import com.i2g.rms.domain.model.Accident;
import com.i2g.rms.domain.model.incident.Incident;

/**
 * Back-end DAO for Accident related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface AccidentDao {
	
	public List<Accident> get();
	
	public Accident get(final long id);
	
	public Accident get(final Incident incident);
	
	public Accident createAccident(final Accident accident);
	
	public Accident updateAccident(final Accident accident);
	
}
