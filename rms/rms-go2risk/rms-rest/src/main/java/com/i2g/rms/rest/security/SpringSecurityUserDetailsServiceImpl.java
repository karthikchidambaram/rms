package com.i2g.rms.rest.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.Role;
import com.i2g.rms.domain.model.User.UserStatus;
import com.i2g.rms.service.UserService;

@Service
public class SpringSecurityUserDetailsServiceImpl implements UserDetailsService {
	
	private final Logger _logger = LoggerFactory.getLogger(SpringSecurityUserDetailsServiceImpl.class);
	
	@Autowired
	private UserService _userService;	
	
	private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

	@Override
	public UserDetails loadUserByUsername(final String loginId) throws UsernameNotFoundException {
		_logger.info("Inside SpringSecurityUserDetailsServiceImpl.loadUserByUsername()");
		
		final com.i2g.rms.domain.model.User domainUser = _userService.getUserByUserLoginId(loginId);

		if (domainUser == null) {
			throw new UsernameNotFoundException("Invalid username and/or password.");
		}
		
		List<GrantedAuthority> authorities = buildUserAuthority(domainUser.getRoles());
		User springUser = buildUserForAuthentication(domainUser, authorities);
		detailsChecker.check(springUser);
		return springUser;
	}
	
	public final com.i2g.rms.domain.model.User loadDomainUserByUsername(final String username) throws UsernameNotFoundException {
		_logger.info("Inside SpringSecurityUserDetailsServiceImpl.loadDomainUserByUsername()");
		
		final com.i2g.rms.domain.model.User user = _userService.getUserByUserLoginId(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("User not found.");
		}
		
		return user;
	}

	// Converts com.i2g.rms.domain.model.User to org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(final com.i2g.rms.domain.model.User user, final List<GrantedAuthority> authorities) {
		_logger.info("Inside SpringSecurityUserDetailsServiceImpl.buildUserForAuthentication()");
		boolean enabled = false;
		
		if (user.getStatus().equals(UserStatus.ACTIVE)) {
			enabled = true;
		}
		return new User(user.getUsername(), user.getPassword(), enabled, true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<Role> roles) {
		_logger.info("Inside SpringSecurityUserDetailsServiceImpl.buildUserAuthority()");
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (Role role : roles) {
			setAuths.add(new SimpleGrantedAuthority(role.getRoleName()));
		}

		List<GrantedAuthority> results = new ArrayList<GrantedAuthority>(setAuths);

		return results;
	}
}
