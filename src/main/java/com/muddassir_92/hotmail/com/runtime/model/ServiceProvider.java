package com.muddassir_92.hotmail.com.runtime.model;

import com.flexicore.model.SecuredBasic;
import com.wizzdi.flexicore.file.model.FileResource;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class ServiceProvider extends SecuredBasic {

  private String password;

  private String email;

  private boolean block;

  private String gender;

  @ManyToOne(targetEntity = Service.class)
  private Service service;

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
   * @return ServiceProvider
   */
  public <T extends ServiceProvider> T setPassword(String password) {
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
   * @return ServiceProvider
   */
  public <T extends ServiceProvider> T setEmail(String email) {
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
   * @return ServiceProvider
   */
  public <T extends ServiceProvider> T setBlock(boolean block) {
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
   * @return ServiceProvider
   */
  public <T extends ServiceProvider> T setGender(String gender) {
    this.gender = gender;
    return (T) this;
  }

  /**
   * @return service
   */
  @ManyToOne(targetEntity = Service.class)
  public Service getService() {
    return this.service;
  }

  /**
   * @param service service to set
   * @return ServiceProvider
   */
  public <T extends ServiceProvider> T setService(Service service) {
    this.service = service;
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
   * @return ServiceProvider
   */
  public <T extends ServiceProvider> T setProfilePicture(FileResource profilePicture) {
    this.profilePicture = profilePicture;
    return (T) this;
  }
}
