package com.i2g.rms.service.lookup;

import java.util.List;

import com.i2g.rms.domain.model.lookup.WitnessLookup;

/**
 * Service interface for witness lookup operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface WitnessLookupService {

	public List<WitnessLookup> get();

}
