package com.i2g.rms.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.i2g.rms.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByUsername(String username);
	
}
