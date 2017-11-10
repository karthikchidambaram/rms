package com.i2g.rms.persistence.dao.admin;

import java.util.List;

import com.i2g.rms.domain.model.admin.AdminDashboardIncidentVolumeByStatus;

/**
 * Back-end DAO for Admin dashboard incident volume grouped by status for the
 * last six months.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface AdminDashboardIncidentVolumeByStatusDao {

	public List<AdminDashboardIncidentVolumeByStatus> get();

}
