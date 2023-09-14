package com.muddassir_92.hotmail.com.runtime.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.muddassir_92.hotmail.com.runtime.model.ServiceToServiceProvider;
import com.wizzdi.flexicore.security.validation.IdValid;

/** Object Used to Update ServiceToServiceProvider */
@com.wizzdi.flexicore.security.validation.IdValid.List({
  @IdValid(
      targetField = "serviceToServiceProvider",
      field = "id",
      fieldType = com.muddassir_92.hotmail.com.runtime.model.ServiceToServiceProvider.class,
      groups = {com.wizzdi.flexicore.security.validation.Update.class})
})
public class ServiceToServiceProviderUpdate extends ServiceToServiceProviderCreate {

  private String id;

  @JsonIgnore private ServiceToServiceProvider serviceToServiceProvider;

  /**
   * @return id
   */
  public String getId() {
    return this.id;
  }

  /**
   * @param id id to set
   * @return ServiceToServiceProviderUpdate
   */
  public <T extends ServiceToServiceProviderUpdate> T setId(String id) {
    this.id = id;
    return (T) this;
  }

  /**
   * @return serviceToServiceProvider
   */
  @JsonIgnore
  public ServiceToServiceProvider getServiceToServiceProvider() {
    return this.serviceToServiceProvider;
  }

  /**
   * @param serviceToServiceProvider serviceToServiceProvider to set
   * @return ServiceToServiceProviderUpdate
   */
  public <T extends ServiceToServiceProviderUpdate> T setServiceToServiceProvider(
      ServiceToServiceProvider serviceToServiceProvider) {
    this.serviceToServiceProvider = serviceToServiceProvider;
    return (T) this;
  }
}
