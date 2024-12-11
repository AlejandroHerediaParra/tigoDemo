package com.tigo.ahp.services;

import com.tigo.ahp.dtos.SignupRequest;
import com.tigo.ahp.dtos.UserDTO;

public interface AuthService {

  UserDTO createdUser(SignupRequest signupRequest);

  String generateJwtForUser(String email);
 
  UserDTO getUserByEmail(String email); 
}