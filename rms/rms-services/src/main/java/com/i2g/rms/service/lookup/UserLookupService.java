package com.i2g.rms.service.lookup;

import java.util.List;

import com.i2g.rms.domain.model.lookup.UserLookup;

/**
 * Service interface for user lookup operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface UserLookupService {

	public List<UserLookup> get();

}
