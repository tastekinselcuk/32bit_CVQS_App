package com.bit.springApp.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bit.springApp.business.abstracts.CarDefectService;
import com.bit.springApp.domain.Defect;
import com.bit.springApp.dto.CarDefectDTO;
import com.bit.springApp.repository.DefectRepository;

public class CarDefectManager implements CarDefectService {
    @Autowired
    private DefectRepository defectRepository;

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

}
