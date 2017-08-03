package com.i2g.rms.util.test.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * REST Object for returning investigation teams to the REST client.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class InvestigationTeamRO extends AbstractEntityRO {

	private long _id;
	private String _investigationTeamName;
	private String _investigationTeamDescription;
	private Set<UserRO> _users;

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

	public Set<UserRO> getUsers() {
		return _users;
	}

	public void setUsers(final Set<UserRO> users) {
		_users = users;
	}
}
