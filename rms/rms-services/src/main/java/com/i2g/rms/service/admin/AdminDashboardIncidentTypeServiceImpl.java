package com.i2g.rms.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.admin.AdminDashboardIncidentType;
import com.i2g.rms.persistence.dao.admin.AdminDashboardIncidentTypeDao;
import com.i2g.rms.service.AbstractService;

/**
 * Back-end service for AdminDashboardIncidentTypeService.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class AdminDashboardIncidentTypeServiceImpl extends AbstractService implements AdminDashboardIncidentTypeService {

	@Autowired
	private AdminDashboardIncidentTypeDao _adminDashboardIncidentTypeDao;

	private AdminDashboardIncidentTypeServiceImpl() {
	}

	@Override
	public List<AdminDashboardIncidentType> get() {
		return _adminDashboardIncidentTypeDao.get();
	}
}
