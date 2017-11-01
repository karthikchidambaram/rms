package com.i2g.rms.rest.model.incident;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.ClaimRO;

/**
 * Search Claim Details RO Object
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClaimDetailRO extends BaseIncidentDetailRO {

	private ClaimRO claim;

	public ClaimRO getClaim() {
		return claim;
	}

	public void setClaim(final ClaimRO claim) {
		this.claim = claim;
	}
}
