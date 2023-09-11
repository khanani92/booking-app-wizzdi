package com.muddassir_92.hotmail.com.runtime.service;

import com.flexicore.model.Baseclass;
import com.flexicore.model.Basic;
import com.flexicore.security.SecurityContextBase;
import com.muddassir_92.hotmail.com.runtime.data.ServiceRepository;
import com.muddassir_92.hotmail.com.runtime.model.Service;
import com.muddassir_92.hotmail.com.runtime.request.ServiceCreate;
import com.muddassir_92.hotmail.com.runtime.request.ServiceFilter;
import com.muddassir_92.hotmail.com.runtime.request.ServiceUpdate;
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
public class ServiceService {

  @Autowired private ServiceRepository repository;

  @Autowired private BasicService basicService;

  /**
   * @param serviceCreate Object Used to Create Service
   * @param securityContext
   * @return created Service
   */
  public Service createService(ServiceCreate serviceCreate, SecurityContextBase securityContext) {
    Service service = createServiceNoMerge(serviceCreate, securityContext);
    this.repository.merge(service);
    return service;
  }

  /**
   * @param serviceCreate Object Used to Create Service
   * @param securityContext
   * @return created Service unmerged
   */
  public Service createServiceNoMerge(
      ServiceCreate serviceCreate, SecurityContextBase securityContext) {
    Service service = new Service();
    service.setId(UUID.randomUUID().toString());
    updateServiceNoMerge(service, serviceCreate);

    BaseclassService.createSecurityObjectNoMerge(service, securityContext);

    return service;
  }

  /**
   * @param serviceCreate Object Used to Create Service
   * @param service
   * @return if service was updated
   */
  public boolean updateServiceNoMerge(Service service, ServiceCreate serviceCreate) {
    boolean update = basicService.updateBasicNoMerge(serviceCreate, service);

    if (serviceCreate.getPrice() != null
        && (!serviceCreate.getPrice().equals(service.getPrice()))) {
      service.setPrice(serviceCreate.getPrice());
      update = true;
    }

    if (serviceCreate.getProfilePicture() != null
        && (service.getProfilePicture() == null
            || !serviceCreate
                .getProfilePicture()
                .getId()
                .equals(service.getProfilePicture().getId()))) {
      service.setProfilePicture(serviceCreate.getProfilePicture());
      update = true;
    }

    return update;
  }

  /**
   * @param serviceUpdate
   * @param securityContext
   * @return service
   */
  public Service updateService(ServiceUpdate serviceUpdate, SecurityContextBase securityContext) {
    Service service = serviceUpdate.getService();
    if (updateServiceNoMerge(service, serviceUpdate)) {
      this.repository.merge(service);
    }
    return service;
  }

  /**
   * @param serviceFilter Object Used to List Service
   * @param securityContext
   * @return PaginationResponse<Service> containing paging information for Service
   */
  public PaginationResponse<Service> getAllServices(
      ServiceFilter serviceFilter, SecurityContextBase securityContext) {
    List<Service> list = listAllServices(serviceFilter, securityContext);
    long count = this.repository.countAllServices(serviceFilter, securityContext);
    return new PaginationResponse<>(list, serviceFilter.getPageSize(), count);
  }

  /**
   * @param serviceFilter Object Used to List Service
   * @param securityContext
   * @return List of Service
   */
  public List<Service> listAllServices(
      ServiceFilter serviceFilter, SecurityContextBase securityContext) {
    return this.repository.listAllServices(serviceFilter, securityContext);
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
