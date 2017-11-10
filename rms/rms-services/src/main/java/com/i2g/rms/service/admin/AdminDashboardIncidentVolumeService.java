package com.i2g.rms.service.admin;

import java.util.List;

import com.i2g.rms.domain.model.admin.AdminDashboardIncidentVolume;

/**
 * Service interface for admin dashboard incident volume graph data.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface AdminDashboardIncidentVolumeService {
	
	public List<AdminDashboardIncidentVolume> get();
	
}
