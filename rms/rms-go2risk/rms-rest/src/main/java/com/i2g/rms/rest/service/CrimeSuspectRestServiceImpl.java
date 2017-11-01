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

import com.i2g.rms.domain.model.CrimeSuspect;
import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.domain.model.tablemaintenance.DistinguishingFeatureDetail;
import com.i2g.rms.rest.model.CrimeSuspectRO;
import com.i2g.rms.rest.model.tablemaintenance.DistinguishingFeatureDetailRO;
import com.i2g.rms.rest.model.wrapper.CrimeSuspectWrapper;
import com.i2g.rms.rest.model.wrapper.DistinguishingFeatureDetailWrapper;
import com.i2g.rms.rest.service.incident.IncidentRestService;
import com.i2g.rms.service.AddressService;
import com.i2g.rms.service.CrimeSuspectService;
import com.i2g.rms.service.SuspectService;
import com.i2g.rms.service.exception.ResourceNotCreatedException;
import com.i2g.rms.service.exception.ResourceNotUpdatedException;
import com.i2g.rms.service.exception.ResourceNotValidException;
import com.i2g.rms.service.incident.IncidentService;
import com.i2g.rms.service.tablemaintenance.TableMaintenanceService;

/**
 * Rest services for crimeSuspect rest controller.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class CrimeSuspectRestServiceImpl extends AbstractRestService implements CrimeSuspectRestService {
	
	@SuppressWarnings("unused")
	private final Logger _logger = LoggerFactory.getLogger(CrimeSuspectRestServiceImpl.class);
	
	@Autowired
	private CrimeSuspectService _crimeSuspectService;
	@Autowired
	private SuspectRestService _suspectRestService;
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
	public List<CrimeSuspectRO> get() {
		List<CrimeSuspect> crimeSuspects = _crimeSuspectService.get();
		List<CrimeSuspectRO> crimeSuspectROs = (crimeSuspects == null || crimeSuspects.isEmpty()) ? Collections.emptyList() : _mapperService.map(crimeSuspects, CrimeSuspectRO.class);
		return crimeSuspectROs;
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public CrimeSuspectRO get(final Long crimeSuspectId) {
		if (crimeSuspectId != null && crimeSuspectId > 0) {
			final CrimeSuspect crimeSuspect = _crimeSuspectService.get(crimeSuspectId);
			validateGenericObject(crimeSuspect);
			return _mapperService.map(crimeSuspect, CrimeSuspectRO.class);
		} else {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.INVALID_CRIME_SUSPECT_ID_PASSED_AS_PARAMETER));
		}
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public CrimeSuspectRO createCrimeSuspect(final CrimeSuspectRO crimeSuspectRO) {
		validateObject(crimeSuspectRO);
		final CrimeSuspect crimeSuspect = constructNewCrimeSuspect(crimeSuspectRO);
		validateGenericObject(crimeSuspect);		
		final CrimeSuspect newCrimeSuspect = _crimeSuspectService.createNewCrimeSuspect(crimeSuspect);
		if (newCrimeSuspect != null) {
			return _mapperService.map(newCrimeSuspect, CrimeSuspectRO.class);
		} else {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CREATE_RECORD));
		}
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public Set<CrimeSuspectRO> createCrimeSuspects(final CrimeSuspectWrapper crimeSuspectWrapper) {
		validateObject(crimeSuspectWrapper);
		validateCollectionObject(crimeSuspectWrapper.getCrimeSuspects());
		final Set<CrimeSuspect> crimeSuspects = new HashSet<CrimeSuspect>(0);
		if (crimeSuspectWrapper.getCrimeSuspects() != null && !crimeSuspectWrapper.getCrimeSuspects().isEmpty()) {
			for (CrimeSuspectRO crimeSuspectRO : crimeSuspectWrapper.getCrimeSuspects()) {
				if (crimeSuspectRO != null) {
					final CrimeSuspect crimeSuspect = constructNewCrimeSuspect(crimeSuspectRO);
					if (crimeSuspect != null) {
						crimeSuspects.add(crimeSuspect);
					}
				}
			}
		}
		final Set<CrimeSuspect> newCrimeSuspects = _crimeSuspectService.createNewCrimeSuspects(crimeSuspects);
		if (newCrimeSuspects != null && !newCrimeSuspects.isEmpty()) {
			return _mapperService.map(newCrimeSuspects, CrimeSuspectRO.class);
		} else {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CREATE_RECORD));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public CrimeSuspectRO updateCrimeSuspect(final CrimeSuspectRO crimeSuspectRO) {
		validateObject(crimeSuspectRO);
		if (crimeSuspectRO.getId() <= 0 ) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.RECORD_FETCH_FAILED_FOR_UPDATE));
		}
		final CrimeSuspect crimeSuspect = constructCrimeSuspect(crimeSuspectRO);
		validateGenericObject(crimeSuspect);
		final CrimeSuspect updatedCrimeSuspect = _crimeSuspectService.updateCrimeSuspect(crimeSuspect);
		if (updatedCrimeSuspect != null) {
			return _mapperService.map(updatedCrimeSuspect, CrimeSuspectRO.class);
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public Set<CrimeSuspectRO> updateCrimeSuspects(final CrimeSuspectWrapper crimeSuspectWrapper) {
		validateObject(crimeSuspectWrapper);
		Set<CrimeSuspect> crimeSuspects = new HashSet<CrimeSuspect>(0);
		for (CrimeSuspectRO crimeSuspectRO : crimeSuspectWrapper.getCrimeSuspects()) {
			if (crimeSuspectRO != null) {
				final CrimeSuspect crimeSuspect = constructCrimeSuspect(crimeSuspectRO);
				if (crimeSuspect != null) {
					crimeSuspects.add(crimeSuspect);
				}
			}
		}
		final Set<CrimeSuspect> updatedCrimeSuspects = _crimeSuspectService.updateCrimeSuspects(crimeSuspects);		
		if (updatedCrimeSuspects != null && !updatedCrimeSuspects.isEmpty()) {
			return _mapperService.map(updatedCrimeSuspects, CrimeSuspectRO.class);
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public void removeDistinguishingFeatureDetailsFromCrimeSuspect(final DistinguishingFeatureDetailWrapper distinguishingFeatureDetailWrapper) {
		validateObject(distinguishingFeatureDetailWrapper);
		validateCollectionObject(distinguishingFeatureDetailWrapper.getDistinguishingFeatureDetails());
		if (distinguishingFeatureDetailWrapper.getCrimeSuspectId() == null || distinguishingFeatureDetailWrapper.getCrimeSuspectId() <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.GENERIC_FETCH_FAILED_MESSAGE));
		}
		final CrimeSuspect crimeSuspect = _crimeSuspectService.get(distinguishingFeatureDetailWrapper.getCrimeSuspectId());
		validateGenericObject(crimeSuspect);
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
		_crimeSuspectService.removeDistinguishingFeatureDetailsFromCrimeSuspect(crimeSuspect, distinguishingFeatureDetails);		
	}
	
	@Override
	public CrimeSuspect constructNewCrimeSuspect(final CrimeSuspectRO crimeSuspectRO) {
		validateObject(crimeSuspectRO);
		// Construction of new crime Suspects
		final CrimeSuspect crimeSuspect = new CrimeSuspect.Builder().setStatusFlag(StatusFlag.ACTIVE).build();
		validateGenericObject(crimeSuspect);
		// Set other values
		return setOtherFieldsForCrimeSuspect(crimeSuspect, crimeSuspectRO);
	}
	
	@Override
	public CrimeSuspect constructCrimeSuspect(final CrimeSuspectRO crimeSuspectRO) {
		validateObject(crimeSuspectRO);
		if (crimeSuspectRO.getId() <= 0 ) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.RECORD_FETCH_FAILED_FOR_UPDATE));
		}
		final CrimeSuspect crimeSuspect = _crimeSuspectService.get(crimeSuspectRO.getId());
		validateGenericObject(crimeSuspect);
		return setOtherFieldsForCrimeSuspect(crimeSuspect, crimeSuspectRO);
	}
	
	@Override
	public CrimeSuspect setOtherFieldsForCrimeSuspect(final CrimeSuspect crimeSuspect, final CrimeSuspectRO crimeSuspectRO) {
		// Set other values for crimeSuspect
		if (crimeSuspect != null && crimeSuspectRO != null) {
			// title
			if (nullOrEmptySafeCheck(crimeSuspectRO.getTitle())) {
				crimeSuspect.setTitle(stringTrimmer(crimeSuspectRO.getTitle()));
			}
			// first name
			if (nullOrEmptySafeCheck(crimeSuspectRO.getFirstName())) {
				crimeSuspect.setFirstName(stringTrimmer(crimeSuspectRO.getFirstName()));
			}
			// middle name
			if (nullOrEmptySafeCheck(crimeSuspectRO.getMiddleName())) {
				crimeSuspect.setMiddleName(stringTrimmer(crimeSuspectRO.getMiddleName()));
			}
			// last name
			if (nullOrEmptySafeCheck(crimeSuspectRO.getLastName())) {
				crimeSuspect.setLastName(stringTrimmer(crimeSuspectRO.getLastName()));
			}
			// name suffix
			if (nullOrEmptySafeCheck(crimeSuspectRO.getNameSuffix())) {
				crimeSuspect.setNameSuffix(stringTrimmer(crimeSuspectRO.getNameSuffix()));
			}
			// gender type
			if (crimeSuspectRO.getGenderType() != null) {
				if (nullOrEmptySafeCheck(crimeSuspectRO.getGenderType().getId())) {
					crimeSuspect.setGenderType(_tableMaintenanceService.getGenderTypeByCode(stringTrimmer(crimeSuspectRO.getGenderType().getId())));
				}
			}
			// gender type other
			if (crimeSuspectRO.getGenderTypeOther() != null && !crimeSuspectRO.getGenderTypeOther().trim().isEmpty()) {
				crimeSuspect.setGenderTypeOther(crimeSuspectRO.getGenderTypeOther().trim());
			}
			// Crime Suspect Type
			if (crimeSuspectRO.getCrimeSuspectType() != null) {
				if (crimeSuspectRO.getCrimeSuspectType().getId() != null && !crimeSuspectRO.getCrimeSuspectType().getId().trim().isEmpty()) {
					crimeSuspect.setCrimeSuspectType(_tableMaintenanceService.getSuspectTypeByCode(crimeSuspectRO.getCrimeSuspectType().getId().trim()));
				}
			}
			// other comments for crime suspect type
			if (crimeSuspectRO.getCrimeSuspectTypeOther() != null && !crimeSuspectRO.getCrimeSuspectTypeOther().trim().isEmpty()) {
				crimeSuspect.setCrimeSuspectTypeOther(crimeSuspectRO.getCrimeSuspectTypeOther().trim());
			}
			// date of birth
			if (crimeSuspectRO.getDateOfBirth() != null) {
				crimeSuspect.setDateOfBirth(crimeSuspectRO.getDateOfBirth());
			}
			// age
			if (crimeSuspectRO.getAge() != null && crimeSuspectRO.getAge() > 0) {
				crimeSuspect.setAge(crimeSuspectRO.getAge());
			}
			// phone
			if (nullOrEmptySafeCheck(crimeSuspectRO.getPhone())) {
				crimeSuspect.setPhone(stringTrimmer(crimeSuspectRO.getPhone()));
			}
			// alternate phone
			if (nullOrEmptySafeCheck(crimeSuspectRO.getAlternatePhone())) {
				crimeSuspect.setAlternatePhone(stringTrimmer(crimeSuspectRO.getAlternatePhone()));
			}
			// email
			if (nullOrEmptySafeCheck(crimeSuspectRO.getEmail())) {
				crimeSuspect.setEmail(stringTrimmer(crimeSuspectRO.getEmail()));
			}
			// website
			if (nullOrEmptySafeCheck(crimeSuspectRO.getWebsite())) {
				crimeSuspect.setWebsite(stringTrimmer(crimeSuspectRO.getWebsite()));
			}
			// crime addresses if any
			if (crimeSuspectRO.getAddresses() != null) {
				crimeSuspect.setAddresses(_suspectRestService.createOrUpdateAddresses(crimeSuspectRO.getAddresses(), null, null, null, null, crimeSuspect, null));
			}
			// construct and set distinguishing feature details
			if (crimeSuspect.getDistinguishingFeatureDetails() == null || crimeSuspect.getDistinguishingFeatureDetails().isEmpty()) {
				crimeSuspect.setDistinguishingFeatureDetails(new HashSet<DistinguishingFeatureDetail>(0));
			}
			crimeSuspect.getDistinguishingFeatureDetails().addAll(_incidentRestService.constructDistinguishingFeatureDetails(crimeSuspectRO.getDistinguishingFeatureDetails()));
			// other comments for distinguishing feature
			if (crimeSuspectRO.getDistinguishingFeatureOther() != null && !crimeSuspectRO.getDistinguishingFeatureOther().trim().isEmpty()) {
				crimeSuspect.setDistinguishingFeatureOther(crimeSuspectRO.getDistinguishingFeatureOther().trim());
			}			
		}
		return crimeSuspect;
	}	
}
