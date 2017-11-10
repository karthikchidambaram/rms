package com.i2g.rms.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.admin.AdminDashboardIncidentVolume;
import com.i2g.rms.persistence.dao.admin.AdminDashboardIncidentVolumeDao;
import com.i2g.rms.service.AbstractService;

/**
 * Back-end service for Document related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class AdminDashboardIncidentVolumeServiceImpl extends AbstractService implements AdminDashboardIncidentVolumeService {

	@Autowired
	private AdminDashboardIncidentVolumeDao _adminDashboardIncidentVolumeDao;

	private AdminDashboardIncidentVolumeServiceImpl() {
	}

	@Override
	public List<AdminDashboardIncidentVolume> get() {
		return _adminDashboardIncidentVolumeDao.get();
	}
}
