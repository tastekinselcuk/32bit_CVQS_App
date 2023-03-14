package com.bit.springApp.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bit.springApp.business.abstracts.CarService;
import com.bit.springApp.domain.Car;
import com.bit.springApp.dto.CarDTO;

/**
 * Rest API for managing cars.
*/
@RestController
@RequestMapping("api/car")
public class CarController {
	
	/**
	 * Constructor for CarController
	 * 
	 * @param carService the car service to be used
	*/
	@Autowired
	private CarService carService;
	
	/**
	 * Returns a list of all cars.
	 * 
	 * @return a ResponseEntity containing a list of all cars
	*/
	@GetMapping("/getAllCar") 
	public List<Car> getAllCar(){
		return this.carService.getAllCar();
	}
    
	/**
	 * Returns a list of all cars as CarDTOs.
	 * 
	 * @return a ResponseEntity containing a list of all cars as CarDTOs
	*/
    @GetMapping("/getAllCarDto")
    public List<CarDTO> getAllCarDto() {
        return this.carService.getAllCarDto();
    }
    
	/**
	 * Returns a specific car by ID.
	 * 
	 * @param id the ID of the car to retrieve
	 * @return a ResponseEntity containing the car with the given ID, or a 500 Internal Server Error status if an unexpected error occurs.
	*/
    @GetMapping("/getCarDtoById/{id}")
    public ResponseEntity<?> getCarDtoById(@PathVariable Integer id) {
	    try {
	    CarDTO carDTO = carService.getCarDtoById(id);
	    return ResponseEntity.ok().body(carDTO);
	    } catch (Exception e) {
	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	    }
    }
    
	/**
	 * Adds a new car
	 * 
	 * @param car the car to add
	 * @return a ResponseEntity containing the newly created car with a 201 Created status, or a 400 Bad Request status if the request is invalid
	*/
	@PostMapping("/saveCar") 
	public ResponseEntity<?> saveCar(@RequestBody Car car){
		try {
			Car createdCar = carService.saveCar(car);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCar);
		} catch (Exception e) {
            String errorMessage = e.getMessage();
            return ResponseEntity.badRequest().body(errorMessage);
		}
	}
	
    /**
     * Updates an existing car.
     *
     * @param id the ID of the car to update
     * @param car the updated car object
     * @return a ResponseEntity containing the updated car with a 200 OK status, or a 500 Internal Server Error status if an unexpected error occurs.
     */
    @PutMapping("/updateCar/{id}")
    public ResponseEntity<?> updateCar(@PathVariable Integer id, @RequestBody Car car) {
        try {
            Car updatedCar = carService.updateCar(id, car);
            return ResponseEntity.ok("Car updated successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Soft delete a car by ID.
     *
     * @param id the ID of the car to delete
     * @return a ResponseEntity containing a success message if the delete was successful, or a 500 Internal Server Error status if an unexpected error occurs.
     */
	@PutMapping("/softDelete/{carId}")
	public ResponseEntity<String> softDeleteCar(@PathVariable int carId) {
		try {
			this.carService.softDeleteCar(carId);
            return ResponseEntity.ok("Car deleted with soft delete.");

		} catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}


