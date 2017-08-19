package com.i2g.rms.rest.security.stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.User;
import com.i2g.rms.service.UserService;

@Service
public class SpringSecurityUserDetailsServiceImpl implements UserDetailsService {
	
	private final Logger _logger = LoggerFactory.getLogger(SpringSecurityUserDetailsServiceImpl.class);
	
	@Autowired
	private UserService _userService;	
	
	private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

	@Override
	public final User loadUserByUsername(final String username) throws UsernameNotFoundException {
		final User user = _userService.getUserByUserLoginId(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found.");
		}
		detailsChecker.check(user);
		return user;
	}	
}
