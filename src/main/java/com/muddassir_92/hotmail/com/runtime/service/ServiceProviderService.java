package com.muddassir_92.hotmail.com.runtime.service;

import com.flexicore.model.Baseclass;
import com.flexicore.model.Basic;
import com.flexicore.security.SecurityContextBase;
import com.muddassir_92.hotmail.com.runtime.data.ServiceProviderRepository;
import com.muddassir_92.hotmail.com.runtime.model.ServiceProvider;
import com.muddassir_92.hotmail.com.runtime.request.ServiceProviderCreate;
import com.muddassir_92.hotmail.com.runtime.request.ServiceProviderFilter;
import com.muddassir_92.hotmail.com.runtime.request.ServiceProviderUpdate;
import com.wizzdi.flexicore.security.response.PaginationResponse;
import com.wizzdi.flexicore.security.service.BaseclassService;
import com.wizzdi.flexicore.security.service.BasicService;
import jakarta.persistence.metamodel.SingularAttribute;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ServiceProviderService {

  @Autowired private ServiceProviderRepository repository;

  @Autowired private BasicService basicService;

  @Autowired private PasswordEncoder passwordEncoder;

  /**
   * @param serviceProviderCreate Object Used to Create ServiceProvider
   * @param securityContext
   * @return created ServiceProvider
   */
  public ServiceProvider createServiceProvider(
      ServiceProviderCreate serviceProviderCreate, SecurityContextBase securityContext) {
    ServiceProvider serviceProvider =
        createServiceProviderNoMerge(serviceProviderCreate, securityContext);
    this.repository.merge(serviceProvider);
    return serviceProvider;
  }

  /**
   * @param serviceProviderCreate Object Used to Create ServiceProvider
   * @param securityContext
   * @return created ServiceProvider unmerged
   */
  public ServiceProvider createServiceProviderNoMerge(
      ServiceProviderCreate serviceProviderCreate, SecurityContextBase securityContext) {
    ServiceProvider serviceProvider = new ServiceProvider();
    serviceProvider.setId(UUID.randomUUID().toString());
    updateServiceProviderNoMerge(serviceProvider, serviceProviderCreate);

    BaseclassService.createSecurityObjectNoMerge(serviceProvider, securityContext);

    return serviceProvider;
  }

  /**
   * @param serviceProviderCreate Object Used to Create ServiceProvider
   * @param serviceProvider
   * @return if serviceProvider was updated
   */
  public boolean updateServiceProviderNoMerge(
      ServiceProvider serviceProvider, ServiceProviderCreate serviceProviderCreate) {
    boolean update = basicService.updateBasicNoMerge(serviceProviderCreate, serviceProvider);

    if (serviceProviderCreate.getProfilePicture() != null
        && (serviceProvider.getProfilePicture() == null
            || !serviceProviderCreate
                .getProfilePicture()
                .getId()
                .equals(serviceProvider.getProfilePicture().getId()))) {
      serviceProvider.setProfilePicture(serviceProviderCreate.getProfilePicture());
      update = true;
    }

    if (serviceProviderCreate.getBlock() != null
        && (!serviceProviderCreate.getBlock().equals(serviceProvider.isBlock()))) {
      serviceProvider.setBlock(serviceProviderCreate.getBlock());
      update = true;
    }

    if (serviceProviderCreate.getService() != null
        && (serviceProvider.getService() == null
            || !serviceProviderCreate
                .getService()
                .getId()
                .equals(serviceProvider.getService().getId()))) {
      serviceProvider.setService(serviceProviderCreate.getService());
      update = true;
    }

    if (serviceProviderCreate.getGender() != null
        && (!serviceProviderCreate.getGender().equals(serviceProvider.getGender()))) {
      serviceProvider.setGender(serviceProviderCreate.getGender());
      update = true;
    }

    if (serviceProviderCreate.getEmail() != null
        && (!serviceProviderCreate.getEmail().equals(serviceProvider.getEmail()))) {
      serviceProvider.setEmail(serviceProviderCreate.getEmail());
      update = true;
    }

    if (serviceProviderCreate.getPassword() != null
        && (!passwordEncoder.matches(
            serviceProviderCreate.getPassword(), serviceProvider.getPassword()))) {
      serviceProvider.setPassword(passwordEncoder.encode(serviceProviderCreate.getPassword()));
      update = true;
    }

    return update;
  }

  /**
   * @param serviceProviderUpdate
   * @param securityContext
   * @return serviceProvider
   */
  public ServiceProvider updateServiceProvider(
      ServiceProviderUpdate serviceProviderUpdate, SecurityContextBase securityContext) {
    ServiceProvider serviceProvider = serviceProviderUpdate.getServiceProvider();
    if (updateServiceProviderNoMerge(serviceProvider, serviceProviderUpdate)) {
      this.repository.merge(serviceProvider);
    }
    return serviceProvider;
  }

  /**
   * @param serviceProviderFilter Object Used to List ServiceProvider
   * @param securityContext
   * @return PaginationResponse<ServiceProvider> containing paging information for ServiceProvider
   */
  public PaginationResponse<ServiceProvider> getAllServiceProviders(
      ServiceProviderFilter serviceProviderFilter, SecurityContextBase securityContext) {
    List<ServiceProvider> list = listAllServiceProviders(serviceProviderFilter, securityContext);
    long count = this.repository.countAllServiceProviders(serviceProviderFilter, securityContext);
    return new PaginationResponse<>(list, serviceProviderFilter.getPageSize(), count);
  }

  /**
   * @param serviceProviderFilter Object Used to List ServiceProvider
   * @param securityContext
   * @return List of ServiceProvider
   */
  public List<ServiceProvider> listAllServiceProviders(
      ServiceProviderFilter serviceProviderFilter, SecurityContextBase securityContext) {
    return this.repository.listAllServiceProviders(serviceProviderFilter, securityContext);
  }

  public <T extends Baseclass> List<T> listByIds(
      Class<T> c, Set<String> ids, SecurityContextBase securityContext) {
    return this.repository.listByIds(c, ids, securityContext);
  }

  public <T extends Baseclass> T getByIdOrNull(
      String id, Class<T> c, SecurityContextBase securityContext) {
    return this.repository.getByIdOrNull(id, c, securityContext);
  }

  public <D extends Basic, E extends Baseclass, T extends D> T getByIdOrNull(
      String id,
      Class<T> c,
      SingularAttribute<D, E> baseclassAttribute,
      SecurityContextBase securityContext) {
    return this.repository.getByIdOrNull(id, c, baseclassAttribute, securityContext);
  }

  public <D extends Basic, E extends Baseclass, T extends D> List<T> listByIds(
      Class<T> c,
      Set<String> ids,
      SingularAttribute<D, E> baseclassAttribute,
      SecurityContextBase securityContext) {
    return this.repository.listByIds(c, ids, baseclassAttribute, securityContext);
  }

  public <D extends Basic, T extends D> List<T> findByIds(
      Class<T> c, Set<String> ids, SingularAttribute<D, String> idAttribute) {
    return this.repository.findByIds(c, ids, idAttribute);
  }

  public <T extends Basic> List<T> findByIds(Class<T> c, Set<String> requested) {
    return this.repository.findByIds(c, requested);
  }

  public <T> T findByIdOrNull(Class<T> type, String id) {
    return this.repository.findByIdOrNull(type, id);
  }

  public void merge(java.lang.Object base) {
    this.repository.merge(base);
  }

  public void massMerge(List<?> toMerge) {
    this.repository.massMerge(toMerge);
  }
}
