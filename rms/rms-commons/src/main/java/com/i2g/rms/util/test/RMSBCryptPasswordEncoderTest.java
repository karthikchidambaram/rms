package com.i2g.rms.util.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class RMSBCryptPasswordEncoderTest {

	public String getBCryptPassword(final String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
	}

	public static void main(String[] args) {
		int i = 0;
		while (i < 10) {
			String password = "123456";
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(password);
			System.out.println(hashedPassword);
			i++;
		}

		RMSBCryptPasswordEncoderTest rMSBCryptPasswordEncoderTest = new RMSBCryptPasswordEncoderTest();
		System.out.println("==============================");
		System.out.println("BCrypted Password for karthik: " + rMSBCryptPasswordEncoderTest.getBCryptPassword("karthik"));
		System.out.println("BCrypted Password for beena: " + rMSBCryptPasswordEncoderTest.getBCryptPassword("beena"));
		System.out.println("BCrypted Password for user1: " + rMSBCryptPasswordEncoderTest.getBCryptPassword("user1"));
		System.out.println("BCrypted Password for user2: " + rMSBCryptPasswordEncoderTest.getBCryptPassword("user2"));
		System.out.println("BCrypted Password for admin1: " + rMSBCryptPasswordEncoderTest.getBCryptPassword("admin1"));
		System.out.println("BCrypted Password for claimsHandler1: " + rMSBCryptPasswordEncoderTest.getBCryptPassword("claimsHandler1"));
		System.out.println("BCrypted Password for claimsHandler2: " + rMSBCryptPasswordEncoderTest.getBCryptPassword("claimsHandler2"));
		System.out.println("BCrypted Password for investigator1: " + rMSBCryptPasswordEncoderTest.getBCryptPassword("investigator1"));
		System.out.println("BCrypted Password for mgr1: " + rMSBCryptPasswordEncoderTest.getBCryptPassword("mgr1"));
		System.out.println("BCrypted Password for mgr2: " + rMSBCryptPasswordEncoderTest.getBCryptPassword("mgr2"));
	}
}
