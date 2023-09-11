package com.muddassir_92.hotmail.com.runtime.request;

import com.wizzdi.flexicore.security.request.BasicCreate;

/** Object Used to Create Admin */
public class AdminCreate extends BasicCreate {

  private Boolean block;

  private String email;

  private String gender;

  private String password;

  /**
   * @return block
   */
  public Boolean getBlock() {
    return this.block;
  }

  /**
   * @param block block to set
   * @return AdminCreate
   */
  public <T extends AdminCreate> T setBlock(Boolean block) {
    this.block = block;
    return (T) this;
  }

  /**
   * @return email
   */
  public String getEmail() {
    return this.email;
  }

  /**
   * @param email email to set
   * @return AdminCreate
   */
  public <T extends AdminCreate> T setEmail(String email) {
    this.email = email;
    return (T) this;
  }

  /**
   * @return gender
   */
  public String getGender() {
    return this.gender;
  }

  /**
   * @param gender gender to set
   * @return AdminCreate
   */
  public <T extends AdminCreate> T setGender(String gender) {
    this.gender = gender;
    return (T) this;
  }

  /**
   * @return password
   */
  public String getPassword() {
    return this.password;
  }

  /**
   * @param password password to set
   * @return AdminCreate
   */
  public <T extends AdminCreate> T setPassword(String password) {
    this.password = password;
    return (T) this;
  }
}
