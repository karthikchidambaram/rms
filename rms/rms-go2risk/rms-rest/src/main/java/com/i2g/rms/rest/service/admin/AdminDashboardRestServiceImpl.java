package com.i2g.rms.rest.service.admin;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i2g.rms.domain.model.admin.AccidentDamageVolume;
import com.i2g.rms.domain.model.admin.AdminDashboardIncidentType;
import com.i2g.rms.domain.model.admin.AdminDashboardIncidentTypeByStatus;
import com.i2g.rms.domain.model.admin.AdminDashboardIncidentVolume;
import com.i2g.rms.domain.model.admin.AdminDashboardIncidentVolumeByStatus;
import com.i2g.rms.domain.model.admin.AssetDamageVolume;
import com.i2g.rms.domain.model.admin.CriminalAttackVolume;
import com.i2g.rms.rest.model.admin.AccidentDamageVolumeRO;
import com.i2g.rms.rest.model.admin.AdminDashboardHeaderStatisticsRO;
import com.i2g.rms.rest.model.admin.AdminDashboardIncidentTypeByStatusRO;
import com.i2g.rms.rest.model.admin.AdminDashboardIncidentTypeRO;
import com.i2g.rms.rest.model.admin.AdminDashboardIncidentVolumeByStatusRO;
import com.i2g.rms.rest.model.admin.AdminDashboardIncidentVolumeRO;
import com.i2g.rms.rest.model.admin.AssetDamageVolumeRO;
import com.i2g.rms.rest.model.admin.CriminalAttackVolumeRO;
import com.i2g.rms.rest.model.admin.IncidentVolumeByEventTypeRO;
import com.i2g.rms.rest.service.AbstractRestService;
import com.i2g.rms.service.admin.AccidentDamageVolumeService;
import com.i2g.rms.service.admin.AdminDashboardHeaderStatisticsService;
import com.i2g.rms.service.admin.AdminDashboardIncidentTypeByStatusService;
import com.i2g.rms.service.admin.AdminDashboardIncidentTypeService;
import com.i2g.rms.service.admin.AdminDashboardIncidentVolumeByStatusService;
import com.i2g.rms.service.admin.AdminDashboardIncidentVolumeService;
import com.i2g.rms.service.admin.AssetDamageVolumeService;
import com.i2g.rms.service.admin.CriminalAttackVolumeService;

/**
 * Rest services for admin dashboard functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class AdminDashboardRestServiceImpl extends AbstractRestService implements AdminDashboardRestService {

	@SuppressWarnings("unused")
	private final Logger _logger = LoggerFactory.getLogger(AdminDashboardRestServiceImpl.class);
	
	@Autowired
	private AdminDashboardHeaderStatisticsService _adminDashboardHeaderStatisticsService;
	@Autowired
	private AdminDashboardIncidentVolumeService _adminDashboardIncidentVolumeService;
	@Autowired
	private AdminDashboardIncidentVolumeByStatusService _adminDashboardIncidentVolumeByStatusService;
	@Autowired
	private AdminDashboardIncidentTypeService _adminDashboardIncidentTypeService;
	@Autowired
	private AdminDashboardIncidentTypeByStatusService _adminDashboardIncidentTypeByStatusService;
	@Autowired
	private CriminalAttackVolumeService _criminalAttackVolumeService;
	@Autowired
	private AssetDamageVolumeService _assetDamageVolumeService;
	@Autowired
	private AccidentDamageVolumeService _accidentDamageVolumeService;
	
	
	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR')")
	@Transactional(readOnly = true)
	public AdminDashboardHeaderStatisticsRO get() {
		return _mapperService.map(_adminDashboardHeaderStatisticsService.get(), AdminDashboardHeaderStatisticsRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR')")
	@Transactional(readOnly = true)
	public List<AdminDashboardIncidentVolumeRO> getAdminDashboardIncidentVolume() {
		List<AdminDashboardIncidentVolume> adminDashboardIncidentVolumes =  _adminDashboardIncidentVolumeService.get();
		List<AdminDashboardIncidentVolumeRO> adminDashboardIncidentVolumeROs = (adminDashboardIncidentVolumes == null || adminDashboardIncidentVolumes.isEmpty()) ? Collections.emptyList() : _mapperService.map(adminDashboardIncidentVolumes, AdminDashboardIncidentVolumeRO.class);
		return adminDashboardIncidentVolumeROs;
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR')")
	@Transactional(readOnly = true)
	public List<AdminDashboardIncidentVolumeByStatusRO> getAdminDashboardIncidentVolumeByStatus() {
		List<AdminDashboardIncidentVolumeByStatus> adminDashboardIncidentVolumesByStatuses =  _adminDashboardIncidentVolumeByStatusService.get();
		List<AdminDashboardIncidentVolumeByStatusRO> adminDashboardIncidentVolumeByStatusROs = (adminDashboardIncidentVolumesByStatuses == null || adminDashboardIncidentVolumesByStatuses.isEmpty()) ? Collections.emptyList() : _mapperService.map(adminDashboardIncidentVolumesByStatuses, AdminDashboardIncidentVolumeByStatusRO.class);
		return adminDashboardIncidentVolumeByStatusROs;
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR')")
	@Transactional(readOnly = true)
	public List<AdminDashboardIncidentTypeRO> getAdminDashboardIncidentType() {
		List<AdminDashboardIncidentType> adminDashboardIncidentTypes =  _adminDashboardIncidentTypeService.get();
		List<AdminDashboardIncidentTypeRO> adminDashboardIncidentTypeROs = (adminDashboardIncidentTypes == null || adminDashboardIncidentTypes.isEmpty()) ? Collections.emptyList() : _mapperService.map(adminDashboardIncidentTypes, AdminDashboardIncidentTypeRO.class);
		return adminDashboardIncidentTypeROs;
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR')")
	@Transactional(readOnly = true)
	public List<AdminDashboardIncidentTypeByStatusRO> getAdminDashboardIncidentTypeByStatus() {
		List<AdminDashboardIncidentTypeByStatus> adminDashboardIncidentTypeByStatuses =  _adminDashboardIncidentTypeByStatusService.get();
		List<AdminDashboardIncidentTypeByStatusRO> adminDashboardIncidentTypeByStatusROs = (adminDashboardIncidentTypeByStatuses == null || adminDashboardIncidentTypeByStatuses.isEmpty()) ? Collections.emptyList() : _mapperService.map(adminDashboardIncidentTypeByStatuses, AdminDashboardIncidentTypeByStatusRO.class);
		return adminDashboardIncidentTypeByStatusROs;
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR')")
	@Transactional(readOnly = true)
	public List<CriminalAttackVolumeRO> getCriminalAttackIncidentVolume() {
		List<CriminalAttackVolume> criminalAttackVolumes =  _criminalAttackVolumeService.get();
		List<CriminalAttackVolumeRO> criminalAttackVolumeROs = (criminalAttackVolumes == null || criminalAttackVolumes.isEmpty()) ? Collections.emptyList() : _mapperService.map(criminalAttackVolumes, CriminalAttackVolumeRO.class);
		return criminalAttackVolumeROs;
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR')")
	@Transactional(readOnly = true)
	public List<AssetDamageVolumeRO> getAssetDamageIncidentVolume() {
		List<AssetDamageVolume> assetDamageVolumes =  _assetDamageVolumeService.get();
		List<AssetDamageVolumeRO> assetDamageVolumeROs = (assetDamageVolumes == null || assetDamageVolumes.isEmpty()) ? Collections.emptyList() : _mapperService.map(assetDamageVolumes, AssetDamageVolumeRO.class);
		return assetDamageVolumeROs;
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR')")
	@Transactional(readOnly = true)
	public List<AccidentDamageVolumeRO> getAccidentDamageIncidentVolume() {
		List<AccidentDamageVolume> accidentDamageVolumes =  _accidentDamageVolumeService.get();
		List<AccidentDamageVolumeRO> accidentDamageVolumeROs = (accidentDamageVolumes == null || accidentDamageVolumes.isEmpty()) ? Collections.emptyList() : _mapperService.map(accidentDamageVolumes, AccidentDamageVolumeRO.class);
		return accidentDamageVolumeROs;
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR')")
	@Transactional(readOnly = true)
	public IncidentVolumeByEventTypeRO getIncidentVolumeByEventType() {
		final IncidentVolumeByEventTypeRO incidentVolumeByEventTypeRO = new IncidentVolumeByEventTypeRO(); 
		incidentVolumeByEventTypeRO.setCriminalAttackVolumes(getCriminalAttackIncidentVolume());
		incidentVolumeByEventTypeRO.setAssetDamageVolumes(getAssetDamageIncidentVolume());
		incidentVolumeByEventTypeRO.setAccidentDamageVolumes(getAccidentDamageIncidentVolume());
		return incidentVolumeByEventTypeRO;
	}
}
