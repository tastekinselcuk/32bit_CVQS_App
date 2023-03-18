package com.bit.springApp.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bit.springApp.business.abstracts.CarDefectService;


@Controller
public class CarDefectWebController {
	
	private CarDefectService carDefectService;

	@Autowired
	public CarDefectWebController(CarDefectService carDefectService) {
		this.carDefectService = carDefectService;
	}

    @PostMapping("/save")
    public String save(@RequestParam(required = true) int carId,
    				   @RequestParam(required = true) String defectPartCategory,
                       @RequestParam(required = true) String defectPartName,
                       @RequestParam(required = false, defaultValue = "selcuk") String reportedBy,
                       @RequestParam(required = true) double latitude,
                       @RequestParam(required = true) double longitude,
                       @RequestParam(required = true) String terminalName) {
    	try {
        	carDefectService.saveCarDefect(carId, defectPartCategory, defectPartName, reportedBy, latitude, longitude, terminalName);
            return "redirect:/index";
    	} catch (Exception e) {
            return "redirect:/errorPage";
		}


    }

}
