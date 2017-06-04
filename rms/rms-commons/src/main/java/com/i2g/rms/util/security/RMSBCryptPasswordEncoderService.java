package com.i2g.rms.util.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RMSBCryptPasswordEncoderService {
	
	@Autowired
	private PasswordEncoder _passwordEncoder;
	
	public PasswordEncoder getPasswordEncoder() {
		return _passwordEncoder;
	}
	
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		_passwordEncoder = passwordEncoder;
	}

	public String getBCryptPassword(final String password) {
		return _passwordEncoder.encode(password);
	}
}
