package com.bit.springApp.security.auth;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

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
	  @RequestMapping(value = "/authenticate", method = RequestMethod.POST) //Parametre olarak şifre ve email gönderilip token döndürülüyor
	  public String authenticate(@ModelAttribute AuthenticationRequest request) {
		  service.authenticate(request);
	      return "redirect:/index";
	  }
	  
	  @GetMapping("/logout")
	  public String logout(HttpSession session) { // Kullanıcının oturumunu sonlandırma
	      session.invalidate();
	      return "redirect:/registration";
	  }

}