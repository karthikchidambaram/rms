package com.i2g.rms.rest.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * REST Object for returning investigation teams to the REST client.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InvestigationTeamRO extends AbstractEntityRO {

	private long _id;
	private String _investigationTeamName;
	private String _investigationTeamDescription;
	private Set<UserRO> _teamLeads = new HashSet<UserRO>(0);

	public long getId() {
		return _id;
	}

	public void setId(long id) {
		_id = id;
	}

	public String getInvestigationTeamName() {
		return _investigationTeamName;
	}

	public void setInvestigationTeamName(final String investigationTeamName) {
		_investigationTeamName = investigationTeamName;
	}

	public String getInvestigationTeamDescription() {
		return _investigationTeamDescription;
	}

	public void setInvestigationTeamDescription(final String investigationTeamDescription) {
		_investigationTeamDescription = investigationTeamDescription;
	}

	public Set<UserRO> getTeamLeads() {
		return _teamLeads;
	}

	public void setTeamLeads(final Set<UserRO> teamLeads) {
		_teamLeads = teamLeads;
	}
}
