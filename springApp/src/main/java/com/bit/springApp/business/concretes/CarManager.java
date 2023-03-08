package com.bit.springApp.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.springApp.business.abstracts.CarService;
import com.bit.springApp.domain.Car;
import com.bit.springApp.dto.CarDTO;
import com.bit.springApp.repository.CarRepository;


@Service
public class CarManager implements CarService{
	
	@Autowired
	private CarRepository carRepository;

	
	@Override
	public List<Car> getAllCar() {
		return carRepository.findByDeletedFalse() ;
	}
	
    public List<CarDTO> getAllCarDto() {
        List<Car> carList = carRepository.findByDeletedFalse();
        List<CarDTO> carDTOList = new ArrayList<>();
        for (Car car : carList) {
        	carDTOList.add(new CarDTO(car.getCarId(), car.getCarModel()));
        }
        return carDTOList;
    }
	
	@Override
	public List<Car> getAllDeletedCar() {
		return carRepository.findByDeletedTrue();
	}

	@Override
	public Car saveCar(Car car) {
		return carRepository.save(car);
	}
	
	@Override
	public Car updateCar(Integer carId, Car car) {
        Car existingCar= carRepository.findByCarIdAndDeletedFalse(carId);
        existingCar.setCarId(car.getCarId());
        existingCar.setCarModel(car.getCarModel());
		return carRepository.save(existingCar);
	}

    public void softDeleteCar(int carId) {
        Car existingCar= carRepository.findByCarIdAndDeletedFalse(carId);
        existingCar.setDeleted(true);
        carRepository.save(existingCar);
    }



}
