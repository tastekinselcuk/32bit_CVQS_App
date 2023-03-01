package com.bit.springApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bit.springApp.domain.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

}