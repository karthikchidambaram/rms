package com.i2g.rms.service.lookup;

import java.util.List;

import com.i2g.rms.domain.model.lookup.CrimeSuspectLookup;

/**
 * Service interface for Crime suspect lookup operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface CrimeSuspectLookupService {

	public List<CrimeSuspectLookup> get();

}
