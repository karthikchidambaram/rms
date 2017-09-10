package com.i2g.rms.rest.service.lookup;

import java.util.List;

import com.i2g.rms.rest.model.lookup.WitnessLookupRO;

/**
 * Rest Service for witness lookup.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface WitnessLookupRestService {

	public List<WitnessLookupRO> get();

}
