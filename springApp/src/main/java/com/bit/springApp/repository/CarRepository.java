package com.bit.springApp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bit.springApp.domain.Car;


public interface CarRepository extends JpaRepository<Car, Integer>, JpaSpecificationExecutor<Car> {

	List<Car> findByDeletedFalse();
	
	List<Car> findByDeletedTrue();

	Car findByCarIdAndDeletedFalse(Integer id);
	
    Page<Car> findAll(Specification<Car> spec, Pageable pageable);

	
	
}

