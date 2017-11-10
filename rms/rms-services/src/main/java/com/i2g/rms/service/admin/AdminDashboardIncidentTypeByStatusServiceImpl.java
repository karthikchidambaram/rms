package com.i2g.rms.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.admin.AdminDashboardIncidentTypeByStatus;
import com.i2g.rms.persistence.dao.admin.AdminDashboardIncidentTypeByStatusDao;
import com.i2g.rms.service.AbstractService;

/**
 * Back-end service for AdminDashboardIncidentTypeByStatusService.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class AdminDashboardIncidentTypeByStatusServiceImpl extends AbstractService implements AdminDashboardIncidentTypeByStatusService {

	@Autowired
	private AdminDashboardIncidentTypeByStatusDao _adminDashboardIncidentTypeByStatusDao;

	private AdminDashboardIncidentTypeByStatusServiceImpl() {
	}

	@Override
	public List<AdminDashboardIncidentTypeByStatus> get() {
		return _adminDashboardIncidentTypeByStatusDao.get();
	}
}
