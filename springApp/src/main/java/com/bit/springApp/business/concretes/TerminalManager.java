package com.bit.springApp.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.springApp.business.abstracts.TerminalService;
import com.bit.springApp.domain.Defect;
import com.bit.springApp.domain.Terminal;
import com.bit.springApp.dto.DefectDTO;
import com.bit.springApp.dto.TerminalDTO;
import com.bit.springApp.repository.TerminalRepository;

import jakarta.transaction.Transactional;

@Service
public class TerminalManager implements TerminalService {
	
	@Autowired
	TerminalRepository terminalRepository;

	@Override
	public List<Terminal> getAllTerminals() {
		return terminalRepository.findByDeletedFalse();
	}
	
	@Override
	public List<TerminalDTO> getAllTerminalDtos() {
    	List<Terminal> terminalList = terminalRepository.findByDeletedFalse();
    	List<TerminalDTO> terminalDTOList = new ArrayList<>();
    	for(Terminal terminal : terminalList) {
    		terminalDTOList.add(new TerminalDTO(terminal.getTerminalId(), terminal.getTerminalName(), terminal.getStatus()));
    	}
		return terminalDTOList;
	}

	@Override
	public Terminal saveTerminal(Terminal terminal) {
		return terminalRepository.save(terminal);
	}

	@Override
	public void softDeleteTerminal(int id) {
		
		Optional<Terminal> optionalDefect = terminalRepository.findByTerminalIdAndDeletedFalse(id);
		Terminal existingTerminal = optionalDefect.orElseThrow(() -> new RuntimeException("Terminal not found"));
		try {
			existingTerminal.setDeleted(true);
			terminalRepository.save(existingTerminal);
		} catch (Exception e) {
            throw new RuntimeException("Error deleting terminal", e);
		}
		
	}

}
