package com.bit.springApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bit.springApp.domain.users.User;


public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByEmail(String email);
	
	boolean existsByEmailAndDeletedFalse(String email);

	List<User> findByDeletedFalse();

	Optional<User> findByIdAndDeletedFalse(Integer id);

}