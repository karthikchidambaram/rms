package com.i2g.rms.rest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i2g.rms.rest.model.AdminDashboardHeaderStatisticsRO;
import com.i2g.rms.service.AdminDashboardHeaderStatisticsService;

/**
 * Rest services for Asset rest controller.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class AdminDashboardRestServiceImpl extends AbstractRestService implements AdminDashboardRestService {

	@SuppressWarnings("unused")
	private final Logger _logger = LoggerFactory.getLogger(AdminDashboardRestServiceImpl.class);
	
	@Autowired
	private AdminDashboardHeaderStatisticsService _adminDashboardHeaderStatisticsService;
	
	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR')")
	@Transactional(readOnly = true)
	public AdminDashboardHeaderStatisticsRO get() {
		return _mapperService.map(_adminDashboardHeaderStatisticsService.get(), AdminDashboardHeaderStatisticsRO.class);
	}	
}
