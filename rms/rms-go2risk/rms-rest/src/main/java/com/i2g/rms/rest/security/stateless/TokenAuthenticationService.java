package com.i2g.rms.rest.security.stateless;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.User;
import com.i2g.rms.util.security.RMSSecurityProperties;

@Service
public class TokenAuthenticationService {
	
	private final TokenHandler tokenHandler;

	@Autowired
	public TokenAuthenticationService(@Value(RMSSecurityProperties.TOKEN_SECRET) String secret) {
		tokenHandler = new TokenHandler(DatatypeConverter.parseBase64Binary(secret));
	}

	public void addAuthentication(HttpServletResponse response, UserAuthentication authentication) {
		final User user = authentication.getDetails();
		user.setExpires(System.currentTimeMillis() + RMSSecurityProperties.TEN_DAYS_IN_MILLIS);
		response.addHeader(RMSSecurityProperties.AUTH_HEADER_NAME, tokenHandler.createTokenForUser(user));
	}

	public Authentication getAuthentication(HttpServletRequest request) {
		final String token = request.getHeader(RMSSecurityProperties.AUTH_HEADER_NAME);
		if (token != null) {
			final User user = tokenHandler.parseUserFromToken(token);
			if (user != null) {
				return new UserAuthentication(user);
			}
		}
		return null;
	}
}
