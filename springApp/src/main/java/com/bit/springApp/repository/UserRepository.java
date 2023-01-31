package com.bit.springApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bit.springApp.domain.users.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByEmail(String email);

}
