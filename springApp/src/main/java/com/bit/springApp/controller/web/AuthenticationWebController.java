package com.bit.springApp.controller.web;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bit.springApp.security.auth.AuthenticationRequest;
import com.bit.springApp.security.auth.AuthenticationService;
import com.bit.springApp.security.auth.RegisterRequest;


/**
 * Authentication Controller
 * <br><br>
 * Mappings for user registration and login are created here
 *
 */
@Controller
@RequiredArgsConstructor
public class AuthenticationWebController {

	@Autowired
	private AuthenticationService service;
     
    /**
     * Register user, if applicable save user.
     *
     * @param RegisterRequest request - contains name, surname, email and password.
     * @return registration page
     */
	  @PostMapping("/register")
	  public String register(@ModelAttribute RegisterRequest request) {
		  try {
			    service.register(request);
		        return "redirect:/registration";
		  } catch (Exception e) {
	            return "redirect:/errorPage";
		}

	  }
  
    /**
     * Authenticate user, if correct return jwt token.
     *
     * @param AuthenticationRequest request - contains email and password.
     * @return index page
     */
	  @PostMapping("/authenticate")
	  public String authenticate(@ModelAttribute AuthenticationRequest request) {
		  try {
			  service.authenticate(request);
		      return "redirect:/index"; 
		  } catch (Exception e) {
	            return "redirect:/errorPage";
		}
	  }
	  

}