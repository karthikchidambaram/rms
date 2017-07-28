package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.VehicleDamageType;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface VehicleDamageTypeDao {
	
	public List<VehicleDamageType> get();

	public VehicleDamageType getByCode(final String code);

	public VehicleDamageType create(final String code, final String description);

	public VehicleDamageType update(final String code, final String description);

	public void delete(final String code);
	
}
