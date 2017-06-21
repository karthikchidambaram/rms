package com.i2g.rms.util.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * This password service is to be used while creating a user with encrypted
 * password.
 * 
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class RMSBCryptPasswordEncoderService {

	@Autowired
	private PasswordEncoder _passwordEncoder;

	public PasswordEncoder getPasswordEncoder() {
		return _passwordEncoder;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this._passwordEncoder = passwordEncoder;
	}

	public String getBCryptPassword(final String password) {
		return getPasswordEncoder().encode(password);
	}
}
