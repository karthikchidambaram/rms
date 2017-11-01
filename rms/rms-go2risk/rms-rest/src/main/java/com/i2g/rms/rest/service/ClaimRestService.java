package com.i2g.rms.rest.service;

import java.util.List;

import com.i2g.rms.domain.model.Claim;
import com.i2g.rms.domain.model.incident.Incident;
import com.i2g.rms.rest.model.ClaimRO;
import com.i2g.rms.rest.model.wrapper.ClaimWrapper;

/**
 * Rest Service Interface for claim rest services.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface ClaimRestService {

	public List<ClaimRO> get();
	
	public List<ClaimRO> get(final String claimHandlerLoginId);

	public ClaimRO get(final Long claimId);
	
	public ClaimRO getClaimByIncidentId(final Long incidentId);
	
	public ClaimRO getClaimByUniqueIncidentId(final String uniqueIncidentId);

	public ClaimRO createClaim(final ClaimRO claimRO);

	public ClaimRO updateClaim(final ClaimRO claimRO);
	
	public ClaimRO addOrUpdateClaim(final ClaimRO claimRO);

	public void deleteClaim(final Long claimId);

	public void deleteClaims(final ClaimWrapper claimWrapper);

	public Claim constructNewClaim(final Incident incident, final ClaimRO claimRO);

	public Claim constructClaim(final ClaimRO claimRO);

	public Claim setOtherFieldsForClaim(final Claim claim, final ClaimRO claimRO);

}
