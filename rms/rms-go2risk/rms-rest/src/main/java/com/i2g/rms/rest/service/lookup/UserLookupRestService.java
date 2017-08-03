package com.i2g.rms.rest.service.lookup;

import java.util.List;

import com.i2g.rms.rest.model.lookup.UserLookupRO;

/**
 * Rest Service for user lookup.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface UserLookupRestService {

	public List<UserLookupRO> get();

}
