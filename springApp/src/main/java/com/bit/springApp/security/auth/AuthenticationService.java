package com.bit.springApp.security.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bit.springApp.security.config.JwtService;
import com.bit.springApp.domain.users.User;
import com.bit.springApp.enums.Role;
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
public class AuthenticationService { //AuthenticationService'de yazdığımız kimlik doğrulama sonucunda oluşturulacak olan AuthenticationResponse buradaki methodlarda oluşturulur.
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
    var user = User.builder()
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(request.getRole())
        .build();
    repository.save(user);
    var jwtToken = jwtService.generateToken(user);
	log.info("registration completed for {}", user.getFirstname());
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  /**
   * Login service, if applicable give permission for login and return token.
   *
   * @param AuthenticationRequest request - contains email and password.
   * @return JWT token
   */
  public AuthenticationResponse authenticate(AuthenticationRequest request) {
	log.info("authentication started");
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
  }
  
  
}
