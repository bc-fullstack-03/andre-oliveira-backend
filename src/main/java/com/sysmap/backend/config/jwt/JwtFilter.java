package com.sysmap.backend.config.jwt;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sysmap.backend.model.LoginRequest;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter extends OncePerRequestFilter {

  private AuthenticationManager authenticationManager;

  public JwtFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String token = request.getHeader("Authorization");

    if (request.getServletPath().contains("/api/v1/user/login")) {
      try {
        LoginRequest loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
        Authentication auth = authenticationManager
            .authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getSenha()));
        var user = (User) auth.getPrincipal();
        String jwtToken = JWT.create().withSubject(user.getUsername()).withIssuedAt(Instant.now())
            .withExpiresAt(Instant.now().plus(30, ChronoUnit.MINUTES))
            .withIssuer(request.getRequestURI()).sign(Algorithm.HMAC512("a73b5898-834f-4b95-88b0-0cc1e0ded7fc"));
        response.setHeader("Authorization", "Bearer " + jwtToken);
        filterChain.doFilter(request, response);
        return;
      } catch (AuthenticationCredentialsNotFoundException e) {
        throw new AuthenticationCredentialsNotFoundException(e.getMessage());
      }
    }
    if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
      return;
    }
    token = token.split(" ")[1];
    try {
      JWTVerifier verifier = JWT.require(Algorithm.HMAC512("a73b5898-834f-4b95-88b0-0cc1e0ded7fc")).build();
      DecodedJWT decoded = verifier.verify(token);
      String username = decoded.getSubject();
      var authToken = new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
      authToken.setDetails(new WebAuthenticationDetails(request));
      SecurityContextHolder.getContext().setAuthentication(authToken);
      filterChain.doFilter(request, response);
    } catch (JWTVerificationException e) {
      throw new JWTVerificationException(e.getMessage());
    }
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    return request.getServletPath().contains("/api/v1/user/create") || request.getServletPath().contains("swagger")
        || request.getServletPath().contains("docs");
  }
}
