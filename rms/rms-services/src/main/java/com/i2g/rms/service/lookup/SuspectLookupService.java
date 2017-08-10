package com.i2g.rms.service.lookup;

import java.util.List;

import com.i2g.rms.domain.model.lookup.SuspectLookup;

/**
 * Service interface for Suspect lookup operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface SuspectLookupService {

	public List<SuspectLookup> get();

}
