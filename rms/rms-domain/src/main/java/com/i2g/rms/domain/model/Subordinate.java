package com.i2g.rms.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity representation of Managers and their subordinates.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_MGR_SUBORDNTS_VW")
public class Subordinate extends AbstractDataModel<Long> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private StatusFlag userStatusFlag;
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
	private StatusFlag managerStatusFlag;

	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected Subordinate() {
	}

	/**
	 * Return the Manager primary key ID.
	 * 
	 * @return id
	 */
	@Id
	@Column(name = "USR_ID")
	@Override
	public Long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	@Column(name = "USR_LGN_ID")
	public String getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(final String userLoginId) {
		this.userLoginId = userLoginId;
	}

	@Column(name = "USR_TITLE")
	public String getUserTitle() {
		return userTitle;
	}

	public void setUserTitle(final String userTitle) {
		this.userTitle = userTitle;
	}

	@Column(name = "USR_FNAME")
	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(final String userFirstName) {
		this.userFirstName = userFirstName;
	}

	@Column(name = "USR_MNAME")
	public String getUserMiddleName() {
		return userMiddleName;
	}

	public void setUserMiddleName(final String userMiddleName) {
		this.userMiddleName = userMiddleName;
	}

	@Column(name = "USR_LNAME")
	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(final String userLastName) {
		this.userLastName = userLastName;
	}

	@Column(name = "USR_PHN")
	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(final String userPhone) {
		this.userPhone = userPhone;
	}

	@Column(name = "USR_ALT_PHN")
	public String getUserAlternatePhone() {
		return userAlternatePhone;
	}

	public void setUserAlternatePhone(final String userAlternatePhone) {
		this.userAlternatePhone = userAlternatePhone;
	}

	@Column(name = "USR_EML")
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(final String userEmail) {
		this.userEmail = userEmail;
	}

	@Column(name = "USR_STS_FLG")
	@Enumerated(EnumType.STRING)
	public StatusFlag getUserStatusFlag() {
		return userStatusFlag;
	}

	public void setUserStatusFlag(final StatusFlag userStatusFlag) {
		this.userStatusFlag = userStatusFlag;
	}

	@Column(name = "MGR_ID")
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

	@Column(name = "MGR_LGN_ID")
	public String getManagerLoginId() {
		return managerLoginId;
	}

	public void setManagerLoginId(final String managerLoginId) {
		this.managerLoginId = managerLoginId;
	}

	@Column(name = "MGR_TITLE")
	public String getManagerTitle() {
		return managerTitle;
	}

	public void setManagerTitle(final String managerTitle) {
		this.managerTitle = managerTitle;
	}

	@Column(name = "MGR_FNAME")
	public String getManagerFirstName() {
		return managerFirstName;
	}

	public void setManagerFirstName(final String managerFirstName) {
		this.managerFirstName = managerFirstName;
	}

	@Column(name = "MGR_MNAME")
	public String getManagerMiddleName() {
		return managerMiddleName;
	}

	public void setManagerMiddleName(final String managerMiddleName) {
		this.managerMiddleName = managerMiddleName;
	}

	@Column(name = "MGR_LNAME")
	public String getManagerLastName() {
		return managerLastName;
	}

	public void setManagerLastName(final String managerLastName) {
		this.managerLastName = managerLastName;
	}

	@Column(name = "MGR_PHN")
	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(final String managerPhone) {
		this.managerPhone = managerPhone;
	}

	@Column(name = "MGR_ALT_PHN")
	public String getManagerAlternatePhone() {
		return managerAlternatePhone;
	}

	public void setManagerAlternatePhone(final String managerAlternatePhone) {
		this.managerAlternatePhone = managerAlternatePhone;
	}

	@Column(name = "MGR_EML")
	public String getManagerEmail() {
		return managerEmail;
	}

	public void setManagerEmail(final String managerEmail) {
		this.managerEmail = managerEmail;
	}

	@Column(name = "MGR_STS_FLG")
	@Enumerated(EnumType.STRING)
	public StatusFlag getManagerStatusFlag() {
		return managerStatusFlag;
	}

	public void setManagerStatusFlag(final StatusFlag managerStatusFlag) {
		this.managerStatusFlag = managerStatusFlag;
	}
}