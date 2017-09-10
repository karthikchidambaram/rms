package com.i2g.rms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.Asset;
import com.i2g.rms.persistence.dao.AssetDao;

/**
 * Back-end service for asset related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class AssetServiceImpl extends AbstractService implements AssetService {

	@Autowired
	private AssetDao _assetDao;

	private AssetServiceImpl() {
	}

	@Override
	public List<Asset> get() {
		return _assetDao.get();
	}

	@Override
	public Asset get(final long id) {
		return _assetDao.get(id);
	}

	@Override
	public Asset create(final Asset asset) {
		return _assetDao.create(asset);
	}
}
