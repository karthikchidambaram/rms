package com.i2g.rms.rest.security.stateless;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.i2g.rms.domain.model.User;
import com.i2g.rms.rest.mapping.CustomObjectMapper;
import com.i2g.rms.util.security.RMSSecurityProperties;

public class TokenHandler {
	
	private final Logger _logger = LoggerFactory.getLogger(TokenHandler.class);
	
	private final Mac hmac;
	
	private CustomObjectMapper _customObjectMapper;
	
	public CustomObjectMapper getCustomObjectMapper() {
		if (_customObjectMapper != null) {
			return _customObjectMapper;
		} else {
			CustomObjectMapper customObjectMapper = new CustomObjectMapper();
			setCustomObjectMapper(customObjectMapper);
			return customObjectMapper;
		}
	}

	public void setCustomObjectMapper(CustomObjectMapper customObjectMapper) {
		_customObjectMapper = customObjectMapper;
	}

	public TokenHandler(byte[] secretKey) {
		try {
			hmac = Mac.getInstance(RMSSecurityProperties.HMAC_ALGO);
			hmac.init(new SecretKeySpec(secretKey, RMSSecurityProperties.HMAC_ALGO));
		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
			throw new IllegalStateException("failed to initialize HMAC: " + e.getMessage(), e);
		}
	}

	public User parseUserFromToken(String token) {
		final String[] parts = token.split(RMSSecurityProperties.SEPARATOR_SPLITTER);
		if (parts.length == 2 && parts[0].length() > 0 && parts[1].length() > 0) {
			try {
				final byte[] userBytes = fromBase64(parts[0]);
				final byte[] hash = fromBase64(parts[1]);

				boolean validHash = Arrays.equals(createHmac(userBytes), hash);
				if (validHash) {
					final User user = fromJSON(userBytes);
					if (new Date().getTime() < user.getExpires()) {
						return user;
					}
				}
			} catch (IllegalArgumentException illegalArgumentException) {
				_logger.error("Exception in TokenHandler.parseUserFromToken(): " + illegalArgumentException.getMessage(), illegalArgumentException);
			}
		}
		return null;
	}

	public String createTokenForUser(final User user) {
		byte[] userBytes = toJSON(user);
		byte[] hash = createHmac(userBytes);
		final StringBuilder sb = new StringBuilder(170);
		sb.append(toBase64(userBytes));
		sb.append(RMSSecurityProperties.SEPARATOR);
		sb.append(toBase64(hash));
		return sb.toString();
	}

	private User fromJSON(final byte[] userBytes) {
		try {
			return getCustomObjectMapper().readValue(new ByteArrayInputStream(userBytes), User.class);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	private byte[] toJSON(final User user) {
		try {
			return getCustomObjectMapper().writeValueAsBytes(user);
		} catch (JsonProcessingException e) {
			throw new IllegalStateException(e);
		}
	}

	private String toBase64(final byte[] content) {
		return DatatypeConverter.printBase64Binary(content);
	}

	private byte[] fromBase64(final String content) {
		return DatatypeConverter.parseBase64Binary(content);
	}

	// synchronized to guard internal hmac object
	private synchronized byte[] createHmac(final byte[] content) {
		return hmac.doFinal(content);
	}
	
}
