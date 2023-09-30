package com.api.test.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.test.model.User;

public interface UserRepository extends JpaRepository<User,Long> {

	 Optional<User> findByEmail(String email);
	
}
