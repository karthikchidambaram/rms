package com.i2g.rms.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity representation of Managers.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_MGR_VW")
public class Manager extends AbstractDataModel<Long> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Manager login id */
	private long _id;
	private String _loginId;
	private String _title;
	private String _firstName;
	private String _middleName;
	private String _lastName;
	private String _phone;
	private String _alternatePhone;
	private String _email;
	private StatusFlag _statusFlag;
	
	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected Manager() {
	}

	/**
	 * Return the Manager primary key ID.
	 * 
	 * @return id
	 */
	@Id
	@Column(name = "MGR_ID")
	@Override
	public Long getId() {
		return _id;
	}

	public void setId(final long id) {
		_id = id;
	}
	
	@Column(name = "MGR_LGN_ID")
	public String getLoginId() {
		return _loginId;
	}

	public void setLoginId(final String loginId) {
		_loginId = loginId;		
	}
	
	/**
	 * @return the title
	 */
	@Column(name = "MGR_TITLE")
	public String getTitle() {
		return _title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(final String title) {
		_title = title;
	}

	/**
	 * @return the firstName
	 */
	@Column(name = "MGR_FNAME")
	public String getFirstName() {
		return _firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(final String firstName) {
		_firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	@Column(name = "MGR_MNAME")
	public String getMiddleName() {
		return _middleName;
	}

	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(final String middleName) {
		_middleName = middleName;
	}

	/**
	 * @return the lastName
	 */
	@Column(name = "MGR_LNAME")
	public String getLastName() {
		return _lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(final String lastName) {
		_lastName = lastName;
	}

	/**
	 * @return the phone
	 */
	@Column(name = "MGR_PHN")
	public String getPhone() {
		return _phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(final String phone) {
		_phone = phone;
	}

	/**
	 * @return the alternatePhone
	 */
	@Column(name = "MGR_ALT_PHN")
	public String getAlternatePhone() {
		return _alternatePhone;
	}

	/**
	 * @param alternatePhone the alternatePhone to set
	 */
	public void setAlternatePhone(final String alternatePhone) {
		_alternatePhone = alternatePhone;
	}
	
	/**
	 * @return the email
	 */
	@Column(name = "MGR_EML")
	public String getEmail() {
		return _email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(final String email) {
		_email = email;
	}
	
	@Column(name = "MGR_STS_FLG")
	@Enumerated(EnumType.STRING)
	public StatusFlag getStatusFlag() {
		return _statusFlag;
	}

	public void setStatusFlag(final StatusFlag statusFlag) {
		_statusFlag = statusFlag;
	}
}
