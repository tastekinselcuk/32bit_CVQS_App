package com.bit.springApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bit.springApp.domain.Terminal;
import com.bit.springApp.enums.TerminalStatus;


@Repository
public interface TerminalRepository extends JpaRepository<Terminal, Integer> {

	List<Terminal> findByStatus(TerminalStatus status);
	
	Optional<Terminal> findByTerminalNameAndStatus(String terminalName, TerminalStatus status);


    Optional<Terminal> findByTerminalIdAndStatus(Integer id, TerminalStatus status);
	
	//Created for paging, sorting and filtering features
    Page<Terminal> findAll(Specification<Terminal> spec, Pageable pageable);

}