package com.i2g.rms.rest.model.test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MyEmployeeRO {

	private Integer empNo;
	private String eName;
	private String job;
	private Date hireDate;
	private Float sal;
	private Float comm;
	private MyDepartmentRO department;
	private MyEmployeeRO _manager;
	private Set<MyEmployeeRO> _subordinates = new HashSet<MyEmployeeRO>(0);

	public Integer getEmpNo() {
		return empNo;
	}

	public void setEmpNo(final Integer empNo) {
		this.empNo = empNo;
	}

	public String getEName() {
		return eName;
	}

	public void setEName(final String eName) {
		this.eName = eName;
	}

	public String getJob() {
		return job;
	}

	public void setJob(final String job) {
		this.job = job;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(final Date hireDate) {
		this.hireDate = hireDate;
	}

	public Float getSal() {
		return sal;
	}

	public void setSal(final Float sal) {
		this.sal = sal;
	}

	public Float getComm() {
		return comm;
	}

	public void setComm(final Float comm) {
		this.comm = comm;
	}

	public MyDepartmentRO getDepartment() {
		return department;
	}

	public void setDepartment(final MyDepartmentRO department) {
		this.department = department;
	}
	
	/**
	 * @return the manager
	 */
	public MyEmployeeRO getManager() {
		return _manager;
	}

	/**
	 * @param manager the manager to set
	 */
	public void setManager(final MyEmployeeRO manager) {
		_manager = manager;
	}
	
	/**
	 * @return the subordinates
	 */
	public Set<MyEmployeeRO> getSubordinates() {
		return _subordinates;
	}

	/**
	 * @param subordinates the subordinates to set
	 */
	public void setSubordinates(final Set<MyEmployeeRO> subordinates) {
		_subordinates = subordinates;
	}
}
