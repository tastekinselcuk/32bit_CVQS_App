package com.bit.springApp.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.springApp.business.abstracts.TerminalService;
import com.bit.springApp.domain.Terminal;
import com.bit.springApp.dto.TerminalDTO;
import com.bit.springApp.repository.TerminalRepository;

/**
 * This class implements the TerminalService interface and provides functionality for managing terminals.
 */
@Service
public class TerminalManager implements TerminalService {
	
	@Autowired
	TerminalRepository terminalRepository;

    /**
     * Returns a list of all terminals in the system.
     *
     * @return List of Terminal objects
     */
	@Override
	public List<Terminal> getAllTerminals() {
		return terminalRepository.findByDeletedFalse();
	}
	
    /**
     * Returns a list of all terminalDtos in the system.
     *
     * @return List of Terminal objects
     */
	@Override
	public List<TerminalDTO> getAllTerminalDtos() {
    	List<Terminal> terminalList = terminalRepository.findByDeletedFalse();
    	List<TerminalDTO> terminalDTOList = new ArrayList<>();
    	for(Terminal terminal : terminalList) {
    		terminalDTOList.add(new TerminalDTO(terminal.getTerminalId(), terminal.getTerminalName(), terminal.getStatus()));
    	}
		return terminalDTOList;
	}

    /**
     * Saves a new terminal to the system.
     *
     * @param terminal Terminal object to be saved
     * @return Terminal object that was saved
     */
	@Override
	public Terminal saveTerminal(Terminal terminal) {
		return terminalRepository.save(terminal);
	}

    /**
     * Soft deletes a terminal in the system by setting the "deleted" flag to true.
     *
     * @param id ID of the terminal to be deleted
     */
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
