package com.bit.springApp.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bit.springApp.business.abstracts.TerminalService;
import com.bit.springApp.domain.Terminal;
import com.bit.springApp.dto.TerminalDTO;

@RestController
@RequestMapping("api/terminals")
public class TerminalController {
	
	@Autowired
	private TerminalService terminalService;

	
    @GetMapping("/getAllTerminals")
    public List<Terminal> getAllTerminals() {
    	return this.terminalService.getAllTerminals();
    }
    
    @GetMapping("/getAllTerminalDtos")
    public List<TerminalDTO> getAllTerminalDtos() {
    	return this.terminalService.getAllTerminalDtos();
    }
    
    @PostMapping("/save")
    public ResponseEntity<String> saveTerminal(@RequestBody Terminal terminal){
    	try {
    		terminalService.saveTerminal(terminal);
            return ResponseEntity.ok("Terminal saved succesfully.");
    	} catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
    }
    
    @PutMapping("/softDeleteTerminal/{id}")
    public ResponseEntity<String> softDeleteTerminal(@PathVariable Integer id) {
    	try {
            terminalService.softDeleteTerminal(id);
            return ResponseEntity.ok("Terminal deleted with soft delete.");
    	} catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
    }
    
    
    
    
}
