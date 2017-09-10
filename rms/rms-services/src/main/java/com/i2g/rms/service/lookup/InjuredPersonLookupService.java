package com.i2g.rms.service.lookup;

import java.util.List;

import com.i2g.rms.domain.model.lookup.InjuredPersonLookup;

/**
 * Service interface for injured person lookup operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface InjuredPersonLookupService {

	public List<InjuredPersonLookup> get();

}
