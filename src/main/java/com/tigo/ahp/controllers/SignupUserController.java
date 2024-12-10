package com.tigo.ahp.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.tigo.ahp.dtos.SignupRequest;
import com.tigo.ahp.dtos.UserDTO;
import com.tigo.ahp.services.AuthService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@Validated
public class SignupUserController {
  @Autowired
  private AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<?> createUser(@Valid @RequestBody SignupRequest signupRequest) {
      UserDTO createdUser = authService.createdUser(signupRequest);
      if (createdUser == null) 
        return new ResponseEntity<>("User is not creted, try again later", HttpStatus.BAD_REQUEST);
      return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
  }
}