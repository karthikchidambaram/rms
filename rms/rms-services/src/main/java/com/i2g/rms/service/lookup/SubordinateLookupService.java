package com.i2g.rms.service.lookup;

import java.util.List;

import com.i2g.rms.domain.model.Subordinate;

/**
 * Service interface for subordinate lookup operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface SubordinateLookupService {

	public List<Subordinate> get();
	
	public List<Subordinate> get(final String managerLoginId);

}
