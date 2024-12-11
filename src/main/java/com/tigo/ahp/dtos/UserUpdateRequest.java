package com.tigo.ahp.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserUpdateRequest {
  
  @NotEmpty
  @NotNull
  private String name;
  @NotEmpty
  @NotNull
  private String lastName;
  @NotEmpty
  @NotNull
  private String adress;
  @NotEmpty
  @Email
  @NotNull
  private String email;
  @NotEmpty
  @NotNull
  private String birthDate;
  @NotEmpty
  @NotNull
  private String password;
}
