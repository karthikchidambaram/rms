package com.i2g.rms.persistence.dao.admin;

import java.util.List;

import com.i2g.rms.domain.model.admin.AdminDashboardIncidentType;

/**
 * Back-end DAO for Admin dashboard incident type
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface AdminDashboardIncidentTypeDao {

	public List<AdminDashboardIncidentType> get();
	
}
