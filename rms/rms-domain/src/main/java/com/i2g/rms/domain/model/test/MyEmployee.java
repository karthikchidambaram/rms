package com.i2g.rms.domain.model.test;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "EMP", uniqueConstraints = @UniqueConstraint(columnNames = "EMPNO"))
@Immutable
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "myDepartment")
public class MyEmployee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer _empNo;
	private String _eName;
	private String _job;
	private Integer _mgr;
	private Date _hireDate;
	private Float _sal;
	private Float _comm;
	private MyDepartment _department;

	public MyEmployee() {
	}

	public MyEmployee(final Integer empNo, final String eName, final String job, final Date hireDate, final Float sal) {
		_empNo = empNo;
		_eName = eName;
		_job = job;
		_hireDate = hireDate;
		_sal = sal;
	}

	public MyEmployee(final Integer empNo, final String eName, final String job, final Date hireDate, final Float sal,
			final MyDepartment department) {
		_empNo = empNo;
		_eName = eName;
		_job = job;
		_hireDate = hireDate;
		_sal = sal;
		_department = department;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "EMPNO", unique = true, nullable = false)
	public Integer getEmpNo() {
		return _empNo;
	}

	public void setEmpNo(Integer empNo) {
		_empNo = empNo;
	}

	@Column(name = "ENAME", length = 10)
	public String getEName() {
		return _eName;
	}

	public void setEName(String eName) {
		_eName = eName;
	}

	@Column(name = "JOB", length = 9)
	public String getJob() {
		return _job;
	}

	public void setJob(String job) {
		_job = job;
	}

	@Column(name = "MGR")
	public Integer getMgr() {
		return _mgr;
	}

	public void setMgr(Integer mgr) {
		_mgr = mgr;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "HIREDATE")
	public Date getHireDate() {
		return _hireDate;
	}

	public void setHireDate(Date hireDate) {
		_hireDate = hireDate;
	}

	@Column(name = "SAL")
	public Float getSal() {
		return _sal;
	}

	public void setSal(Float sal) {
		_sal = sal;
	}

	@Column(name = "COMM")
	public Float getComm() {
		return _comm;
	}

	public void setComm(Float comm) {
		_comm = comm;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPTNO")
	public MyDepartment getDepartment() {
		return _department;
	}

	public void setDepartment(MyDepartment department) {
		_department = department;
	}
}
