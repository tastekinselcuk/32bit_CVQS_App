package com.bit.springApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Authentication Controller
 * <br><br>
 * Mappings for all pages are defined here.
 *
 */
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
	
	@GetMapping("/login")
	public String Getlogin() {
		return "login";
	}
	
	@GetMapping("/result")
	public String Getresult() {
		return "result";
	}

	@GetMapping("/register")
	public String GetRegister() {
		return "register";
	}
	
	
    
}