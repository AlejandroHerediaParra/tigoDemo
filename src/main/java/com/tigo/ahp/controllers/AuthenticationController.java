package com.tigo.ahp.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tigo.ahp.dtos.AuthenticationRequest;
import com.tigo.ahp.dtos.AuthenticationResponse;
import com.tigo.ahp.services.jwt.UserDetailServiceImpl;
import com.tigo.ahp.utils.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class AuthenticationController {
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserDetailServiceImpl userDetailService;

  @Autowired
  private JwtUtil jwtUtil;

  @PostMapping("/authentication")
  public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
    } catch (BadCredentialsException e) {
      throw new BadCredentialsException("incorrect Username or password");
    } catch (DisabledException e) {
      response.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not created, register user first");
      return null;
    }
    final UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getEmail());
    final String jwt = jwtUtil.generateToken(userDetails.getUsername());
    return new AuthenticationResponse(jwt);
  }
}