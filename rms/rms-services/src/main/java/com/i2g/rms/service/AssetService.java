package com.i2g.rms.service;

import java.util.List;

import com.i2g.rms.domain.model.Asset;

/**
 * Service interface for all Asset related operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface AssetService {
	
	public List<Asset> get();
	
	public Asset get(final long id);
	
	public Asset create(final Asset asset);
	
}
