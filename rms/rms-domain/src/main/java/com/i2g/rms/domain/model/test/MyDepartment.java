package com.i2g.rms.domain.model.test;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "DEPT", uniqueConstraints = { @UniqueConstraint(columnNames = "DEPTNO"),
		@UniqueConstraint(columnNames = "DNAME") })
@Immutable
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "myDepartment")
public class MyDepartment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long _deptNo;
	private String _dname;
	private String _loc;
	private Set<MyEmployee> _employees = new HashSet<MyEmployee>(0);

	public MyDepartment() {
	}

	public MyDepartment(final Long deptNo, final String dname, final String loc) {
		_deptNo = deptNo;
		_dname = dname;
		_loc = loc;
	}

	public MyDepartment(final Long deptNo, final String dname, final String loc, final Set<MyEmployee> employees) {
		_deptNo = deptNo;
		_dname = dname;
		_loc = loc;
		_employees = employees;
	}

	@Id
	@Column(name = "DEPTNO", unique = true, nullable = false)
	public Long getDeptNo() {
		return _deptNo;
	}

	public void setDeptNo(Long deptNo) {
		_deptNo = deptNo;
	}

	@Column(name = "DNAME", unique = true, nullable = false, length = 14)
	public String getDname() {
		return _dname;
	}

	public void setDname(String dname) {
		_dname = dname;
	}

	@Column(name = "LOC", length = 13)
	public String getLoc() {
		return _loc;
	}

	public void setLoc(String loc) {
		_loc = loc;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
	public Set<MyEmployee> getEmployees() {
		return _employees;
	}

	public void setEmployees(Set<MyEmployee> employees) {
		_employees = employees;
	}
}
