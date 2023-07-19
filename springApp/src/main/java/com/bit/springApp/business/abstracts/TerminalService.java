package com.bit.springApp.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bit.springApp.domain.Terminal;
import com.bit.springApp.dto.TerminalDTO;

public interface TerminalService {
	
	List<Terminal> getAllTerminals();
	
	List<TerminalDTO> getAllTerminalDtos();
	
	Terminal saveTerminal(Terminal terminal);
	    
    void changeTerminalStatus(int id);
    
	Page<TerminalDTO> getTerminals(String status, String terminalName, Pageable pageable);

	

}
