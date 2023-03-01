package com.bit.springApp.controller.restController;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bit.springApp.business.abstracts.CarDefectService;
import com.bit.springApp.domain.Car;
import com.bit.springApp.domain.Defect;
import com.bit.springApp.domain.Location;
import com.bit.springApp.domain.Terminal;
import com.bit.springApp.repository.CarRepository;
import com.bit.springApp.repository.DefectRepository;
import com.bit.springApp.repository.LocationRepository;
import com.bit.springApp.repository.TerminalRepository;

@Controller
public class CarDefectController {
	
	private CarDefectService carDefectService;

    @Autowired
    private DefectRepository defectRepository;
    
    @Autowired
    private CarRepository carRepository;
    
    @Autowired
    private TerminalRepository terminalRepository;
  
    @Autowired
    private LocationRepository locationRepository;

    @PostMapping("/save")
    public String save(@RequestParam(required = true) int carId,
    				   @RequestParam(required = true) String defectPartCategory,
                       @RequestParam(required = true) String defectPartName,
                       @RequestParam(required = false, defaultValue = "selcuk") String reportedBy,
                       @RequestParam(required = true) double latitude,
                       @RequestParam(required = true) double longitude,
                       @RequestParam(required = true) String terminalName) {
    	//Car
    	//buradaki hatayı çöz, formda kullanıcıya istediği araç id'sini girmesinde özgür bırakıyoruz ama eğer girilen araç id için tanımlanan identity kuralına uymuyorsa hatayla karşılaşıyoruz
    	//Aşağıdaki gibi veritabanını kontrol edip kayıt yapmayan bir sürüm de kullanabiliriz ama bu kodda da hata döndürme kısmında hata var, tekrar bak.
//    	try {
//    	    Car car = carRepository.findById(carId).orElseThrow(() -> new IllegalArgumentException("Invalid car id"));
//    	} catch (IllegalArgumentException e) {
//    	    // Hata mesajını döndür
//    	    return "Invalid car id: " + e.getMessage();
//    	} 
    	Car car = new Car(); 
    	car.setCarId(carId);
    	car.setCarModel("Corolla"); //temporary
        carRepository.save(car);
        //Location
        Location location = new Location();
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        locationRepository.save(location);
        //Terminal
        Terminal terminal = new Terminal();
        terminal.setTerminalName(terminalName);
        terminalRepository.save(terminal);
    	//Fault
        Defect defect = new Defect(); 
        defect.setDefectPartCategory(defectPartCategory);
        defect.setDefectPartName(defectPartName);
        defect.setReportedBy(reportedBy);
        defect.setReportedDate(new Date());

        
        defect.setLocation(location);
        defect.setCar(car);
        defect.setTerminal(terminal);

        location.getDefects().add(defect);

        defectRepository.save(defect);
        
        return "redirect:/index";
    }
}
