package com.tigo.ahp.services;

import com.tigo.ahp.dtos.SignupRequest;
import com.tigo.ahp.dtos.UserDTO;
import com.tigo.ahp.dtos.UserUpdateRequest;

public interface UserService {
  UserDTO updateUser(Long userId, UserUpdateRequest userUpdateRequest);
}
