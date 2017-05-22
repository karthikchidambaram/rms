package com.i2g.rms.domain.model.type;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.usertype.UserType;

/**
 * Implementation of Hibernate {@link UserType} for persisting {@link LocalDate}
 * instances.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
public class LocalDateType implements UserType, Serializable {

	/** @see java.io.Serializable */
	private final static long serialVersionUID = 1L;

	@Override
	public int[] sqlTypes() {
		return new int[] { Types.TIMESTAMP };
	}

	@Override
	public Class<?> returnedClass() {
		return LocalDate.class;
	}

	@Override
	public boolean equals(final Object x, final Object y) throws HibernateException {
		return Objects.equals(x, y);
	}

	@Override
	public int hashCode(final Object x) throws HibernateException {
		return Objects.hashCode(x);
	}

	@Override
	public Object nullSafeGet(final ResultSet rs, final String[] names, final SharedSessionContractImplementor session,
			final Object owner) throws HibernateException, SQLException {
		final Object timestamp = StandardBasicTypes.TIMESTAMP.nullSafeGet(rs, names, session, owner);
		if (timestamp == null) {
			return null;
		}
		return ((Date) timestamp).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	@Override
	public void nullSafeSet(final PreparedStatement st, final Object value, final int index,
			final SharedSessionContractImplementor session) throws HibernateException, SQLException {
		if (value == null) {
			StandardBasicTypes.TIMESTAMP.nullSafeSet(st, null, index, session);
		} else {
			final LocalDate localDate = (LocalDate) value;
			final Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
			StandardBasicTypes.TIMESTAMP.nullSafeSet(st, Date.from(instant), index, session);
		}
	}

	@Override
	public Object deepCopy(final Object value) throws HibernateException {
		return value;
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Serializable disassemble(final Object value) throws HibernateException {
		return (Serializable) value;
	}

	@Override
	public Object assemble(final Serializable cached, final Object owner) throws HibernateException {
		return cached;
	}

	@Override
	public Object replace(final Object original, final Object target, final Object owner) throws HibernateException {
		return original;
	}

}
