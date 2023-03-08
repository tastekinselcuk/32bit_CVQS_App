package com.bit.springApp.business.abstracts;

import java.util.List;

import com.bit.springApp.dto.CarDefectDTO;

import jakarta.transaction.Transactional;

@Transactional
public interface CarDefectService {

    List<CarDefectDTO> getCarDefects();
    
    public void saveCarDefect(int carId, String defectPartCategory, String defectPartName, 
    		String reportedBy, double latitude, double longitude, String terminalName);
}
