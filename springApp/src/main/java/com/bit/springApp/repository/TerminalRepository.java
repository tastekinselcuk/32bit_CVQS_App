package com.bit.springApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bit.springApp.domain.Car;
import com.bit.springApp.domain.Terminal;

public interface TerminalRepository extends JpaRepository<Terminal, Integer> {

	List<Car> findByDeletedFalse();

	Car findByTerminalIdAndDeletedFalse(Integer id);
}