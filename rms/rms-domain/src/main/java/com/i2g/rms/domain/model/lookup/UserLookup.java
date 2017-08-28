package com.i2g.rms.domain.model.lookup;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.i2g.rms.domain.model.AbstractDataModel;
import com.i2g.rms.domain.model.StatusFlag;

/**
 * User lookup entity object based on a view.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_USR_LKUP_VW")
public class UserLookup extends AbstractDataModel<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	private String userLoginId;
	private String title;
	private String firstName;
	private String middleName;
	private String lastName;
	private String nameSuffix;
	private LocalDate dateOfBirth;
	private Integer age;
	private LocalDate dateOfJoining;
	private LocalDate dateOfLeaving;
	private StatusFlag statusFlag;
	private String genderTypeCode;
	private String genderTypeDescription;
	private String phone;
	private String alternatePhone;
	private String email;
	private String employeeId;
	private Long managerId;
	private String managerName;
	private String employeeTypeCode;
	private String employeeTypeDescription;
	private String positionCode;
	private String positionDescription;
	private String organizationCode;
	private String organizationDescription;
	private String departmentCode;
	private String departmentDescription;
	private String departmentOrganizationCode;
	private String positionLevelCode;
	private String positionLevelDescription;

	/**
	 * @return the id
	 */
	@Id
	@Column(name = "USR_ID")
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the userLoginId
	 */
	@Column(name = "LGN_ID")
	public String getUserLoginId() {
		return userLoginId;
	}

	/**
	 * @param userLoginId
	 *            the userLoginId to set
	 */
	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}

	/**
	 * @return the firstName
	 */
	@Column(name = "FNAME")
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	@Column(name = "MNAME")
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName
	 *            the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the lastName
	 */
	@Column(name = "LNAME")
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the phone
	 */
	@Column(name = "PHN")
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the alternatePhone
	 */
	@Column(name = "ALT_PHN")
	public String getAlternatePhone() {
		return alternatePhone;
	}

	/**
	 * @param alternatePhone
	 *            the alternatePhone to set
	 */
	public void setAlternatePhone(String alternatePhone) {
		this.alternatePhone = alternatePhone;
	}

	/**
	 * @return the email
	 */
	@Column(name = "EML")
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the employeeId
	 */
	@Column(name = "EMP_ID")
	public String getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId
	 *            the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the managerId
	 */
	@Column(name = "MGR_ID")
	public Long getManagerId() {
		return managerId;
	}

	/**
	 * @param managerId
	 *            the managerId to set
	 */
	public void setManagerId(Long managerId) {
		if (managerId != null) {
			this.managerId = managerId;
		} else {
			this.managerId = 0l;
		}
	}

	/**
	 * @return the employeeTypeCode
	 */
	@Column(name = "EMP_TYP_CDE")
	public String getEmployeeTypeCode() {
		return employeeTypeCode;
	}

	/**
	 * @param employeeTypeCode
	 *            the employeeTypeCode to set
	 */
	public void setEmployeeTypeCode(String employeeTypeCode) {
		this.employeeTypeCode = employeeTypeCode;
	}

	/**
	 * @return the employeeTypeDescription
	 */
	@Column(name = "EMP_TYP_DSCPTN")
	public String getEmployeeTypeDescription() {
		return employeeTypeDescription;
	}

	/**
	 * @param employeeTypeDescription
	 *            the employeeTypeDescription to set
	 */
	public void setEmployeeTypeDescription(String employeeTypeDescription) {
		this.employeeTypeDescription = employeeTypeDescription;
	}

	/**
	 * @return the title
	 */
	@Column(name = "TITLE")
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the nameSuffix
	 */
	@Column(name = "NAM_SUFFIX")
	public String getNameSuffix() {
		return nameSuffix;
	}

	/**
	 * @param nameSuffix
	 *            the nameSuffix to set
	 */
	public void setNameSuffix(String nameSuffix) {
		this.nameSuffix = nameSuffix;
	}

	/**
	 * @return the statusFlag
	 */
	@Column(name = "STS_FLG")
	@Enumerated(EnumType.STRING)
	public StatusFlag getStatusFlag() {
		return statusFlag;
	}

	/**
	 * @param statusFlag
	 *            the statusFlag to set
	 */
	public void setStatusFlag(StatusFlag statusFlag) {
		this.statusFlag = statusFlag;
	}

	/**
	 * @return the genderTypeCode
	 */
	@Column(name = "GNDR_TYP_CDE")
	public String getGenderTypeCode() {
		return genderTypeCode;
	}

	/**
	 * @param genderTypeCode
	 *            the genderTypeCode to set
	 */
	public void setGenderTypeCode(String genderTypeCode) {
		this.genderTypeCode = genderTypeCode;
	}

	/**
	 * @return the genderTypeDescription
	 */
	@Column(name = "GNDR_TYP_DSCPTN")
	public String getGenderTypeDescription() {
		return genderTypeDescription;
	}

	/**
	 * @param genderTypeDescription
	 *            the genderTypeDescription to set
	 */
	public void setGenderTypeDescription(String genderTypeDescription) {
		this.genderTypeDescription = genderTypeDescription;
	}

	/**
	 * @return the positionCode
	 */
	@Column(name = "POSTN_CDE")
	public String getPositionCode() {
		return positionCode;
	}

	/**
	 * @param positionCode
	 *            the positionCode to set
	 */
	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	/**
	 * @return the positionDescription
	 */
	@Column(name = "POSTN_DSCPTN")
	public String getPositionDescription() {
		return positionDescription;
	}

	/**
	 * @param positionDescription
	 *            the positionDescription to set
	 */
	public void setPositionDescription(String positionDescription) {
		this.positionDescription = positionDescription;
	}

	/**
	 * @return the organizationCode
	 */
	@Column(name = "ORG_CDE")
	public String getOrganizationCode() {
		return organizationCode;
	}

	/**
	 * @param organizationCode
	 *            the organizationCode to set
	 */
	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	/**
	 * @return the organizationDescription
	 */
	@Column(name = "ORG_DSCPTN")
	public String getOrganizationDescription() {
		return organizationDescription;
	}

	/**
	 * @param organizationDescription
	 *            the organizationDescription to set
	 */
	public void setOrganizationDescription(String organizationDescription) {
		this.organizationDescription = organizationDescription;
	}

	/**
	 * @return the departmentCode
	 */
	@Column(name = "DEPT_CDE")
	public String getDepartmentCode() {
		return departmentCode;
	}

	/**
	 * @param departmentCode
	 *            the departmentCode to set
	 */
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	/**
	 * @return the departmentDescription
	 */
	@Column(name = "DEPT_DSCPTN")
	public String getDepartmentDescription() {
		return departmentDescription;
	}

	/**
	 * @param departmentDescription
	 *            the departmentDescription to set
	 */
	public void setDepartmentDescription(String departmentDescription) {
		this.departmentDescription = departmentDescription;
	}

	/**
	 * @return the positionLevelCode
	 */
	@Column(name = "POSTN_LVL_CDE")
	public String getPositionLevelCode() {
		return positionLevelCode;
	}

	/**
	 * @param positionLevelCode
	 *            the positionLevelCode to set
	 */
	public void setPositionLevelCode(String positionLevelCode) {
		this.positionLevelCode = positionLevelCode;
	}

	/**
	 * @return the positionLevelDescription
	 */
	@Column(name = "POSTN_LVL_DSCPTN")
	public String getPositionLevelDescription() {
		return positionLevelDescription;
	}

	/**
	 * @param positionLevelDescription
	 *            the positionLevelDescription to set
	 */
	public void setPositionLevelDescription(String positionLevelDescription) {
		this.positionLevelDescription = positionLevelDescription;
	}

	/**
	 * @return the departmentOrganizationCode
	 */
	@Column(name = "DEPT_ORG_CDE")
	public String getDepartmentOrganizationCode() {
		return departmentOrganizationCode;
	}

	/**
	 * @param departmentOrganizationCode
	 *            the departmentOrganizationCode to set
	 */
	public void setDepartmentOrganizationCode(String departmentOrganizationCode) {
		this.departmentOrganizationCode = departmentOrganizationCode;
	}

	/**
	 * @return the dateOfBirth
	 */
	@Column(name = "DOB")
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth
	 *            the dateOfBirth to set
	 */
	public void setDateOfBirth(final LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the age
	 */
	@Column(name = "AGE")
	public Integer getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(final Integer age) {
		this.age = age;
	}

	/**
	 * @return the dateOfJoining
	 */
	@Column(name = "DOJ")
	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}

	/**
	 * @param dateOfJoining
	 *            the dateOfJoining to set
	 */
	public void setDateOfJoining(final LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	/**
	 * @return the dateOfLeaving
	 */
	@Column(name = "DOL")
	public LocalDate getDateOfLeaving() {
		return dateOfLeaving;
	}

	/**
	 * @param dateOfLeaving
	 *            the dateOfLeaving to set
	 */
	public void setDateOfLeaving(final LocalDate dateOfLeaving) {
		this.dateOfLeaving = dateOfLeaving;
	}

	/**
	 * @return the managerName
	 */
	@Column(name = "MGR_NAME")
	public String getManagerName() {
		return managerName;
	}

	/**
	 * @param managerName the managerName to set
	 */
	public void setManagerName(final String managerName) {
		this.managerName = managerName;
	}	

}
