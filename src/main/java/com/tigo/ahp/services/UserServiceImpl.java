package com.tigo.ahp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigo.ahp.dtos.SignupRequest;
import com.tigo.ahp.dtos.UserDTO;
import com.tigo.ahp.dtos.UserUpdateRequest;
import com.tigo.ahp.models.User;
import com.tigo.ahp.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository; 

    @Override
    public UserDTO updateUser(Long userId, UserUpdateRequest userUpdateRequest) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (userUpdateRequest.getName() != null) {
                user.setName(userUpdateRequest.getName());
            }
            if (userUpdateRequest.getAdress() != null) {
              user.setAdress(userUpdateRequest.getAdress());
            }
            if (userUpdateRequest.getBirthDate() != null) {
              user.setBirthDate(userUpdateRequest.getBirthDate());
            }
            if (userUpdateRequest.getEmail() != null) {
              user.setEmail(userUpdateRequest.getEmail());
            }
            if (userUpdateRequest.getLastName() != null) {
              user.setLastName(userUpdateRequest.getLastName());
            }

            userRepository.save(user); 
            return mapToUserDTO(user); 
        } else {
            throw new RuntimeException("User not found"); 
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