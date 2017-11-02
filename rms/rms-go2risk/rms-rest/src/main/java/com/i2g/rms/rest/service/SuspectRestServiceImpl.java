package com.i2g.rms.rest.service;

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
import com.i2g.rms.domain.model.YesNoType;
import com.i2g.rms.domain.model.tablemaintenance.DistinguishingFeatureDetail;
import com.i2g.rms.domain.model.tablemaintenance.WeaponType;
import com.i2g.rms.rest.model.AddressRO;
import com.i2g.rms.rest.model.SuspectRO;
import com.i2g.rms.rest.model.tablemaintenance.DistinguishingFeatureDetailRO;
import com.i2g.rms.rest.model.wrapper.DistinguishingFeatureDetailWrapper;
import com.i2g.rms.rest.model.wrapper.SuspectWrapper;
import com.i2g.rms.rest.service.incident.IncidentRestService;
import com.i2g.rms.service.AddressService;
import com.i2g.rms.service.SuspectService;
import com.i2g.rms.service.exception.ResourceNotCreatedException;
import com.i2g.rms.service.exception.ResourceNotUpdatedException;
import com.i2g.rms.service.exception.ResourceNotValidException;
import com.i2g.rms.service.incident.IncidentService;
import com.i2g.rms.service.tablemaintenance.TableMaintenanceService;

/**
 * Rest services for suspect rest controller.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class SuspectRestServiceImpl extends AbstractRestService implements SuspectRestService {
	
	@SuppressWarnings("unused")
	private final Logger _logger = LoggerFactory.getLogger(SuspectRestServiceImpl.class);
	
	@Autowired
	private SuspectService _suspectService;
	@Autowired
	private TableMaintenanceService _tableMaintenanceService;
	@Autowired
	private IncidentRestService _incidentRestService; 
	@Autowired
	private AddressService _addressService;
	@Autowired
	private IncidentService _incidentService;
		
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public List<SuspectRO> get() {
		List<Suspect> suspects = _suspectService.get();
		List<SuspectRO> suspectROs = (suspects == null || suspects.isEmpty()) ? Collections.emptyList() : _mapperService.map(suspects, SuspectRO.class);
		return suspectROs;
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public SuspectRO get(final Long suspectId) {
		if (suspectId != null && suspectId > 0) {
			final Suspect suspect = _suspectService.get(suspectId);
			validateGenericObject(suspect);
			return _mapperService.map(suspect, SuspectRO.class);
		} else {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.INVALID_SUSPECT_ID_PASSED_AS_PARAMETER));
		}
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public List<SuspectRO> getSuspectsByIncidentId(final Long incidentId) {
		validateKeyId(incidentId);
		List<Suspect> suspects = _suspectService.getSuspectsByIncidentId(incidentId);
		List<SuspectRO> suspectROs = (suspects == null || suspects.isEmpty()) ? Collections.emptyList() : _mapperService.map(suspects, SuspectRO.class);
		return suspectROs;
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public List<SuspectRO> get(final String uniqueIncidentId) {
		validateUniqueIncidentId(uniqueIncidentId);
		List<Suspect> suspects = _suspectService.get(uniqueIncidentId.trim());
		List<SuspectRO> suspectROs = (suspects == null || suspects.isEmpty()) ? Collections.emptyList() : _mapperService.map(suspects, SuspectRO.class);
		return suspectROs;
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public SuspectRO createSuspect(final SuspectRO suspectRO) {
		validateObject(suspectRO);
		final Suspect suspect = constructNewSuspect(suspectRO);
		validateGenericObject(suspect);		
		final Suspect newSuspect = _suspectService.createNewSuspect(suspect);
		if (newSuspect != null) {
			return _mapperService.map(newSuspect, SuspectRO.class);
		} else {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CREATE_RECORD));
		}
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public List<SuspectRO> createSuspects(final SuspectWrapper suspectWrapper) {
		validateObject(suspectWrapper);
		validateCollectionObject(suspectWrapper.getSuspects());
		final Set<Suspect> suspects = new HashSet<Suspect>(0);
		if (suspectWrapper.getSuspects() != null && !suspectWrapper.getSuspects().isEmpty()) {
			for (SuspectRO suspectRO : suspectWrapper.getSuspects()) {
				if (suspectRO != null) {
					final Suspect suspect = constructNewSuspect(suspectRO);
					if (suspect != null) {
						suspects.add(suspect);
					}
				}
			}
		}
		final List<Suspect> newSuspects = _suspectService.createNewSuspects(suspects);
		if (newSuspects != null && !newSuspects.isEmpty()) {
			return _mapperService.map(newSuspects, SuspectRO.class);
		} else {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CREATE_RECORD));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public SuspectRO updateSuspect(final SuspectRO suspectRO) {
		validateObject(suspectRO);
		if (suspectRO.getId() <= 0 ) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.RECORD_FETCH_FAILED_FOR_UPDATE));
		}
		final Suspect suspect = constructSuspect(suspectRO);
		validateGenericObject(suspect);
		final Suspect updatedSuspect = _suspectService.updateSuspect(suspect);
		if (updatedSuspect != null) {
			return _mapperService.map(updatedSuspect, SuspectRO.class);
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public List<SuspectRO> updateSuspects(final SuspectWrapper suspectWrapper) {
		validateObject(suspectWrapper);
		Set<Suspect> suspects = new HashSet<Suspect>(0);
		for (SuspectRO suspectRO : suspectWrapper.getSuspects()) {
			if (suspectRO != null) {
				final Suspect suspect = constructSuspect(suspectRO);
				if (suspect != null) {
					suspects.add(suspect);
				}
			}
		}
		final List<Suspect> updatedSuspects = _suspectService.updateSuspects(suspects);		
		if (updatedSuspects != null && !updatedSuspects.isEmpty()) {
			return _mapperService.map(updatedSuspects, SuspectRO.class);
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public void removeDistinguishingFeatureDetailsFromSuspect(final DistinguishingFeatureDetailWrapper distinguishingFeatureDetailWrapper) {
		validateObject(distinguishingFeatureDetailWrapper);
		validateCollectionObject(distinguishingFeatureDetailWrapper.getDistinguishingFeatureDetails());
		if (distinguishingFeatureDetailWrapper.getSuspectId() == null || distinguishingFeatureDetailWrapper.getSuspectId() <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.GENERIC_FETCH_FAILED_MESSAGE));
		}
		final Suspect suspect = _suspectService.get(distinguishingFeatureDetailWrapper.getSuspectId());
		validateGenericObject(suspect);
		Set<DistinguishingFeatureDetail> distinguishingFeatureDetails = new HashSet<DistinguishingFeatureDetail>(0);
		for (DistinguishingFeatureDetailRO distinguishingFeatureDetailRO : distinguishingFeatureDetailWrapper.getDistinguishingFeatureDetails()) {
			if (distinguishingFeatureDetailRO != null) {
				if (distinguishingFeatureDetailRO.getId() != null && !distinguishingFeatureDetailRO.getId().trim().isEmpty()) {
					final DistinguishingFeatureDetail distinguishingFeatureDetail = _tableMaintenanceService.getDistinguishingFeatureDetailByCode(distinguishingFeatureDetailRO.getId().trim());
					if (distinguishingFeatureDetail != null) {
						distinguishingFeatureDetails.add(distinguishingFeatureDetail);
					}
				}
			}
		}
		_suspectService.removeDistinguishingFeatureDetailsFromSuspect(suspect, distinguishingFeatureDetails);		
	}
	
	@Override
	public Suspect constructNewSuspect(final SuspectRO suspectRO) {
		validateObject(suspectRO);
		// Construction of new suspects
		final Suspect suspect = new Suspect.Builder().setStatusFlag(StatusFlag.ACTIVE).build();
		validateGenericObject(suspect);
		// Set other values
		return setOtherFieldsForSuspect(suspect, suspectRO);
	}
	
	@Override
	public Suspect constructSuspect(final SuspectRO suspectRO) {
		validateObject(suspectRO);
		if (suspectRO.getId() <= 0 ) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.RECORD_FETCH_FAILED_FOR_UPDATE));
		}
		final Suspect suspect = _suspectService.get(suspectRO.getId());
		validateGenericObject(suspect);
		return setOtherFieldsForSuspect(suspect, suspectRO);
	}
	
	@Override
	public Suspect setOtherFieldsForSuspect(final Suspect suspect, final SuspectRO suspectRO) {
		// Set other values for suspect
		if (suspect != null && suspectRO != null) {
			if (suspectRO.getTitle() != null && !suspectRO.getTitle().trim().isEmpty()) {
				suspect.setTitle(suspectRO.getTitle().trim());
			}
			if (suspectRO.getFirstName() != null && !suspectRO.getFirstName().trim().isEmpty()) {
				suspect.setFirstName(suspectRO.getFirstName().trim());
			}
			if (suspectRO.getMiddleName() != null && !suspectRO.getMiddleName().trim().isEmpty()) {
				suspect.setMiddleName(suspectRO.getMiddleName().trim());
			}
			if (suspectRO.getLastName() != null && !suspectRO.getLastName().trim().isEmpty()) {
				suspect.setLastName(suspectRO.getLastName().trim());
			}
			if (suspectRO.getNameSuffix() != null && !suspectRO.getNameSuffix().trim().isEmpty()) {
				suspect.setNameSuffix(suspectRO.getNameSuffix().trim());
			}
			if (suspectRO.getPhone() != null && !suspectRO.getPhone().trim().isEmpty()) {
				suspect.setPhone(suspectRO.getPhone().trim());
			}
			if (suspectRO.getFax() != null && !suspectRO.getFax().trim().isEmpty()) {
				suspect.setFax(suspectRO.getFax().trim());
			}
			if (suspectRO.getAlternatePhone() != null && !suspectRO.getAlternatePhone().trim().isEmpty()) {
				suspect.setAlternatePhone(suspectRO.getAlternatePhone().trim());
			}
			if (suspectRO.getEmail() != null && !suspectRO.getEmail().trim().isEmpty()) {
				suspect.setEmail(suspectRO.getEmail().trim());
			}
			if (suspectRO.getWebsite() != null && !suspectRO.getWebsite().trim().isEmpty()) {
				suspect.setWebsite(suspectRO.getWebsite().trim());
			}
			// Gender type
			if (suspectRO.getGenderType() != null) {
				if (suspectRO.getGenderType().getId() != null && !suspectRO.getGenderType().getId().trim().isEmpty()) {
					suspect.setGenderType(_tableMaintenanceService.getGenderTypeByCode(suspectRO.getGenderType().getId().trim()));
				}
			}
			// gender type other
			if (suspectRO.getGenderTypeOther() != null && !suspectRO.getGenderTypeOther().trim().isEmpty()) {
				suspect.setGenderTypeOther(suspectRO.getGenderTypeOther().trim());
			}
			if (suspectRO.getAge() != null && suspectRO.getAge() > 0) {
				suspect.setAge(suspectRO.getAge());
			}
			if (suspectRO.getDateOfBirth() != null) {
				suspect.setDateOfBirth(suspectRO.getDateOfBirth());
			}
			// Was there a weapon involved?
			YesNoType weaponInvolved = YesNoType.N;
			if (suspectRO.getWeaponInvolved() != null && suspectRO.getWeaponInvolved().name().equals("Y")) {
				weaponInvolved = YesNoType.Y;
			}
			// Weapon type
			WeaponType weaponType = null;
			if (suspectRO.getWeaponType() != null) {
				if (suspectRO.getWeaponType().getId() != null && !suspectRO.getWeaponType().getId().trim().isEmpty()) {
					weaponType = _tableMaintenanceService.getWeaponTypeByCode(suspectRO.getWeaponType().getId().trim());
				}
			}
			_incidentRestService.validateWeaponInvolvedAndType(weaponInvolved, weaponType);
			suspect.setWeaponInvolved(weaponInvolved);
			suspect.setWeaponType(weaponType);
			// weapon type other
			if (suspectRO.getWeaponTypeOther() != null && !suspectRO.getWeaponTypeOther().trim().isEmpty()) {
				suspect.setWeaponTypeOther(suspectRO.getWeaponTypeOther().trim());
			}
			// Suspect Type
			if (suspectRO.getSuspectType() != null) {
				if (suspectRO.getSuspectType().getId() != null && !suspectRO.getSuspectType().getId().trim().isEmpty()) {
					suspect.setSuspectType(_tableMaintenanceService.getSuspectTypeByCode(suspectRO.getSuspectType().getId().trim()));
				}
			}
			// suspect addresses if any
			if (suspectRO.getAddresses() != null && !suspectRO.getAddresses().isEmpty()) {
				suspect.setAddresses(createOrUpdateAddresses(suspectRO.getAddresses(), null, suspect, null, null, null, null));
			}
			// other comments for suspect type
			if (suspectRO.getSuspectTypeOther() != null && !suspectRO.getSuspectTypeOther().trim().isEmpty()) {
				suspect.setSuspectTypeOther(suspectRO.getSuspectTypeOther().trim());
			}
			// construct and set distinguishing feature details
			if (suspectRO.getDistinguishingFeatureDetails() != null && !suspectRO.getDistinguishingFeatureDetails().isEmpty()) {
				suspect.setDistinguishingFeatureDetails(_incidentRestService.constructDistinguishingFeatureDetails(suspectRO.getDistinguishingFeatureDetails()));
			}
			// other comments for distinguishing feature
			if (suspectRO.getDistinguishingFeatureOther() != null && !suspectRO.getDistinguishingFeatureOther().trim().isEmpty()) {
				suspect.setDistinguishingFeatureOther(suspectRO.getDistinguishingFeatureOther().trim());
			}			
			return suspect;
		} else {
			return null;
		}
	}
	
	@Override
	public Set<Address> createOrUpdateAddresses(final Set<AddressRO> addressROs,
												final User user, 
												final Suspect suspect,
												final InjuredPerson injuredPerson, 
												final Witness witness, 
												final CrimeSuspect crimeSuspect,
												final Building building) {
		final Set<Address> addresses = new HashSet<Address>(0);
		if (addressROs != null && !addressROs.isEmpty()) {
			for (AddressRO addressRO : addressROs) {
				if (addressRO != null) {
					final Address address = getAddress(addressRO, user, suspect, injuredPerson, witness, crimeSuspect, building);
					if (address != null) {
						addresses.add(address);
					}
				}
			}
		}
		return addresses;
	}
	
	@Override
	public Address getAddress(final AddressRO addressRO,
								final User user, 
								final Suspect suspect,
								final InjuredPerson injuredPerson, 
								final Witness witness, 
								final CrimeSuspect crimeSuspect,
								final Building building) {
		Address address = null;
		if (addressRO != null) {
			if (addressRO.getId() > 0) {
				address = _addressService.get(addressRO.getId());
			} else {
				address = new Address.Builder().setStatusFlag(StatusFlag.ACTIVE).build();
				// This step determines to which entity the addresses are
				// constructed. It can be suspect, injured person, witness etc.
				if (user != null) {
					address.setUser(user);
				} else if (suspect != null) {
					address.setSuspect(suspect);
				} else if (injuredPerson != null) {
					address.setInjuredPerson(injuredPerson);
				} else if (witness != null) {
					address.setWitness(witness);
				} else if (crimeSuspect != null) {
					address.setCrimeSuspect(crimeSuspect);
				} else if (building != null) {
					address.setBuilding(building);
				}
			}
		}
		return setOtherFieldsForAddress(address, addressRO);
	}
	
	@Override
	public Address setOtherFieldsForAddress(final Address address, final AddressRO addressRO) {
		if (address != null && addressRO != null) {
			// other address fields
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
		}
		return address;
	}	
}
