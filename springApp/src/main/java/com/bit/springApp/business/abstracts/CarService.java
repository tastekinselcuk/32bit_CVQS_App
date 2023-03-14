package com.bit.springApp.business.abstracts;

import java.util.List;

import com.bit.springApp.domain.Car;
import com.bit.springApp.dto.CarDTO;



public interface CarService {

	
	List<Car> getAllCar();
	
    List<CarDTO> getAllCarDto();
    
    CarDTO getCarDtoById(Integer id);
    
	public Car saveCar(Car car);
	
    Car updateCar(Integer id, Car car);
    
    void softDeleteCar(int id);

	
	
}

