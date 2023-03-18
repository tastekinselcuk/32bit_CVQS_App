package com.bit.springApp.security.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bit.springApp.security.config.JwtService;
import com.bit.springApp.domain.users.User;
import com.bit.springApp.repository.UserRepository;
/**
 * Authentication Service
 * 
 * procedures for registration and login are written here
 *
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  
  /**
   * Register service, if applicable save user and return token.
   *
   * @param RegisterRequest request - contains name, surname, email and password.
   * @return JWT token
   */
  public AuthenticationResponse register(RegisterRequest request) {
	log.info("registration started");
	
    if(repository.existsByEmailAndDeletedFalse(request.getEmail())) {
        throw new RuntimeException("Email already exists");
    }
    if (request.getPassword().length() < 8 || !request.getPassword().matches(".*[a-z].*") || !request.getPassword().matches(".*[A-Z].*")) {
        throw new RuntimeException("Password should contain at least one lowercase letter, one uppercase letter, and be at least 8 characters long!");
    }
    try {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(request.getRoles())
                .deleted(false)
                .build();
            repository.save(user);
            var jwtToken = jwtService.generateToken(user);
        	log.info("registration completed for {}", user.getFirstname());
            return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    } catch (Exception e) {
        throw new RuntimeException("Error saving user", e);
	}

  }

  /**
   * Login service, if applicable give permission for login and return token.
   *
   * @param AuthenticationRequest request - contains email and password.
   * @return JWT token
   */
  public AuthenticationResponse authenticate(AuthenticationRequest request) {
	log.info("authentication started");
	try {
	    authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(
	                request.getEmail(),
	                request.getPassword()
	            )
	        );
	        var user = repository.findByEmail(request.getEmail())
	            .orElseThrow();
	        var jwtToken = jwtService.generateToken(user);
	    	log.info("authentication completed for {}", user.getFirstname());
	        return AuthenticationResponse.builder()
	            .token(jwtToken)
	            .build();
	} catch (Exception e) {
        throw new RuntimeException("Invalid email or password", e);
	}
  }
  
  
}
