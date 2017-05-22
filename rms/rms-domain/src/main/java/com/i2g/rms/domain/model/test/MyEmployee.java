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

	private Integer empNo;
	private String eName;
	private String job;
	private Integer mgr;
	private Date hireDate;
	private Float sal;
	private Float comm;
	private MyDepartment department;

	public MyEmployee() {
	}

	public MyEmployee(final Integer empNo, final String eName, final String job, final Date hireDate, final Float sal) {
		this.empNo = empNo;
		this.eName = eName;
		this.job = job;
		this.hireDate = hireDate;
		this.sal = sal;
	}

	public MyEmployee(final Integer empNo, final String eName, final String job, final Date hireDate, final Float sal,
			final MyDepartment department) {
		this.empNo = empNo;
		this.eName = eName;
		this.job = job;
		this.hireDate = hireDate;
		this.sal = sal;
		this.department = department;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "EMPNO", unique = true, nullable = false)
	public Integer getEmpNo() {
		return empNo;
	}

	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}

	@Column(name = "ENAME", length = 10)
	public String getEName() {
		return eName;
	}

	public void setEName(String eName) {
		this.eName = eName;
	}

	@Column(name = "JOB", length = 9)
	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@Column(name = "MGR")
	public Integer getMgr() {
		return mgr;
	}

	public void setMgr(Integer mgr) {
		this.mgr = mgr;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "HIREDATE")
	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	@Column(name = "SAL")
	public Float getSal() {
		return sal;
	}

	public void setSal(Float sal) {
		this.sal = sal;
	}

	@Column(name = "COMM")
	public Float getComm() {
		return comm;
	}

	public void setComm(Float comm) {
		this.comm = comm;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPTNO")
	public MyDepartment getDepartment() {
		return department;
	}

	public void setDepartment(MyDepartment department) {
		this.department = department;
	}
}
