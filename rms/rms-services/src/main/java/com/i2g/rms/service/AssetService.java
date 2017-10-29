package com.i2g.rms.service;

import java.util.List;

import com.i2g.rms.domain.model.Asset;
import com.i2g.rms.domain.model.incident.Incident;

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
	
	public Asset get(final Incident incident);
	
	public Asset createAsset(final Asset asset);

	public Asset updateAsset(final Asset asset);

}
