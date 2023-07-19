package com.bit.springApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bit.springApp.domain.CarImage;


@Repository
public interface CarImageRepository extends JpaRepository<CarImage, Long> {

    Optional<CarImage> findById(Long carId);

}
