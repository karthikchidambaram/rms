package com.i2g.rms.domain.model;

import java.io.Serializable;

public class ClaimInvestigationLeadDetails implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String _claimInvestigationTeamLeadId;
	private String _claimInvestigationTeamLeadTitle;
	private String _claimInvestigationTeamLeadFirstName;
	private String _claimInvestigationTeamLeadMiddleName;
	private String _claimInvestigationTeamLeadLastName;
	
	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected ClaimInvestigationLeadDetails() {
	}

	/**
	 * @return the claimInvestigationTeamLeadId
	 */
	public String getInvestigationTeamLeadId() {
		return _claimInvestigationTeamLeadId;
	}

	/**
	 * @param claimInvestigationTeamLeadId the claimInvestigationTeamLeadId to set
	 */
	public void setInvestigationTeamLeadId(final String claimInvestigationTeamLeadId) {
		_claimInvestigationTeamLeadId = claimInvestigationTeamLeadId;
	}

	/**
	 * @return the claimInvestigationTeamLeadTitle
	 */
	public String getInvestigationTeamLeadTitle() {
		return _claimInvestigationTeamLeadTitle;
	}

	/**
	 * @param claimInvestigationTeamLeadTitle the claimInvestigationTeamLeadTitle to set
	 */
	public void setInvestigationTeamLeadTitle(final String claimInvestigationTeamLeadTitle) {
		_claimInvestigationTeamLeadTitle = claimInvestigationTeamLeadTitle;
	}

	/**
	 * @return the claimInvestigationTeamLeadFirstName
	 */
	public String getInvestigationTeamLeadFirstName() {
		return _claimInvestigationTeamLeadFirstName;
	}

	/**
	 * @param claimInvestigationTeamLeadFirstName the claimInvestigationTeamLeadFirstName to set
	 */
	public void setInvestigationTeamLeadFirstName(final String claimInvestigationTeamLeadFirstName) {
		_claimInvestigationTeamLeadFirstName = claimInvestigationTeamLeadFirstName;
	}

	/**
	 * @return the claimInvestigationTeamLeadMiddleName
	 */
	public String getInvestigationTeamLeadMiddleName() {
		return _claimInvestigationTeamLeadMiddleName;
	}

	/**
	 * @param claimInvestigationTeamLeadMiddleName the claimInvestigationTeamLeadMiddleName to set
	 */
	public void setInvestigationTeamLeadMiddleName(final String claimInvestigationTeamLeadMiddleName) {
		_claimInvestigationTeamLeadMiddleName = claimInvestigationTeamLeadMiddleName;
	}

	/**
	 * @return the claimInvestigationTeamLeadLastName
	 */
	public String getInvestigationTeamLeadLastName() {
		return _claimInvestigationTeamLeadLastName;
	}

	/**
	 * @param claimInvestigationTeamLeadLastName the claimInvestigationTeamLeadLastName to set
	 */
	public void setInvestigationTeamLeadLastName(final String claimInvestigationTeamLeadLastName) {
		_claimInvestigationTeamLeadLastName = claimInvestigationTeamLeadLastName;
	}	
}
