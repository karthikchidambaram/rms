package com.i2g.rms.rest.service.lookup;

import java.util.List;

import com.i2g.rms.rest.model.ManagerRO;

/**
 * Rest Service for manager lookup.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface ManagerLookupRestService {

	public List<ManagerRO> get();

}
