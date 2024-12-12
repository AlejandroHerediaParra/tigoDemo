package com.tigo.ahp.services;

import com.tigo.ahp.dtos.SignupRequest;
import com.tigo.ahp.dtos.UserDTO;
import com.tigo.ahp.dtos.UserUpdateRequest;
import com.tigo.ahp.models.User;

public interface UserService {
  UserDTO updateUser(Long userId, UserUpdateRequest userUpdateRequest);

  User getUserByEmail(String userEmail);
}
