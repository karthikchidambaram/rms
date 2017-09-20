package com.i2g.rms.persistence.dao;

import java.util.List;

import com.i2g.rms.domain.model.OfficeAddress;

/**
 * Back-end DAO for Office Address related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface OfficeAddressDao {

	public List<OfficeAddress> get();

	public OfficeAddress get(final long id);

	public OfficeAddress create(final OfficeAddress OfficeAddress);

}
