package com.muddassir_92.hotmail.com.runtime.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.muddassir_92.hotmail.com.runtime.model.ServiceToServiceProvider;
import com.wizzdi.flexicore.file.model.FileResource;
import com.wizzdi.flexicore.security.request.BasicPropertiesFilter;
import com.wizzdi.flexicore.security.request.PaginationFilter;
import com.wizzdi.flexicore.security.validation.IdValid;
import java.util.List;
import java.util.Set;

/** Object Used to List Service */
@com.wizzdi.flexicore.security.validation.IdValid.List({
  @IdValid(
      targetField = "profilePictures",
      field = "profilePictureIds",
      fieldType = com.wizzdi.flexicore.file.model.FileResource.class),
  @IdValid(
      targetField = "serviceServiceToServiceProviderses",
      field = "serviceServiceToServiceProvidersIds",
      fieldType = com.muddassir_92.hotmail.com.runtime.model.ServiceToServiceProvider.class)
})
public class ServiceFilter extends PaginationFilter {

  private BasicPropertiesFilter basicPropertiesFilter;

  private Set<Boolean> block;

  private Float priceEnd;

  private Float priceStart;

  private Set<String> profilePictureIds;

  @JsonIgnore private List<FileResource> profilePictures;

  private Set<String> serviceServiceToServiceProvidersIds;

  @JsonIgnore private List<ServiceToServiceProvider> serviceServiceToServiceProviderses;

  /**
   * @return basicPropertiesFilter
   */
  public BasicPropertiesFilter getBasicPropertiesFilter() {
    return this.basicPropertiesFilter;
  }

  /**
   * @param basicPropertiesFilter basicPropertiesFilter to set
   * @return ServiceFilter
   */
  public <T extends ServiceFilter> T setBasicPropertiesFilter(
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
   * @return ServiceFilter
   */
  public <T extends ServiceFilter> T setBlock(Set<Boolean> block) {
    this.block = block;
    return (T) this;
  }

  /**
   * @return priceEnd
   */
  public Float getPriceEnd() {
    return this.priceEnd;
  }

  /**
   * @param priceEnd priceEnd to set
   * @return ServiceFilter
   */
  public <T extends ServiceFilter> T setPriceEnd(Float priceEnd) {
    this.priceEnd = priceEnd;
    return (T) this;
  }

  /**
   * @return priceStart
   */
  public Float getPriceStart() {
    return this.priceStart;
  }

  /**
   * @param priceStart priceStart to set
   * @return ServiceFilter
   */
  public <T extends ServiceFilter> T setPriceStart(Float priceStart) {
    this.priceStart = priceStart;
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
   * @return ServiceFilter
   */
  public <T extends ServiceFilter> T setProfilePictureIds(Set<String> profilePictureIds) {
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
   * @return ServiceFilter
   */
  public <T extends ServiceFilter> T setProfilePictures(List<FileResource> profilePictures) {
    this.profilePictures = profilePictures;
    return (T) this;
  }

  /**
   * @return serviceServiceToServiceProvidersIds
   */
  public Set<String> getServiceServiceToServiceProvidersIds() {
    return this.serviceServiceToServiceProvidersIds;
  }

  /**
   * @param serviceServiceToServiceProvidersIds serviceServiceToServiceProvidersIds to set
   * @return ServiceFilter
   */
  public <T extends ServiceFilter> T setServiceServiceToServiceProvidersIds(
      Set<String> serviceServiceToServiceProvidersIds) {
    this.serviceServiceToServiceProvidersIds = serviceServiceToServiceProvidersIds;
    return (T) this;
  }

  /**
   * @return serviceServiceToServiceProviderses
   */
  @JsonIgnore
  public List<ServiceToServiceProvider> getServiceServiceToServiceProviderses() {
    return this.serviceServiceToServiceProviderses;
  }

  /**
   * @param serviceServiceToServiceProviderses serviceServiceToServiceProviderses to set
   * @return ServiceFilter
   */
  public <T extends ServiceFilter> T setServiceServiceToServiceProviderses(
      List<ServiceToServiceProvider> serviceServiceToServiceProviderses) {
    this.serviceServiceToServiceProviderses = serviceServiceToServiceProviderses;
    return (T) this;
  }
}
