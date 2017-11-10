package com.i2g.rms.service.admin;

import java.util.List;

import com.i2g.rms.domain.model.admin.AdminDashboardIncidentVolumeByStatus;

/**
 * Service interface for admin dashboard incident volume grouped by status graph
 * data.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface AdminDashboardIncidentVolumeByStatusService {

	public List<AdminDashboardIncidentVolumeByStatus> get();

}
