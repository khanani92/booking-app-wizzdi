package com.muddassir_92.hotmail.com.runtime.request;

import com.wizzdi.flexicore.security.request.SecurityUserCreate;

/** Object Used to Create AppUser */
public class AppUserCreate extends SecurityUserCreate {

  private String password;

  private String username;

  /**
   * @return password
   */
  public String getPassword() {
    return this.password;
  }

  /**
   * @param password password to set
   * @return AppUserCreate
   */
  public <T extends AppUserCreate> T setPassword(String password) {
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
   * @return AppUserCreate
   */
  public <T extends AppUserCreate> T setUsername(String username) {
    this.username = username;
    return (T) this;
  }
}
