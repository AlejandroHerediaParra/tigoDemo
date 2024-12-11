package com.tigo.ahp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.tigo.ahp.dtos.SignupRequest;
import com.tigo.ahp.dtos.UserDTO;
import com.tigo.ahp.models.User;
import com.tigo.ahp.repositories.UserRepository;
import com.tigo.ahp.services.jwt.UserDetailServiceImpl;
import com.tigo.ahp.utils.JwtUtil;

import jakarta.validation.Valid;

@Service
public class AuthServiceImpl implements AuthService {
  
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserDetailServiceImpl userDetailService;

  @Autowired
  private JwtUtil jwtUtil;

  @Override
  public UserDTO createdUser(@Valid @RequestBody SignupRequest signupRequest) {
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

  public String generateJwtForUser(String email) {
      UserDetails userDetails = userDetailService.loadUserByUsername(email);

      String jwt = jwtUtil.generateToken(userDetails.getUsername());

      return jwt;
  }

  @Override
    public UserDTO getUserByEmail(String email) {

        User user = userRepository.findFirstByEmail(email); 
        if (user != null) {
            return mapToUserDTO(user); 
        } else {
            return null; 
        }
    }

    private UserDTO mapToUserDTO(User user) {
      UserDTO userDTO = new UserDTO();
      userDTO.setEmail(user.getEmail());
      userDTO.setAdress(user.getAdress());
      userDTO.setBirthDate(user.getBirthDate());
      userDTO.setLastName(user.getLastName());
      userDTO.setName(user.getName());
      userDTO.setPassword(user.getPassword());
      userDTO.setId(user.getId());
      return userDTO;
    }
}
