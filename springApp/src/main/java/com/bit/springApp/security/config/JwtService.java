package com.bit.springApp.security.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
/**
 * JWT Service
 * 
 * The methods to be used for JWT token are created in this class
 *
 */
@Log4j2
@Service
public class JwtService { 

  private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

  public String extractUsername(String token) {
	    log.info("extractUsername method worked");
    return extractClaim(token, Claims::getSubject);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	    log.info("extractClaim method worked");
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }
  
  public String generateToken(UserDetails userDetails) {
	    log.info("generateToken(UserDetails userDetails) method worked");
    return generateToken(new HashMap<>(), userDetails);
  }

  public String generateToken(
      Map<String, Object> extraClaims,
      UserDetails userDetails
  ) {
	    log.info("generateToken(Map<String, Object> extraClaims, UserDetails userDetails) method worked");
    return Jwts
        .builder()
        .setClaims(extraClaims)
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
        .signWith(getSignInKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
	    log.info("isTokenValid method worked");
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
	    log.info("isTokenExpired method worked");
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
	    log.info("extractExpiration method worked");
    return extractClaim(token, Claims::getExpiration);
  }

  private Claims extractAllClaims(String token) {
	    log.info("extractAllClaims method worked");
    return Jwts
        .parserBuilder()
        .setSigningKey(getSignInKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  private Key getSignInKey() { //JWT oluştururken hash'lediğimiz kullanıcı şifresini geri çözme işlemini yaptık
	    log.info("getSignInKey - password decode");
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}