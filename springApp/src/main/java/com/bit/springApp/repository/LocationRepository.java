package com.bit.springApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bit.springApp.domain.Location;


@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

	List<Location> findByDeletedFalse();

	Location findByLocationIdAndDeletedFalse(Integer id);
	
}