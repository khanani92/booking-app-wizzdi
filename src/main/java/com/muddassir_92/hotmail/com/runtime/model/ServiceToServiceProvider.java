package com.muddassir_92.hotmail.com.runtime.model;

import com.flexicore.model.SecuredBasic;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class ServiceToServiceProvider extends SecuredBasic {

  @ManyToOne(targetEntity = Service.class)
  private Service service;

  @ManyToOne(targetEntity = ServiceProvider.class)
  private ServiceProvider serviceProvider;

  /**
   * @return service
   */
  @ManyToOne(targetEntity = Service.class)
  public Service getService() {
    return this.service;
  }

  /**
   * @param service service to set
   * @return ServiceToServiceProvider
   */
  public <T extends ServiceToServiceProvider> T setService(Service service) {
    this.service = service;
    return (T) this;
  }

  /**
   * @return serviceProvider
   */
  @ManyToOne(targetEntity = ServiceProvider.class)
  public ServiceProvider getServiceProvider() {
    return this.serviceProvider;
  }

  /**
   * @param serviceProvider serviceProvider to set
   * @return ServiceToServiceProvider
   */
  public <T extends ServiceToServiceProvider> T setServiceProvider(
      ServiceProvider serviceProvider) {
    this.serviceProvider = serviceProvider;
    return (T) this;
  }
}
