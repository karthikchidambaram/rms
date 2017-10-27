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

import com.i2g.rms.domain.model.InjuredPerson;
import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.domain.model.YesNoType;
import com.i2g.rms.domain.model.tablemaintenance.BodyPart;
import com.i2g.rms.domain.model.tablemaintenance.DistinguishingFeatureDetail;
import com.i2g.rms.rest.model.InjuredPersonRO;
import com.i2g.rms.rest.model.tablemaintenance.DistinguishingFeatureDetailRO;
import com.i2g.rms.rest.model.wrapper.BodyPartWrapper;
import com.i2g.rms.rest.model.wrapper.DistinguishingFeatureDetailWrapper;
import com.i2g.rms.rest.model.wrapper.InjuredPersonWrapper;
import com.i2g.rms.rest.service.incident.IncidentRestService;
import com.i2g.rms.service.InjuredPersonService;
import com.i2g.rms.service.exception.ResourceNotCreatedException;
import com.i2g.rms.service.exception.ResourceNotRemovedException;
import com.i2g.rms.service.exception.ResourceNotUpdatedException;
import com.i2g.rms.service.exception.ResourceNotValidException;
import com.i2g.rms.service.tablemaintenance.TableMaintenanceService;

/**
 * Rest services for injured person rest controller.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class InjuredPersonRestServiceImpl extends AbstractRestService implements InjuredPersonRestService {
	
	@SuppressWarnings("unused")
	private final Logger _logger = LoggerFactory.getLogger(InjuredPersonRestServiceImpl.class);
	
	@Autowired
	private InjuredPersonService _injuredPersonService;
	@Autowired
	private TableMaintenanceService _tableMaintenanceService;
	@Autowired
	private IncidentRestService _incidentRestService;
	@Autowired
	private SuspectRestService _suspectRestService;
	
	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public List<InjuredPersonRO> get() {
		List<InjuredPerson> injuredPersons = _injuredPersonService.get();
		List<InjuredPersonRO> injuredPersonROs = injuredPersons.isEmpty() ? Collections.emptyList() : _mapperService.map(injuredPersons, InjuredPersonRO.class);
		return injuredPersonROs;
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional(readOnly = true)
	public InjuredPersonRO get(final Long injuredPersonId) {
		if (injuredPersonId != null && injuredPersonId > 0) {
			final InjuredPerson injuredPerson = _injuredPersonService.get(injuredPersonId);
			validateGenericObject(injuredPerson);
			return _mapperService.map(injuredPerson, InjuredPersonRO.class);
		} else {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.GENERIC_FETCH_FAILED_MESSAGE));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public InjuredPersonRO createInjuredPerson(final InjuredPersonRO injuredPersonRO) {
		validateObject(injuredPersonRO);
		final InjuredPerson injuredPerson = constructNewInjuredPerson(injuredPersonRO);
		validateGenericObject(injuredPerson);		
		final InjuredPerson newInjuredPerson = _injuredPersonService.createInjuredPerson(injuredPerson);
		if (newInjuredPerson != null) {
			return _mapperService.map(newInjuredPerson, InjuredPersonRO.class);
		} else {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CREATE_RECORD));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public Set<InjuredPersonRO> createInjuredPersons(final InjuredPersonWrapper injuredPersonWrapper) {
		validateObject(injuredPersonWrapper);
		validateCollectionObject(injuredPersonWrapper.getInjuredPersons());
		final Set<InjuredPerson> injuredPersons = new HashSet<InjuredPerson>(0);
		if (injuredPersonWrapper.getInjuredPersons() != null && !injuredPersonWrapper.getInjuredPersons().isEmpty()) {
			for (InjuredPersonRO injuredPersonRO : injuredPersonWrapper.getInjuredPersons()) {
				if (injuredPersonRO != null) {
					final InjuredPerson injuredPerson = constructNewInjuredPerson(injuredPersonRO);
					if (injuredPerson != null) {
						injuredPersons.add(injuredPerson);
					}
				}
			}
		}		
		if (injuredPersons != null && !injuredPersons.isEmpty()) {
			final Set<InjuredPerson> newInjuredPersons = _injuredPersonService.createInjuredPersons(injuredPersons);
			if (newInjuredPersons != null && !newInjuredPersons.isEmpty()) {
				return _mapperService.map(newInjuredPersons, InjuredPersonRO.class);
			} else {
				throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CREATE_RECORD));
			}
		} else {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CONSTRUCT_OBJECT_FOR_RECORD_CREATION));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public InjuredPersonRO updateInjuredPerson(final InjuredPersonRO injuredPersonRO) {
		validateObject(injuredPersonRO);
		final InjuredPerson injuredPerson = constructInjuredPerson(injuredPersonRO);
		validateGenericObject(injuredPerson);
		final InjuredPerson updatedInjuredPerson = _injuredPersonService.updateInjuredPerson(injuredPerson);
		if (updatedInjuredPerson != null) {
			return _mapperService.map(updatedInjuredPerson, InjuredPersonRO.class);
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public Set<InjuredPersonRO> updateInjuredPersons(final InjuredPersonWrapper injuredPersonWrapper) {
		validateObject(injuredPersonWrapper);
		validateCollectionObject(injuredPersonWrapper.getInjuredPersons());
		final Set<InjuredPerson> injuredPersons = new HashSet<InjuredPerson>(0);
		if (injuredPersonWrapper.getInjuredPersons() != null && !injuredPersonWrapper.getInjuredPersons().isEmpty()) {
			for (InjuredPersonRO injuredPersonRO : injuredPersonWrapper.getInjuredPersons()) {
				if (injuredPersonRO != null) {
					final InjuredPerson injuredPerson = constructInjuredPerson(injuredPersonRO);
					if (injuredPerson != null) {
						injuredPersons.add(injuredPerson);
					}
				}
			}
		}		
		if (injuredPersons != null && !injuredPersons.isEmpty()) {
			final Set<InjuredPerson> updatedInjuredPersons = _injuredPersonService.updateInjuredPersons(injuredPersons);
			if (updatedInjuredPersons != null && !updatedInjuredPersons.isEmpty()) {
				return _mapperService.map(updatedInjuredPersons, InjuredPersonRO.class);
			} else {
				throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
			}
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CONSTRUCT_OBJECT_FOR_RECORD_CREATION));
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public void removeDistinguishingFeatureDetailsFromInjuredPerson(final DistinguishingFeatureDetailWrapper distinguishingFeatureDetailWrapper) {
		validateObject(distinguishingFeatureDetailWrapper);
		validateCollectionObject(distinguishingFeatureDetailWrapper.getDistinguishingFeatureDetails());
		if (distinguishingFeatureDetailWrapper.getInjuredPersonId() == null || distinguishingFeatureDetailWrapper.getInjuredPersonId() <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.GENERIC_FETCH_FAILED_MESSAGE));
		}
		InjuredPerson injuredPerson = _injuredPersonService.get(distinguishingFeatureDetailWrapper.getInjuredPersonId());
		validateGenericObject(injuredPerson);
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
		_injuredPersonService.removeDistinguishingFeatureDetailsFromInjuredPerson(injuredPerson, distinguishingFeatureDetails);		
	}

	@Override
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR', 'SUPERVISOR')")
	@Transactional
	public void removeBodyPartsFromInjuredPerson(final BodyPartWrapper bodyPartWrapper) {
		validateObject(bodyPartWrapper);
		validateCollectionObject(bodyPartWrapper.getBodyParts());
		if (bodyPartWrapper.getInjuredPersonId() == null || bodyPartWrapper.getInjuredPersonId() <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.GENERIC_FETCH_FAILED_MESSAGE));
		}
		final InjuredPerson injuredPerson = _injuredPersonService.get(bodyPartWrapper.getInjuredPersonId());
		validateGenericObject(injuredPerson);
		final Set<BodyPart> bodyParts = _incidentRestService.constructBodyParts(bodyPartWrapper.getBodyParts());
		if (bodyParts != null && !bodyParts.isEmpty()) {
			_injuredPersonService.removeBodyPartsFromInjuredPerson(injuredPerson, bodyParts);
		} else {
			throw new ResourceNotRemovedException(_messageBuilder.build(RestMessage.NO_RECORDS_WERE_DELETED));
		}
	}

	@Override
	public InjuredPerson constructNewInjuredPerson(final InjuredPersonRO injuredPersonRO) {
		validateObject(injuredPersonRO);
		final InjuredPerson injuredPerson = new InjuredPerson.Builder().setStatusFlag(StatusFlag.ACTIVE).build();
		validateGenericObject(injuredPerson);
		return setOtherFieldsForInjuredPerson(injuredPerson, injuredPersonRO);
	}
	
	@Override
	public InjuredPerson constructInjuredPerson(final InjuredPersonRO injuredPersonRO) {
		validateObject(injuredPersonRO);
		final InjuredPerson injuredPerson = _injuredPersonService.get(injuredPersonRO.getId());
		validateGenericObject(injuredPerson);
		return setOtherFieldsForInjuredPerson(injuredPerson, injuredPersonRO);
	}
	
	@Override
	public InjuredPerson setOtherFieldsForInjuredPerson(final InjuredPerson injuredPerson, final InjuredPersonRO injuredPersonRO) {
		if (injuredPerson != null && injuredPersonRO != null) {
			// Set other values for (new) injured person(s)
			// Injured person type
			if (injuredPersonRO.getInjuredPersonType() != null) {
				if (injuredPersonRO.getInjuredPersonType().getId() != null && !injuredPersonRO.getInjuredPersonType().getId().trim().isEmpty()) {
					injuredPerson.setInjuredPersonType(_tableMaintenanceService.getInjuredPersonTypeByCode(injuredPersonRO.getInjuredPersonType().getId().trim()));
				}
			}
			if (injuredPersonRO.getTitle() != null && !injuredPersonRO.getTitle().trim().isEmpty()) {
				injuredPerson.setTitle(injuredPersonRO.getTitle().trim());
			}
			if (injuredPersonRO.getFirstName() != null && !injuredPersonRO.getFirstName().trim().isEmpty()) {
				injuredPerson.setFirstName(injuredPersonRO.getFirstName().trim());
			}
			if (injuredPersonRO.getMiddleName() != null && !injuredPersonRO.getMiddleName().trim().isEmpty()) {
				injuredPerson.setMiddleName(injuredPersonRO.getMiddleName().trim());
			}
			if (injuredPersonRO.getLastName() != null && !injuredPersonRO.getLastName().trim().isEmpty()) {
				injuredPerson.setLastName(injuredPersonRO.getLastName().trim());
			}
			if (injuredPersonRO.getNameSuffix() != null && !injuredPersonRO.getNameSuffix().trim().isEmpty()) {
				injuredPerson.setNameSuffix(injuredPersonRO.getNameSuffix());
			}
			if (injuredPersonRO.getPhone() != null && !injuredPersonRO.getPhone().trim().isEmpty()) {
				injuredPerson.setPhone(injuredPersonRO.getPhone().trim());
			}
			if (injuredPersonRO.getFax() != null && !injuredPersonRO.getFax().trim().isEmpty()) {
				injuredPerson.setFax(injuredPersonRO.getFax().trim());
			}
			if (injuredPersonRO.getAlternatePhone() != null && !injuredPersonRO.getAlternatePhone().trim().isEmpty()) {
				injuredPerson.setAlternatePhone(injuredPersonRO.getAlternatePhone().trim());
			}
			if (injuredPersonRO.getEmail() != null && !injuredPersonRO.getEmail().trim().isEmpty()) {
				injuredPerson.setEmail(injuredPersonRO.getEmail().trim());
			}
			if (injuredPersonRO.getWebsite() != null && !injuredPersonRO.getWebsite().trim().isEmpty()) {
				injuredPerson.setWebsite(injuredPersonRO.getWebsite().trim());
			}
			// Gender type
			if (injuredPersonRO.getGenderType() != null) {
				if (injuredPersonRO.getGenderType().getId() != null && !injuredPersonRO.getGenderType().getId().trim().isEmpty()) {
					injuredPerson.setGenderType(_tableMaintenanceService.getGenderTypeByCode(injuredPersonRO.getGenderType().getId().trim()));
				}
			}
			// gender type other
			if (injuredPersonRO.getGenderTypeOther() != null && !injuredPersonRO.getGenderTypeOther().trim().isEmpty()) {
				injuredPerson.setGenderTypeOther(injuredPersonRO.getGenderTypeOther().trim());
			}
			// Injury Cause
			if (injuredPersonRO.getInjuryCause() != null) {
				if (injuredPersonRO.getInjuryCause().getId() != null && !injuredPersonRO.getInjuryCause().getId().trim().isEmpty()) {
					injuredPerson.setInjuryCause(_tableMaintenanceService.getInjuryCauseByCode(injuredPersonRO.getInjuryCause().getId().trim()));
				}
			}
			// injury cause other
			if (injuredPersonRO.getInjuryCauseOther() != null && injuredPersonRO.getInjuryCauseOther().trim().isEmpty()) {
				injuredPerson.setInjuryCauseOther(injuredPersonRO.getInjuryCauseOther());
			}
			// Injury type
			if (injuredPersonRO.getInjuryType() != null) {
				if (injuredPersonRO.getInjuryType().getId() != null && !injuredPersonRO.getInjuryType().getId().trim().isEmpty()) {
					injuredPerson.setInjuryType(_tableMaintenanceService.getInjuryTypeByCode(injuredPersonRO.getInjuryType().getId().trim()));
				}
			}
			// Injury type detail
			if (injuredPersonRO.getInjuryTypeDetail() != null) {
				if (injuredPersonRO.getInjuryTypeDetail().getId() != null && !injuredPersonRO.getInjuryTypeDetail().getId().trim().isEmpty()) {
					injuredPerson.setInjuryTypeDetail(_tableMaintenanceService.getInjuryTypeDetailByCode(injuredPersonRO.getInjuryTypeDetail().getId().trim()));
				}
			}
			// Injury type detail spec
			if (injuredPersonRO.getInjuryTypeDetailSpec() != null) {
				if (injuredPersonRO.getInjuryTypeDetailSpec().getId() != null && !injuredPersonRO.getInjuryTypeDetailSpec().getId().trim().isEmpty()) {
					injuredPerson.setInjuryTypeDetailSpec(_tableMaintenanceService.getInjuryTypeDetailSpecByCode(injuredPersonRO.getInjuryTypeDetailSpec().getId().trim()));
				}
			}
			// injury type other
			if (injuredPersonRO.getInjuryTypeOther() != null
					&& !injuredPersonRO.getInjuryTypeOther().trim().isEmpty()) {
				injuredPerson.setInjuryTypeOther(injuredPersonRO.getInjuryTypeOther().trim());
			}
			// Age
			if (injuredPersonRO.getAge() != null && injuredPersonRO.getAge() > 0) {
				injuredPerson.setAge(injuredPersonRO.getAge());
			}
			// Date of Birth
			if (injuredPersonRO.getDateOfBirth() != null) {
				injuredPerson.setDateOfBirth(injuredPersonRO.getDateOfBirth());
			}
			// Any first aid given?
			YesNoType firstAidGiven = YesNoType.N;
			if (injuredPersonRO.getFirstAidGiven() != null && injuredPersonRO.getFirstAidGiven().name().equals("Y")) {
				firstAidGiven = YesNoType.Y;
			}
			injuredPerson.setFirstAidGiven(firstAidGiven);
			// add addresses of the injured person
			if (injuredPersonRO.getAddresses() != null && !injuredPersonRO.getAddresses().isEmpty()) {
				injuredPerson.setAddresses(_suspectRestService.createOrUpdateAddresses(injuredPersonRO.getAddresses(), null, null, injuredPerson, null, null, null));
			}
			// body parts
			if (injuredPersonRO.getBodyParts() != null && !injuredPersonRO.getBodyParts().isEmpty()) {
				injuredPerson.setBodyParts(_incidentRestService.constructBodyParts(injuredPersonRO.getBodyParts()));
			}
			// other comments for injured person type
			if (injuredPersonRO.getInjuredPersonTypeOther() != null && !injuredPersonRO.getInjuredPersonTypeOther().trim().isEmpty()) {
				injuredPerson.setInjuredPersonTypeOther(injuredPersonRO.getInjuredPersonTypeOther().trim());
			}
			// construct and set distinguishing feature details
			if (injuredPerson.getDistinguishingFeatureDetails() == null || injuredPerson.getDistinguishingFeatureDetails().isEmpty()) {
				injuredPerson.setDistinguishingFeatureDetails(new HashSet<DistinguishingFeatureDetail>(0));
			}
			injuredPerson.getDistinguishingFeatureDetails().addAll(_incidentRestService.constructDistinguishingFeatureDetails(injuredPersonRO.getDistinguishingFeatureDetails()));
			// other comments for distinguishing feature
			if (injuredPersonRO.getDistinguishingFeatureOther() != null && !injuredPersonRO.getDistinguishingFeatureOther().trim().isEmpty()) {
				injuredPerson.setDistinguishingFeatureOther(injuredPersonRO.getDistinguishingFeatureOther().trim());
			}
			//add address of the injured person if any
			if (injuredPersonRO.getAddresses() != null && !injuredPersonRO.getAddresses().isEmpty()) {
				injuredPerson.setAddresses(_suspectRestService.createOrUpdateAddresses(injuredPersonRO.getAddresses(), null, null, injuredPerson, null, null, null));
			}
			return injuredPerson;
		} else {
			return null;
		}
	}
}
