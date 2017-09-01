package com.i2g.rms.rest.service;

import java.util.List;

import com.i2g.rms.rest.model.AddressRO;

/**
 * Rest Service Interface for address rest services.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface AddressRestService {
	
	public List<AddressRO> get();
	
	public AddressRO get(final long id);
	
	public AddressRO create(final AddressRO addressRO);
	
}
