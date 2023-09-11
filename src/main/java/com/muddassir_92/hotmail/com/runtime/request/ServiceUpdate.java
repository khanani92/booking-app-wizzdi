package com.muddassir_92.hotmail.com.runtime.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.muddassir_92.hotmail.com.runtime.model.Service;
import com.wizzdi.flexicore.security.validation.IdValid;

/** Object Used to Update Service */
@com.wizzdi.flexicore.security.validation.IdValid.List({
  @IdValid(
      targetField = "service",
      field = "id",
      fieldType = com.muddassir_92.hotmail.com.runtime.model.Service.class,
      groups = {com.wizzdi.flexicore.security.validation.Update.class})
})
public class ServiceUpdate extends ServiceCreate {

  private String id;

  @JsonIgnore private Service service;

  /**
   * @return id
   */
  public String getId() {
    return this.id;
  }

  /**
   * @param id id to set
   * @return ServiceUpdate
   */
  public <T extends ServiceUpdate> T setId(String id) {
    this.id = id;
    return (T) this;
  }

  /**
   * @return service
   */
  @JsonIgnore
  public Service getService() {
    return this.service;
  }

  /**
   * @param service service to set
   * @return ServiceUpdate
   */
  public <T extends ServiceUpdate> T setService(Service service) {
    this.service = service;
    return (T) this;
  }
}
