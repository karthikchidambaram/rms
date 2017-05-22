package com.i2g.rms.persistence.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.Role;
import com.i2g.rms.domain.model.User.UserStatus;
import com.i2g.rms.persistence.dao.UserDao;

@Service
public class SpringSecurityUserDetailsServiceImpl implements UserDetailsService {
	
	private final Logger _logger = LoggerFactory.getLogger(SpringSecurityUserDetailsServiceImpl.class);
	
	@Autowired
	private UserDao _userDao;

	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
		_logger.info("Inside SpringSecurityUserDetailsServiceImpl.loadUserByUsername()");
		
		com.i2g.rms.domain.model.User user = _userDao.getUserByUserLoginId(loginId);

		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		List<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());

		return buildUserForAuthentication(user, authorities);
	}

	// Converts com.i2g.rms.domain.model.User user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(com.i2g.rms.domain.model.User user, List<GrantedAuthority> authorities) {
		_logger.info("Inside SpringSecurityUserDetailsServiceImpl.buildUserForAuthentication()");
		boolean enabled = false;
		
		if (user.getStatus().equals(UserStatus.ACTIVE)) {
			enabled = true;
		}
		return new User(user.getLoginId(), user.getPassword(), enabled, true, true, true, authorities);
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

	public UserDao getUserDao() {
		return _userDao;
	}

	public void setUserDao(UserDao userDao) {
		_userDao = userDao;
	}

}
