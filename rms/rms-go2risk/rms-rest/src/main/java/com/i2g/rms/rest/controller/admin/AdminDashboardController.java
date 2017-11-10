package com.i2g.rms.rest.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.controller.AbstractRestController;
import com.i2g.rms.rest.model.admin.AccidentDamageVolumeRO;
import com.i2g.rms.rest.model.admin.AdminDashboardHeaderStatisticsRO;
import com.i2g.rms.rest.model.admin.AdminDashboardIncidentTypeByStatusRO;
import com.i2g.rms.rest.model.admin.AdminDashboardIncidentTypeRO;
import com.i2g.rms.rest.model.admin.AdminDashboardIncidentVolumeByStatusRO;
import com.i2g.rms.rest.model.admin.AdminDashboardIncidentVolumeRO;
import com.i2g.rms.rest.model.admin.AssetDamageVolumeRO;
import com.i2g.rms.rest.model.admin.CriminalAttackVolumeRO;
import com.i2g.rms.rest.model.admin.IncidentVolumeByEventTypeRO;
import com.i2g.rms.rest.service.admin.AdminDashboardRestService;

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
	
	@RequestMapping(value = RequestMappingConstants.ADMIN_DASHBOARD_INCIDENT_VOLUME, method = RequestMethod.GET)
	public List<AdminDashboardIncidentVolumeRO> getAdminDashboardIncidentVolume() {
		return _adminDashboardRestService.getAdminDashboardIncidentVolume();
	}
	
	@RequestMapping(value = RequestMappingConstants.ADMIN_DASHBOARD_INCIDENT_VOLUME_BY_STATUS, method = RequestMethod.GET)
	public List<AdminDashboardIncidentVolumeByStatusRO> getAdminDashboardIncidentVolumeByStatus() {
		return _adminDashboardRestService.getAdminDashboardIncidentVolumeByStatus();
	}
	
	@RequestMapping(value = RequestMappingConstants.ADMIN_DASHBOARD_INCIDENT_TYPE, method = RequestMethod.GET)
	public List<AdminDashboardIncidentTypeRO> getAdminDashboardIncidentType() {
		return _adminDashboardRestService.getAdminDashboardIncidentType();
	}
	
	@RequestMapping(value = RequestMappingConstants.ADMIN_DASHBOARD_INCIDENT_TYPE_BY_STATUS, method = RequestMethod.GET)
	public List<AdminDashboardIncidentTypeByStatusRO> getAdminDashboardIncidentTypeByStatus() {
		return _adminDashboardRestService.getAdminDashboardIncidentTypeByStatus();
	}
	
	@RequestMapping(value = RequestMappingConstants.ADMIN_DASHBOARD_CRIMINAL_ATTACK_INCIDENT_VOLUME, method = RequestMethod.GET)
	public List<CriminalAttackVolumeRO> getCriminalAttackIncidentVolume() {
		return _adminDashboardRestService.getCriminalAttackIncidentVolume();
	}
	
	@RequestMapping(value = RequestMappingConstants.ADMIN_DASHBOARD_ACCIDENT_DAMAGE_INCIDENT_VOLUME, method = RequestMethod.GET)
	public List<AccidentDamageVolumeRO> getAccidentDamageIncidentVolume() {
		return _adminDashboardRestService.getAccidentDamageIncidentVolume();
	}
	
	@RequestMapping(value = RequestMappingConstants.ADMIN_DASHBOARD_ASSET_DAMAGE_INCIDENT_VOLUME, method = RequestMethod.GET)
	public List<AssetDamageVolumeRO> getAssetDamageIncidentVolume() {
		return _adminDashboardRestService.getAssetDamageIncidentVolume();
	}
	
	@RequestMapping(value = RequestMappingConstants.ADMIN_DASHBOARD_INCIDENT_VOLUME_BY_EVENT_TYPE, method = RequestMethod.GET)
	public IncidentVolumeByEventTypeRO getIncidentVolumeByEventType() {
		return _adminDashboardRestService.getIncidentVolumeByEventType();
	}
}
