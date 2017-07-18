package com.i2g.rms.rest.service.tablemaintenance;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i2g.rms.rest.model.tablemaintenance.AccidentLocationDetailsRO;
import com.i2g.rms.rest.model.tablemaintenance.AccidentLocationRO;
import com.i2g.rms.rest.model.tablemaintenance.AssetCategoryRO;
import com.i2g.rms.rest.model.tablemaintenance.ClaimRequestRegistrationTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.ClaimStatusRO;
import com.i2g.rms.rest.model.tablemaintenance.ClaimTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.DistinguishingFeaturesDetailRO;
import com.i2g.rms.rest.model.tablemaintenance.DistinguishingFeaturesRO;
import com.i2g.rms.rest.model.tablemaintenance.EmployeeTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.EntryPointRO;
import com.i2g.rms.rest.model.tablemaintenance.EventTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.ExternalAgencyRO;
import com.i2g.rms.rest.model.tablemaintenance.IncidentLocationDetailsRO;
import com.i2g.rms.rest.model.tablemaintenance.IncidentLocationRO;
import com.i2g.rms.rest.model.tablemaintenance.IncidentTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.InjuryCauseRO;
import com.i2g.rms.rest.model.tablemaintenance.InjuryTypeDetailsRO;
import com.i2g.rms.rest.model.tablemaintenance.InjuryTypeDetailsSpecRO;
import com.i2g.rms.rest.model.tablemaintenance.InjuryTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.SuspectTypeRO;
import com.i2g.rms.rest.model.tablemaintenance.TableMaintenanceRO;
import com.i2g.rms.rest.model.tablemaintenance.WeaponTypeRO;
import com.i2g.rms.rest.service.AbstractRestService;
import com.i2g.rms.rest.service.RestMessage;
import com.i2g.rms.service.exception.ResourceNotFoundException;
import com.i2g.rms.service.exception.ResourceNotValidException;
import com.i2g.rms.service.tablemaintenance.TableMaintenanceService;
import com.i2g.rms.util.tablemaintenance.TableMaintenanceOperations;

/**
 * Rest services for all table maintenance objects.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class TableMaintenanceRestServiceImpl extends AbstractRestService implements TableMaintenanceRestService {

	@SuppressWarnings("unused")
	private final Logger _logger = LoggerFactory.getLogger(TableMaintenanceRestServiceImpl.class);

	/** Generic service for all table maintenance related operations. */
	@Autowired
	private TableMaintenanceService _tableMaintenanceService;

	/**
	 * Default constructor
	 */
	public TableMaintenanceRestServiceImpl() {}
	
	/** Generic approach */
	@Override
	public List<TableMaintenanceRO> get(final String tableName, final String operation) {
		validateTableName(tableName);
		validateOperation(operation);
		List<TableMaintenanceRO> tableMaintenanceROs = new ArrayList<>();
		List<Object[]> objects = _tableMaintenanceService.get(tableName, operation);
		if (objects != null && !objects.isEmpty()) {
			for (Object[] object : objects) {
				TableMaintenanceRO tableMaintenanceRO = new TableMaintenanceRO();
				tableMaintenanceRO.setCode(object[0].toString());
				tableMaintenanceRO.setDescription(object[1].toString());
				tableMaintenanceROs.add(tableMaintenanceRO);
			}
		}		
		return tableMaintenanceROs;
	}	

	@Override
	public TableMaintenanceRO getByCode(String tableName, String operation, String code) {
		validateTableName(tableName);
		validateOperation(operation);
		validateCode(code);
		TableMaintenanceRO tableMaintenanceRO = new TableMaintenanceRO();
		List<Object[]> objects = _tableMaintenanceService.getByCode(tableName, operation, code);
		if (objects != null) {
			Object[] object = objects.get(0);
			tableMaintenanceRO.setCode(object[0].toString());
			tableMaintenanceRO.setDescription(object[1].toString());
		} else {
			throw new ResourceNotFoundException(_messageBuilder.build(RestMessage.OBJECT_ARRAY_NULL_OR_EMPTY));
		}
		return tableMaintenanceRO;
	}

	@Override
	@Transactional
	public TableMaintenanceRO create(TableMaintenanceRO tableMaintenanceRO, String tableName, String operation) {
		TableMaintenanceRO tableMaintenanceRONew = null;
		validateTableName(tableName);
		validateOperation(operation);
		validateObject(tableMaintenanceRO);
		String code = tableMaintenanceRO.getCode();
		String description = tableMaintenanceRO.getDescription();
		validateCode(code);
		validateDescription(description);
		int result = _tableMaintenanceService.create(tableName, operation, code, description);
		if (result > 0) {
			List<Object[]> objects = _tableMaintenanceService.getByCode(tableName, TableMaintenanceOperations.GET_BY_CODE.value, code);
			if (objects != null) {
				tableMaintenanceRONew = new TableMaintenanceRO();
				Object[] object = objects.get(0);
				tableMaintenanceRONew.setCode(object[0].toString());
				tableMaintenanceRONew.setDescription(object[1].toString());
			} else {
				throw new ResourceNotFoundException(_messageBuilder.build(RestMessage.OBJECT_ARRAY_NULL_OR_EMPTY));
			}
		} else {
			throw new ResourceNotFoundException(_messageBuilder.build(RestMessage.CREATE_TABLE_MAINTENANCE_RECORD_FAILED));
		}
		return tableMaintenanceRONew;
	}

	@Override
	@Transactional
	public TableMaintenanceRO update(TableMaintenanceRO tableMaintenanceRO, String tableName, String operation) {
		TableMaintenanceRO tableMaintenanceRONew = null;
		validateTableName(tableName);
		validateOperation(operation);
		validateObject(tableMaintenanceRO);
		String code = tableMaintenanceRO.getCode();
		String description = tableMaintenanceRO.getDescription();
		validateCode(code);
		validateDescription(description);
		int result = _tableMaintenanceService.update(tableName, operation, code, description);
		if (result > 0) {
			List<Object[]> objects = _tableMaintenanceService.getByCode(tableName, TableMaintenanceOperations.GET_BY_CODE.value, code);
			if (objects != null) {
				tableMaintenanceRONew = new TableMaintenanceRO();
				Object[] object = objects.get(0);
				tableMaintenanceRONew.setCode(object[0].toString());
				tableMaintenanceRONew.setDescription(object[1].toString());
			} else {
				throw new ResourceNotFoundException(_messageBuilder.build(RestMessage.OBJECT_ARRAY_NULL_OR_EMPTY));
			}
		} else {
			throw new ResourceNotFoundException(_messageBuilder.build(RestMessage.CREATE_TABLE_MAINTENANCE_RECORD_FAILED));
		}
		return tableMaintenanceRONew;
	}

	@Override
	@Transactional
	public void delete(TableMaintenanceRO tableMaintenanceRO, String tableName, String operation) {
		validateTableName(tableName);
		validateOperation(operation);
		validateObject(tableMaintenanceRO);
		List<String> codes = tableMaintenanceRO.getCodes();		
		if (codes == null || codes.isEmpty()) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.CODE_NULL_OR_EMPTY));
		}
		int result = _tableMaintenanceService.delete(tableName, operation, codes);
		if (result <= 0 ) {
			throw new ResourceNotFoundException(_messageBuilder.build(RestMessage.DELETE_OPERATION_FAILED));
		}
		if (result != codes.size()) {
			throw new ResourceNotFoundException(_messageBuilder.build(RestMessage.NOT_ALL_RECORDS_WERE_DELETED));
		}		
	}
	
	/** Methods related to Accident Location */
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	@Transactional
	public List<AccidentLocationRO> getAccidentLocations() {
		return _mapperService.map(_tableMaintenanceService.getAccidentLocations(), AccidentLocationRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	@Transactional
	public AccidentLocationRO getAccidentLocationByCode(final String code) {
		validateCode(code);
		return _mapperService.map(_tableMaintenanceService.getAccidentLocationByCode(code), AccidentLocationRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@Transactional
	public AccidentLocationRO createAccidentLocation(final AccidentLocationRO accidentLocationRO) {
		validateObject(accidentLocationRO);
		validateCode(accidentLocationRO.getId());
		validateDescription(accidentLocationRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.createAccidentLocation(accidentLocationRO.getId(), accidentLocationRO.getDescription()), AccidentLocationRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@Transactional
	public AccidentLocationRO updateAccidentLocation(final AccidentLocationRO accidentLocationRO) {
		validateObject(accidentLocationRO);
		validateCode(accidentLocationRO.getId());
		validateDescription(accidentLocationRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.updateAccidentLocation(accidentLocationRO.getId(), accidentLocationRO.getDescription()), AccidentLocationRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@Transactional
	public void deleteAccidentLocation(final String code) {
		// Validate input parameter(s) if any
		validateCode(code);
		_tableMaintenanceService.deleteAccidentLocation(code);
	}
	
	/** Methods related to Accident Location Details */
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	@Transactional
	public List<AccidentLocationDetailsRO> getAccidentLocationDetails() {
		return _mapperService.map(_tableMaintenanceService.getAccidentLocationDetails(), AccidentLocationDetailsRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	@Transactional
	public AccidentLocationDetailsRO getAccidentLocationDetailsByCode(final String code) {
		validateCode(code);
		return _mapperService.map(_tableMaintenanceService.getAccidentLocationDetailsByCode(code), AccidentLocationDetailsRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@Transactional
	public AccidentLocationDetailsRO createAccidentLocationDetails(final AccidentLocationDetailsRO accidentLocationDetailsRO) {
		validateObject(accidentLocationDetailsRO);
		validateCode(accidentLocationDetailsRO.getId());
		validateDescription(accidentLocationDetailsRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.createAccidentLocationDetails(accidentLocationDetailsRO.getId(), accidentLocationDetailsRO.getDescription()), AccidentLocationDetailsRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@Transactional
	public AccidentLocationDetailsRO updateAccidentLocationDetails(final AccidentLocationDetailsRO accidentLocationDetailsRO) {
		validateObject(accidentLocationDetailsRO);
		validateCode(accidentLocationDetailsRO.getId());
		validateDescription(accidentLocationDetailsRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.updateAccidentLocationDetails(accidentLocationDetailsRO.getId(), accidentLocationDetailsRO.getDescription()), AccidentLocationDetailsRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@Transactional
	public void deleteAccidentLocationDetails(final String code) {
		// Validate input parameter(s) if any
		validateCode(code);
		_tableMaintenanceService.deleteAccidentLocationDetails(code);
	}
	
	/** Methods related to Asset Category */
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public List<AssetCategoryRO> getAssetCategories() {
		return _mapperService.map(_tableMaintenanceService.getAssetCategories(), AssetCategoryRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public AssetCategoryRO getAssetCategoryByCode(final String code) {
		validateCode(code);
		return _mapperService.map(_tableMaintenanceService.getAssetCategoryByCode(code), AssetCategoryRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public AssetCategoryRO createAssetCategory(final AssetCategoryRO assetCategoryRO) {
		validateObject(assetCategoryRO);
		validateCode(assetCategoryRO.getId());
		validateDescription(assetCategoryRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.createAssetCategory(assetCategoryRO.getId(), assetCategoryRO.getDescription()), AssetCategoryRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public AssetCategoryRO updateAssetCategory(final AssetCategoryRO assetCategoryRO) {
		validateObject(assetCategoryRO);
		validateCode(assetCategoryRO.getId());
		validateDescription(assetCategoryRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.updateAssetCategory(assetCategoryRO.getId(), assetCategoryRO.getDescription()), AssetCategoryRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public void deleteAssetCategory(final String code) {
		// Validate input parameter(s) if any
		validateCode(code);
		_tableMaintenanceService.deleteAssetCategory(code);
	}
	
	/** Methods related to Claim Request Registration Type */
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public List<ClaimRequestRegistrationTypeRO> getClaimRequestRegistrationTypes() {
		return _mapperService.map(_tableMaintenanceService.getClaimRequestRegistrationTypes(), ClaimRequestRegistrationTypeRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public ClaimRequestRegistrationTypeRO getClaimRequestRegistrationTypeByCode(final String code) {
		validateCode(code);
		return _mapperService.map(_tableMaintenanceService.getClaimRequestRegistrationTypeByCode(code), ClaimRequestRegistrationTypeRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public ClaimRequestRegistrationTypeRO createClaimRequestRegistrationType(final ClaimRequestRegistrationTypeRO claimRequestRegistrationTypeRO) {
		validateObject(claimRequestRegistrationTypeRO);
		validateCode(claimRequestRegistrationTypeRO.getId());
		validateDescription(claimRequestRegistrationTypeRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.createClaimRequestRegistrationType(claimRequestRegistrationTypeRO.getId(), claimRequestRegistrationTypeRO.getDescription()), ClaimRequestRegistrationTypeRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public ClaimRequestRegistrationTypeRO updateClaimRequestRegistrationType(final ClaimRequestRegistrationTypeRO claimRequestRegistrationTypeRO) {
		validateObject(claimRequestRegistrationTypeRO);
		validateCode(claimRequestRegistrationTypeRO.getId());
		validateDescription(claimRequestRegistrationTypeRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.updateClaimRequestRegistrationType(claimRequestRegistrationTypeRO.getId(), claimRequestRegistrationTypeRO.getDescription()), ClaimRequestRegistrationTypeRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public void deleteClaimRequestRegistrationType(final String code) {
		// Validate input parameter(s) if any
		validateCode(code);
		_tableMaintenanceService.deleteClaimRequestRegistrationType(code);
	}
	
	/** Methods related to Claim Status */
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public List<ClaimStatusRO> getClaimStatuses() {
		return _mapperService.map(_tableMaintenanceService.getClaimStatuses(), ClaimStatusRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public ClaimStatusRO getClaimStatusByCode(final String code) {
		validateCode(code);
		return _mapperService.map(_tableMaintenanceService.getClaimStatusByCode(code), ClaimStatusRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public ClaimStatusRO createClaimStatus(final ClaimStatusRO claimStatusRO) {
		validateObject(claimStatusRO);
		validateCode(claimStatusRO.getId());
		validateDescription(claimStatusRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.createClaimStatus(claimStatusRO.getId(), claimStatusRO.getDescription()), ClaimStatusRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public ClaimStatusRO updateClaimStatus(final ClaimStatusRO claimStatusRO) {
		validateObject(claimStatusRO);
		validateCode(claimStatusRO.getId());
		validateDescription(claimStatusRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.updateClaimStatus(claimStatusRO.getId(), claimStatusRO.getDescription()), ClaimStatusRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public void deleteClaimStatus(final String code) {
		// Validate input parameter(s) if any
		validateCode(code);
		_tableMaintenanceService.deleteClaimStatus(code);
	}
	
	/** Methods related to Claim Type */
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public List<ClaimTypeRO> getClaimTypes() {
		return _mapperService.map(_tableMaintenanceService.getClaimTypes(), ClaimTypeRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public ClaimTypeRO getClaimTypeByCode(final String code) {
		validateCode(code);
		return _mapperService.map(_tableMaintenanceService.getClaimTypeByCode(code), ClaimTypeRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public ClaimTypeRO createClaimType(final ClaimTypeRO claimTypeRO) {
		validateObject(claimTypeRO);
		validateCode(claimTypeRO.getId());
		validateDescription(claimTypeRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.createClaimType(claimTypeRO.getId(), claimTypeRO.getDescription()), ClaimTypeRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public ClaimTypeRO updateClaimType(final ClaimTypeRO claimTypeRO) {
		validateObject(claimTypeRO);
		validateCode(claimTypeRO.getId());
		validateDescription(claimTypeRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.updateClaimType(claimTypeRO.getId(), claimTypeRO.getDescription()), ClaimTypeRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public void deleteClaimType(final String code) {
		// Validate input parameter(s) if any
		validateCode(code);
		_tableMaintenanceService.deleteClaimType(code);
	}
	
	/** Methods related to Distinguishing Features */
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	@Transactional
	public List<DistinguishingFeaturesRO> getDistinguishingFeatures() {
		return _mapperService.map(_tableMaintenanceService.getDistinguishingFeatures(), DistinguishingFeaturesRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	@Transactional
	public DistinguishingFeaturesRO getDistinguishingFeaturesByCode(final String code) {
		validateCode(code);
		return _mapperService.map(_tableMaintenanceService.getDistinguishingFeaturesByCode(code), DistinguishingFeaturesRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@Transactional
	public DistinguishingFeaturesRO createDistinguishingFeatures(final DistinguishingFeaturesRO distinguishingFeaturesRO) {
		validateObject(distinguishingFeaturesRO);
		validateCode(distinguishingFeaturesRO.getId());
		validateDescription(distinguishingFeaturesRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.createDistinguishingFeatures(distinguishingFeaturesRO.getId(), distinguishingFeaturesRO.getDescription()), DistinguishingFeaturesRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@Transactional
	public DistinguishingFeaturesRO updateDistinguishingFeatures(final DistinguishingFeaturesRO distinguishingFeaturesRO) {
		validateObject(distinguishingFeaturesRO);
		validateCode(distinguishingFeaturesRO.getId());
		validateDescription(distinguishingFeaturesRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.updateDistinguishingFeatures(distinguishingFeaturesRO.getId(), distinguishingFeaturesRO.getDescription()), DistinguishingFeaturesRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@Transactional
	public void deleteDistinguishingFeatures(final String code) {
		// Validate input parameter(s) if any
		validateCode(code);
		_tableMaintenanceService.deleteDistinguishingFeatures(code);
	}
	
	/** Methods related to Distinguishing Features Detail */
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	@Transactional
	public List<DistinguishingFeaturesDetailRO> getDistinguishingFeaturesDetails() {
		return _mapperService.map(_tableMaintenanceService.getDistinguishingFeaturesDetails(), DistinguishingFeaturesDetailRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	@Transactional
	public DistinguishingFeaturesDetailRO getDistinguishingFeaturesDetailByCode(final String code) {
		validateCode(code);
		return _mapperService.map(_tableMaintenanceService.getDistinguishingFeaturesDetailByCode(code), DistinguishingFeaturesDetailRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@Transactional
	public DistinguishingFeaturesDetailRO createDistinguishingFeaturesDetail(final DistinguishingFeaturesDetailRO distinguishingFeaturesDetailRO) {
		validateObject(distinguishingFeaturesDetailRO);
		validateCode(distinguishingFeaturesDetailRO.getId());
		validateDescription(distinguishingFeaturesDetailRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.createDistinguishingFeaturesDetail(distinguishingFeaturesDetailRO.getId(), distinguishingFeaturesDetailRO.getDescription()), DistinguishingFeaturesDetailRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@Transactional
	public DistinguishingFeaturesDetailRO updateDistinguishingFeaturesDetail(final DistinguishingFeaturesDetailRO distinguishingFeaturesDetailRO) {
		validateObject(distinguishingFeaturesDetailRO);
		validateCode(distinguishingFeaturesDetailRO.getId());
		validateDescription(distinguishingFeaturesDetailRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.updateDistinguishingFeaturesDetail(distinguishingFeaturesDetailRO.getId(), distinguishingFeaturesDetailRO.getDescription()), DistinguishingFeaturesDetailRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@Transactional
	public void deleteDistinguishingFeaturesDetail(final String code) {
		// Validate input parameter(s) if any
		validateCode(code);
		_tableMaintenanceService.deleteDistinguishingFeaturesDetail(code);
	}
	
	/** Methods related to Employee Type */
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public List<EmployeeTypeRO> getEmployeeTypes() {
		return _mapperService.map(_tableMaintenanceService.getEmployeeTypes(), EmployeeTypeRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public EmployeeTypeRO getEmployeeTypeByCode(final String code) {
		validateCode(code);
		return _mapperService.map(_tableMaintenanceService.getEmployeeTypeByCode(code), EmployeeTypeRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public EmployeeTypeRO createEmployeeType(final EmployeeTypeRO employeeTypeRO) {
		validateObject(employeeTypeRO);
		validateCode(employeeTypeRO.getId());
		validateDescription(employeeTypeRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.createEmployeeType(employeeTypeRO.getId(), employeeTypeRO.getDescription()), EmployeeTypeRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public EmployeeTypeRO updateEmployeeType(final EmployeeTypeRO employeeTypeRO) {
		validateObject(employeeTypeRO);
		validateCode(employeeTypeRO.getId());
		validateDescription(employeeTypeRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.updateEmployeeType(employeeTypeRO.getId(), employeeTypeRO.getDescription()), EmployeeTypeRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public void deleteEmployeeType(final String code) {
		// Validate input parameter(s) if any
		validateCode(code);
		_tableMaintenanceService.deleteEmployeeType(code);
	}
	
	/** Methods related to Entry Point */
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public List<EntryPointRO> getEntryPoints() {
		return _mapperService.map(_tableMaintenanceService.getEntryPoints(), EntryPointRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public EntryPointRO getEntryPointByCode(final String code) {
		validateCode(code);
		return _mapperService.map(_tableMaintenanceService.getEntryPointByCode(code), EntryPointRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public EntryPointRO createEntryPoint(final EntryPointRO entryPointRO) {
		validateObject(entryPointRO);
		validateCode(entryPointRO.getId());
		validateDescription(entryPointRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.createEntryPoint(entryPointRO.getId(), entryPointRO.getDescription()), EntryPointRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public EntryPointRO updateEntryPoint(final EntryPointRO entryPointRO) {
		validateObject(entryPointRO);
		validateCode(entryPointRO.getId());
		validateDescription(entryPointRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.updateEntryPoint(entryPointRO.getId(), entryPointRO.getDescription()), EntryPointRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public void deleteEntryPoint(final String code) {
		// Validate input parameter(s) if any
		validateCode(code);
		_tableMaintenanceService.deleteEntryPoint(code);
	}
	
	/** Methods related to Event Type */
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public List<EventTypeRO> getEventTypes() {
		return _mapperService.map(_tableMaintenanceService.getEventTypes(), EventTypeRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public EventTypeRO getEventTypeByCode(final String code) {
		validateCode(code);
		return _mapperService.map(_tableMaintenanceService.getEventTypeByCode(code), EventTypeRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public EventTypeRO createEventType(final EventTypeRO eventTypeRO) {
		validateObject(eventTypeRO);
		validateCode(eventTypeRO.getId());
		validateDescription(eventTypeRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.createEventType(eventTypeRO.getId(), eventTypeRO.getDescription()), EventTypeRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public EventTypeRO updateEventType(final EventTypeRO eventTypeRO) {
		validateObject(eventTypeRO);
		validateCode(eventTypeRO.getId());
		validateDescription(eventTypeRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.updateEventType(eventTypeRO.getId(), eventTypeRO.getDescription()), EventTypeRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public void deleteEventType(final String code) {
		// Validate input parameter(s) if any
		validateCode(code);
		_tableMaintenanceService.deleteEventType(code);
	}
	
	/** Methods related to External Agency */
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public List<ExternalAgencyRO> getExternalAgencies() {
		return _mapperService.map(_tableMaintenanceService.getExternalAgencies(), ExternalAgencyRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public ExternalAgencyRO getExternalAgencyByCode(final String code) {
		validateCode(code);
		return _mapperService.map(_tableMaintenanceService.getExternalAgencyByCode(code), ExternalAgencyRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public ExternalAgencyRO createExternalAgency(final ExternalAgencyRO externalAgencyRO) {
		validateObject(externalAgencyRO);
		validateCode(externalAgencyRO.getId());
		validateDescription(externalAgencyRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.createExternalAgency(externalAgencyRO.getId(), externalAgencyRO.getDescription()), ExternalAgencyRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public ExternalAgencyRO updateExternalAgency(final ExternalAgencyRO externalAgencyRO) {
		validateObject(externalAgencyRO);
		validateCode(externalAgencyRO.getId());
		validateDescription(externalAgencyRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.updateExternalAgency(externalAgencyRO.getId(), externalAgencyRO.getDescription()), ExternalAgencyRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public void deleteExternalAgency(final String code) {
		// Validate input parameter(s) if any
		validateCode(code);
		_tableMaintenanceService.deleteExternalAgency(code);
	}
	
	/** Methods related to Incident Location */
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	@Transactional
	public List<IncidentLocationRO> getIncidentLocations() {
		return _mapperService.map(_tableMaintenanceService.getIncidentLocations(), IncidentLocationRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	@Transactional
	public IncidentLocationRO getIncidentLocationByCode(final String code) {
		validateCode(code);
		return _mapperService.map(_tableMaintenanceService.getIncidentLocationByCode(code), IncidentLocationRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@Transactional
	public IncidentLocationRO createIncidentLocation(final IncidentLocationRO incidentLocationRO) {
		validateObject(incidentLocationRO);
		validateCode(incidentLocationRO.getId());
		validateDescription(incidentLocationRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.createIncidentLocation(incidentLocationRO.getId(), incidentLocationRO.getDescription()), IncidentLocationRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@Transactional
	public IncidentLocationRO updateIncidentLocation(final IncidentLocationRO incidentLocationRO) {
		validateObject(incidentLocationRO);
		validateCode(incidentLocationRO.getId());
		validateDescription(incidentLocationRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.updateIncidentLocation(incidentLocationRO.getId(), incidentLocationRO.getDescription()), IncidentLocationRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@Transactional
	public void deleteIncidentLocation(final String code) {
		// Validate input parameter(s) if any
		validateCode(code);
		_tableMaintenanceService.deleteIncidentLocation(code);
	}
	
	/** Methods related to Incident Location Detail */
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	@Transactional
	public List<IncidentLocationDetailsRO> getIncidentLocationDetails() {
		return _mapperService.map(_tableMaintenanceService.getIncidentLocationDetails(), IncidentLocationDetailsRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	@Transactional
	public IncidentLocationDetailsRO getIncidentLocationDetailsByCode(final String code) {
		validateCode(code);
		return _mapperService.map(_tableMaintenanceService.getIncidentLocationDetailsByCode(code), IncidentLocationDetailsRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@Transactional
	public IncidentLocationDetailsRO createIncidentLocationDetails(final IncidentLocationDetailsRO incidentLocationDetailsRO) {
		validateObject(incidentLocationDetailsRO);
		validateCode(incidentLocationDetailsRO.getId());
		validateDescription(incidentLocationDetailsRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.createIncidentLocationDetails(incidentLocationDetailsRO.getId(), incidentLocationDetailsRO.getDescription()), IncidentLocationDetailsRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@Transactional
	public IncidentLocationDetailsRO updateIncidentLocationDetails(final IncidentLocationDetailsRO incidentLocationDetailsRO) {
		validateObject(incidentLocationDetailsRO);
		validateCode(incidentLocationDetailsRO.getId());
		validateDescription(incidentLocationDetailsRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.updateIncidentLocationDetails(incidentLocationDetailsRO.getId(), incidentLocationDetailsRO.getDescription()), IncidentLocationDetailsRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@Transactional
	public void deleteIncidentLocationDetails(final String code) {
		// Validate input parameter(s) if any
		validateCode(code);
		_tableMaintenanceService.deleteIncidentLocationDetails(code);
	}
	
	/** Methods related to Incident Type */
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public List<IncidentTypeRO> getIncidentTypes() {
		return _mapperService.map(_tableMaintenanceService.getIncidentTypes(), IncidentTypeRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public IncidentTypeRO getIncidentTypeByCode(final String code) {
		validateCode(code);
		return _mapperService.map(_tableMaintenanceService.getIncidentTypeByCode(code), IncidentTypeRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public IncidentTypeRO createIncidentType(final IncidentTypeRO incidentTypeRO) {
		validateObject(incidentTypeRO);
		validateCode(incidentTypeRO.getId());
		validateDescription(incidentTypeRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.createIncidentType(incidentTypeRO.getId(), incidentTypeRO.getDescription()), IncidentTypeRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public IncidentTypeRO updateIncidentType(final IncidentTypeRO incidentTypeRO) {
		validateObject(incidentTypeRO);
		validateCode(incidentTypeRO.getId());
		validateDescription(incidentTypeRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.updateIncidentType(incidentTypeRO.getId(), incidentTypeRO.getDescription()), IncidentTypeRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public void deleteIncidentType(final String code) {
		// Validate input parameter(s) if any
		validateCode(code);
		_tableMaintenanceService.deleteIncidentType(code);
	}
	
	/** Methods related to Injury Cause */
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public List<InjuryCauseRO> getInjuryCauses() {
		return _mapperService.map(_tableMaintenanceService.getInjuryCauses(), InjuryCauseRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public InjuryCauseRO getInjuryCauseByCode(final String code) {
		validateCode(code);
		return _mapperService.map(_tableMaintenanceService.getInjuryCauseByCode(code), InjuryCauseRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public InjuryCauseRO createInjuryCause(final InjuryCauseRO injuryCauseRO) {
		validateObject(injuryCauseRO);
		validateCode(injuryCauseRO.getId());
		validateDescription(injuryCauseRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.createInjuryCause(injuryCauseRO.getId(), injuryCauseRO.getDescription()), InjuryCauseRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public InjuryCauseRO updateInjuryCause(final InjuryCauseRO injuryCauseRO) {
		validateObject(injuryCauseRO);
		validateCode(injuryCauseRO.getId());
		validateDescription(injuryCauseRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.updateInjuryCause(injuryCauseRO.getId(), injuryCauseRO.getDescription()), InjuryCauseRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public void deleteInjuryCause(final String code) {
		// Validate input parameter(s) if any
		validateCode(code);
		_tableMaintenanceService.deleteInjuryCause(code);
	}
	
	/** Methods related to Injury Type */
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	@Transactional
	public List<InjuryTypeRO> getInjuryTypes() {
		return _mapperService.map(_tableMaintenanceService.getInjuryTypes(), InjuryTypeRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	@Transactional
	public InjuryTypeRO getInjuryTypeByCode(final String code) {
		validateCode(code);
		return _mapperService.map(_tableMaintenanceService.getInjuryTypeByCode(code), InjuryTypeRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@Transactional
	public InjuryTypeRO createInjuryType(final InjuryTypeRO injuryTypeRO) {
		validateObject(injuryTypeRO);
		validateCode(injuryTypeRO.getId());
		validateDescription(injuryTypeRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.createInjuryType(injuryTypeRO.getId(), injuryTypeRO.getDescription()), InjuryTypeRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@Transactional
	public InjuryTypeRO updateInjuryType(final InjuryTypeRO injuryTypeRO) {
		validateObject(injuryTypeRO);
		validateCode(injuryTypeRO.getId());
		validateDescription(injuryTypeRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.updateInjuryType(injuryTypeRO.getId(), injuryTypeRO.getDescription()), InjuryTypeRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@Transactional
	public void deleteInjuryType(final String code) {
		// Validate input parameter(s) if any
		validateCode(code);
		_tableMaintenanceService.deleteInjuryType(code);
	}
	
	/** Methods related to Injury Type Details */
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	@Transactional
	public List<InjuryTypeDetailsRO> getInjuryTypeDetails() {
		return _mapperService.map(_tableMaintenanceService.getInjuryTypeDetails(), InjuryTypeDetailsRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	@Transactional
	public InjuryTypeDetailsRO getInjuryTypeDetailsByCode(final String code) {
		validateCode(code);
		return _mapperService.map(_tableMaintenanceService.getInjuryTypeDetailsByCode(code), InjuryTypeDetailsRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@Transactional
	public InjuryTypeDetailsRO createInjuryTypeDetails(final InjuryTypeDetailsRO injuryTypeDetailsRO) {
		validateObject(injuryTypeDetailsRO);
		validateCode(injuryTypeDetailsRO.getId());
		validateDescription(injuryTypeDetailsRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.createInjuryTypeDetails(injuryTypeDetailsRO.getId(), injuryTypeDetailsRO.getDescription()), InjuryTypeDetailsRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@Transactional
	public InjuryTypeDetailsRO updateInjuryTypeDetails(final InjuryTypeDetailsRO injuryTypeDetailsRO) {
		validateObject(injuryTypeDetailsRO);
		validateCode(injuryTypeDetailsRO.getId());
		validateDescription(injuryTypeDetailsRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.updateInjuryTypeDetails(injuryTypeDetailsRO.getId(), injuryTypeDetailsRO.getDescription()), InjuryTypeDetailsRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@Transactional
	public void deleteInjuryTypeDetails(final String code) {
		// Validate input parameter(s) if any
		validateCode(code);
		_tableMaintenanceService.deleteInjuryTypeDetails(code);
	}
	
	/** Methods related to Injury Type Details Specification */
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	@Transactional
	public List<InjuryTypeDetailsSpecRO> getInjuryTypeDetailsSpecs() {
		return _mapperService.map(_tableMaintenanceService.getInjuryTypeDetailsSpecs(), InjuryTypeDetailsSpecRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	@Transactional
	public InjuryTypeDetailsSpecRO getInjuryTypeDetailsSpecByCode(final String code) {
		validateCode(code);
		return _mapperService.map(_tableMaintenanceService.getInjuryTypeDetailsSpecByCode(code), InjuryTypeDetailsSpecRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@Transactional
	public InjuryTypeDetailsSpecRO createInjuryTypeDetailsSpec(final InjuryTypeDetailsSpecRO injuryTypeDetailsSpecRO) {
		validateObject(injuryTypeDetailsSpecRO);
		validateCode(injuryTypeDetailsSpecRO.getId());
		validateDescription(injuryTypeDetailsSpecRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.createInjuryTypeDetailsSpec(injuryTypeDetailsSpecRO.getId(), injuryTypeDetailsSpecRO.getDescription()), InjuryTypeDetailsSpecRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@Transactional
	public InjuryTypeDetailsSpecRO updateInjuryTypeDetailsSpec(final InjuryTypeDetailsSpecRO injuryTypeDetailsSpecRO) {
		validateObject(injuryTypeDetailsSpecRO);
		validateCode(injuryTypeDetailsSpecRO.getId());
		validateDescription(injuryTypeDetailsSpecRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.updateInjuryTypeDetailsSpec(injuryTypeDetailsSpecRO.getId(), injuryTypeDetailsSpecRO.getDescription()), InjuryTypeDetailsSpecRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@Transactional
	public void deleteInjuryTypeDetailsSpec(final String code) {
		// Validate input parameter(s) if any
		validateCode(code);
		_tableMaintenanceService.deleteInjuryTypeDetailsSpec(code);
	}
	
	/** Methods related to Suspect Type */
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public List<SuspectTypeRO> getSuspectTypes() {
		return _mapperService.map(_tableMaintenanceService.getSuspectTypes(), SuspectTypeRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public SuspectTypeRO getSuspectTypeByCode(final String code) {
		validateCode(code);
		return _mapperService.map(_tableMaintenanceService.getSuspectTypeByCode(code), SuspectTypeRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public SuspectTypeRO createSuspectType(final SuspectTypeRO suspectTypeRO) {
		validateObject(suspectTypeRO);
		validateCode(suspectTypeRO.getId());
		validateDescription(suspectTypeRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.createSuspectType(suspectTypeRO.getId(), suspectTypeRO.getDescription()), SuspectTypeRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public SuspectTypeRO updateSuspectType(final SuspectTypeRO suspectTypeRO) {
		validateObject(suspectTypeRO);
		validateCode(suspectTypeRO.getId());
		validateDescription(suspectTypeRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.updateSuspectType(suspectTypeRO.getId(), suspectTypeRO.getDescription()), SuspectTypeRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public void deleteSuspectType(final String code) {
		// Validate input parameter(s) if any
		validateCode(code);
		_tableMaintenanceService.deleteSuspectType(code);
	}
	
	/** Methods related to Weapon Involved */
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public List<WeaponTypeRO> getWeaponTypes() {
		return _mapperService.map(_tableMaintenanceService.getWeaponTypes(), WeaponTypeRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public WeaponTypeRO getWeaponTypeByCode(final String code) {
		validateCode(code);
		return _mapperService.map(_tableMaintenanceService.getWeaponTypeByCode(code), WeaponTypeRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public WeaponTypeRO createWeaponType(final WeaponTypeRO weaponInvolvedRO) {
		validateObject(weaponInvolvedRO);
		validateCode(weaponInvolvedRO.getId());
		validateDescription(weaponInvolvedRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.createWeaponType(weaponInvolvedRO.getId(), weaponInvolvedRO.getDescription()), WeaponTypeRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public WeaponTypeRO updateWeaponType(final WeaponTypeRO weaponInvolvedRO) {
		validateObject(weaponInvolvedRO);
		validateCode(weaponInvolvedRO.getId());
		validateDescription(weaponInvolvedRO.getDescription());
		return _mapperService.map(_tableMaintenanceService.updateWeaponType(weaponInvolvedRO.getId(), weaponInvolvedRO.getDescription()), WeaponTypeRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public void deleteWeaponType(final String code) {
		// Validate input parameter(s) if any
		validateCode(code);
		_tableMaintenanceService.deleteWeaponType(code);
	}
}
