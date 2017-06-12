package com.i2g.rms.rest.controller.security.stateless;

import static javax.xml.bind.DatatypeConverter.printBase64Binary;
import static org.junit.Assert.*;

import java.security.SecureRandom;
import java.util.Date;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.i2g.rms.domain.model.User;
import com.i2g.rms.rest.security.stateless.TokenHandler;

public class TokenHandlerTest {
	
	private TokenHandler tokenHandler;

	@Before
	public void init() {
		byte[] secret = new byte[70];
		new SecureRandom().nextBytes(secret);
		tokenHandler = new TokenHandler(secret);
	}

	/*@Test
	public void testRoundTrip_ProperData() {
		final User user = new User("Robbert", new Date(new Date().getTime() + 10000));
		user.grantRole(UserRole.ADMIN);

		final User parsedUser = tokenHandler.parseUserFromToken(tokenHandler.createTokenForUser(user));

		assertEquals(user.getUsername(), parsedUser.getUsername());
		assertTrue(parsedUser.hasRole(UserRole.ADMIN));
	}*/

	@Test
	@Ignore
	public void testCreateToken_SeparatorCharInUsername() {
		final User user = new User("R.bbert", new Date(new Date().getTime() + 10000));

		final User parsedUser = tokenHandler.parseUserFromToken(tokenHandler.createTokenForUser(user));

		assertEquals(user.getLoginId(), parsedUser.getLoginId());
	}

	/*@Test
	public void testCreateToken_ExcludePasswords() {
		final User user = new User("Robbert", new Date(new Date().getTime() + 10000));
		user.setPassword("abc");
		user.setNewPassword("def");

		final User parsedUser = tokenHandler.parseUserFromToken(tokenHandler.createTokenForUser(user));

		assertEquals(user.getUsername(), parsedUser.getUsername());
		assertNull(parsedUser.getPassword());
		assertNull(parsedUser.getNewPassword());
	}*/
	
	@Test
	@Ignore
	public void testParseInvalidTokens_NoParseExceptions() throws JsonProcessingException {
		final String unsignedToken = printBase64Binary(new ObjectMapper().writeValueAsBytes(new User("test")));

		testForNullResult("");
		testForNullResult(unsignedToken);
		testForNullResult(unsignedToken + ".");
		testForNullResult(unsignedToken + "." + unsignedToken);
	}

	private void testForNullResult(final String token) {
		final User result = tokenHandler.parseUserFromToken(token);
		assertNull(result);
	}
}
