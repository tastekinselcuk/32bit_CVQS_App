package com.bit.springApp.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bit.springApp.domain.Car;
import com.bit.springApp.dto.CarDTO;


public interface CarService {

	
	List<Car> getAllCar();
	
    List<CarDTO> getAllCarDto();
    
    CarDTO getCarDtoById(Integer id);
    
	public Car saveCar(Car car);
	
    Car updateCar(Integer id, Car car);
    
    void softDeleteCar(int id);
    
    Page<CarDTO> getPageableCar(Pageable pageable);

	
}

