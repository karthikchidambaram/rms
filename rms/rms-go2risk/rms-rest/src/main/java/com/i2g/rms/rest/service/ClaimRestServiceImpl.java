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

import com.i2g.rms.domain.model.Claim;
import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.domain.model.User;
import com.i2g.rms.domain.model.YesNoType;
import com.i2g.rms.domain.model.incident.Incident;
import com.i2g.rms.rest.model.ClaimRO;
import com.i2g.rms.rest.model.wrapper.ClaimWrapper;
import com.i2g.rms.service.ClaimService;
import com.i2g.rms.service.UserService;
import com.i2g.rms.service.exception.ResourceConflictException;
import com.i2g.rms.service.exception.ResourceNotCreatedException;
import com.i2g.rms.service.exception.ResourceNotUpdatedException;
import com.i2g.rms.service.exception.ResourceNotValidException;
import com.i2g.rms.service.incident.IncidentService;
import com.i2g.rms.service.tablemaintenance.TableMaintenanceService;
import com.i2g.rms.util.ApplicationUtilService;

/**
 * Rest services for claim rest controller.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class ClaimRestServiceImpl extends AbstractRestService implements ClaimRestService {
	
	private final Logger _logger = LoggerFactory.getLogger(ClaimRestServiceImpl.class);
	
	@Autowired
	private TableMaintenanceService _tableMaintenanceService;
	@Autowired
	private IncidentService _incidentService;
	@Autowired
	private ClaimService _claimService;
	@Autowired
	private UserService _userService;
		
	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR')")
	@Transactional(readOnly = true)
	public List<ClaimRO> get() {
		final List<Claim> claims = _claimService.get();
		final List<ClaimRO> claimROs = (claims == null || claims.isEmpty()) ? Collections.emptyList() : _mapperService.map(claims, ClaimRO.class);
		return claimROs;
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR')")
	@Transactional(readOnly = true)
	public List<ClaimRO> get(final String claimHandlerLoginId) {
		validateStringObject(claimHandlerLoginId);
		final User claimHandler = _userService.getUserByUserLoginId(claimHandlerLoginId.trim());
		validateGenericObject(claimHandler);
		final List<Claim> claims = _claimService.get(claimHandler);
		final List<ClaimRO> claimROs = (claims == null || claims.isEmpty()) ? Collections.emptyList() : _mapperService.map(claims, ClaimRO.class);
		return claimROs;
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR')")
	@Transactional(readOnly = true)
	public ClaimRO get(final Long claimId) {
		validateKeyId(claimId);
		final Claim claim = _claimService.get(claimId);
		validateGenericObject(claim);
		return _mapperService.map(claim, ClaimRO.class);		
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR')")
	@Transactional(readOnly = true)
	public ClaimRO getClaimByIncidentId(final Long incidentId) {
		validateKeyId(incidentId);
		final Incident incident = _incidentService.get(incidentId);
		validateGenericObject(incident);
		final Claim claim = _claimService.get(incident);
		validateGenericObject(claim);
		return _mapperService.map(claim, ClaimRO.class);		
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR')")
	@Transactional(readOnly = true)
	public ClaimRO getClaimByUniqueIncidentId(final String uniqueIncidentId) {
		validateUniqueIncidentId(uniqueIncidentId);
		final Incident incident = _incidentService.getIncidentByUniqueIncidentId(uniqueIncidentId.trim());
		validateGenericObject(incident);
		final Claim claim = _claimService.get(incident);
		validateGenericObject(claim);
		return _mapperService.map(claim, ClaimRO.class);
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR')")
	@Transactional
	public ClaimRO createClaim(final ClaimRO claimRO) {
		validateObject(claimRO);
		final Incident incident = getIncident(claimRO);
		validateGenericObject(incident);
		final Claim existingClaim = _claimService.get(incident);
		if (existingClaim != null) {
			throw new ResourceConflictException(_messageBuilder.build(RestMessage.INCIDENT_ALREADY_HAS_EXISTING_CLAIM_RECORD));
		}
		final Claim claim = constructNewClaim(incident, claimRO);
		validateGenericObject(claim);
		final Claim newClaim = _claimService.createClaim(claim);
		if (newClaim != null) {
			return _mapperService.map(newClaim, ClaimRO.class);
		} else {
			throw new ResourceNotCreatedException(_messageBuilder.build(RestMessage.UNABLE_TO_CREATE_RECORD));
		}	
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR')")
	@Transactional
	public ClaimRO updateClaim(final ClaimRO claimRO) {
		validateObject(claimRO);
		if (claimRO.getId() <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.GENERIC_FETCH_FAILED_MESSAGE));
		}
		final Claim claim = constructClaim(claimRO);
		validateGenericObject(claim);
		final Claim udpatedClaim = _claimService.updateClaim(claim);
		if (udpatedClaim != null) {
			return _mapperService.map(udpatedClaim, ClaimRO.class);
		} else {
			throw new ResourceNotUpdatedException(_messageBuilder.build(RestMessage.UNABLE_TO_UPDATE_RECORD));
		}
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR')")
	@Transactional
	public ClaimRO addOrUpdateClaim(final ClaimRO claimRO) {
		validateObject(claimRO);
		if (claimRO.getId() > 0) {
			return updateClaim(claimRO);
		} else {
			return createClaim(claimRO);
		}
	}

	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR')")
	@Transactional
	public void deleteClaim(final Long claimId) {
		validateKeyId(claimId);
		final Claim claim = _claimService.get(claimId);
		validateGenericObject(claim);
		_claimService.deleteClaim(claim);
	}
	
	@Override
	@PreAuthorize("hasAnyAuthority('ADMIN', 'CLAIMS_HANDLER', 'INVESTIGATOR')")
	@Transactional
	public void deleteClaims(final ClaimWrapper claimWrapper) {
		validateObject(claimWrapper);
		validateCollectionObject(claimWrapper.getClaims());
		Set<Claim> claims = new HashSet<Claim>(0);
		for (ClaimRO claimRO : claimWrapper.getClaims()) {
			if (claimRO != null) {
				if (claimRO.getId() > 0) {
					final Claim claim = _claimService.get(claimRO.getId());
					claims.add(claim);
				}
			}
		}
		validateCollectionObject(claims);
		_claimService.deleteClaims(claims);
	}

	@Override
	public Claim constructNewClaim(final Incident incident, final ClaimRO claimRO) {
		validateObject(incident);
		validateObject(claimRO);
		Claim claim = new Claim.Builder().setStatusFlag(StatusFlag.ACTIVE).setIncident(incident).build();
		//claim id (auto generated)
		claim.setClaimId(ApplicationUtilService.getUniqueClaimId());
		return setOtherFieldsForClaim(claim, claimRO);
	}

	@Override
	public Claim constructClaim(final ClaimRO claimRO) {
		validateObject(claimRO);
		if (claimRO.getId() <= 0) {
			throw new ResourceNotValidException(_messageBuilder.build(RestMessage.RECORD_FETCH_FAILED_FOR_UPDATE));
		}
		Claim claim = _claimService.get(claimRO.getId());
		return setOtherFieldsForClaim(claim, claimRO);
	}
	
	@Override
	public Claim setOtherFieldsForClaim(final Claim claim, final ClaimRO claimRO) {
		if (claim != null && claimRO != null) {
			//set other values for claims from the RO
			//claim status
			if (claimRO.getClaimStatus() != null) {
				if (claimRO.getClaimStatus().getId() != null && !claimRO.getClaimStatus().getId().trim().isEmpty()) {
					claim.setClaimStatus(_tableMaintenanceService.getClaimStatusByCode(claimRO.getClaimStatus().getId().trim()));
				}				
			}
			//claim type
			if (claimRO.getClaimType() != null) {
				if (claimRO.getClaimType().getId() != null && !claimRO.getClaimType().getId().trim().isEmpty()) {
					claim.setClaimType(_tableMaintenanceService.getClaimTypeByCode(claimRO.getClaimType().getId().trim()));
				}				
			}
			//claim request registration type
			if (claimRO.getClaimRequestRegistrationType() != null) {
				if (claimRO.getClaimRequestRegistrationType().getId() != null && !claimRO.getClaimRequestRegistrationType().getId().trim().isEmpty()) {
					claim.setClaimRequestRegistrationType(_tableMaintenanceService.getClaimRequestRegistrationTypeByCode(claimRO.getClaimRequestRegistrationType().getId().trim()));
				}				
			}
			//claimant name
			if (claimRO.getClaimantName() != null && !claimRO.getClaimantName().trim().isEmpty()) {
				claim.setClaimantName(claimRO.getClaimantName().trim());
			}
			//insured name
			if (claimRO.getInsuredName() != null && !claimRO.getInsuredName().trim().isEmpty()) {
				claim.setInsuredName(claimRO.getInsuredName().trim());
			}
			//policy number
			if (claimRO.getPolicyNumber() != null && !claimRO.getPolicyNumber().trim().isEmpty()) {
				claim.setPolicyNumber(claimRO.getPolicyNumber().trim());
			}
			//policy holder name
			if (claimRO.getPolicyHolderName() != null && !claimRO.getPolicyHolderName().trim().isEmpty()) {
				claim.setPolicyHolderName(claimRO.getPolicyHolderName().trim());
			}
			//policy type
			if (claimRO.getPolicyType() != null) {
				if (claimRO.getPolicyType().getId() != null && !claimRO.getPolicyType().getId().trim().isEmpty()) {
					claim.setPolicyType(_tableMaintenanceService.getPolicyTypeByCode(claimRO.getPolicyType().getId().trim()));
				}				
			}
			//security requested
			YesNoType securityRequested = YesNoType.N;
			if (claimRO.getSecurityRequested() != null && claimRO.getSecurityRequested().name().equals("Y")) {
				securityRequested = YesNoType.Y;
			}
			claim.setSecurityRequested(securityRequested);			
			//training requested
			YesNoType trainingRequested = YesNoType.N;
			if (claimRO.getTrainingRequested() != null && claimRO.getTrainingRequested().name().equals("Y")) {
				trainingRequested = YesNoType.Y;
			}
			claim.setTrainingRequested(trainingRequested);
			//claim handler is automatically assigned by the batch program.
			//set other values for claim history
			//amount
			if (claimRO.getClaimRequestedAmount() != null) {
				claim.setClaimRequestedAmount(claimRO.getClaimRequestedAmount());
			}
			if (claimRO.getClaimApprovedAmount() != null) {
				claim.setClaimApprovedAmount(claimRO.getClaimApprovedAmount());
			}
			if (claimRO.getClaimSettlementAmount() != null) {
				claim.setClaimSettlementAmount(claimRO.getClaimSettlementAmount());
			}
			//date
			if (claimRO.getClaimRequestedDate() != null) {
				claim.setClaimRequestedDate(claimRO.getClaimRequestedDate());
			}
			if (claimRO.getClaimApprovedDate() != null) {
				claim.setClaimApprovedDate(claimRO.getClaimApprovedDate());
			}
			if (claimRO.getClaimSettlementDate() != null) {
				claim.setClaimSettlementDate(claimRO.getClaimSettlementDate());
			}
			if (claimRO.getClaimDeclinedDate() != null) {
				claim.setClaimDeclinedDate(claimRO.getClaimDeclinedDate());
			}
			if (claimRO.getClaimReopenedDate() != null) {
				claim.setClaimReopenedDate(claimRO.getClaimReopenedDate());
			}
			//requested by
			if (claimRO.getClaimRequestedBy() != null && !claimRO.getClaimRequestedBy().trim().isEmpty()) {
				claim.setClaimRequestedBy(claimRO.getClaimRequestedBy().trim());
			}			
			if (claimRO.getClaimApprovedBy() != null && !claimRO.getClaimApprovedBy().trim().isEmpty()) {
				claim.setClaimApprovedBy(claimRO.getClaimApprovedBy().trim());
			}
			if (claimRO.getClaimSettlementBy() != null && !claimRO.getClaimSettlementBy().trim().isEmpty()) {
				claim.setClaimSettlementBy(claimRO.getClaimSettlementBy().trim());
			}
			if (claimRO.getClaimDeclinedBy() != null && !claimRO.getClaimDeclinedBy().trim().isEmpty()) {
				claim.setClaimDeclinedBy(claimRO.getClaimDeclinedBy().trim());
			}
			if (claimRO.getClaimReopenedBy() != null && !claimRO.getClaimReopenedBy().trim().isEmpty()) {
				claim.setClaimReopenedBy(claimRO.getClaimReopenedBy().trim());
			}
			//comments
			if (claimRO.getClaimRequestedComments() != null && !claimRO.getClaimRequestedComments().trim().isEmpty()) {
				claim.setClaimRequestedComments(claimRO.getClaimRequestedComments().trim());
			}
			if (claimRO.getClaimApprovedComments() != null && !claimRO.getClaimApprovedComments().trim().isEmpty()) {
				claim.setClaimApprovedComments(claimRO.getClaimApprovedComments().trim());
			}
			if (claimRO.getClaimSettlementComments() != null && !claimRO.getClaimSettlementComments().trim().isEmpty()) {
				claim.setClaimSettlementComments(claimRO.getClaimSettlementComments().trim());
			}
			if (claimRO.getClaimDeclinedComments() != null && !claimRO.getClaimDeclinedComments().trim().isEmpty()) {
				claim.setClaimDeclinedComments(claimRO.getClaimDeclinedComments().trim());
			}
			if (claimRO.getClaimReopenedComments() != null && !claimRO.getClaimReopenedComments().trim().isEmpty()) {
				claim.setClaimReopenedComments(claimRO.getClaimReopenedComments().trim());
			}
			//fields for others
			if (claimRO.getClaimTypeOther() != null && !claimRO.getClaimTypeOther().trim().isEmpty()) {
				claim.setClaimTypeOther(claimRO.getClaimTypeOther().trim());
			}
			if (claimRO.getClaimRequestRegistrationTypeOther() != null && !claimRO.getClaimRequestRegistrationTypeOther().trim().isEmpty()) {
				claim.setClaimRequestRegistrationTypeOther(claimRO.getClaimRequestRegistrationTypeOther().trim());
			}
			if (claimRO.getPolicyTypeOther() != null && !claimRO.getPolicyTypeOther().trim().isEmpty()) {
				claim.setPolicyTypeOther(claimRO.getPolicyTypeOther());
			}
		}
		return claim;
	}
	
	private Incident getIncident(final ClaimRO claimRO) {
		Incident incident = null;
		if (claimRO != null) {
			if (claimRO.getIncident().getId() > 0) {
				incident = _incidentService.get(claimRO.getIncident().getId());
			} else if (claimRO.getIncident().getUniqueIncidentId() != null && !claimRO.getIncident().getUniqueIncidentId().trim().isEmpty()) {
				incident = _incidentService.getIncidentByUniqueIncidentId(claimRO.getIncident().getUniqueIncidentId().trim());
			}
		}
		return incident;
	}
}
