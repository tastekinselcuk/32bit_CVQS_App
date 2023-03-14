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
     * <br><br>
     * Request body sample:
     * <pre>
     *     password= Selcuk123 - $2a$10$BF9UFNgyf5hNqGI76XILvOotZ.519SJMmHuxgU7jAZ9Zjk4WBD37G
     *     email= selcuk@gmail.com
     * </pre>
     *
     * @param RegisterRequest request - contains name, surname, email and password.
     * @return JWT token
     */
	  @PostMapping("/register")
	  public String register(@ModelAttribute RegisterRequest request) {
	    service.register(request);
        return "redirect:/registration";
	  }
  
    /**
     * Authenticate user, if correct return jwt token.
     * <br><br>
     * Request body sample:
     * <pre>
     *     password= Selcuk123 - $2a$10$BF9UFNgyf5hNqGI76XILvOotZ.519SJMmHuxgU7jAZ9Zjk4WBD37G
     *     email= selcuk@gmail.com
     * </pre>
     *
     * @param AuthenticationRequest request - contains email and password.
     * @return JWT token
     */
	  @PostMapping("/authenticate")
	  public String authenticate(@ModelAttribute AuthenticationRequest request) {
		  service.authenticate(request);
	      return "redirect:/index";
	  }
	  

}