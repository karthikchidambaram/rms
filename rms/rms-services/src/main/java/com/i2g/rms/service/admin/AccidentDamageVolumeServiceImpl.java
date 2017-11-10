package com.i2g.rms.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.admin.AccidentDamageVolume;
import com.i2g.rms.persistence.dao.admin.AccidentDamageVolumeDao;
import com.i2g.rms.service.AbstractService;

/**
 * Back-end service for admin dashboard accident damage volume related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class AccidentDamageVolumeServiceImpl extends AbstractService implements AccidentDamageVolumeService {

	@Autowired
	private AccidentDamageVolumeDao _accidentDamageVolumeDao;

	private AccidentDamageVolumeServiceImpl() {
	}

	@Override
	public List<AccidentDamageVolume> get() {
		return _accidentDamageVolumeDao.get();
	}
}
