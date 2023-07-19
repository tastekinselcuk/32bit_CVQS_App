package com.bit.springApp.business.concretes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bit.springApp.business.abstracts.CarDefectService;
import com.bit.springApp.domain.Car;
import com.bit.springApp.domain.Defect;
import com.bit.springApp.domain.Location;
import com.bit.springApp.domain.Terminal;
import com.bit.springApp.dto.CarDefectServiceDTO;
import com.bit.springApp.enums.TerminalStatus;
import com.bit.springApp.exception.AppException;
import com.bit.springApp.repository.CarRepository;
import com.bit.springApp.repository.DefectRepository;
import com.bit.springApp.repository.LocationRepository;
import com.bit.springApp.repository.TerminalRepository;
import lombok.RequiredArgsConstructor;

/**
 * The CarDefectManager class is responsible for managing car defects.
 * It implements the CarDefectService interface and provides methods for
 * retrieving and saving car defects using the repositories for the Defect, Car,
 * Location, and Terminal entities.
 * 
 * @see CarDefectService
 * @see DefectRepository
 * @see CarRepository
 * @see LocationRepository
 * @see TerminalRepository
*/
@RequiredArgsConstructor
@Service
public class CarDefectManager implements CarDefectService {
	
    private final DefectRepository defectRepository;
    private final CarRepository carRepository;
    private final LocationRepository locationRepository;
    private final TerminalRepository terminalRepository;

    
    /**
     * Returns a list of all car defects.
     * 
     * @return a list of car defect data transfer objects
     */
	@Override
	public List<CarDefectServiceDTO> getCarDefects() {
        List<CarDefectServiceDTO> result = new ArrayList<>();
        List<Defect> defects = defectRepository.findByDeletedFalse();
        for (Defect defect : defects) {
        	CarDefectServiceDTO dto = new CarDefectServiceDTO();
            dto.setCarId(defect.getCar().getCarId());
            dto.setDefectPartCategory(defect.getDefectPartCategory());
            dto.setDefectPartName(defect.getDefectPartName());
            dto.setReportedBy(defect.getReportedBy());
            dto.setReportedDate(defect.getReportedDate());
            dto.setLatitude(defect.getLocation().getLatitude());
            dto.setLongitude(defect.getLocation().getLongitude());
            dto.setTerminalName(defect.getTerminal().getTerminalName());
            result.add(dto);
        }
        return result;
	}
	
	/**
	 * Saves a car defect using the given parameters.
	 * 
	 * @param carId The ID of the car.
	 * @param defectPartCategory The category of the defect part.
	 * @param defectPartName The name of the defect part.
	 * @param reportedBy The person who reported the defect.
	 * @param latitude The latitude of the location where the defect will be captured in a photo.
	 * @param longitude The longitude of the location where the defect will be captured in a photo.
	 * @param terminalName The name or ID of the terminal where the defect was reported from.
	 */
	@Override
    public void saveCarDefect(int carId, String defectPartCategory, String defectPartName, 
    		String reportedBy, double latitude, double longitude, String terminalName) {
        
        //Car
        Optional<Car> optionalCar = carRepository.findByCarIdAndDeletedFalse(carId);
	    if (optionalCar.isEmpty()) {
	        throw new AppException(
	                HttpStatus.BAD_REQUEST,
	                "No Id Provided",
	                "Please provide id of the record you want to update.",
	                "No id provided for the record to be updated.");
	    } 
        Car car = optionalCar.get();
        
        //Location
        Location location = new Location();
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        locationRepository.save(location);
        
        //Terminal
        Optional<Terminal> optionalTerminal= terminalRepository.findByTerminalNameAndStatus(terminalName, TerminalStatus.ACTIVE);
	    if (optionalTerminal.isEmpty()) {
	        throw new AppException(
	                HttpStatus.BAD_REQUEST,
	                "No Id Provided",
	                "Please provide id of the record you want to update.",
	                "No id provided for the record to be updated.");
	    } 
        Terminal terminal = optionalTerminal.get();
        
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


	public Page<CarDefectServiceDTO> getPageableCarDefect(Pageable pageable) {
	    Page<Defect> defects = defectRepository.findByDeletedFalse(pageable);
	    return defects.map(this::convertToDto);
	}

	public CarDefectServiceDTO convertToDto(Defect defect) {
	    return CarDefectServiceDTO.builder()
	        .carId(defect.getCar().getCarId())
	        .defectPartCategory(defect.getDefectPartCategory())
	        .defectPartName(defect.getDefectPartName())
	        .reportedBy(defect.getReportedBy())
	        .reportedDate(defect.getReportedDate())
	        .latitude(defect.getLocation().getLatitude())
	        .longitude(defect.getLocation().getLongitude())
	        .terminalName(defect.getTerminal().getTerminalName())
	        .build();
	}




}
