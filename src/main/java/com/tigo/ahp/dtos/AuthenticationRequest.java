package com.tigo.ahp.dtos;

import lombok.Data;

@Data
public class AuthenticationRequest {
  
  private String email;
  private String password;
}
