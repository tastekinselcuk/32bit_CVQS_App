package com.bit.springApp.business.abstracts;

import java.util.List;

import com.bit.springApp.domain.carDefects.Car;


public interface CarService {

	
	List<Car> getAllCar();
	public Car saveCar(Car car);
	public void deleteCar(int id);
	
	
}

