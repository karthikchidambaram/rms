package com.i2g.rms.persistence.dao;

import java.util.List;

import com.i2g.rms.domain.model.Address;

/**
 * Back-end DAO for address related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface AddressDao {
	
	public List<Address> get();
	
	public Address get(final long id);
	
	public Address create(final Address address);
	
}
