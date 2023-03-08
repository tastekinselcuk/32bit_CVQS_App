package com.bit.springApp.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bit.springApp.business.abstracts.DefectService;
import com.bit.springApp.domain.Defect;
import com.bit.springApp.dto.DefectDTO;

@RestController
@RequestMapping("api/defect")
public class DefectController {
	
	@Autowired
	private DefectService defectService;
	
    @ResponseBody
	@GetMapping("/getAllDefect") 
	public List<Defect> getAllDefect(){
		return this.defectService.getAllDefect();
	}
    
    @ResponseBody
	@GetMapping("/getAllDefectDto") 
	public List<DefectDTO> getAllDefectDTO(){
		return this.defectService.getAllDefectDto();
	}
    
    @PutMapping("/softDeleteCarDefectLocation/{DefectId}")
    public void softDeleteCarDefectLocation(@PathVariable int DefectId) {
        defectService.softDeleteDefect(DefectId);
    }

}
