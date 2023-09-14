package com.muddassir_92.hotmail.com.runtime;

import com.flexicore.security.SecurityContextBase;
import com.muddassir_92.hotmail.com.runtime.model.Service;
import com.muddassir_92.hotmail.com.runtime.model.ServiceProvider;
import com.muddassir_92.hotmail.com.runtime.request.ServiceCreate;
import com.muddassir_92.hotmail.com.runtime.request.ServiceProviderCreate;
import com.muddassir_92.hotmail.com.runtime.service.ServiceProviderService;
import com.muddassir_92.hotmail.com.runtime.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppInitConfig {

  @Autowired private ServiceService serviceService;

  @Autowired private ServiceProviderService serviceProviderService;

  @Autowired
  @Qualifier("adminSecurityContext")
  private SecurityContextBase securityContext;

  @Bean
  public Service service() {
    ServiceCreate serviceCreate = new ServiceCreate();
    return serviceService.createService(serviceCreate, securityContext);
  }

  @Bean
  public ServiceProvider serviceProvider() {
    ServiceProviderCreate serviceProviderCreate = new ServiceProviderCreate();
    return serviceProviderService.createServiceProvider(serviceProviderCreate, securityContext);
  }
}
