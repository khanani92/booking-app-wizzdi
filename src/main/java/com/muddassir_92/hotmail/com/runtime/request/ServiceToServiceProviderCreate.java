package com.muddassir_92.hotmail.com.runtime.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.muddassir_92.hotmail.com.runtime.model.Service;
import com.muddassir_92.hotmail.com.runtime.model.ServiceProvider;
import com.wizzdi.flexicore.security.request.BasicCreate;
import com.wizzdi.flexicore.security.validation.IdValid;

/** Object Used to Create ServiceToServiceProvider */
@com.wizzdi.flexicore.security.validation.IdValid.List({
  @IdValid(
      targetField = "serviceProvider",
      field = "serviceProviderId",
      fieldType = com.muddassir_92.hotmail.com.runtime.model.ServiceProvider.class,
      groups = {
        com.wizzdi.flexicore.security.validation.Update.class,
        com.wizzdi.flexicore.security.validation.Create.class
      }),
  @IdValid(
      targetField = "service",
      field = "serviceId",
      fieldType = com.muddassir_92.hotmail.com.runtime.model.Service.class,
      groups = {
        com.wizzdi.flexicore.security.validation.Update.class,
        com.wizzdi.flexicore.security.validation.Create.class
      })
})
public class ServiceToServiceProviderCreate extends BasicCreate {

  @JsonIgnore private Service service;

  private String serviceId;

  @JsonIgnore private ServiceProvider serviceProvider;

  private String serviceProviderId;

  /**
   * @return service
   */
  @JsonIgnore
  public Service getService() {
    return this.service;
  }

  /**
   * @param service service to set
   * @return ServiceToServiceProviderCreate
   */
  public <T extends ServiceToServiceProviderCreate> T setService(Service service) {
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
   * @return ServiceToServiceProviderCreate
   */
  public <T extends ServiceToServiceProviderCreate> T setServiceId(String serviceId) {
    this.serviceId = serviceId;
    return (T) this;
  }

  /**
   * @return serviceProvider
   */
  @JsonIgnore
  public ServiceProvider getServiceProvider() {
    return this.serviceProvider;
  }

  /**
   * @param serviceProvider serviceProvider to set
   * @return ServiceToServiceProviderCreate
   */
  public <T extends ServiceToServiceProviderCreate> T setServiceProvider(
      ServiceProvider serviceProvider) {
    this.serviceProvider = serviceProvider;
    return (T) this;
  }

  /**
   * @return serviceProviderId
   */
  public String getServiceProviderId() {
    return this.serviceProviderId;
  }

  /**
   * @param serviceProviderId serviceProviderId to set
   * @return ServiceToServiceProviderCreate
   */
  public <T extends ServiceToServiceProviderCreate> T setServiceProviderId(
      String serviceProviderId) {
    this.serviceProviderId = serviceProviderId;
    return (T) this;
  }
}
