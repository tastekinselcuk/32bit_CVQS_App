package com.bit.springApp.controller.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bit.springApp.business.abstracts.CarDefectService;


@RestController
@RequestMapping("/api/carDefect")
public class CarDefectController {
	
	private CarDefectService carDefectService;

	@Autowired
	public CarDefectController(CarDefectService carDefectService) {
		this.carDefectService = carDefectService;
	}

    @PostMapping("/save")
    public ResponseEntity<?> saveCarDefect(@RequestParam(required = true) int carId,
			   @RequestParam(required = true) String defectPartCategory,
               @RequestParam(required = true) String defectPartName,
               @RequestParam(required = false, defaultValue = "selcuk") String reportedBy,
               @RequestParam(required = true) double latitude,
               @RequestParam(required = true) double longitude,
               @RequestParam(required = true) String terminalName) {
        try {
        	carDefectService.saveCarDefect(carId, defectPartCategory, defectPartName, reportedBy, latitude, longitude, terminalName);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
