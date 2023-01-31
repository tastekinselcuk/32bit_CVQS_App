package com.bit.springApp.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.springApp.business.abstracts.CarService;
import com.bit.springApp.domain.carDefects.Car;
import com.bit.springApp.repository.CarRepository;

@Service
public class CarManager implements CarService{
	
	private CarRepository carRepository;

	@Autowired
	public CarManager(CarRepository carRepository) {
		this.carRepository = carRepository;
	}
	
	@Override
	public List<Car> getAllCar() {
		return carRepository.findAll() ;
	}

	@Override
	public Car saveCar(Car car) {
		return carRepository.save(car);
	}

	@Override
	public void deleteCar(int id) {
		carRepository.deleteById(id);
	}


}
