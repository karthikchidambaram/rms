package com.i2g.rms.rest.service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i2g.rms.domain.model.Address;
import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.domain.model.User;
import com.i2g.rms.rest.model.AddressRO;
import com.i2g.rms.service.AddressService;
import com.i2g.rms.service.UserService;
import com.i2g.rms.service.exception.ResourceNotCreatedException;

/**
 * Rest services for role rest controller.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class AddressRestServiceImpl extends AbstractRestService implements AddressRestService {
	
	@SuppressWarnings("unused")
	private final Logger _logger = LoggerFactory.getLogger(AddressRestServiceImpl.class);
	
	@Autowired
	private AddressService _addressService;
	@Autowired
	private UserService _userService;
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public List<AddressRO> get() {
		List<Address> addresses = _addressService.get();
		List<AddressRO> addressesRO = addresses.isEmpty() ? Collections.emptyList() : _mapperService.map(addresses, AddressRO.class);
		return addressesRO;
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public AddressRO get(final long id) {
		if (id > 0) {
			return _mapperService.map(_addressService.get(id), AddressRO.class);
		} else {
			return null;
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AddressRO create(final AddressRO addressRO) {
		//Validate input param (object)
		validateObject(addressRO);
		
		//Construct new address object
		final Address address = new Address.Builder()
								.setStatusFlag(StatusFlag.ACTIVE).build();
		
		//Validate the newly created object
		validateGenericObject(address);
		
		//Set if the address is for user
		if (addressRO.getUser() != null) {
			if (addressRO.getUser().getLoginId() != null && !addressRO.getUser().getLoginId().trim().isEmpty()) {
				final User user = _userService.getUserByUserLoginId(addressRO.getUser().getLoginId());
				validateGenericObject(user);
				address.setUser(user);
			}
		}
		
		//Set other fields if necessary
		if (addressRO.getOrganizationName() != null && !addressRO.getOrganizationName().trim().isEmpty()) {
			address.setOrganizationName(addressRO.getOrganizationName());
		}
		if (addressRO.getBuildingName() != null && !addressRO.getBuildingName().trim().isEmpty()) {
			address.setBuildingName(addressRO.getBuildingName());
		}
		if (addressRO.getStreetName() != null && !addressRO.getStreetName().trim().isEmpty()) {
			address.setStreetName(addressRO.getStreetName());
		}
		if (addressRO.getLocalityName() != null && !addressRO.getLocalityName().trim().isEmpty()) {
			address.setLocalityName(addressRO.getLocalityName());
		}
		if (addressRO.getPostTown() != null && !addressRO.getPostTown().trim().isEmpty()) {
			address.setPostTown(addressRO.getPostTown());
		}
		if (addressRO.getCounty() != null && !addressRO.getCounty().trim().isEmpty()) {
			address.setCounty(addressRO.getCounty());
		}
		if (addressRO.getCity() != null && !addressRO.getCity().trim().isEmpty()) {
			address.setCity(addressRO.getCity());
		}
		if (addressRO.getPostcode() != null && !addressRO.getPostcode().trim().isEmpty()) {
			address.setPostcode(addressRO.getPostcode());
		}
		if (addressRO.getCountry() != null && !addressRO.getCountry().trim().isEmpty()) {
			address.setCountry(addressRO.getCountry());
		}
		
		//Save
		Address newAddress = _addressService.create(address);
		
		if (newAddress != null) {
			return _mapperService.map(newAddress, AddressRO.class);
		} else {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CREATE_RECORD));
		}
	}
}
