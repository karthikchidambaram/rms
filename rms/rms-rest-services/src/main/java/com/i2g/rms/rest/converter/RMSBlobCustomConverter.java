package com.i2g.rms.rest.converter;

import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;

import org.dozer.CustomConverter;
import org.dozer.MappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RMSBlobCustomConverter implements CustomConverter {

	private final Logger _logger = LoggerFactory.getLogger(RMSBlobCustomConverter.class);

	@Override
	public Object convert(Object destination, Object source, Class<?> destinationClass, Class<?> sourceClass) {

		if (source == null) {
			return null;
		}

		if (source instanceof Blob) {
			Blob blob = (Blob) source;
			int blobLength = 0;
			byte[] blobAsBytes = null;
			try {
				blobLength = (int) blob.length();
				blobAsBytes = blob.getBytes(1, blobLength);
			} catch (SQLException sQLException) {
				sQLException.printStackTrace();
				_logger.error("SQLException while trying to convert BLOB to byte[]: " + sQLException.toString());
			}
			return blobAsBytes;
		} else if (source instanceof byte[]) {
			byte[] sourceAsByte = (byte[]) source;
			Blob blob = null;
			try {
				blob = new SerialBlob(sourceAsByte);
			} catch (SQLException sQLException) {
				sQLException.printStackTrace();
				_logger.error("SQLException while trying to convert byte[] to BLOB: " + sQLException.toString());				
			}
			return blob;
		} else {
			throw new MappingException("Converter RMSBlobCustomConverter used incorrectly. Arguments passed in were: "
					+ destination + " and " + source);
		}
	}
}
