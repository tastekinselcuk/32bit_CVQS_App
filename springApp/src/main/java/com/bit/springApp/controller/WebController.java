package com.bit.springApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
	


	//Page Links

	@GetMapping("/index")
	public String GetForm() {
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
	
    
}