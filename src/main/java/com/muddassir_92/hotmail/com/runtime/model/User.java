package com.muddassir_92.hotmail.com.runtime.model;

import com.flexicore.model.SecuredBasic;
import com.wizzdi.flexicore.file.model.FileResource;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class User extends SecuredBasic {

  private String password;

  private String email;

  private boolean block;

  private String gender;

  @ManyToOne(targetEntity = FileResource.class)
  private FileResource profilePicture;

  /**
   * @return password
   */
  public String getPassword() {
    return this.password;
  }

  /**
   * @param password password to set
   * @return User
   */
  public <T extends User> T setPassword(String password) {
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
   * @return User
   */
  public <T extends User> T setEmail(String email) {
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
   * @return User
   */
  public <T extends User> T setBlock(boolean block) {
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
   * @return User
   */
  public <T extends User> T setGender(String gender) {
    this.gender = gender;
    return (T) this;
  }

  /**
   * @return profilePicture
   */
  @ManyToOne(targetEntity = FileResource.class)
  public FileResource getProfilePicture() {
    return this.profilePicture;
  }

  /**
   * @param profilePicture profilePicture to set
   * @return User
   */
  public <T extends User> T setProfilePicture(FileResource profilePicture) {
    this.profilePicture = profilePicture;
    return (T) this;
  }
}
