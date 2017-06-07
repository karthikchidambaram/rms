package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.DistinguishingFeaturesDetail;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface DistinguishingFeaturesDetailDao {
	
	public List<DistinguishingFeaturesDetail> get();

	public DistinguishingFeaturesDetail getByCode(final String code);

	public DistinguishingFeaturesDetail create(final String code, final String description);

	public DistinguishingFeaturesDetail update(final String code, final String description);

	public void delete(final String code);
	
}
