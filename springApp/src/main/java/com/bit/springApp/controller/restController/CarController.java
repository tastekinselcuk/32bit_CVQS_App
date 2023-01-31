package com.bit.springApp.controller.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bit.springApp.business.abstracts.CarService;
import com.bit.springApp.domain.carDefects.Car;


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
	@PostMapping("/add") 
	public Car add(Car car){
		return this.carService.saveCar(car);
	}

}

