package com.tigo.ahp.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthenticationRequest {
  
  @NotEmpty
  @Email
  @NotNull
  private String email;

  @NotEmpty
  @NotNull
  private String password;
}
