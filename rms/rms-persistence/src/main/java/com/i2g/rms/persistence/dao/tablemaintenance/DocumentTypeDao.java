package com.i2g.rms.persistence.dao.tablemaintenance;

import java.util.List;

import com.i2g.rms.domain.model.tablemaintenance.DocumentType;

/**
 * Back-end DAO for table maintenance (drop-down values) pages.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface DocumentTypeDao {
	
	public List<DocumentType> get();

	public DocumentType getByCode(final String code);

	public DocumentType create(final String code, final String description);

	public DocumentType update(final String code, final String description);

	public void delete(final String code);
	
}
