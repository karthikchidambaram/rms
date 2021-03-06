package com.i2g.rms.persistence.dao;

import java.util.List;
import java.util.Set;

import com.i2g.rms.domain.model.Address;
import com.i2g.rms.domain.model.User;

/**
 * Back-end DAO for user related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface UserDao {

	public List<User> getUsers();
	
	public List<User> get();
	
	public User get(final long id);
	
	public User getUserByUserLoginId(final String loginId);

	public List<User> getSubordinates(final User manager);
	
	public User updateUser(final User user);
	
	public void deleteAddresses(final User user, final Set<Address> addresses);

}
