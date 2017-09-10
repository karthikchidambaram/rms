package com.i2g.rms.rest.service.lookup;

import java.util.List;

import com.i2g.rms.rest.model.lookup.InjuredPersonLookupRO;

/**
 * Rest Service for Injured person lookup.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface InjuredPersonLookupRestService {

	public List<InjuredPersonLookupRO> get();

}
