package com.bit.springApp.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.springApp.business.abstracts.DefectService;
import com.bit.springApp.domain.Defect;
import com.bit.springApp.domain.Location;
import com.bit.springApp.dto.DefectDTO;
import com.bit.springApp.domain.Car;

import com.bit.springApp.repository.DefectRepository;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class DefectManager implements DefectService {
	
	@Autowired
	private DefectRepository defectRepository;

    @Transactional
	@Override
	public List<Defect> getAllDefect() {
		return defectRepository.findByDeletedFalse();
	}
    
    @Transactional
	@Override
	public List<DefectDTO> getAllDefectDto() {
    	List<Defect> defectList = defectRepository.findByDeletedFalse();
    	List<DefectDTO> defectDTOList = new ArrayList<>();
    	for(Defect defect : defectList) {
    		defectDTOList.add(new DefectDTO(defect.getDefectId(), defect.getDefectPartCategory(), defect.getDefectPartName(), defect.getReportedBy(), defect.getReportedDate(), defect.getLocation(), defect.getTerminal()));
    	}
		return defectDTOList;
	}

    @Transactional
    @Override
    public void softDeleteDefect(int DefectId) {
        Defect existingDefect = defectRepository.findByDefectIdAndDeletedFalse(DefectId);
        if (existingDefect != null) {
            existingDefect.setDeleted(true);
            Location location = existingDefect.getLocation();
            if (location != null) {
                location.setDeleted(true);
            }
            Car car = existingDefect.getCar();
            if (car != null) {
                car.setDeleted(true);
            }
            defectRepository.save(existingDefect);
        } else {
        	log.error("Defect with ID {} does not exist",DefectId);
        }
    }



}
