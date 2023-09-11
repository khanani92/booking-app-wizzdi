package com.muddassir_92.hotmail.com.runtime.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.muddassir_92.hotmail.com.runtime.model.Service;
import com.wizzdi.flexicore.file.model.FileResource;
import com.wizzdi.flexicore.security.request.BasicCreate;
import com.wizzdi.flexicore.security.validation.IdValid;

/** Object Used to Create ServiceProvider */
@com.wizzdi.flexicore.security.validation.IdValid.List({
  @IdValid(
      targetField = "service",
      field = "serviceId",
      fieldType = com.muddassir_92.hotmail.com.runtime.model.Service.class,
      groups = {
        com.wizzdi.flexicore.security.validation.Update.class,
        com.wizzdi.flexicore.security.validation.Create.class
      }),
  @IdValid(
      targetField = "profilePicture",
      field = "profilePictureId",
      fieldType = com.wizzdi.flexicore.file.model.FileResource.class,
      groups = {
        com.wizzdi.flexicore.security.validation.Update.class,
        com.wizzdi.flexicore.security.validation.Create.class
      })
})
public class ServiceProviderCreate extends BasicCreate {

  private Boolean block;

  private String email;

  private String gender;

  private String password;

  @JsonIgnore private FileResource profilePicture;

  private String profilePictureId;

  @JsonIgnore private Service service;

  private String serviceId;

  /**
   * @return block
   */
  public Boolean getBlock() {
    return this.block;
  }

  /**
   * @param block block to set
   * @return ServiceProviderCreate
   */
  public <T extends ServiceProviderCreate> T setBlock(Boolean block) {
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
   * @return ServiceProviderCreate
   */
  public <T extends ServiceProviderCreate> T setEmail(String email) {
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
   * @return ServiceProviderCreate
   */
  public <T extends ServiceProviderCreate> T setGender(String gender) {
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
   * @return ServiceProviderCreate
   */
  public <T extends ServiceProviderCreate> T setPassword(String password) {
    this.password = password;
    return (T) this;
  }

  /**
   * @return profilePicture
   */
  @JsonIgnore
  public FileResource getProfilePicture() {
    return this.profilePicture;
  }

  /**
   * @param profilePicture profilePicture to set
   * @return ServiceProviderCreate
   */
  public <T extends ServiceProviderCreate> T setProfilePicture(FileResource profilePicture) {
    this.profilePicture = profilePicture;
    return (T) this;
  }

  /**
   * @return profilePictureId
   */
  public String getProfilePictureId() {
    return this.profilePictureId;
  }

  /**
   * @param profilePictureId profilePictureId to set
   * @return ServiceProviderCreate
   */
  public <T extends ServiceProviderCreate> T setProfilePictureId(String profilePictureId) {
    this.profilePictureId = profilePictureId;
    return (T) this;
  }

  /**
   * @return service
   */
  @JsonIgnore
  public Service getService() {
    return this.service;
  }

  /**
   * @param service service to set
   * @return ServiceProviderCreate
   */
  public <T extends ServiceProviderCreate> T setService(Service service) {
    this.service = service;
    return (T) this;
  }

  /**
   * @return serviceId
   */
  public String getServiceId() {
    return this.serviceId;
  }

  /**
   * @param serviceId serviceId to set
   * @return ServiceProviderCreate
   */
  public <T extends ServiceProviderCreate> T setServiceId(String serviceId) {
    this.serviceId = serviceId;
    return (T) this;
  }
}
