package com.i2g.rms.service;

import java.util.List;
import java.util.Set;

import com.i2g.rms.domain.model.InjuredPerson;

/**
 * Service interface for all injured person related operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface InjuredPersonService {
	
	public List<InjuredPerson> get();
	
	public InjuredPerson get(final long id);
	
	public Set<InjuredPerson> createNewInjuredPersons(final Set<InjuredPerson> injuredPersons);
	
}
