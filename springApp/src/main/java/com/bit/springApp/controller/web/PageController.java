package com.bit.springApp.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Page Controller
 * <br><br>
 * Mappings for all pages are defined here.
 *
 */
@Controller
public class PageController {
	

	@GetMapping("/index")
	public String GetMainPage() {
		return "index";
	}
	
	@GetMapping("/terminalPage")
	public String GetTerminals() {
		return "terminalPage";
	}

	@GetMapping("/usage")
	public String GetUsage() {
		return "usage";
	}
	
	@GetMapping("/problemRecord")
	public String GetProblemRecord() {
		return "problemRecord";
	}
	
	@GetMapping("/registration")
	public String GetRegistration() {
		return "registration";
	}
	
	@GetMapping("/error")
	public String GetError() {
		return "error";
	}
	
    
}