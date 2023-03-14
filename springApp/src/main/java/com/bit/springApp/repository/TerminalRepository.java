package com.bit.springApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bit.springApp.domain.Terminal;

public interface TerminalRepository extends JpaRepository<Terminal, Integer> {

	List<Terminal> findByDeletedFalse();

	Optional<Terminal> findByTerminalIdAndDeletedFalse(Integer id);
}