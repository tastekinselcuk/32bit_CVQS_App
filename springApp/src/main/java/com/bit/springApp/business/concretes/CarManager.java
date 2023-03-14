package com.bit.springApp.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public CarDTO getCarDtoById(Integer id) {
        Optional<Car> optionalCar = carRepository.findByCarIdAndDeletedFalse(id);
        optionalCar.orElseThrow(() -> new RuntimeException("Car not found"));
        
        Car car = optionalCar.get();
        return new CarDTO(car.getCarId(), car.getCarModel());
    }
	
	@Override
	public Car saveCar(Car car) {		
	 try {
		 carRepository.save(car);
            return car;
        } catch (Exception e) {
            throw new RuntimeException("Error saving car", e);
        }
		
	}
	
	@Override
	public Car updateCar(Integer carId, Car car) {
        Optional<Car> optionalCar = carRepository.findByCarIdAndDeletedFalse(carId);
        Car existingCar = optionalCar.orElseThrow(() -> new RuntimeException("Car not found"));
        
        existingCar.setCarId(car.getCarId());
        existingCar.setCarModel(car.getCarModel());
		return carRepository.save(existingCar);
	}

    public void softDeleteCar(int carId) {
        Optional<Car> optionalCar = carRepository.findByCarIdAndDeletedFalse(carId);
        Car existingCar = optionalCar.orElseThrow(() -> new RuntimeException("Car not found"));
        try {
            existingCar.setDeleted(true);
            carRepository.save(existingCar);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting user", e);
        }
    }



}
