package com.bit.springApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bit.springApp.domain.Defect;

@Repository
public interface DefectRepository extends JpaRepository<Defect, Integer> {

}