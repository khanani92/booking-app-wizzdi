package com.muddassir_92.hotmail.com.runtime.model;

import com.flexicore.model.SecuredBasic;
import jakarta.persistence.Entity;

@Entity
public class Admin extends SecuredBasic {

  private String password;

  private String email;

  private boolean block;

  private String gender;

  /**
   * @return password
   */
  public String getPassword() {
    return this.password;
  }

  /**
   * @param password password to set
   * @return Admin
   */
  public <T extends Admin> T setPassword(String password) {
    this.password = password;
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
   * @return Admin
   */
  public <T extends Admin> T setEmail(String email) {
    this.email = email;
    return (T) this;
  }

  /**
   * @return block
   */
  public boolean isBlock() {
    return this.block;
  }

  /**
   * @param block block to set
   * @return Admin
   */
  public <T extends Admin> T setBlock(boolean block) {
    this.block = block;
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
   * @return Admin
   */
  public <T extends Admin> T setGender(String gender) {
    this.gender = gender;
    return (T) this;
  }
}
