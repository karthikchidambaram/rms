package com.i2g.rms.service.lookup;

import java.util.List;

import com.i2g.rms.domain.model.Manager;

/**
 * Service interface for manager lookup operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface ManagerLookupService {

	public List<Manager> get();

}
