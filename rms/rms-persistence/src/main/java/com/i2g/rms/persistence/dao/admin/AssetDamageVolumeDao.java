package com.i2g.rms.persistence.dao.admin;

import java.util.List;

import com.i2g.rms.domain.model.admin.AssetDamageVolume;

/**
 * Back-end DAO for Admin dashboard asset damage incident volume
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface AssetDamageVolumeDao {

	public List<AssetDamageVolume> get();
	
}
