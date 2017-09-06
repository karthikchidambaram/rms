package com.i2g.rms.rest.service;

import java.util.List;

import com.i2g.rms.rest.model.AccidentRO;

/**
 * Rest Service Interface for Accident rest services.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface AccidentRestService {

	public List<AccidentRO> get();

	public AccidentRO get(final long id);

}
