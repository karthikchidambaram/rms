package com.i2g.rms.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.admin.CriminalAttackVolume;
import com.i2g.rms.persistence.dao.admin.CriminalAttackVolumeDao;
import com.i2g.rms.service.AbstractService;

/**
 * Back-end service for admin dashboard criminal attack volume related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class CriminalAttackVolumeServiceImpl extends AbstractService implements CriminalAttackVolumeService {

	@Autowired
	private CriminalAttackVolumeDao _criminalAttackVolumeDao;

	private CriminalAttackVolumeServiceImpl() {
	}

	@Override
	public List<CriminalAttackVolume> get() {
		return _criminalAttackVolumeDao.get();
	}
}
