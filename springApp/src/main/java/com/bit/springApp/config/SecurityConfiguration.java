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
    	.requestMatchers("/index").permitAll()
    	.requestMatchers("/terminalPage").permitAll()
    	.requestMatchers("/usage").permitAll()
    	.requestMatchers("/register").permitAll()
    	.requestMatchers("/authenticate").permitAll()
    	.requestMatchers("/register").permitAll()

//    	.requestMatchers("/problemRecord").permitAll()
    	.and()
    	
    	//Rollere özel sayfalar
    	.authorizeHttpRequests()
    	.requestMatchers("/problemRecord").hasRole("OPERATOR")
//    	.requestMatchers("/api/v1/auth/**").hasRole("TEAMLEAD")
//    	.requestMatchers("/api/v1/auth/**").hasRole("MANAGER")
    	.and()

    	
    	.formLogin().loginPage("/login").permitAll()
        .defaultSuccessUrl("/index.html", true) //giriş yapıldıktan sonra iletilecek sayfa
    	.and()
    	
        .logout().logoutUrl("/logout").deleteCookies("JSESSIONID").logoutSuccessUrl("/admin/login.html")
		.and()
		
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        .and()
        
        .authenticationProvider(authenticationProvider).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
}