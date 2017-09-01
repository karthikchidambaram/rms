package com.i2g.rms.service;

import java.util.List;

import com.i2g.rms.domain.model.Address;

/**
 * Service interface for all address related operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface AddressService {
	
	public List<Address> get();
	
	public Address get(final long id);
	
	public Address create(final Address address);
	
}
