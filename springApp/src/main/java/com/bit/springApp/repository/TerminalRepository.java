package com.bit.springApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bit.springApp.domain.Terminal;

public interface TerminalRepository extends JpaRepository<Terminal, Integer> {

}