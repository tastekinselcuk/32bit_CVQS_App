package com.bit.springApp.controller.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bit.springApp.business.abstracts.CarDefectService;
import com.bit.springApp.domain.Car;
import com.bit.springApp.domain.CarSpecifications;
import com.bit.springApp.domain.Defect;
import com.bit.springApp.domain.Terminal;
import com.bit.springApp.dto.CarDefectDTO;
import com.bit.springApp.repository.CarRepository;
import com.bit.springApp.repository.DefectRepository;
import com.bit.springApp.repository.TerminalRepository;

@RestController
@RequestMapping("/page")
public class CarDefectListController {
  
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private DefectRepository defectRepository;

    @Autowired
    private TerminalRepository terminalRepository;

    @Autowired
    private CarDefectService carDefectService;

    
    @GetMapping("/carDefects")
    public ResponseEntity<List<CarDefectDTO>> getCarDefects() {
        List<CarDefectDTO> carDefects = carDefectService.getCarDefects();
        return new ResponseEntity<>(carDefects, HttpStatus.OK);
    }
    
    @GetMapping("/cars")
    public Page<Car> getCars(@PageableDefault(size = 20) Pageable pageable,
                             @RequestParam(value = "carModel", required = false) String carModel) {
        if (carModel != null) {
            return carRepository.findAll(CarSpecifications.hasCarModel(carModel), pageable);
        }
        return carRepository.findAll(pageable);
    }


    @GetMapping("/defects")
    public Page<Defect> getDefects(@PageableDefault(size = 20) Pageable pageable) {
        return defectRepository.findAll(pageable);
    }

    @GetMapping("/terminals")
    public Page<Terminal> getTerminals(@PageableDefault(size = 20) Pageable pageable) {
        return terminalRepository.findAll(pageable);
    }
    
    

}
