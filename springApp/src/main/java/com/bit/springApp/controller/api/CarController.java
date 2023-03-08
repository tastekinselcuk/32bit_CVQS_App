package com.bit.springApp.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bit.springApp.business.abstracts.CarService;
import com.bit.springApp.domain.Car;
import com.bit.springApp.dto.CarDTO;

/**
 * Car Controller
 * <br><br>
 * Mappings for registered Car information
 *
 */
@RestController
@RequestMapping("api/car")
public class CarController {
	
	@Autowired
	private CarService carService;
	
    @ResponseBody
	@GetMapping("/getAllCar") 
	public List<Car> getAllCar(){
		return this.carService.getAllCar();
	}
    
    @ResponseBody
    @GetMapping("/getAllCarDto")
    public List<CarDTO> getAllUserDto() {
        return this.carService.getAllCarDto();
    }
    
    @ResponseBody
	@GetMapping("/getAllDeletedCar") 
	public List<Car> getAllDeletedCar(){
		return this.carService.getAllDeletedCar();
	}
    
    @ResponseBody
	@PostMapping("/add") 
	public Car add(Car car){
		return this.carService.saveCar(car);
	}

	@PutMapping("/softDelete/{carId}")
	public void softDeleteCar(@PathVariable int carId) {
		this.carService.softDeleteCar(carId);
	}
}


//package com.bit.springApp.controller.api;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.bit.springApp.business.abstracts.CarService;
//import com.bit.springApp.domain.Car;
//
//import jakarta.validation.Valid;
//
///**
// * Car Controller
// * <br><br>
// * Mappings for registered Car information
// *
// */
//@RestController
//@RequestMapping("api/car")
//public class CarController {
//	
//	@Autowired
//	private CarService carService;
//	
//    @ResponseBody
//	@GetMapping("/getAllCar") 
//	public List<Car> getAllCar(){
//		return this.carService.getAllCar();
//	}
//
//    @PostMapping("/add")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<Car> addUser(@Valid @RequestBody Car car) {
//        Car createdCar = carService.saveCar(car);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdCar);
//    }
//    
//    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN') or @userSecurity.checkUserId(authentication,#id)")
//    public ResponseEntity<Car> updateCar(@PathVariable Integer id, @Valid @RequestBody Car car) {
//        Car updatedCar = carService.updateCar(id, car);
//        return ResponseEntity.ok().body(updatedCar);
//    }
//    
//    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN') or @userSecurity.checkUserId(authentication,#id)")
//    public ResponseEntity<?> deleteCar(@PathVariable Integer id) {
//        carService.softDeleteCar(id);
//        return ResponseEntity.ok().build();
//    }
//
//}
//


