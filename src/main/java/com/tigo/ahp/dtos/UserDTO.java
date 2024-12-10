package com.tigo.ahp.dtos;

import lombok.Data;

@Data
public class UserDTO {

  private Long id;
  private String name;
  private String lastName;
  private String adress;
  private String email;
  private String birthDate;
  private String password;
}