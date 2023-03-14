package com.bit.springApp.business.concretes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.springApp.business.abstracts.CarDefectService;
import com.bit.springApp.domain.Car;
import com.bit.springApp.domain.Defect;
import com.bit.springApp.domain.Location;
import com.bit.springApp.domain.Terminal;
import com.bit.springApp.dto.CarDefectDTO;
import com.bit.springApp.repository.CarRepository;
import com.bit.springApp.repository.DefectRepository;
import com.bit.springApp.repository.LocationRepository;
import com.bit.springApp.repository.TerminalRepository;

@Service
public class CarDefectManager implements CarDefectService {
	


	@Autowired
    private DefectRepository defectRepository;
    private CarRepository carRepository; 
    private LocationRepository locationRepository;
    private TerminalRepository terminalRepository;

    @Autowired
	public CarDefectManager(DefectRepository defectRepository, CarRepository carRepository,
			LocationRepository locationRepository, TerminalRepository terminalRepository) {
		super();
		this.defectRepository = defectRepository;
		this.carRepository = carRepository;
		this.locationRepository = locationRepository;
		this.terminalRepository = terminalRepository;
	}
    
    public CarDefectManager() {
		super();
	}

	@Override
	public List<CarDefectDTO> getCarDefects() {
        List<CarDefectDTO> result = new ArrayList<>();
        List<Defect> faults = defectRepository.findAll();
        for (Defect fault : faults) {
        	CarDefectDTO dto = new CarDefectDTO();
            dto.setCarId(fault.getCar().getCarId());
            dto.setDefectPartCategory(fault.getDefectPartCategory());
            dto.setDefectPartName(fault.getDefectPartName());
            dto.setReportedBy(fault.getReportedBy());
            dto.setReportedDate(fault.getReportedDate());
            dto.setLatitude(fault.getLocation().getLatitude());
            dto.setLongitude(fault.getLocation().getLongitude());
            dto.setTerminalName(fault.getTerminal().getTerminalName());
            result.add(dto);
        }
        return result;
	}
	
	@Override
    public void saveCarDefect(int carId, String defectPartCategory, String defectPartName, 
    		String reportedBy, double latitude, double longitude, String terminalName) {
        
        //Car
        Optional<Car> optionalCar = carRepository.findByCarIdAndDeletedFalse(carId);
        Car car = optionalCar.orElseThrow(() -> new RuntimeException("Car not found"));
        
        //Location
        Location location = new Location();
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        locationRepository.save(location);
        
        //Terminal
        Terminal terminal = new Terminal();
        terminal.setTerminalName(terminalName);
        terminalRepository.save(terminal);
        
        //Defect
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
   }


}
