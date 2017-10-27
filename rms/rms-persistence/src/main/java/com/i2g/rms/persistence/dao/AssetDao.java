package com.i2g.rms.persistence.dao;

import java.util.List;

import com.i2g.rms.domain.model.Asset;

/**
 * Back-end DAO for Asset related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface AssetDao {

	public List<Asset> get();

	public Asset get(final long id);

	public Asset createAsset(final Asset asset);

	public Asset updateAsset(final Asset asset);

}
