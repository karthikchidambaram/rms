package com.i2g.rms.util.security;

public class RMSPasswordHashingTest {
	
	private String password = "karthik";
	
	public RMSPasswordHashingTest() {}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public static void main (String args[]) {
		RMSPasswordHashingTest rMSPasswordHashingTest = new RMSPasswordHashingTest();
		String password = rMSPasswordHashingTest.getPassword();
		
		RMSPasswordHashingService _rMSPasswordHashingService = new RMSPasswordHashingService();
		String saltedPassword = _rMSPasswordHashingService.getSaltedPassword(password);
		String hashedPassword = _rMSPasswordHashingService.getHashedPassword(saltedPassword);
		
		System.out.println("Password: " + password);
		System.out.println("Salted Password: " + saltedPassword);
		System.out.println("Hashed Password: " + hashedPassword);
	}
}
