package com.i2g.rms.util.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RMSPasswordHashingService {

	private final Logger _logger = LoggerFactory.getLogger(RMSPasswordHashingService.class);

	public String getSaltedPassword(final String password) {
		if (password == null || password.trim().isEmpty()) {
			throw new IllegalArgumentException("The input passowrd string cannot be empty.");
		}
		return RMSSecurityProperties.SALT_KEY + password;
	}

	public String getHashedPassword(final String password) {
		if (password == null || password.trim().isEmpty()) {
			throw new IllegalArgumentException("Cannot hash an empty password.");
		}
		return generateHash(getSaltedPassword(password));
	}

	/**
	 * Method that generates hash for password.
	 * 
	 * @param input
	 * @return String
	 */
	public String generateHash(String input) {

		StringBuilder hash = new StringBuilder();

		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] hashedBytes = sha.digest(input.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException noSuchAlgorithmException) {
			_logger.error("Error during hash generation for password with input String as {} ", input, noSuchAlgorithmException);
		}

		return hash.toString();
	}
}
