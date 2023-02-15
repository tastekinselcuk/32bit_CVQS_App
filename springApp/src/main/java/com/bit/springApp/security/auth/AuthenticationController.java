package com.bit.springApp.security.auth;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Authentication Controller
 * <br><br>
 * Mappings for user registration and login are created here
 *
 */
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

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
	  @RequestMapping(value = "/register", method = RequestMethod.POST)
	  public ResponseEntity<AuthenticationResponse> register(@ModelAttribute RegisterRequest request) {
	    return ResponseEntity.ok(service.register(request));
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
	  @RequestMapping(value = "/authenticate", method = RequestMethod.POST) //Parametre olarak şifre ve email gönderilip token döndürülüyor
	  public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
	    return ResponseEntity.ok(service.authenticate(request));
	  }

}

