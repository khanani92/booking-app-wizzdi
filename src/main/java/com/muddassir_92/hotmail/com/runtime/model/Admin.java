package com.muddassir_92.hotmail.com.runtime.model;

import com.flexicore.model.SecuredBasic;
import com.wizzdi.flexicore.file.model.FileResource;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Admin extends SecuredBasic {

  private String password;

  private String email;

  private boolean block;

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
   * @return profilePicture
   */
  @ManyToOne(targetEntity = FileResource.class)
  public FileResource getProfilePicture() {
    return this.profilePicture;
  }

  /**
   * @param profilePicture profilePicture to set
   * @return Admin
   */
  public <T extends Admin> T setProfilePicture(FileResource profilePicture) {
    this.profilePicture = profilePicture;
    return (T) this;
  }
}
