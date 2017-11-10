package com.i2g.rms.service.admin;

import java.util.List;

import com.i2g.rms.domain.model.admin.AssetDamageVolume;

/**
 * Service interface for admin dashboard asset damage incident volume graph
 * data.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface AssetDamageVolumeService {

	public List<AssetDamageVolume> get();

}
