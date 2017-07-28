package com.i2g.rms.domain.model.type;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.io.IOUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

/**
 * Implementation of Hibernate's {@link UserType} to represent an Oracle BLOB
 * column type.
 * 
 * @see org.hibernate.usertype.UserType
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
public class OracleCompressedBlobType implements UserType, Serializable {
	
	/** @see java.io.Serializable */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Creates a new instance of {@code OracleCompressedBlobType}
	 * 
	 */
	public OracleCompressedBlobType() {
	}
	
	@Override
	public Object nullSafeGet(final ResultSet rs, final String[] names, final SharedSessionContractImplementor si, final Object owner) throws HibernateException, SQLException {
		if( rs.getBlob(names[0]) == null ) {
			return null;
		}
		final Blob blob = rs.getBlob(names[0]);
		final ByteArrayOutputStream decompressed = new ByteArrayOutputStream();
		try {
			final GZIPInputStream gzipIn = new GZIPInputStream(blob.getBinaryStream());
			IOUtils.copy(gzipIn, decompressed);
			return IOUtils.toString(decompressed.toByteArray(), StandardCharsets.UTF_8.name());
		} catch (IOException ioe) {
			throw new SQLException("Failed to decompress Blob to String", ioe);
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void nullSafeSet(final PreparedStatement pstmt, final Object value, final int index, final SharedSessionContractImplementor si) throws HibernateException, SQLException {
		if( value == null ) {
			pstmt.setBlob(index, (Blob) null);
		} else {
			final Blob blob = pstmt.getConnection().createBlob();
			try( final GZIPOutputStream out = new GZIPOutputStream(blob.setBinaryStream(0)) ) {
				IOUtils.write((String) value, out);
			} catch (IOException ioe) {
				throw new SQLException("Failed to compress String to Blob", ioe);
			}
			pstmt.setBlob(index, blob);
		}
	}
	
	@Override
	public Object assemble(Serializable cached, Object owner) {
		return cached;
	}	
	
	@Override
	public Object deepCopy(Object value) {
		return value;
	}
	
	@Override
	public Serializable disassemble(Object value) {
		return (Serializable) value;
	}
	
	@Override
	public boolean equals(Object x, Object y) {
		return (x == y) || (x != null && y != null && x.equals(y));
	}
	
	@Override
	public int hashCode(Object x) {
		return x.hashCode();
	}
	
	@Override
	public boolean isMutable() {
		return true;
	}
	
	@Override
	public Object replace(Object original, Object target, Object owner) {
		return original;
	}
	
	@Override
	public Class<?> returnedClass() {
		return String.class;
	}
	
	@Override
	public int[] sqlTypes() {
		return new int[]{Types.BLOB};
	}	
}
