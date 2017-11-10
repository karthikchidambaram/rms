package com.i2g.rms.rest.service.admin;

import java.util.List;

import com.i2g.rms.rest.model.admin.AccidentDamageVolumeRO;
import com.i2g.rms.rest.model.admin.AdminDashboardHeaderStatisticsRO;
import com.i2g.rms.rest.model.admin.AdminDashboardIncidentTypeByStatusRO;
import com.i2g.rms.rest.model.admin.AdminDashboardIncidentTypeRO;
import com.i2g.rms.rest.model.admin.AdminDashboardIncidentVolumeByStatusRO;
import com.i2g.rms.rest.model.admin.AdminDashboardIncidentVolumeRO;
import com.i2g.rms.rest.model.admin.AssetDamageVolumeRO;
import com.i2g.rms.rest.model.admin.CriminalAttackVolumeRO;
import com.i2g.rms.rest.model.admin.IncidentVolumeByEventTypeRO;

/**
 * Rest Service Interface for Admin Dashboard Header Statistics rest services.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface AdminDashboardRestService {

	public AdminDashboardHeaderStatisticsRO get();
	
	public List<AdminDashboardIncidentVolumeRO> getAdminDashboardIncidentVolume();
	
	public List<AdminDashboardIncidentVolumeByStatusRO> getAdminDashboardIncidentVolumeByStatus();
	
	public List<AdminDashboardIncidentTypeRO> getAdminDashboardIncidentType();
	
	public List<AdminDashboardIncidentTypeByStatusRO> getAdminDashboardIncidentTypeByStatus();
	
	public List<CriminalAttackVolumeRO> getCriminalAttackIncidentVolume();
	
	public List<AssetDamageVolumeRO> getAssetDamageIncidentVolume();
	
	public List<AccidentDamageVolumeRO> getAccidentDamageIncidentVolume();
	
	public IncidentVolumeByEventTypeRO getIncidentVolumeByEventType();
}
