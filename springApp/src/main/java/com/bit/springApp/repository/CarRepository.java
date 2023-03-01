package com.bit.springApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bit.springApp.domain.Car;


public interface CarRepository extends JpaRepository<Car, Integer> {


}

