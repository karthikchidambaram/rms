package com.i2g.rms.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.i2g.rms.domain.model.Address;
import com.i2g.rms.rest.constants.RequestMappingConstants;
import com.i2g.rms.rest.model.AddressRO;
import com.i2g.rms.rest.model.DeleteRO;
import com.i2g.rms.rest.model.wrapper.AddressWrapper;
import com.i2g.rms.rest.search.Searchable;
import com.i2g.rms.rest.service.AddressRestService;

/**
 * Rest controller for Address read/create/update/delete operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@RestController
public class AddressController extends AbstractRestController {
	
	@Autowired
	private AddressRestService _addressRestService;
	
	@RequestMapping(value = RequestMappingConstants.GET_ADDRESSES, method = RequestMethod.GET)
	@Searchable(sourceType = AddressRO.class, value = Address.class)
	public List<AddressRO> get() {
		return _addressRestService.get();
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_ADDRESSES_FOR_USER_ID, method = RequestMethod.GET)
	@Searchable(sourceType = AddressRO.class, value = Address.class)
	public List<AddressRO> getAddressesForUserId(@PathVariable final Long userId) {
		return _addressRestService.getAddressesForUserId(userId);
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_ADDRESSES_FOR_USER_LOGIN_ID, method = RequestMethod.GET)
	@Searchable(sourceType = AddressRO.class, value = Address.class)
	public List<AddressRO> getAddressesForUserLoginId(@PathVariable final String userLoginId) {
		return _addressRestService.getAddressesForUserLoginId(userLoginId);
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_ADDRESSES_FOR_BUILDING_ID, method = RequestMethod.GET)
	@Searchable(sourceType = AddressRO.class, value = Address.class)
	public List<AddressRO> getAddressesForBuildingId(@PathVariable final Long buildingId) {
		return _addressRestService.getAddressesForBuildingId(buildingId);
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_ADDRESSES_FOR_WITNESS_ID, method = RequestMethod.GET)
	@Searchable(sourceType = AddressRO.class, value = Address.class)
	public List<AddressRO> getAddressesForWitnessId(@PathVariable final Long witnessId) {
		return _addressRestService.getAddressesForWitnessId(witnessId);
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_ADDRESSES_FOR_SUSPECT_ID, method = RequestMethod.GET)
	@Searchable(sourceType = AddressRO.class, value = Address.class)
	public List<AddressRO> getAddressesForSuspectId(@PathVariable final Long suspectId) {
		return _addressRestService.getAddressesForSuspectId(suspectId);
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_ADDRESSES_FOR_CRIME_SUSPECT_ID, method = RequestMethod.GET)
	@Searchable(sourceType = AddressRO.class, value = Address.class)
	public List<AddressRO> getAddressesForCrimeSuspectId(@PathVariable final Long crimeSuspectId) {
		return _addressRestService.getAddressesForCrimeSuspectId(crimeSuspectId);
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_ADDRESSES_FOR_INJURED_PERSON_ID, method = RequestMethod.GET)
	@Searchable(sourceType = AddressRO.class, value = Address.class)
	public List<AddressRO> getAddressesForInjuredPersonId(@PathVariable final Long injuredPersonId) {
		return _addressRestService.getAddressesForInjuredPersonId(injuredPersonId);
	}
	
	@RequestMapping(value = RequestMappingConstants.GET_ADDRESS, method = RequestMethod.GET)
	public AddressRO get(final @PathVariable long addressId) {
		return _addressRestService.get(addressId);
	}
	
	@RequestMapping(value = RequestMappingConstants.CREATE_ADDRESS, method = RequestMethod.POST)
	public AddressRO create(final @Valid @RequestBody AddressRO addressRO) {
		return _addressRestService.create(addressRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.CREATE_ADDRESSES, method = RequestMethod.POST)
	public List<AddressRO> createAddresses(@Valid @RequestBody final AddressWrapper addressWrapper) {
		return _addressRestService.createAddresses(addressWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.UPDATE_ADDRESS, method = RequestMethod.PUT)
	public AddressRO updateAddress(@Valid @RequestBody final AddressRO addressRO) {
		return _addressRestService.updateAddress(addressRO);
	}
	
	@RequestMapping(value = RequestMappingConstants.UPDATE_ADDRESSES, method = RequestMethod.PUT)
	public List<AddressRO> updateAddresses(@Valid @RequestBody final AddressWrapper addressWrapper) {
		return _addressRestService.updateAddresses(addressWrapper);
	}
	
	@RequestMapping(value = RequestMappingConstants.DELETE_ADDRESS, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAddress(@PathVariable final Long addressId) {
		_addressRestService.deleteAddress(addressId);
	}
	
	@RequestMapping(value = RequestMappingConstants.DELETE_ADDRESSES, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAddresses(@Valid @RequestBody final DeleteRO deleteRO) {
		_addressRestService.deleteAddresses(deleteRO);
	}	
}
