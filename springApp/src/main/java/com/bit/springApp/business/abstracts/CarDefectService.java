package com.bit.springApp.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bit.springApp.dto.CarDefectServiceDTO;
import jakarta.transaction.Transactional;

@Transactional
public interface CarDefectService {

    List<CarDefectServiceDTO> getCarDefects();
    
    void saveCarDefect(int carId, String defectPartCategory, String defectPartName, 
    		String reportedBy, double latitude, double longitude, String terminalName);
    
    Page<CarDefectServiceDTO> getPageableCarDefect(Pageable pageable);

}
