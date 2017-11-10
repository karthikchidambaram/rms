package com.i2g.rms.persistence.dao.admin;

import java.util.List;

import com.i2g.rms.domain.model.admin.AdminDashboardIncidentVolume;

/**
 * Back-end DAO for Admin dashboard incident volume
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface AdminDashboardIncidentVolumeDao {

	public List<AdminDashboardIncidentVolume> get();
	
}
