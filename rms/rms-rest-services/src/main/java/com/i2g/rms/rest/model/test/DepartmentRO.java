package com.i2g.rms.rest.model.test;

import com.i2g.rms.rest.model.AbstractEntityRO;

public class DepartmentRO extends AbstractEntityRO {

	private Long _id;
	private Long _deptNo;
	private String _dname;
	private String _loc;

	public Long getId() {
		return _id;
	}

	public void setId(Long id) {
		_id = id;
	}

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
}
