package com.bit.springApp.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bit.springApp.config.JwtService;
import com.bit.springApp.domain.users.User;
import com.bit.springApp.enums.Role;
import com.bit.springApp.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService { //AuthenticationService'de yazdığımız kimlik doğrulama sonucunda oluşturulacak olan AuthenticationResponse buradaki methodlarda oluşturulur.
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) { //Yeni kayıt olan kullanıcı için token bu methodda oluşturulur.
    var user = User.builder()
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.MANAGER)
        .build();
    repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) { //kayıtlı kullanıcının doğrulanması ve sonucunda token oluşturulması bu methodda gerçekleştirilir.
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    var user = repository.findByEmail(request.getEmail())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }
}
