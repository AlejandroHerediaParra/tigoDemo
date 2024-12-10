package com.tigo.ahp.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDTO {

  @NotEmpty
  @NotNull
  private Long id;
  @NotEmpty
  @NotNull
  private String name;
  @NotNull
  @NotEmpty
  private String lastName;
  @NotNull
  @NotEmpty
  private String adress;
  @NotEmpty
  @NotNull
  @Email
  private String email;
  @NotNull
  @NotEmpty
  private String birthDate;
  @NotNull
  @NotEmpty
  private String password;
}