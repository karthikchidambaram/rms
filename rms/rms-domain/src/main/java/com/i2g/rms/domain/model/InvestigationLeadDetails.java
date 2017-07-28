package com.i2g.rms.domain.model;

import java.io.Serializable;

public class InvestigationLeadDetails implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String investigationTeamLeadId;
	private String investigationTeamLeadTitle;
	private String investigationTeamLeadFirstName;
	private String investigationTeamLeadMiddleName;
	private String investigationTeamLeadLastName;
	
	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected InvestigationLeadDetails() {
	}

	/**
	 * @return the investigationTeamLeadId
	 */
	public String getInvestigationTeamLeadId() {
		return investigationTeamLeadId;
	}

	/**
	 * @param investigationTeamLeadId the investigationTeamLeadId to set
	 */
	public void setInvestigationTeamLeadId(final String investigationTeamLeadId) {
		this.investigationTeamLeadId = investigationTeamLeadId;
	}

	/**
	 * @return the investigationTeamLeadTitle
	 */
	public String getInvestigationTeamLeadTitle() {
		return investigationTeamLeadTitle;
	}

	/**
	 * @param investigationTeamLeadTitle the investigationTeamLeadTitle to set
	 */
	public void setInvestigationTeamLeadTitle(final String investigationTeamLeadTitle) {
		this.investigationTeamLeadTitle = investigationTeamLeadTitle;
	}

	/**
	 * @return the investigationTeamLeadFirstName
	 */
	public String getInvestigationTeamLeadFirstName() {
		return investigationTeamLeadFirstName;
	}

	/**
	 * @param investigationTeamLeadFirstName the investigationTeamLeadFirstName to set
	 */
	public void setInvestigationTeamLeadFirstName(final String investigationTeamLeadFirstName) {
		this.investigationTeamLeadFirstName = investigationTeamLeadFirstName;
	}

	/**
	 * @return the investigationTeamLeadMiddleName
	 */
	public String getInvestigationTeamLeadMiddleName() {
		return investigationTeamLeadMiddleName;
	}

	/**
	 * @param investigationTeamLeadMiddleName the investigationTeamLeadMiddleName to set
	 */
	public void setInvestigationTeamLeadMiddleName(final String investigationTeamLeadMiddleName) {
		this.investigationTeamLeadMiddleName = investigationTeamLeadMiddleName;
	}

	/**
	 * @return the investigationTeamLeadLastName
	 */
	public String getInvestigationTeamLeadLastName() {
		return investigationTeamLeadLastName;
	}

	/**
	 * @param investigationTeamLeadLastName the investigationTeamLeadLastName to set
	 */
	public void setInvestigationTeamLeadLastName(final String investigationTeamLeadLastName) {
		this.investigationTeamLeadLastName = investigationTeamLeadLastName;
	}	
}
