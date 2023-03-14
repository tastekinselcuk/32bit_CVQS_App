package com.bit.springApp.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bit.springApp.business.abstracts.DefectService;
import com.bit.springApp.domain.Defect;
import com.bit.springApp.dto.DefectDTO;

/**
 * Rest API for managing defects.
 */
@RestController
@RequestMapping("api/defect")
public class DefectController {
	
    /**
     * Constructor for DefectController
     * 
     * @param defectService the defect service to be used
     */
	@Autowired
	private DefectService defectService;
	
    /**
     * Returns a list of all active defects.
     * 
     * @return a ResponseEntity containing a list of all active defects
     */
	@GetMapping("/getAllDefect") 
	public List<Defect> getAllDefect(){
		return this.defectService.getAllDefect();
	}
    
    /**
     * Returns a list of all defects as DefectDTOs.
     * 
     * @return a ResponseEntity containing a list of all defects as DefectDTOs
     */
	@GetMapping("/getAllDefectDto") 
	public List<DefectDTO> getAllDefectDto(){
		return this.defectService.getAllDefectDto();
	}
	
	/**
     * Returns the defect with the given ID as a DefectDTO.
     * 
     * @param id the ID of the defect to be returned
     * @return a ResponseEntity containing the defect with the given ID as a DefectDTO
     */
	@GetMapping("/getDefectDtoById/{id}") 
    public ResponseEntity<?> getDefectDtoById(@PathVariable Integer id) {
		try {
			DefectDTO defectDTO = defectService.getDefectDtoById(id);
            return ResponseEntity.ok().body(defectDTO);
		} catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
    
	/**
     * Soft deletes the defect with the given ID.
     * 
     * @param DefectId the ID of the defect to be soft deleted
     * @return a ResponseEntity containing a message indicating whether the soft delete was successful or not
     */
    @PutMapping("/softDeleteCarDefectLocation/{DefectId}")
    public ResponseEntity<String> softDeleteCarDefectLocation(@PathVariable int DefectId) {
    	try {
            defectService.softDeleteDefect(DefectId);
            return ResponseEntity.ok("Defect deleted with soft delete.");
    	} catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
    }

}
