package com.i2g.rms.rest.service.lookup;

import java.util.List;

import com.i2g.rms.rest.model.lookup.SuspectLookupRO;

/**
 * Rest Service for Suspect lookup.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface SuspectLookupRestService {

	public List<SuspectLookupRO> get();

}
