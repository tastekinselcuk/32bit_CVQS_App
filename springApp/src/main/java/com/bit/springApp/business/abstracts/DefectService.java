package com.bit.springApp.business.abstracts;

import java.util.List;

import com.bit.springApp.domain.Defect;
import com.bit.springApp.dto.DefectDTO;

public interface DefectService {

	List<Defect> getAllDefect();
	
	List<DefectDTO> getAllDefectDto();
	
	DefectDTO getDefectDtoById(int id);
	
    void softDeleteDefect(int DefectId);


}
