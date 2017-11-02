package com.i2g.rms.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.model.AdminDashboardHeaderStatisticsRO;
import com.i2g.rms.rest.service.AdminDashboardRestService;

/**
 * Rest controller for admin dashboard.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@RestController
public class AdminDashboardController extends AbstractRestController {
	
	@Autowired
	private AdminDashboardRestService _adminDashboardRestService;
	
	@RequestMapping(value = RequestMappingConstants.ADMIN_DASHBOARD_HEADER_STATISTICS, method = RequestMethod.GET)
	public AdminDashboardHeaderStatisticsRO get() {
		return _adminDashboardRestService.get();
	}
	
}
