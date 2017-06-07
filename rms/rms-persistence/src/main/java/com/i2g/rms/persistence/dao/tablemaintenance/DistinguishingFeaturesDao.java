package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.DistinguishingFeatures;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface DistinguishingFeaturesDao {
	
	public List<DistinguishingFeatures> get();

	public DistinguishingFeatures getByCode(final String code);

	public DistinguishingFeatures create(final String code, final String description);

	public DistinguishingFeatures update(final String code, final String description);

	public void delete(final String code);
	
}
