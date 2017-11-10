package com.i2g.rms.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.admin.AssetDamageVolume;
import com.i2g.rms.persistence.dao.admin.AssetDamageVolumeDao;
import com.i2g.rms.service.AbstractService;

/**
 * Back-end service for admin dashboard asset damage volume related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class AssetDamageVolumeServiceImpl extends AbstractService implements AssetDamageVolumeService {

	@Autowired
	private AssetDamageVolumeDao _assetDamageVolumeDao;

	private AssetDamageVolumeServiceImpl() {
	}

	@Override
	public List<AssetDamageVolume> get() {
		return _assetDamageVolumeDao.get();
	}
}
