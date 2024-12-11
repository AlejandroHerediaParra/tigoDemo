package com.tigo.ahp.dtos;

import lombok.Data;

@Data
public class UserWithJwtDTO {
  private UserDTO user;
  private String jwt;

  public UserWithJwtDTO(UserDTO user, String jwt) {
      this.user = user;
      this.jwt = jwt;
  }

  // Getters and Setters
}