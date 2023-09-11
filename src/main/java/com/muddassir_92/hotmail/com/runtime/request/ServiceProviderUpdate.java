package com.muddassir_92.hotmail.com.runtime.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.muddassir_92.hotmail.com.runtime.model.ServiceProvider;
import com.wizzdi.flexicore.security.validation.IdValid;

/** Object Used to Update ServiceProvider */
@com.wizzdi.flexicore.security.validation.IdValid.List({
  @IdValid(
      targetField = "serviceProvider",
      field = "id",
      fieldType = com.muddassir_92.hotmail.com.runtime.model.ServiceProvider.class,
      groups = {com.wizzdi.flexicore.security.validation.Update.class})
})
public class ServiceProviderUpdate extends ServiceProviderCreate {

  private String id;

  @JsonIgnore private ServiceProvider serviceProvider;

  /**
   * @return id
   */
  public String getId() {
    return this.id;
  }

  /**
   * @param id id to set
   * @return ServiceProviderUpdate
   */
  public <T extends ServiceProviderUpdate> T setId(String id) {
    this.id = id;
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
   * @return ServiceProviderUpdate
   */
  public <T extends ServiceProviderUpdate> T setServiceProvider(ServiceProvider serviceProvider) {
    this.serviceProvider = serviceProvider;
    return (T) this;
  }
}
