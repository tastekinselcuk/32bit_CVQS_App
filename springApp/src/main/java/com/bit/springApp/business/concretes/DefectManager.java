package com.bit.springApp.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import com.bit.springApp.business.abstracts.DefectService;
import com.bit.springApp.domain.Defect;
import com.bit.springApp.domain.Location;
import com.bit.springApp.dto.DefectDTO;
import com.bit.springApp.exception.AppException;
import com.bit.springApp.repository.DefectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

/**
 * This class implements the DefectService interface and provides methods for managing defects in the system.
 */
@RequiredArgsConstructor
@Service
public class DefectManager implements DefectService {
	
	@Autowired
	private final DefectRepository defectRepository;

	/**
	 * Returns a list of all defects in the system.
	 *
	 * @return List of Defect objects
	 */
	@Override
	public List<Defect> getAllDefect() {
		return defectRepository.findByDeletedFalse();
	}
    
	/**
	 * Returns a list of all defect DTOs in the system.
	 *
	 * @return List of DefectDTO objects
	 */
	@Override
	public List<DefectDTO> getAllDefectDto() {
    	List<Defect> defectList = defectRepository.findByDeletedFalse();
    	List<DefectDTO> defectDTOList = new ArrayList<>();
    	for(Defect defect : defectList) {
    		defectDTOList.add(new DefectDTO(defect.getDefectId(), defect.getDefectPartCategory(), defect.getDefectPartName(), defect.getReportedBy(), defect.getReportedDate(), defect.getLocation().getLatitude(), defect.getLocation().getLongitude(), defect.getTerminal().getTerminalName()));
    	}
		return defectDTOList;
	}
    
	/**
	 * Returns a defect DTO with the specified ID.
	 *
	 * @param defectId ID of the defect to be returned
	 * @return DefectDTO object
	 */
	@Override
	public DefectDTO getDefectDtoById(int DefectId) {
        if (defectRepository.findByDefectIdAndDeletedFalse(DefectId) == null) {
            throw new AppException(
                    HttpStatus.BAD_REQUEST,
                    "No Id Provided",
                    "Please provide id of the record you want to see.",
                    "No id provided for the record to be seen.");
        } 
	    Optional<Defect> optionalDefect = defectRepository.findByDefectIdAndDeletedFalse(DefectId);
	    Defect existingDefect = optionalDefect.get();
	    return new DefectDTO(existingDefect.getDefectId(), existingDefect.getDefectPartCategory(), existingDefect.getDefectPartName(), existingDefect.getReportedBy(), existingDefect.getReportedDate(), existingDefect.getLocation().getLatitude(), existingDefect.getLocation().getLongitude(), existingDefect.getTerminal().getTerminalName());
	} 
	
    @Override
    public Page<DefectDTO> getPageableDefect(Pageable pageable) {
        Page<Defect> defects = defectRepository.findByDeletedFalse(pageable);
        return defects.map(this::convertToDto);
    }


    /**
     * Soft deletes a defect in the system by setting its 'deleted' flag to true and marking its associated Location as deleted, if present..
     *
     * @param defectId ID of the defect to be deleted
     */
    @Transactional
    @Override
    public void softDeleteDefect(int DefectId) {
        if (defectRepository.findByDefectIdAndDeletedFalse(DefectId) == null) {
            throw new AppException(
                    HttpStatus.BAD_REQUEST,
                    "No Id Provided",
                    "Please provide id of the record you want to delete.",
                    "No id provided for the record to be deleted.");
        } 
        Optional<Defect> optionalDefect = defectRepository.findByDefectIdAndDeletedFalse(DefectId);
        Defect existingDefect = optionalDefect.get();
        existingDefect.setDeleted(true);
        Location location = existingDefect.getLocation();
        if (location != null) {
            location.setDeleted(true);
        }
        defectRepository.save(existingDefect);
        
    }


    public DefectDTO convertToDto(Defect defect) {
        return DefectDTO.builder()
            .defectId(defect.getDefectId())
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
