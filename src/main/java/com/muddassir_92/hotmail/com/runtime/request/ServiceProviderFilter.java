package com.muddassir_92.hotmail.com.runtime.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.muddassir_92.hotmail.com.runtime.model.Service;
import com.wizzdi.flexicore.file.model.FileResource;
import com.wizzdi.flexicore.security.request.BasicPropertiesFilter;
import com.wizzdi.flexicore.security.request.PaginationFilter;
import com.wizzdi.flexicore.security.validation.IdValid;
import java.util.List;
import java.util.Set;

/** Object Used to List ServiceProvider */
@com.wizzdi.flexicore.security.validation.IdValid.List({
  @IdValid(
      targetField = "services",
      field = "serviceIds",
      fieldType = com.muddassir_92.hotmail.com.runtime.model.Service.class),
  @IdValid(
      targetField = "profilePictures",
      field = "profilePictureIds",
      fieldType = com.wizzdi.flexicore.file.model.FileResource.class)
})
public class ServiceProviderFilter extends PaginationFilter {

  private BasicPropertiesFilter basicPropertiesFilter;

  private Set<Boolean> block;

  private Set<String> email;

  private Set<String> gender;

  private Set<String> profilePictureIds;

  @JsonIgnore private List<FileResource> profilePictures;

  private Set<String> serviceIds;

  @JsonIgnore private List<Service> services;

  /**
   * @return basicPropertiesFilter
   */
  public BasicPropertiesFilter getBasicPropertiesFilter() {
    return this.basicPropertiesFilter;
  }

  /**
   * @param basicPropertiesFilter basicPropertiesFilter to set
   * @return ServiceProviderFilter
   */
  public <T extends ServiceProviderFilter> T setBasicPropertiesFilter(
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
   * @return ServiceProviderFilter
   */
  public <T extends ServiceProviderFilter> T setBlock(Set<Boolean> block) {
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
   * @return ServiceProviderFilter
   */
  public <T extends ServiceProviderFilter> T setEmail(Set<String> email) {
    this.email = email;
    return (T) this;
  }

  /**
   * @return gender
   */
  public Set<String> getGender() {
    return this.gender;
  }

  /**
   * @param gender gender to set
   * @return ServiceProviderFilter
   */
  public <T extends ServiceProviderFilter> T setGender(Set<String> gender) {
    this.gender = gender;
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
   * @return ServiceProviderFilter
   */
  public <T extends ServiceProviderFilter> T setProfilePictureIds(Set<String> profilePictureIds) {
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
   * @return ServiceProviderFilter
   */
  public <T extends ServiceProviderFilter> T setProfilePictures(
      List<FileResource> profilePictures) {
    this.profilePictures = profilePictures;
    return (T) this;
  }

  /**
   * @return serviceIds
   */
  public Set<String> getServiceIds() {
    return this.serviceIds;
  }

  /**
   * @param serviceIds serviceIds to set
   * @return ServiceProviderFilter
   */
  public <T extends ServiceProviderFilter> T setServiceIds(Set<String> serviceIds) {
    this.serviceIds = serviceIds;
    return (T) this;
  }

  /**
   * @return services
   */
  @JsonIgnore
  public List<Service> getServices() {
    return this.services;
  }

  /**
   * @param services services to set
   * @return ServiceProviderFilter
   */
  public <T extends ServiceProviderFilter> T setServices(List<Service> services) {
    this.services = services;
    return (T) this;
  }
}
