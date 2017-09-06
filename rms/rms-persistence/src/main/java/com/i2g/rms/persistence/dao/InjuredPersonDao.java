package com.i2g.rms.persistence.dao;

import java.util.List;
import java.util.Set;

import com.i2g.rms.domain.model.InjuredPerson;

/**
 * Back-end DAO for Injured Person related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface InjuredPersonDao {	
	
	public List<InjuredPerson> get();
	
	public InjuredPerson get(final long id);
	
	public Set<InjuredPerson> createNewInjuredPersons(final List<InjuredPerson> injuredPersons);
	
}
