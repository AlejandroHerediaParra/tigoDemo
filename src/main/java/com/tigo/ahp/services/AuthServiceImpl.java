package com.tigo.ahp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.tigo.ahp.dtos.SignupRequest;
import com.tigo.ahp.dtos.UserDTO;
import com.tigo.ahp.models.User;
import com.tigo.ahp.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {
  
  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDTO createdUser(SignupRequest signupRequest) {
    User user = new User();
    user.setEmail(signupRequest.getEmail());
    user.setAdress(signupRequest.getAdress());
    user.setBirthDate(signupRequest.getBirthDate());
    user.setLastName(signupRequest.getLastName());
    user.setName(signupRequest.getName());
    user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));

    User createdUser = userRepository.save(user);
    UserDTO userDTO = new UserDTO();
    userDTO.setEmail(createdUser.getEmail());
    userDTO.setAdress(createdUser.getAdress());
    userDTO.setBirthDate(createdUser.getBirthDate());
    userDTO.setLastName(createdUser.getLastName());
    userDTO.setName(createdUser.getName());
    userDTO.setPassword(createdUser.getPassword());
    userDTO.setId(createdUser.getId());
    return userDTO;
  }

}
