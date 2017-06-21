package com.i2g.rms.rest.controller.security.stateless;

import static javax.xml.bind.DatatypeConverter.printBase64Binary;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.security.SecureRandom;
import java.util.Date;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.i2g.rms.domain.model.User;
import com.i2g.rms.rest.mapping.CustomObjectMapper;
import com.i2g.rms.rest.security.stateless.TokenHandler;

public class TokenHandlerTest {
	
	private TokenHandler tokenHandler;
	private CustomObjectMapper _customObjectMapper;

	@Before
	public void init() {
		byte[] secret = new byte[70];
		new SecureRandom().nextBytes(secret);
		tokenHandler = new TokenHandler(secret);
	}

	@Test
	@Ignore
	public void testCreateToken_SeparatorCharInUsername() {
		final User user = new User("R.bbert", new Date(new Date().getTime() + 10000));
		final User parsedUser = tokenHandler.parseUserFromToken(tokenHandler.createTokenForUser(user));
		assertEquals(user.getLoginId(), parsedUser.getLoginId());
	}

	@Test
	@Ignore
	public void testParseInvalidTokens_NoParseExceptions() throws JsonProcessingException {
		final String unsignedToken = printBase64Binary(getCustomObjectMapper().writeValueAsBytes(new User("test")));

		testForNullResult("");
		testForNullResult(unsignedToken);
		testForNullResult(unsignedToken + ".");
		testForNullResult(unsignedToken + "." + unsignedToken);
	}

	private void testForNullResult(final String token) {
		final User result = tokenHandler.parseUserFromToken(token);
		assertNull(result);
	}
	
	private CustomObjectMapper getCustomObjectMapper() {
		if (_customObjectMapper != null) {
			return _customObjectMapper;
		} else {
			CustomObjectMapper customObjectMapper = new CustomObjectMapper();
			setCustomObjectMapper(customObjectMapper);
			return customObjectMapper;
		}
	}

	private void setCustomObjectMapper(CustomObjectMapper customObjectMapper) {
		_customObjectMapper = customObjectMapper;
	}
}
