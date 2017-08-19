package com.i2g.rms.domain.model.test;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.i2g.rms.domain.model.AbstractDataModel;

/**
 * Entity object for table RMS_TEST_DEPT
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Entity
@Table(name = "RMS_TEST_DEPT")
public class TestDepartment extends AbstractDataModel<Long> implements Serializable, Comparable<TestDepartment> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Primary key ID for entity. */
	private Long _id;
	private String _dname;
	private Long _deptNo;
	private String _loc;

	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected TestDepartment() {
	}

	/**
	 * Creates a new immutable instance of {@code Department} from the specified
	 * {@code builder}.
	 * 
	 * <p>
	 * <strong>Note:</strong> Constructor must remain with private access as it
	 * should only be invoked from the builder.
	 * </p>
	 * 
	 * @param builder
	 */
	private TestDepartment(final Builder builder) {
		_deptNo = Objects.requireNonNull(builder._deptNo, "Department number cannot be null or empty");
		_dname = Objects.requireNonNull(builder._dname, "Department name cannot be null or empty");
		_loc = Objects.requireNonNull(builder._loc, "Department location cannot be null or empty");
	}

	/**
	 * Returns the primary key ID for the record.
	 * 
	 * @return primary key ID
	 */
	@Id
	@Column(name = "ID", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rms_test_dept_id_seq")
	@SequenceGenerator(name = "rms_test_dept_id_seq", sequenceName = "RMS_TEST_DEPT_ID_SEQ", allocationSize = 1)
	@Override
	public Long getId() {
		return _id;
	}

	/**
	 * Sets the primary surrogate key ID.
	 * 
	 * @param id
	 */
	protected void setId(final Long id) {
		_id = id;
	}

	@Column(name = "DNAME")
	public String getDname() {
		return _dname;
	}

	public void setDname(String dname) {
		_dname = dname;
	}

	@Column(name = "DEPTNO", nullable = false)
	@NotNull
	public Long getDeptNo() {
		return _deptNo;
	}

	protected void setDeptNo(Long deptNo) {
		_deptNo = deptNo;
	}

	@Column(name = "LOC")
	public String getLoc() {
		return _loc;
	}

	public void setLoc(String loc) {
		_loc = loc;
	}

	@Override
	public int compareTo(final TestDepartment o) {
		if (getCreated() == null) {
			return 1;
		} else if (o == null || o.getCreated() == null) {
			return -1;
		}
		return getCreated().compareTo(o.getCreated());
	}

	@Override
	public boolean equals(final Object obj) {
		return obj == this || (obj instanceof TestDepartment && ((TestDepartment) obj)._id == _id);
	}

	@Override
	public int hashCode() {
		return _id.intValue();
	}

	@Override
	public String toString() {
		return "Dept ID: " + _id + " Dept No: " + _deptNo + " Dept Name: " + _dname + " Location: " + _loc;
	}

	/**
	 * Implementation of Builder pattern for constructing immutable instances of
	 * {@link TestDepartment}.
	 */
	public final static class Builder {
		/** Department Number. */
		private Long _deptNo;
		private String _dname;
		private String _loc;

		/**
		 * Builds a new immutable instance of {@link TestDepartment}.
		 * 
		 * @return new immutable instance
		 */
		public TestDepartment build() {
			return new TestDepartment(this);
		}

		/**
		 * Sets the specified {@code deptNo}.
		 * 
		 * @param deptNo
		 * @return this builder
		 */
		public Builder setDeptNo(final Long deptNo) {
			_deptNo = deptNo;
			return this;
		}

		/**
		 * Sets the specified {@code dname}.
		 * 
		 * @param dname
		 * @return this builder
		 */
		public Builder setDname(final String dname) {
			_dname = dname;
			return this;
		}

		/**
		 * Sets the specified {@code loc}.
		 * 
		 * @param loc
		 * @return this builder
		 */
		public Builder setLoc(final String loc) {
			_loc = loc;
			return this;
		}
	}
}
