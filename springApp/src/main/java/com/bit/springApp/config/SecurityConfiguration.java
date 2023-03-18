package com.bit.springApp.config;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bit.springApp.security.config.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration { //HTTP istekleri ve roller için configurasyon yapıldı

  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
    	//CSRF(Cross-Site Request Forgery)-CORS(Cross-Origin Resource Sharing)
        .csrf().disable()
        .cors().disable() 
        
        //Tüm rollerin erişebileceği ana sayfalar
        .authorizeHttpRequests()
        //pages
    	.requestMatchers("/index").permitAll()
    	.requestMatchers("/terminalPage").permitAll()
    	.requestMatchers("/usage").permitAll()
    	.requestMatchers("/problemRecord").permitAll()
    	.requestMatchers("/registration").permitAll()
    	.requestMatchers("/error").permitAll()

    	.requestMatchers("/register").permitAll()
    	.requestMatchers("/authenticate").permitAll()
    	.requestMatchers("/api/users/getAllUsers").permitAll()
    	.requestMatchers("/api/users/getAllUserDto").permitAll()
    	.requestMatchers("/faults-locations").permitAll()
    	.requestMatchers("/save").permitAll()
    	.requestMatchers("/logout").permitAll()
    	.requestMatchers("/page/**").permitAll()
    	.requestMatchers("/api/**").permitAll()
        .requestMatchers("/softDeleteCarDefectLocation/**").permitAll()
        .requestMatchers("/api/v1/demo-controller").permitAll()





//    	.requestMatchers("/problemRecord").permitAll()
    	.and()
    	
    	//Rollere özel sayfalar
    	.authorizeHttpRequests()
    	.requestMatchers("/api/auth").hasRole("ADMIN")
    	.requestMatchers("/problemRecord").hasRole("OPERATOR")
    	.and()

    	
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        .and()
        
        .authenticationProvider(authenticationProvider).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
  
 
}