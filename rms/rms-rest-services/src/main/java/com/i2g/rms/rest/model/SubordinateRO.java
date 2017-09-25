package com.i2g.rms.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * REST Object for returning subordinate details to the REST client.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubordinateRO extends AbstractEntityRO {

	// User columns
	private long id;
	private String userLoginId;
	private String userTitle;
	private String userFirstName;
	private String userMiddleName;
	private String userLastName;
	private String userPhone;
	private String userAlternatePhone;
	private String userEmail;
	private StatusFlagRO userStatusFlag;
	// Manager columns
	private Long managerId;
	private String managerLoginId;
	private String managerTitle;
	private String managerFirstName;
	private String managerMiddleName;
	private String managerLastName;
	private String managerPhone;
	private String managerAlternatePhone;
	private String managerEmail;
	private StatusFlagRO managerStatusFlag;

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public String getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(final String userLoginId) {
		this.userLoginId = userLoginId;
	}

	public String getUserTitle() {
		return userTitle;
	}

	public void setUserTitle(final String userTitle) {
		this.userTitle = userTitle;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(final String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserMiddleName() {
		return userMiddleName;
	}

	public void setUserMiddleName(final String userMiddleName) {
		this.userMiddleName = userMiddleName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(final String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(final String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserAlternatePhone() {
		return userAlternatePhone;
	}

	public void setUserAlternatePhone(final String userAlternatePhone) {
		this.userAlternatePhone = userAlternatePhone;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(final String userEmail) {
		this.userEmail = userEmail;
	}

	public StatusFlagRO getUserStatusFlag() {
		return userStatusFlag;
	}

	public void setUserStatusFlag(final StatusFlagRO userStatusFlag) {
		this.userStatusFlag = userStatusFlag;
	}

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(final Long managerId) {
		if (managerId != null) {
			this.managerId = managerId;
		} else {
			this.managerId = 0l;
		}
	}

	public String getManagerLoginId() {
		return managerLoginId;
	}

	public void setManagerLoginId(final String managerLoginId) {
		this.managerLoginId = managerLoginId;
	}

	public String getManagerTitle() {
		return managerTitle;
	}

	public void setManagerTitle(final String managerTitle) {
		this.managerTitle = managerTitle;
	}

	public String getManagerFirstName() {
		return managerFirstName;
	}

	public void setManagerFirstName(final String managerFirstName) {
		this.managerFirstName = managerFirstName;
	}

	public String getManagerMiddleName() {
		return managerMiddleName;
	}

	public void setManagerMiddleName(final String managerMiddleName) {
		this.managerMiddleName = managerMiddleName;
	}

	public String getManagerLastName() {
		return managerLastName;
	}

	public void setManagerLastName(final String managerLastName) {
		this.managerLastName = managerLastName;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(final String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public String getManagerAlternatePhone() {
		return managerAlternatePhone;
	}

	public void setManagerAlternatePhone(final String managerAlternatePhone) {
		this.managerAlternatePhone = managerAlternatePhone;
	}

	public String getManagerEmail() {
		return managerEmail;
	}

	public void setManagerEmail(final String managerEmail) {
		this.managerEmail = managerEmail;
	}

	public StatusFlagRO getManagerStatusFlag() {
		return managerStatusFlag;
	}

	public void setManagerStatusFlag(final StatusFlagRO managerStatusFlag) {
		this.managerStatusFlag = managerStatusFlag;
	}
}
