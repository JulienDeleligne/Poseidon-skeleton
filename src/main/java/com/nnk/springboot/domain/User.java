package com.nnk.springboot.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "users")
@AllArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @NotBlank(message = "Username is mandatory")
  private String username;
  @NotBlank(message = "Password is mandatory")
  private String password;
  @NotBlank(message = "FullName is mandatory")
  private String fullName;
  @NotBlank(message = "Role is mandatory")
  private String role;

  public User() {

  }
}
