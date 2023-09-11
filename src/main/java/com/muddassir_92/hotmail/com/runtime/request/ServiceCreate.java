package com.muddassir_92.hotmail.com.runtime.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wizzdi.flexicore.file.model.FileResource;
import com.wizzdi.flexicore.security.request.BasicCreate;
import com.wizzdi.flexicore.security.validation.IdValid;

/** Object Used to Create Service */
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
public class ServiceCreate extends BasicCreate {

  private Float price;

  @JsonIgnore private FileResource profilePicture;

  private String profilePictureId;

  /**
   * @return price
   */
  public Float getPrice() {
    return this.price;
  }

  /**
   * @param price price to set
   * @return ServiceCreate
   */
  public <T extends ServiceCreate> T setPrice(Float price) {
    this.price = price;
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
   * @return ServiceCreate
   */
  public <T extends ServiceCreate> T setProfilePicture(FileResource profilePicture) {
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
   * @return ServiceCreate
   */
  public <T extends ServiceCreate> T setProfilePictureId(String profilePictureId) {
    this.profilePictureId = profilePictureId;
    return (T) this;
  }
}
