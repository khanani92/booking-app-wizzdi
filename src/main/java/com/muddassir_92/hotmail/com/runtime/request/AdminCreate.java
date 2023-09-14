package com.muddassir_92.hotmail.com.runtime.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wizzdi.flexicore.file.model.FileResource;
import com.wizzdi.flexicore.security.request.BasicCreate;
import com.wizzdi.flexicore.security.validation.IdValid;

/** Object Used to Create Admin */
@com.wizzdi.flexicore.security.validation.IdValid.List({
  @IdValid(
      targetField = "profilePicture",
      field = "profilePictureId",
      fieldType = com.wizzdi.flexicore.file.model.FileResource.class,
      groups = {
        com.wizzdi.flexicore.security.validation.Update.class,
        com.wizzdi.flexicore.security.validation.Create.class
      })
})
public class AdminCreate extends BasicCreate {

  private Boolean block;

  private String email;

  private String password;

  @JsonIgnore private FileResource profilePicture;

  private String profilePictureId;

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

  /**
   * @return profilePicture
   */
  @JsonIgnore
  public FileResource getProfilePicture() {
    return this.profilePicture;
  }

  /**
   * @param profilePicture profilePicture to set
   * @return AdminCreate
   */
  public <T extends AdminCreate> T setProfilePicture(FileResource profilePicture) {
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
   * @return AdminCreate
   */
  public <T extends AdminCreate> T setProfilePictureId(String profilePictureId) {
    this.profilePictureId = profilePictureId;
    return (T) this;
  }
}
