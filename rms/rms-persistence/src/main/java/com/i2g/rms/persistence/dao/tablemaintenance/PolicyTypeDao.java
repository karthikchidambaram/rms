package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.PolicyType;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface PolicyTypeDao {
	
	public List<PolicyType> get();

	public PolicyType getByCode(final String code);

	public PolicyType create(final String code, final String description);

	public PolicyType update(final String code, final String description);

	public void delete(final String code);
	
}
