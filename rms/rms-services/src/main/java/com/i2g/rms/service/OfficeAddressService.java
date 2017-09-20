package com.i2g.rms.service;

import java.util.List;

import com.i2g.rms.domain.model.OfficeAddress;

/**
 * Service interface for all Office address related operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface OfficeAddressService {

	public List<OfficeAddress> get();

	public OfficeAddress get(final long id);

	public OfficeAddress create(final OfficeAddress officeAddress);

}
