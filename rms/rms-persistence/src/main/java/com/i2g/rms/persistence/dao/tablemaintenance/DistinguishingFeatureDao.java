package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.DistinguishingFeature;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface DistinguishingFeatureDao {
	
	public List<DistinguishingFeature> get();

	public DistinguishingFeature getByCode(final String code);

	public DistinguishingFeature create(final String code, final String description);

	public DistinguishingFeature update(final String code, final String description);

	public void delete(final String code);
	
}
