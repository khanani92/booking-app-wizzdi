package com.muddassir_92.hotmail.com.runtime.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wizzdi.flexicore.file.model.FileResource;
import com.wizzdi.flexicore.security.request.BasicPropertiesFilter;
import com.wizzdi.flexicore.security.request.PaginationFilter;
import com.wizzdi.flexicore.security.validation.IdValid;
import java.util.List;
import java.util.Set;

/** Object Used to List Admin */
@com.wizzdi.flexicore.security.validation.IdValid.List({
  @IdValid(
      targetField = "profilePictures",
      field = "profilePictureIds",
      fieldType = com.wizzdi.flexicore.file.model.FileResource.class)
})
public class AdminFilter extends PaginationFilter {

  private BasicPropertiesFilter basicPropertiesFilter;

  private Set<Boolean> block;

  private Set<String> email;

  private Set<String> profilePictureIds;

  @JsonIgnore private List<FileResource> profilePictures;

  /**
   * @return basicPropertiesFilter
   */
  public BasicPropertiesFilter getBasicPropertiesFilter() {
    return this.basicPropertiesFilter;
  }

  /**
   * @param basicPropertiesFilter basicPropertiesFilter to set
   * @return AdminFilter
   */
  public <T extends AdminFilter> T setBasicPropertiesFilter(
      BasicPropertiesFilter basicPropertiesFilter) {
    this.basicPropertiesFilter = basicPropertiesFilter;
    return (T) this;
  }

  /**
   * @return block
   */
  public Set<Boolean> getBlock() {
    return this.block;
  }

  /**
   * @param block block to set
   * @return AdminFilter
   */
  public <T extends AdminFilter> T setBlock(Set<Boolean> block) {
    this.block = block;
    return (T) this;
  }

  /**
   * @return email
   */
  public Set<String> getEmail() {
    return this.email;
  }

  /**
   * @param email email to set
   * @return AdminFilter
   */
  public <T extends AdminFilter> T setEmail(Set<String> email) {
    this.email = email;
    return (T) this;
  }

  /**
   * @return profilePictureIds
   */
  public Set<String> getProfilePictureIds() {
    return this.profilePictureIds;
  }

  /**
   * @param profilePictureIds profilePictureIds to set
   * @return AdminFilter
   */
  public <T extends AdminFilter> T setProfilePictureIds(Set<String> profilePictureIds) {
    this.profilePictureIds = profilePictureIds;
    return (T) this;
  }

  /**
   * @return profilePictures
   */
  @JsonIgnore
  public List<FileResource> getProfilePictures() {
    return this.profilePictures;
  }

  /**
   * @param profilePictures profilePictures to set
   * @return AdminFilter
   */
  public <T extends AdminFilter> T setProfilePictures(List<FileResource> profilePictures) {
    this.profilePictures = profilePictures;
    return (T) this;
  }
}
