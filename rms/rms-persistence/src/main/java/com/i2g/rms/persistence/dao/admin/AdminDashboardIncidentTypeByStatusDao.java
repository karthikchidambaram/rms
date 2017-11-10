package com.i2g.rms.persistence.dao.admin;

import java.util.List;

import com.i2g.rms.domain.model.admin.AdminDashboardIncidentTypeByStatus;

/**
 * Back-end DAO for Admin dashboard incident type by status
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface AdminDashboardIncidentTypeByStatusDao {

	public List<AdminDashboardIncidentTypeByStatus> get();
	
}
