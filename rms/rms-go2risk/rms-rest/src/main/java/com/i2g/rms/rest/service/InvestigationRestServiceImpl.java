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

import com.i2g.rms.domain.model.Investigation;
import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.domain.model.User;
import com.i2g.rms.domain.model.YesNoType;
import com.i2g.rms.domain.model.incident.Incident;
import com.i2g.rms.rest.model.InvestigationRO;
import com.i2g.rms.rest.model.wrapper.InvestigationWrapper;
import com.i2g.rms.service.InvestigationService;
import com.i2g.rms.service.UserService;
import com.i2g.rms.service.exception.ResourceConflictException;
import com.i2g.rms.service.exception.ResourceNotCreatedException;
import com.i2g.rms.service.exception.ResourceNotUpdatedException;
import com.i2g.rms.service.exception.ResourceNotValidException;
import com.i2g.rms.service.incident.IncidentService;

/**
 * Rest services for investigation rest controller.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class InvestigationRestServiceImpl extends AbstractRestService implements InvestigationRestService {
	
	private final Logger _logger = LoggerFactory.getLogger(InvestigationRestServiceImpl.class);
	
	@Autowired
	private IncidentService _incidentService;
	@Autowired
	private InvestigationService _investigationService;
	@Autowired
	private UserService _userService;
		
	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR')")
	@Transactional(readOnly = true)
	public List<InvestigationRO> get() {
		final List<Investigation> investigations = _investigationService.get();
		final List<InvestigationRO> investigationROs = (investigations == null || investigations.isEmpty()) ? Collections.emptyList() : _mapperService.map(investigations, InvestigationRO.class);
		return investigationROs;
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR')")
	@Transactional(readOnly = true)
	public List<InvestigationRO> get(final String investigatorLoginId) {
		validateStringObject(investigatorLoginId);
		final User investigator = _userService.getUserByUserLoginId(investigatorLoginId.trim());
		validateGenericObject(investigator);
		final List<Investigation> investigations = _investigationService.get(investigator);
		final List<InvestigationRO> investigationROs = (investigations == null || investigations.isEmpty()) ? Collections.emptyList() : _mapperService.map(investigations, InvestigationRO.class);
		return investigationROs;
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR')")
	@Transactional(readOnly = true)
	public InvestigationRO get(final Long investigationId) {
		validateKeyId(investigationId);
		final Investigation investigation = _investigationService.get(investigationId);
		validateGenericObject(investigation);
		return _mapperService.map(investigation, InvestigationRO.class);		
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR')")
	@Transactional(readOnly = true)
	public InvestigationRO getInvestigationByIncidentId(final Long incidentId) {
		validateKeyId(incidentId);
		final Incident incident = _incidentService.get(incidentId);
		validateGenericObject(incident);
		final Investigation investigation = _investigationService.get(incident);
		validateGenericObject(investigation);
		return _mapperService.map(investigation, InvestigationRO.class);		
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR')")
	@Transactional(readOnly = true)
	public InvestigationRO getInvestigationByUniqueIncidentId(final String uniqueIncidentId) {
		validateUniqueIncidentId(uniqueIncidentId);
		final Incident incident = _incidentService.getIncidentByUniqueIncidentId(uniqueIncidentId.trim());
		validateGenericObject(incident);
		final Investigation investigation = _investigationService.get(incident);
		validateGenericObject(investigation);
		return _mapperService.map(investigation, InvestigationRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR')")
	@Transactional
	public InvestigationRO createInvestigation(final InvestigationRO investigationRO) {
		validateObject(investigationRO);
		final Incident incident = getIncident(investigationRO);
		validateGenericObject(incident);
		final Investigation existingInvestigation = _investigationService.get(incident);
		if (existingInvestigation != null) {
			throw new ResourceConflictException(_messageBuilder.build(RestMessage.INCIDENT_ALREADY_HAS_EXISTING_INVESTIGATION_RECORD));
		}
		final Investigation investigation = constructNewInvestigation(incident, investigationRO);
		validateGenericObject(investigation);
		final Investigation newInvestigation = _investigationService.createInvestigation(investigation);
		if (newInvestigation != null) {
			return _mapperService.map(newInvestigation, InvestigationRO.class);
		} else {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CREATE_RECORD));
		}	
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR')")
	@Transactional
	public InvestigationRO updateInvestigation(final InvestigationRO investigationRO) {
		validateObject(investigationRO);
		if (investigationRO.getId() <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.GENERIC_FETCH_FAILED_MESSAGE));
		}
		final Investigation investigation = constructInvestigation(investigationRO);
		validateGenericObject(investigation);
		final Investigation udpatedInvestigation = _investigationService.updateInvestigation(investigation);
		if (udpatedInvestigation != null) {
			return _mapperService.map(udpatedInvestigation, InvestigationRO.class);
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR')")
	@Transactional
	public InvestigationRO addOrUpdateInvestigation(final InvestigationRO investigationRO) {
		validateObject(investigationRO);
		if (investigationRO.getId() > 0) {
			return updateInvestigation(investigationRO);
		} else {
			return createInvestigation(investigationRO);
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR')")
	@Transactional
	public void deleteInvestigation(final Long investigationId) {
		validateKeyId(investigationId);
		final Investigation investigation = _investigationService.get(investigationId);
		validateGenericObject(investigation);
		_investigationService.deleteInvestigation(investigation);
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR')")
	@Transactional
	public void deleteInvestigations(final InvestigationWrapper investigationWrapper) {
		validateObject(investigationWrapper);
		validateCollectionObject(investigationWrapper.getInvestigations());
		Set<Investigation> investigations = new HashSet<Investigation>(0);
		for (InvestigationRO investigationRO : investigationWrapper.getInvestigations()) {
			if (investigationRO != null) {
				if (investigationRO.getId() > 0) {
					final Investigation investigation = _investigationService.get(investigationRO.getId());
					investigations.add(investigation);
				}
			}
		}
		validateCollectionObject(investigations);
		_investigationService.deleteInvestigations(investigations);
	}

	@Override
	public Investigation constructNewInvestigation(final Incident incident, final InvestigationRO investigationRO) {
		validateObject(incident);
		validateObject(investigationRO);
		Investigation investigation = new Investigation.Builder().setStatusFlag(StatusFlag.ACTIVE).setIncident(incident).build();
		return setOtherFieldsForInvestigation(investigation, investigationRO);
	}

	@Override
	public Investigation constructInvestigation(final InvestigationRO investigationRO) {
		validateObject(investigationRO);
		if (investigationRO.getId() <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.RECORD_FETCH_FAILED_FOR_UPDATE));
		}
		Investigation investigation = _investigationService.get(investigationRO.getId());
		return setOtherFieldsForInvestigation(investigation, investigationRO);
	}
	
	@Override
	public Investigation setOtherFieldsForInvestigation(final Investigation investigation, final InvestigationRO investigationRO) {
		if (investigation != null && investigationRO != null) {
			//set other values for investigations from the RO
			YesNoType securityRequested = YesNoType.N;
			YesNoType trainingRequested = YesNoType.N;
			YesNoType reviewedInvestigationRecords = YesNoType.N;
			YesNoType reviewedCCTV = YesNoType.N;
			YesNoType reviewedPictures = YesNoType.N;
			YesNoType reviewedWitnessStatement = YesNoType.N;
			YesNoType reviewedLearnerRecords = YesNoType.N;
			YesNoType reviewedAssetRecords = YesNoType.N;
			YesNoType reviewedComplianceRecords = YesNoType.N;
			
			if (investigationRO.getSecurityRequested() != null && investigationRO.getSecurityRequested().name().equals("Y")) {
				securityRequested = YesNoType.Y;
			}
			if (investigationRO.getTrainingRequested() != null && investigationRO.getTrainingRequested().name().equals("Y")) {
				trainingRequested = YesNoType.Y;
			}
			if (investigationRO.getReviewedInvestigationRecords() != null && investigationRO.getReviewedInvestigationRecords().name().equals("Y")) {
				reviewedInvestigationRecords = YesNoType.Y;
			}
			if (investigationRO.getReviewedCCTV() != null && investigationRO.getReviewedCCTV().name().equals("Y")) {
				reviewedCCTV = YesNoType.Y;
			}
			if (investigationRO.getReviewedPictures() != null && investigationRO.getReviewedPictures().name().equals("Y")) {
				reviewedPictures = YesNoType.Y;
			}
			if (investigationRO.getReviewedWitnessStatement() != null && investigationRO.getReviewedWitnessStatement().name().equals("Y")) {
				reviewedWitnessStatement = YesNoType.Y;
			}
			if (investigationRO.getReviewedLearnerRecords() != null && investigationRO.getReviewedLearnerRecords().name().equals("Y")) {
				reviewedLearnerRecords = YesNoType.Y;
			}
			if (investigationRO.getReviewedAssetRecords() != null && investigationRO.getReviewedAssetRecords().name().equals("Y")) {
				reviewedAssetRecords = YesNoType.Y;
			}
			if (investigationRO.getReviewedComplianceRecords() != null && investigationRO.getReviewedComplianceRecords().name().equals("Y")) {
				reviewedComplianceRecords = YesNoType.Y;
			}
			
			investigation.setSecurityRequested(securityRequested);
			investigation.setTrainingRequested(trainingRequested);
			investigation.setReviewedInvestigationRecords(reviewedInvestigationRecords);
			investigation.setReviewedCCTV(reviewedCCTV);
			investigation.setReviewedPictures(reviewedPictures);
			investigation.setReviewedWitnessStatement(reviewedWitnessStatement);
			investigation.setReviewedLearnerRecords(reviewedLearnerRecords);
			investigation.setReviewedAssetRecords(reviewedAssetRecords);
			investigation.setReviewedComplianceRecords(reviewedComplianceRecords);
			
			if (investigationRO.getInvestigator() != null) {
				if (investigationRO.getInvestigator().getLoginId() != null && !investigationRO.getInvestigator().getLoginId().trim().isEmpty()) {
					final User investigator = _userService.getUserByUserLoginId(investigationRO.getInvestigator().getLoginId().trim());
					if (investigator != null) {
						investigation.setInvestigator(investigator);
					}
				}
			}
			
			if (investigationRO.getInvestigatorStatement() != null && !investigationRO.getInvestigatorStatement().trim().isEmpty()) {
				investigation.setInvestigatorStatement(investigationRO.getInvestigatorStatement().trim());
			}
		}	
		return investigation;
	}
	
	private Incident getIncident(final InvestigationRO investigationRO) {
		Incident incident = null;
		if (investigationRO != null) {
			if (investigationRO.getIncident().getId() > 0) {
				incident = _incidentService.get(investigationRO.getIncident().getId());
			} else if (investigationRO.getIncident().getUniqueIncidentId() != null && !investigationRO.getIncident().getUniqueIncidentId().trim().isEmpty()) {
				incident = _incidentService.getIncidentByUniqueIncidentId(investigationRO.getIncident().getUniqueIncidentId().trim());
			}
		}
		return incident;
	}
}
