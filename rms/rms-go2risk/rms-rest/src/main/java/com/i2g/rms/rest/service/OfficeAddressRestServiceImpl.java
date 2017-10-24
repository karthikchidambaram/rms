package com.i2g.rms.rest.service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i2g.rms.domain.model.OfficeAddress;
import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.rest.model.OfficeAddressRO;
import com.i2g.rms.service.OfficeAddressService;
import com.i2g.rms.service.exception.ResourceNotCreatedException;
import com.i2g.rms.service.tablemaintenance.TableMaintenanceService;

/**
 * Rest services for Office address rest controller.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class OfficeAddressRestServiceImpl extends AbstractRestService implements OfficeAddressRestService {

	@SuppressWarnings("unused")
	private final Logger _logger = LoggerFactory.getLogger(OfficeAddressRestServiceImpl.class);

	@Autowired
	private OfficeAddressService _officeAddressService;
	@Autowired
	private TableMaintenanceService _tableMaintenanceService;

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public List<OfficeAddressRO> get() {
		List<OfficeAddress> officeAddresses = _officeAddressService.get();
		List<OfficeAddressRO> officeAddressROs = officeAddresses.isEmpty() ? Collections.emptyList()
				: _mapperService.map(officeAddresses, OfficeAddressRO.class);
		return officeAddressROs;
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public OfficeAddressRO get(final long id) {
		if (id > 0) {
			final OfficeAddress officeAddress = _officeAddressService.get(id);
			validateGenericObject(officeAddress);
			return _mapperService.map(officeAddress, OfficeAddressRO.class);
		} else {
			return null;
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public OfficeAddressRO create(final OfficeAddressRO officeAddressRO) {
		// Validate input param (object)
		validateObject(officeAddressRO);

		// Construct new address object
		final OfficeAddress officeAddress = new OfficeAddress.Builder().setStatusFlag(StatusFlag.ACTIVE).build();

		// Validate the newly created object
		validateGenericObject(officeAddress);

		// Set if the address is for an organization
		if (officeAddressRO.getOrganization() != null) {
			if (officeAddressRO.getOrganization().getId() != null && !officeAddressRO.getOrganization().getId().trim().isEmpty()) {
				officeAddress.setOrganization(_tableMaintenanceService.getOrganizationByCode(officeAddressRO.getOrganization().getId().trim()));
			}
		}
		// Set other fields if necessary
		if (officeAddressRO.getOrganizationName() != null && !officeAddressRO.getOrganizationName().trim().isEmpty()) {
			officeAddress.setOrganizationName(officeAddressRO.getOrganizationName());
		}
		if (officeAddressRO.getBuildingName() != null && !officeAddressRO.getBuildingName().trim().isEmpty()) {
			officeAddress.setBuildingName(officeAddressRO.getBuildingName());
		}
		if (officeAddressRO.getStreetName() != null && !officeAddressRO.getStreetName().trim().isEmpty()) {
			officeAddress.setStreetName(officeAddressRO.getStreetName());
		}
		if (officeAddressRO.getLocalityName() != null && !officeAddressRO.getLocalityName().trim().isEmpty()) {
			officeAddress.setLocalityName(officeAddressRO.getLocalityName());
		}
		if (officeAddressRO.getPostTown() != null && !officeAddressRO.getPostTown().trim().isEmpty()) {
			officeAddress.setPostTown(officeAddressRO.getPostTown());
		}
		if (officeAddressRO.getCounty() != null && !officeAddressRO.getCounty().trim().isEmpty()) {
			officeAddress.setCounty(officeAddressRO.getCounty());
		}
		if (officeAddressRO.getCity() != null && !officeAddressRO.getCity().trim().isEmpty()) {
			officeAddress.setCity(officeAddressRO.getCity());
		}
		if (officeAddressRO.getPostcode() != null && !officeAddressRO.getPostcode().trim().isEmpty()) {
			officeAddress.setPostcode(officeAddressRO.getPostcode());
		}
		if (officeAddressRO.getCountry() != null && !officeAddressRO.getCountry().trim().isEmpty()) {
			officeAddress.setCountry(officeAddressRO.getCountry());
		}
		if (officeAddressRO.getDoorNumber() != null && !officeAddressRO.getDoorNumber().trim().isEmpty()) {
			officeAddress.setDoorNumber(officeAddressRO.getDoorNumber().trim());
		}
		if (officeAddressRO.getBlockNumber() != null && !officeAddressRO.getBlockNumber().trim().isEmpty()) {
			officeAddress.setBlockNumber(officeAddressRO.getBlockNumber().trim());
		}

		// Save
		OfficeAddress newOfficeAddress = _officeAddressService.create(officeAddress);

		if (newOfficeAddress != null) {
			return _mapperService.map(newOfficeAddress, OfficeAddressRO.class);
		} else {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CREATE_RECORD));
		}
	}
}
