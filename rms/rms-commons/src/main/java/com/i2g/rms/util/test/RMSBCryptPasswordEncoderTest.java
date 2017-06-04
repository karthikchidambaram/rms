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
		System.out.println("BCrypted Password for test1: " + rMSBCryptPasswordEncoderTest.getBCryptPassword("test1"));
		System.out.println("BCrypted Password for test2: " + rMSBCryptPasswordEncoderTest.getBCryptPassword("test2"));
		System.out.println("BCrypted Password for test3: " + rMSBCryptPasswordEncoderTest.getBCryptPassword("test3"));
		System.out.println("BCrypted Password for admin1: " + rMSBCryptPasswordEncoderTest.getBCryptPassword("admin1"));
		System.out.println("BCrypted Password for admin2: " + rMSBCryptPasswordEncoderTest.getBCryptPassword("admin2"));
		System.out.println("BCrypted Password for admin3: " + rMSBCryptPasswordEncoderTest.getBCryptPassword("admin3"));
	}
}
