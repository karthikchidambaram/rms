package com.i2g.rms.rest.model.test;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MyDepartmentRO {

	private Long _deptNo;
	private String _dname;
	private String _loc;
	private Set<MyEmployeeRO> _employees = new HashSet<MyEmployeeRO>(0);

	public Long getDeptNo() {
		return _deptNo;
	}

	public void setDeptNo(Long deptNo) {
		_deptNo = deptNo;
	}

	public String getDname() {
		return _dname;
	}

	public void setDname(String dname) {
		_dname = dname;
	}

	public String getLoc() {
		return _loc;
	}

	public void setLoc(String loc) {
		_loc = loc;
	}
	
	public Set<MyEmployeeRO> getEmployees() {
		return _employees;
	}

	public void setEmployees(Set<MyEmployeeRO> employees) {
		_employees = employees;
	}
}
