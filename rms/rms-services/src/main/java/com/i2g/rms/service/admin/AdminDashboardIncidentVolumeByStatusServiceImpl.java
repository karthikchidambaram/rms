package com.i2g.rms.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.admin.AdminDashboardIncidentVolumeByStatus;
import com.i2g.rms.persistence.dao.admin.AdminDashboardIncidentVolumeByStatusDao;
import com.i2g.rms.service.AbstractService;

/**
 * Back-end service for Admin Dashboard Incident Volume By Status Dao related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class AdminDashboardIncidentVolumeByStatusServiceImpl extends AbstractService implements AdminDashboardIncidentVolumeByStatusService {

	@Autowired
	private AdminDashboardIncidentVolumeByStatusDao _adminDashboardIncidentVolumeByStatusDao;

	private AdminDashboardIncidentVolumeByStatusServiceImpl() {
	}

	@Override
	public List<AdminDashboardIncidentVolumeByStatus> get() {
		return _adminDashboardIncidentVolumeByStatusDao.get();
	}
}
