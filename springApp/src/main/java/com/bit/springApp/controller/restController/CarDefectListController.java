package com.bit.springApp.controller.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bit.springApp.domain.Car;
import com.bit.springApp.domain.Defect;
import com.bit.springApp.domain.Terminal;
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

    @GetMapping("/cars")
    public Page<Car> getCars(@PageableDefault(size = 20) Pageable pageable) {
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
