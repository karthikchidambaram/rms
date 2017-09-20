package com.i2g.rms.rest.service;

import java.util.List;

import com.i2g.rms.rest.model.OfficeAddressRO;

/**
 * Rest Service Interface for Office address rest services.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface OfficeAddressRestService {
	
	public List<OfficeAddressRO> get();
	
	public OfficeAddressRO get(final long id);
	
	public OfficeAddressRO create(final OfficeAddressRO officeAddressRO);
	
}
