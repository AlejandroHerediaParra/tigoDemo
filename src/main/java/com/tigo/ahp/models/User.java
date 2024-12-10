package com.tigo.ahp.models;

import org.aspectj.lang.annotation.RequiredTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;
  @Column(name = "lastName", nullable = false)
  private String lastName;
  @Column(name = "adress", nullable = false)
  private String adress;
  @Column(name = "email", nullable = false)
  private String email;
  @Column(name = "birthDate", nullable = false)
  private String birthDate;
  @Column(name = "password", nullable = false)
  private String password;
}
