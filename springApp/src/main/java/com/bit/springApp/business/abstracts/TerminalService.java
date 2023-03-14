package com.bit.springApp.business.abstracts;

import java.util.List;

import com.bit.springApp.domain.Terminal;
import com.bit.springApp.dto.TerminalDTO;

public interface TerminalService {
	
	List<Terminal> getAllTerminals();
	
	List<TerminalDTO> getAllTerminalDtos();
	
	public Terminal saveTerminal(Terminal terminal);
	
    void softDeleteTerminal(int id);
	
	

}
