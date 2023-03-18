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

/**
 * This class implements the CarService interface and provides methods for managing cars in the system.
 */
@Service
public class CarManager implements CarService{
	
	@Autowired
	private CarRepository carRepository;


    /**
     * Returns a list of all cars in the system.
     *
     * @return List of Car objects
     */
	@Override
	public List<Car> getAllCar() {
		return carRepository.findByDeletedFalse() ;
	}
	
    /**
     * Returns a list of all car DTOs in the system.
     *
     * @return List of CarDTO objects
     */
    public List<CarDTO> getAllCarDto() {
        List<Car> carList = carRepository.findByDeletedFalse();
        List<CarDTO> carDTOList = new ArrayList<>();
        for (Car car : carList) {
        	carDTOList.add(new CarDTO(car.getCarId(), car.getCarModel()));
        }
        return carDTOList;
    }
    
    /**
     * Returns a car DTO with the specified ID.
     *
     * @param id ID of the car to be returned
     * @return CarDTO object
     */
    @Override
    public CarDTO getCarDtoById(Integer id) {
        Optional<Car> optionalCar = carRepository.findByCarIdAndDeletedFalse(id);
        optionalCar.orElseThrow(() -> new RuntimeException("Car not found"));
        
        Car car = optionalCar.get();
        return new CarDTO(car.getCarId(), car.getCarModel());
    }
	
    /**
     * Saves a new car to the system.
     *
     * @param car Car object to be saved
     * @return Saved Car object
     */
	@Override
	public Car saveCar(Car car) {		
	 try {
		 carRepository.save(car);
            return car;
        } catch (Exception e) {
            throw new RuntimeException("Error saving car", e);
        }
		
	}
	
    /**
     * Updates an existing car in the system.
     *
     * @param carId ID of the car to be updated
     * @param car Updated Car object
     * @return Updated Car object
     */
	@Override
	public Car updateCar(Integer carId, Car car) {
        Optional<Car> optionalCar = carRepository.findByCarIdAndDeletedFalse(carId);
        Car existingCar = optionalCar.orElseThrow(() -> new RuntimeException("Car not found"));
        
        existingCar.setCarId(car.getCarId());
        existingCar.setCarModel(car.getCarModel());
		return carRepository.save(existingCar);
	}

    /**
     * Soft deletes a car in the system by setting the "deleted" flag to true.
     *
     * @param carId ID of the car to be deleted
     */
    public void softDeleteCar(int carId) {
        Optional<Car> optionalCar = carRepository.findByCarIdAndDeletedFalse(carId);
        Car existingCar = optionalCar.orElseThrow(() -> new RuntimeException("Car not found"));
        
        existingCar.setDeleted(true);
        carRepository.save(existingCar);
    }


}
