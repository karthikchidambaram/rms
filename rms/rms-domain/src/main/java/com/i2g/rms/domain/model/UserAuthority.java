package com.i2g.rms.domain.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@IdClass(UserAuthority.class)
public class UserAuthority implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnoreProperties("authorities")
	@Id
	private User _user;

	@NotNull
	@Id
	private String _authority;

	public User getUser() {
		return _user;
	}

	public void setUser(User user) {
		_user = user;
	}

	@Override
	public String getAuthority() {
		return _authority;
	}

	public void setAuthority(String authority) {
		_authority = authority;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof UserAuthority))
			return false;

		UserAuthority ua = (UserAuthority) obj;
		return ua.getAuthority() == this.getAuthority() || ua.getAuthority().equals(this.getAuthority());
	}

	@Override
	public int hashCode() {
		return getAuthority() == null ? 0 : getAuthority().hashCode();
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + ": " + getAuthority();
	}
}
