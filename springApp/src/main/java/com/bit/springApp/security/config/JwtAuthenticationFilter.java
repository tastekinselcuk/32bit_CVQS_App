package com.bit.springApp.security.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Authentication filter
 * 
 * Each request sent from the client will first refer here
 * and check if the JWT token exists and is still valid.
 *
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
		 throws ServletException, IOException {
    log.info("Authentication filter started filtering..");
    
    final String authHeader = request.getHeader("Authorization");
    final String jwt;
    final String userEmail;
    if (authHeader == null ||!authHeader.startsWith("Bearer ")) { //bearer added for front end
      filterChain.doFilter(request, response);
      log.info("Authentication request header checked, Authentication request header is {}", authHeader);
      return;
    }
    jwt = authHeader.substring(7);
    log.info("Authentication request header checked, Authentication request header is {}", authHeader.substring(7));
    userEmail = jwtService.extractUsername(jwt);
    log.debug("UserEmail was extracted from authorization token: {}", userEmail);
    
    if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
      log.info("Proceeding to next filter on filter chain...");
      if (jwtService.isTokenValid(jwt, userDetails)) {
        log.debug("Authorization token was not validated for {}", userEmail);
        
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            userDetails,
            null,
            userDetails.getAuthorities()
        );
        authToken.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request)
        );
        SecurityContextHolder.getContext().setAuthentication(authToken);
        
        log.debug("Roles are: " + userDetails.getAuthorities());
        log.debug("Authentication is set for userEmail: {}", userEmail);
      }
    }
    log.info("Proceeding to next filter on filter chain...");
    
    filterChain.doFilter(request, response);
  }
}

