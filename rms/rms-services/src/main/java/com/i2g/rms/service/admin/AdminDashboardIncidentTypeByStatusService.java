package com.i2g.rms.service.admin;

import java.util.List;

import com.i2g.rms.domain.model.admin.AdminDashboardIncidentTypeByStatus;

/**
 * Service interface for admin dashboard incident Type by status graph data.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface AdminDashboardIncidentTypeByStatusService {
	
	public List<AdminDashboardIncidentTypeByStatus> get();
	
}
