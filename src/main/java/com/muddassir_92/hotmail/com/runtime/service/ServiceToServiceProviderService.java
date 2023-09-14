package com.muddassir_92.hotmail.com.runtime.service;

import com.flexicore.model.Baseclass;
import com.flexicore.model.Basic;
import com.flexicore.security.SecurityContextBase;
import com.muddassir_92.hotmail.com.runtime.data.ServiceToServiceProviderRepository;
import com.muddassir_92.hotmail.com.runtime.model.ServiceToServiceProvider;
import com.muddassir_92.hotmail.com.runtime.request.ServiceToServiceProviderCreate;
import com.muddassir_92.hotmail.com.runtime.request.ServiceToServiceProviderFilter;
import com.muddassir_92.hotmail.com.runtime.request.ServiceToServiceProviderUpdate;
import com.wizzdi.flexicore.security.response.PaginationResponse;
import com.wizzdi.flexicore.security.service.BaseclassService;
import com.wizzdi.flexicore.security.service.BasicService;
import jakarta.persistence.metamodel.SingularAttribute;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceToServiceProviderService {

  @Autowired private ServiceToServiceProviderRepository repository;

  @Autowired private BasicService basicService;

  /**
   * @param serviceToServiceProviderCreate Object Used to Create ServiceToServiceProvider
   * @param securityContext
   * @return created ServiceToServiceProvider
   */
  public ServiceToServiceProvider createServiceToServiceProvider(
      ServiceToServiceProviderCreate serviceToServiceProviderCreate,
      SecurityContextBase securityContext) {
    ServiceToServiceProvider serviceToServiceProvider =
        createServiceToServiceProviderNoMerge(serviceToServiceProviderCreate, securityContext);
    this.repository.merge(serviceToServiceProvider);
    return serviceToServiceProvider;
  }

  /**
   * @param serviceToServiceProviderCreate Object Used to Create ServiceToServiceProvider
   * @param securityContext
   * @return created ServiceToServiceProvider unmerged
   */
  public ServiceToServiceProvider createServiceToServiceProviderNoMerge(
      ServiceToServiceProviderCreate serviceToServiceProviderCreate,
      SecurityContextBase securityContext) {
    ServiceToServiceProvider serviceToServiceProvider = new ServiceToServiceProvider();
    serviceToServiceProvider.setId(UUID.randomUUID().toString());
    updateServiceToServiceProviderNoMerge(serviceToServiceProvider, serviceToServiceProviderCreate);

    BaseclassService.createSecurityObjectNoMerge(serviceToServiceProvider, securityContext);

    return serviceToServiceProvider;
  }

  /**
   * @param serviceToServiceProviderCreate Object Used to Create ServiceToServiceProvider
   * @param serviceToServiceProvider
   * @return if serviceToServiceProvider was updated
   */
  public boolean updateServiceToServiceProviderNoMerge(
      ServiceToServiceProvider serviceToServiceProvider,
      ServiceToServiceProviderCreate serviceToServiceProviderCreate) {
    boolean update =
        basicService.updateBasicNoMerge(serviceToServiceProviderCreate, serviceToServiceProvider);

    if (serviceToServiceProviderCreate.getService() != null
        && (serviceToServiceProvider.getService() == null
            || !serviceToServiceProviderCreate
                .getService()
                .getId()
                .equals(serviceToServiceProvider.getService().getId()))) {
      serviceToServiceProvider.setService(serviceToServiceProviderCreate.getService());
      update = true;
    }

    if (serviceToServiceProviderCreate.getServiceProvider() != null
        && (serviceToServiceProvider.getServiceProvider() == null
            || !serviceToServiceProviderCreate
                .getServiceProvider()
                .getId()
                .equals(serviceToServiceProvider.getServiceProvider().getId()))) {
      serviceToServiceProvider.setServiceProvider(
          serviceToServiceProviderCreate.getServiceProvider());
      update = true;
    }

    return update;
  }

  /**
   * @param serviceToServiceProviderUpdate
   * @param securityContext
   * @return serviceToServiceProvider
   */
  public ServiceToServiceProvider updateServiceToServiceProvider(
      ServiceToServiceProviderUpdate serviceToServiceProviderUpdate,
      SecurityContextBase securityContext) {
    ServiceToServiceProvider serviceToServiceProvider =
        serviceToServiceProviderUpdate.getServiceToServiceProvider();
    if (updateServiceToServiceProviderNoMerge(
        serviceToServiceProvider, serviceToServiceProviderUpdate)) {
      this.repository.merge(serviceToServiceProvider);
    }
    return serviceToServiceProvider;
  }

  /**
   * @param serviceToServiceProviderFilter Object Used to List ServiceToServiceProvider
   * @param securityContext
   * @return PaginationResponse<ServiceToServiceProvider> containing paging information for
   *     ServiceToServiceProvider
   */
  public PaginationResponse<ServiceToServiceProvider> getAllServiceToServiceProviders(
      ServiceToServiceProviderFilter serviceToServiceProviderFilter,
      SecurityContextBase securityContext) {
    List<ServiceToServiceProvider> list =
        listAllServiceToServiceProviders(serviceToServiceProviderFilter, securityContext);
    long count =
        this.repository.countAllServiceToServiceProviders(
            serviceToServiceProviderFilter, securityContext);
    return new PaginationResponse<>(list, serviceToServiceProviderFilter.getPageSize(), count);
  }

  /**
   * @param serviceToServiceProviderFilter Object Used to List ServiceToServiceProvider
   * @param securityContext
   * @return List of ServiceToServiceProvider
   */
  public List<ServiceToServiceProvider> listAllServiceToServiceProviders(
      ServiceToServiceProviderFilter serviceToServiceProviderFilter,
      SecurityContextBase securityContext) {
    return this.repository.listAllServiceToServiceProviders(
        serviceToServiceProviderFilter, securityContext);
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
