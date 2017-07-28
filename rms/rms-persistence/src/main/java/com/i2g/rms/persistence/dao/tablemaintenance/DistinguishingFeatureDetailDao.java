package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.DistinguishingFeatureDetail;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface DistinguishingFeatureDetailDao {
	
	public List<DistinguishingFeatureDetail> get();

	public DistinguishingFeatureDetail getByCode(final String code);

	public DistinguishingFeatureDetail create(final String code, final String description);

	public DistinguishingFeatureDetail update(final String code, final String description);

	public void delete(final String code);
	
}
