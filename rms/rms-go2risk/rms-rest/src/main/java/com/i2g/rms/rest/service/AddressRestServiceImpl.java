package com.i2g.rms.rest.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i2g.rms.domain.model.Address;
import com.i2g.rms.domain.model.Building;
import com.i2g.rms.domain.model.CrimeSuspect;
import com.i2g.rms.domain.model.InjuredPerson;
import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.domain.model.Suspect;
import com.i2g.rms.domain.model.User;
import com.i2g.rms.domain.model.Witness;
import com.i2g.rms.rest.model.AddressRO;
import com.i2g.rms.rest.model.DeleteRO;
import com.i2g.rms.rest.model.wrapper.AddressWrapper;
import com.i2g.rms.service.AddressService;
import com.i2g.rms.service.BuildingService;
import com.i2g.rms.service.CrimeSuspectService;
import com.i2g.rms.service.InjuredPersonService;
import com.i2g.rms.service.SuspectService;
import com.i2g.rms.service.UserService;
import com.i2g.rms.service.WitnessService;
import com.i2g.rms.service.exception.ResourceNotCreatedException;
import com.i2g.rms.service.exception.ResourceNotValidException;

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
	@Autowired
	private BuildingService _buildingService;
	@Autowired
	private SuspectService _suspectService;
	@Autowired
	private InjuredPersonService _injuredPersonService;
	@Autowired
	private WitnessService _witnessService;
	@Autowired
	private CrimeSuspectService _crimeSuspectService;
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public List<AddressRO> get() {
		List<Address> addresses = _addressService.get();
		List<AddressRO> addressesROs = (addresses == null || addresses.isEmpty()) ? Collections.emptyList() : _mapperService.map(addresses, AddressRO.class);
		return addressesROs;
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public AddressRO get(final long addressId) {
		if (addressId > 0) {
			final Address address = _addressService.get(addressId);
			validateGenericObject(address);
			return _mapperService.map(address, AddressRO.class);
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
		final Address address = constructNewAddress(addressRO);
		validateGenericObject(address);
		//Save
		final Address newAddress = _addressService.createAddress(address);		
		if (newAddress != null) {
			return _mapperService.map(newAddress, AddressRO.class);
		} else {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CREATE_RECORD));
		}
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public List<AddressRO> createAddresses(final AddressWrapper addressWrapper) {
		validateObject(addressWrapper);
		validateCollectionObject(addressWrapper.getAddresses());
		final Set<Address> addresses = constructNewAddresses(addressWrapper);
		validateCollectionObject(addresses);
		final List<Address> newAddresses = _addressService.createAddresses(new ArrayList<Address>(addresses));
		if (newAddresses != null && !newAddresses.isEmpty()) {
			return _mapperService.map(newAddresses, AddressRO.class);
		} else {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CREATE_RECORD));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public AddressRO updateAddress(final AddressRO addressRO) {
		//Validate input param (object)
		validateObject(addressRO);
		if (addressRO.getId() <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.GENERIC_FETCH_FAILED_MESSAGE));
		}		
		//Construct new address object
		Address address = _addressService.get(addressRO.getId());
		//Validate the newly created object
		validateGenericObject(address);		
		//udpate
		final Address updatedAddress = _addressService.updateAddress(setOtherFieldsForAddress(address, addressRO));
		if (updatedAddress != null) {
			return _mapperService.map(updatedAddress, AddressRO.class);
		} else {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}
	}	

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public List<AddressRO> updateAddresses(final AddressWrapper addressWrapper) {
		validateObject(addressWrapper);
		validateCollectionObject(addressWrapper.getAddresses());
		final Set<Address> addresses = new HashSet<Address>(0);
		for (AddressRO addressRO : addressWrapper.getAddresses()) {
			if (addressRO != null) {
				if (addressRO.getId() > 0) {
					final Address address = setOtherFieldsForAddress(_addressService.get(addressRO.getId()), addressRO);
					if (address != null) {
						addresses.add(address);
					}
				}
			}
		}
		validateCollectionObject(addresses);
		final List<Address> updatedAddresses = _addressService.updateAddresses(new ArrayList<Address>(addresses));
		if (updatedAddresses != null && !updatedAddresses.isEmpty()) {
			return _mapperService.map(updatedAddresses, AddressRO.class);
		} else {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public void deleteAddress(final Long addressId) {
		if (addressId == null || addressId <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.INVALID_KEY_PASSED_FOR_DELETE));
		}
		final Address address = _addressService.get(addressId);
		validateGenericObject(address);
		_addressService.deleteAddress(address);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public void deleteAddresses(final DeleteRO deleteRO) {
		validateObject(deleteRO);
		final Long[] ids  = deleteRO.getIds();
		if (ids == null || ids.length <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.NOTHING_TO_DELETE));
		}
		final Set<Address> addresses = new HashSet<Address>(0);
		for (int i = 0; i < ids.length; i++) {
			final Long id = ids[i];
			if (id != null && id > 0) {
				final Address address = _addressService.get(id);
				if (address != null) {
					addresses.add(address);
				}
			}
		}
		_addressService.deleteAddresses(new ArrayList<Address>(addresses));
	}
	
	private Set<Address> constructNewAddresses(final AddressWrapper addressWrapper) {
		validateObject(addressWrapper);
		
		User user = null;
		Building building = null;
		Suspect suspect = null;
		InjuredPerson injuredPerson = null;
		Witness witness = null;
		CrimeSuspect crimeSuspect = null;
		
		if (addressWrapper.getUserId() != null && addressWrapper.getUserId() > 0) {
			user = _userService.get(addressWrapper.getUserId());
		}
		
		if (addressWrapper.getBuildingId() != null && addressWrapper.getBuildingId() > 0) {
			building = _buildingService.get(addressWrapper.getBuildingId());
		}
		
		if (addressWrapper.getSuspectId() != null && addressWrapper.getSuspectId() > 0) {
			suspect = _suspectService.get(addressWrapper.getSuspectId());
		}
		
		if (addressWrapper.getInjuredPersonId() != null && addressWrapper.getInjuredPersonId() > 0) {
			injuredPerson = _injuredPersonService.get(addressWrapper.getInjuredPersonId());
		}
		
		if (addressWrapper.getWitnessId() != null && addressWrapper.getWitnessId() > 0) {
			witness = _witnessService.get(addressWrapper.getWitnessId());
		}
		
		if (addressWrapper.getCrimeSuspectId() != null && addressWrapper.getCrimeSuspectId() > 0) {
			crimeSuspect = _crimeSuspectService.get(addressWrapper.getCrimeSuspectId());
		}			
		
		final Set<Address> addresses = new HashSet<Address>(0);
		
		if (addressWrapper.getAddresses() != null && !addressWrapper.getAddresses().isEmpty()) {
			for (AddressRO addressRO : addressWrapper.getAddresses()) {
				if (addressRO != null) {
					final Address address = constructNewAddress(addressRO);
					if (address != null) {
						if (user != null) {
							address.setUser(user);
						} else if (building != null) {
							address.setBuilding(building);
						} else if (suspect != null) {
							address.setSuspect(suspect);
						} else if (injuredPerson != null) {
							address.setInjuredPerson(injuredPerson);
						} else if (witness != null) {
							address.setWitness(witness);
						} else if (crimeSuspect != null) {
							address.setCrimeSuspect(crimeSuspect);
						}
						addresses.add(address);
					}
				}
			}
		}		
		return addresses;
	}
	
	private Address constructNewAddress(final AddressRO addressRO) {
		validateObject(addressRO);
		//Construct new address object
		Address address = new Address.Builder().setStatusFlag(StatusFlag.ACTIVE).build();
		//Validate the newly created object
		validateGenericObject(address);
		
		//Set if the address is for user
		if (addressRO.getUser() != null) {
			if (addressRO.getUser().getLoginId() != null && !addressRO.getUser().getLoginId().trim().isEmpty()) {
				final User user = _userService.getUserByUserLoginId(addressRO.getUser().getLoginId());
				validateGenericObject(user);
				address.setUser(user);
			} else if (addressRO.getUser().getId() > 0) {
				final User user = _userService.get(addressRO.getUser().getId());
				validateGenericObject(user);
				address.setUser(user);
			}			
		}
		//if the address is for building
		if (addressRO.getBuilding() != null) {
			if (addressRO.getBuilding().getId() > 0) {
				final Building building = _buildingService.get(addressRO.getBuilding().getId());
				validateGenericObject(building);
				address.setBuilding(building);
			}
		}
		//if the address is for suspect
		if (addressRO.getSuspect() != null) {
			if (addressRO.getSuspect().getId() > 0) {
				final Suspect suspect = _suspectService.get(addressRO.getSuspect().getId());
				validateGenericObject(suspect);
				address.setSuspect(suspect);
			}
		}
		//if the address is for injured person
		if (addressRO.getInjuredPerson() != null) {
			if (addressRO.getInjuredPerson().getId() > 0) {
				final InjuredPerson injuredPerson = _injuredPersonService.get(addressRO.getInjuredPerson().getId());
				validateGenericObject(injuredPerson);
				address.setInjuredPerson(injuredPerson);
			}
		}
		//if the address is for witness
		if (addressRO.getWitness() != null) {
			if (addressRO.getWitness().getId() > 0) {
				final Witness witness = _witnessService.get(addressRO.getWitness().getId());
				validateGenericObject(witness);
				address.setWitness(witness);
			}
		}
		//if the address is for crime suspect
		if (addressRO.getCrimeSuspect() != null) {
			if (addressRO.getCrimeSuspect().getId() > 0) {
				final CrimeSuspect crimeSuspect = _crimeSuspectService.get(addressRO.getCrimeSuspect().getId());
				validateGenericObject(crimeSuspect);
				address.setCrimeSuspect(crimeSuspect);
			}
		}
		return setOtherFieldsForAddress(address, addressRO);
	}
	
	private Address setOtherFieldsForAddress(final Address address, final AddressRO addressRO) {
		if (address != null && addressRO != null) {
			//Set other fields if necessary
			if (addressRO.getOrganizationName() != null && !addressRO.getOrganizationName().trim().isEmpty()) {
				address.setOrganizationName(addressRO.getOrganizationName().trim());
			}
			if (addressRO.getBuildingName() != null && !addressRO.getBuildingName().trim().isEmpty()) {
				address.setBuildingName(addressRO.getBuildingName().trim());
			}
			if (addressRO.getStreetName() != null && !addressRO.getStreetName().trim().isEmpty()) {
				address.setStreetName(addressRO.getStreetName().trim());
			}
			if (addressRO.getLocalityName() != null && !addressRO.getLocalityName().trim().isEmpty()) {
				address.setLocalityName(addressRO.getLocalityName().trim());
			}
			if (addressRO.getPostTown() != null && !addressRO.getPostTown().trim().isEmpty()) {
				address.setPostTown(addressRO.getPostTown().trim());
			}
			if (addressRO.getCounty() != null && !addressRO.getCounty().trim().isEmpty()) {
				address.setCounty(addressRO.getCounty().trim());
			}
			if (addressRO.getCity() != null && !addressRO.getCity().trim().isEmpty()) {
				address.setCity(addressRO.getCity().trim());
			}
			if (addressRO.getPostcode() != null && !addressRO.getPostcode().trim().isEmpty()) {
				address.setPostcode(addressRO.getPostcode().trim());
			}
			if (addressRO.getCountry() != null && !addressRO.getCountry().trim().isEmpty()) {
				address.setCountry(addressRO.getCountry().trim());
			}
			if (addressRO.getDoorNumber() != null && !addressRO.getDoorNumber().trim().isEmpty()) {
				address.setDoorNumber(addressRO.getDoorNumber().trim());
			}
			if (addressRO.getBlockNumber() != null && !addressRO.getBlockNumber().trim().isEmpty()) {
				address.setBlockNumber(addressRO.getBlockNumber().trim());
			}
			if (addressRO.getApartmentNumber() != null && !addressRO.getApartmentNumber().trim().isEmpty()) {
				address.setApartmentNumber(addressRO.getApartmentNumber().trim());
			}
			return address;
		} else {
			return null;
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public List<AddressRO> getAddressesForUserId(final Long userId) {
		if (userId == null || userId <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.GENERIC_FETCH_FAILED_MESSAGE));
		}
		final User user = _userService.get(userId);
		validateGenericObject(user);
		List<Address> addresses = _addressService.get(user);
		List<AddressRO> addressesROs = (addresses == null || addresses.isEmpty()) ? Collections.emptyList() : _mapperService.map(addresses, AddressRO.class);
		return addressesROs;
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public List<AddressRO> getAddressesForUserLoginId(final String userLoginId) {
		if (userLoginId == null || userLoginId.trim().isEmpty()) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.GENERIC_FETCH_FAILED_MESSAGE));
		}
		final User user = _userService.getUserByUserLoginId(userLoginId);
		validateGenericObject(user);
		List<Address> addresses = _addressService.get(user);
		List<AddressRO> addressesROs = (addresses == null || addresses.isEmpty()) ? Collections.emptyList() : _mapperService.map(addresses, AddressRO.class);
		return addressesROs;
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public List<AddressRO> getAddressesForBuildingId(final Long buildingId) {
		if (buildingId == null || buildingId <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.GENERIC_FETCH_FAILED_MESSAGE));
		}
		final Building building = _buildingService.get(buildingId);
		validateGenericObject(building);
		List<Address> addresses = _addressService.get(building);
		List<AddressRO> addressesROs = (addresses == null || addresses.isEmpty()) ? Collections.emptyList() : _mapperService.map(addresses, AddressRO.class);
		return addressesROs;
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public List<AddressRO> getAddressesForInjuredPersonId(final Long injuredPersonId) {
		if (injuredPersonId == null || injuredPersonId <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.GENERIC_FETCH_FAILED_MESSAGE));
		}
		final InjuredPerson injuredPerson = _injuredPersonService.get(injuredPersonId);
		validateGenericObject(injuredPerson);
		List<Address> addresses = _addressService.get(injuredPerson);
		List<AddressRO> addressesROs = (addresses == null || addresses.isEmpty()) ? Collections.emptyList() : _mapperService.map(addresses, AddressRO.class);
		return addressesROs;
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public List<AddressRO> getAddressesForSuspectId(final Long suspectId) {
		if (suspectId == null || suspectId <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.GENERIC_FETCH_FAILED_MESSAGE));
		}
		final Suspect suspect = _suspectService.get(suspectId);
		validateGenericObject(suspect);
		List<Address> addresses = _addressService.get(suspect);
		List<AddressRO> addressesROs = (addresses == null || addresses.isEmpty()) ? Collections.emptyList() : _mapperService.map(addresses, AddressRO.class);
		return addressesROs;
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public List<AddressRO> getAddressesForCrimeSuspectId(final Long crimeSuspectId) {
		if (crimeSuspectId == null || crimeSuspectId <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.GENERIC_FETCH_FAILED_MESSAGE));
		}
		final CrimeSuspect crimeSuspect = _crimeSuspectService.get(crimeSuspectId);
		validateGenericObject(crimeSuspect);
		List<Address> addresses = _addressService.get(crimeSuspect);
		List<AddressRO> addressesROs = (addresses == null || addresses.isEmpty()) ? Collections.emptyList() : _mapperService.map(addresses, AddressRO.class);
		return addressesROs;
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public List<AddressRO> getAddressesForWitnessId(final Long witnessId) {
		if (witnessId == null || witnessId <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.GENERIC_FETCH_FAILED_MESSAGE));
		}
		final Witness witness = _witnessService.get(witnessId);
		validateGenericObject(witness);
		List<Address> addresses = _addressService.get(witness);
		List<AddressRO> addressesROs = (addresses == null || addresses.isEmpty()) ? Collections.emptyList() : _mapperService.map(addresses, AddressRO.class);
		return addressesROs;
	}		
}
