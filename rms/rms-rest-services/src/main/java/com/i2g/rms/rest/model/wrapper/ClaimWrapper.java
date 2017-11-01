package com.i2g.rms.rest.model.wrapper;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.ClaimRO;
import com.i2g.rms.rest.model.incident.BaseIncidentDetailRO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClaimWrapper extends BaseIncidentDetailRO {

	private Long claimId;
	private String claimHandlerLoginId;
	private Set<ClaimRO> claims = new HashSet<ClaimRO>(0);

	public Set<ClaimRO> getClaims() {
		return claims;
	}

	public void setClaims(final Set<ClaimRO> claims) {
		this.claims = claims;
	}

	public Long getClaimId() {
		return claimId;
	}

	public void setClaimId(final Long claimId) {
		this.claimId = claimId;
	}

	public String getClaimHandlerLoginId() {
		return claimHandlerLoginId;
	}

	public void setClaimHandlerLoginId(final String claimHandlerLoginId) {
		this.claimHandlerLoginId = claimHandlerLoginId;
	}	
}
