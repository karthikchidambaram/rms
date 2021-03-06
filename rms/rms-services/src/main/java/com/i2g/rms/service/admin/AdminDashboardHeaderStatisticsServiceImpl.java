package com.i2g.rms.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.admin.AdminDashboardHeaderStatistics;
import com.i2g.rms.persistence.dao.admin.AdminDashboardHeaderStatisticsDao;
import com.i2g.rms.service.AbstractService;

/**
 * Back-end service for Document related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class AdminDashboardHeaderStatisticsServiceImpl extends AbstractService implements AdminDashboardHeaderStatisticsService {

	@Autowired
	private AdminDashboardHeaderStatisticsDao _adminDashboardHeaderStatisticsDao;

	private AdminDashboardHeaderStatisticsServiceImpl() {
	}

	@Override
	public AdminDashboardHeaderStatistics get() {
		return _adminDashboardHeaderStatisticsDao.get();
	}
}
