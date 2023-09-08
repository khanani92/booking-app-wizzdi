package com.muddassir_92.hotmail.com.runtime.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flexicore.model.SecurityUser;
import jakarta.persistence.Entity;

@Entity
public class AppUser extends SecurityUser {

  @JsonIgnore private String password;

  private String username;

  /**
   * @return password
   */
  @JsonIgnore
  public String getPassword() {
    return this.password;
  }

  /**
   * @param password password to set
   * @return AppUser
   */
  public <T extends AppUser> T setPassword(String password) {
    this.password = password;
    return (T) this;
  }

  /**
   * @return username
   */
  public String getUsername() {
    return this.username;
  }

  /**
   * @param username username to set
   * @return AppUser
   */
  public <T extends AppUser> T setUsername(String username) {
    this.username = username;
    return (T) this;
  }
}
