package com.i2g.rms.service.admin;

import java.util.List;

import com.i2g.rms.domain.model.admin.AdminDashboardIncidentType;

/**
 * Service interface for admin dashboard incident Type graph data.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface AdminDashboardIncidentTypeService {
	
	public List<AdminDashboardIncidentType> get();
	
}
