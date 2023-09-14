package com.muddassir_92.hotmail.com.runtime.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.muddassir_92.hotmail.com.runtime.model.Service;
import com.muddassir_92.hotmail.com.runtime.model.ServiceProvider;
import com.wizzdi.flexicore.security.request.BasicPropertiesFilter;
import com.wizzdi.flexicore.security.request.PaginationFilter;
import com.wizzdi.flexicore.security.validation.IdValid;
import java.util.List;
import java.util.Set;

/** Object Used to List ServiceToServiceProvider */
@com.wizzdi.flexicore.security.validation.IdValid.List({
  @IdValid(
      targetField = "serviceProviders",
      field = "serviceProviderIds",
      fieldType = com.muddassir_92.hotmail.com.runtime.model.ServiceProvider.class),
  @IdValid(
      targetField = "services",
      field = "serviceIds",
      fieldType = com.muddassir_92.hotmail.com.runtime.model.Service.class)
})
public class ServiceToServiceProviderFilter extends PaginationFilter {

  private BasicPropertiesFilter basicPropertiesFilter;

  private Set<String> serviceIds;

  private Set<String> serviceProviderIds;

  @JsonIgnore private List<ServiceProvider> serviceProviders;

  @JsonIgnore private List<Service> services;

  /**
   * @return basicPropertiesFilter
   */
  public BasicPropertiesFilter getBasicPropertiesFilter() {
    return this.basicPropertiesFilter;
  }

  /**
   * @param basicPropertiesFilter basicPropertiesFilter to set
   * @return ServiceToServiceProviderFilter
   */
  public <T extends ServiceToServiceProviderFilter> T setBasicPropertiesFilter(
      BasicPropertiesFilter basicPropertiesFilter) {
    this.basicPropertiesFilter = basicPropertiesFilter;
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
   * @return ServiceToServiceProviderFilter
   */
  public <T extends ServiceToServiceProviderFilter> T setServiceIds(Set<String> serviceIds) {
    this.serviceIds = serviceIds;
    return (T) this;
  }

  /**
   * @return serviceProviderIds
   */
  public Set<String> getServiceProviderIds() {
    return this.serviceProviderIds;
  }

  /**
   * @param serviceProviderIds serviceProviderIds to set
   * @return ServiceToServiceProviderFilter
   */
  public <T extends ServiceToServiceProviderFilter> T setServiceProviderIds(
      Set<String> serviceProviderIds) {
    this.serviceProviderIds = serviceProviderIds;
    return (T) this;
  }

  /**
   * @return serviceProviders
   */
  @JsonIgnore
  public List<ServiceProvider> getServiceProviders() {
    return this.serviceProviders;
  }

  /**
   * @param serviceProviders serviceProviders to set
   * @return ServiceToServiceProviderFilter
   */
  public <T extends ServiceToServiceProviderFilter> T setServiceProviders(
      List<ServiceProvider> serviceProviders) {
    this.serviceProviders = serviceProviders;
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
   * @return ServiceToServiceProviderFilter
   */
  public <T extends ServiceToServiceProviderFilter> T setServices(List<Service> services) {
    this.services = services;
    return (T) this;
  }
}
