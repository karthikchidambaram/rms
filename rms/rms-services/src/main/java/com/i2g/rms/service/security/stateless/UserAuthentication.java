package com.i2g.rms.service.security.stateless;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.i2g.rms.domain.model.Role;
import com.i2g.rms.domain.model.User;

public class UserAuthentication implements Authentication {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final User _user;
	private boolean _authenticated = true;
	
	public UserAuthentication(User user) {
		_user = user;
	}
	
	@Override
	public String getName() {
		return _user.getLoginId();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return buildUserAuthority(_user.getRoles());
	}

	@Override
	public Object getCredentials() {
		return _user.getPassword();
	}

	@Override
	public User getDetails() {
		return _user;
	}

	@Override
	public Object getPrincipal() {
		return _user.getLoginId();
	}

	@Override
	public boolean isAuthenticated() {
		return _authenticated;
	}

	@Override
	public void setAuthenticated(boolean authenticated) {
		_authenticated = authenticated;
	}
	
	private List<GrantedAuthority> buildUserAuthority(Set<Role> roles) {
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (Role role : roles) {
			setAuths.add(new SimpleGrantedAuthority(role.getRoleName()));
		}

		List<GrantedAuthority> results = new ArrayList<GrantedAuthority>(setAuths);
		return results;
	}
}
