package com.i2g.rms.rest.service.lookup;

import java.util.List;

import com.i2g.rms.rest.model.lookup.CrimeSuspectLookupRO;

/**
 * Rest Service for Crime suspect lookup.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface CrimeSuspectLookupRestService {

	public List<CrimeSuspectLookupRO> get();

}
