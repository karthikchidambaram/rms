package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.DocumentCategory;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface DocumentCategoryDao {
	
	public List<DocumentCategory> get();

	public DocumentCategory getByCode(final String code);

	public DocumentCategory create(final String code, final String description);

	public DocumentCategory update(final String code, final String description);

	public void delete(final String code);
	
}
