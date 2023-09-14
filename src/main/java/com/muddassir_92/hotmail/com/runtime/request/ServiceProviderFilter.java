package com.muddassir_92.hotmail.com.runtime.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.muddassir_92.hotmail.com.runtime.model.ServiceToServiceProvider;
import com.wizzdi.flexicore.file.model.FileResource;
import com.wizzdi.flexicore.security.request.BasicPropertiesFilter;
import com.wizzdi.flexicore.security.request.PaginationFilter;
import com.wizzdi.flexicore.security.validation.IdValid;
import java.util.List;
import java.util.Set;

/** Object Used to List ServiceProvider */
@com.wizzdi.flexicore.security.validation.IdValid.List({
  @IdValid(
      targetField = "profilePictures",
      field = "profilePictureIds",
      fieldType = com.wizzdi.flexicore.file.model.FileResource.class),
  @IdValid(
      targetField = "serviceProviderServiceToServiceProviderses",
      field = "serviceProviderServiceToServiceProvidersIds",
      fieldType = com.muddassir_92.hotmail.com.runtime.model.ServiceToServiceProvider.class)
})
public class ServiceProviderFilter extends PaginationFilter {

  private Set<String> address;

  private BasicPropertiesFilter basicPropertiesFilter;

  private Set<Boolean> block;

  private Set<String> email;

  private Set<String> gender;

  private Set<String> phoneNumber;

  private Set<String> profilePictureIds;

  @JsonIgnore private List<FileResource> profilePictures;

  private Set<String> serviceProviderServiceToServiceProvidersIds;

  @JsonIgnore private List<ServiceToServiceProvider> serviceProviderServiceToServiceProviderses;

  /**
   * @return address
   */
  public Set<String> getAddress() {
    return this.address;
  }

  /**
   * @param address address to set
   * @return ServiceProviderFilter
   */
  public <T extends ServiceProviderFilter> T setAddress(Set<String> address) {
    this.address = address;
    return (T) this;
  }

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
   * @return phoneNumber
   */
  public Set<String> getPhoneNumber() {
    return this.phoneNumber;
  }

  /**
   * @param phoneNumber phoneNumber to set
   * @return ServiceProviderFilter
   */
  public <T extends ServiceProviderFilter> T setPhoneNumber(Set<String> phoneNumber) {
    this.phoneNumber = phoneNumber;
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
   * @return serviceProviderServiceToServiceProvidersIds
   */
  public Set<String> getServiceProviderServiceToServiceProvidersIds() {
    return this.serviceProviderServiceToServiceProvidersIds;
  }

  /**
   * @param serviceProviderServiceToServiceProvidersIds serviceProviderServiceToServiceProvidersIds
   *     to set
   * @return ServiceProviderFilter
   */
  public <T extends ServiceProviderFilter> T setServiceProviderServiceToServiceProvidersIds(
      Set<String> serviceProviderServiceToServiceProvidersIds) {
    this.serviceProviderServiceToServiceProvidersIds = serviceProviderServiceToServiceProvidersIds;
    return (T) this;
  }

  /**
   * @return serviceProviderServiceToServiceProviderses
   */
  @JsonIgnore
  public List<ServiceToServiceProvider> getServiceProviderServiceToServiceProviderses() {
    return this.serviceProviderServiceToServiceProviderses;
  }

  /**
   * @param serviceProviderServiceToServiceProviderses serviceProviderServiceToServiceProviderses to
   *     set
   * @return ServiceProviderFilter
   */
  public <T extends ServiceProviderFilter> T setServiceProviderServiceToServiceProviderses(
      List<ServiceToServiceProvider> serviceProviderServiceToServiceProviderses) {
    this.serviceProviderServiceToServiceProviderses = serviceProviderServiceToServiceProviderses;
    return (T) this;
  }
}
