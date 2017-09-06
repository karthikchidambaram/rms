package com.i2g.rms.service;

import java.util.List;

import com.i2g.rms.domain.model.Accident;

/**
 * Service interface for all Accident related operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface AccidentService {
	
	public List<Accident> get();
	
	public Accident get(final long id);
	
	public Accident create(final Accident accident);
	
}
