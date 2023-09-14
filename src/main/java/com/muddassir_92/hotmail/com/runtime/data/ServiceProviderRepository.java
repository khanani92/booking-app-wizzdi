package com.muddassir_92.hotmail.com.runtime.data;

import com.flexicore.model.Baseclass;
import com.flexicore.model.Basic;
import com.flexicore.security.SecurityContextBase;
import com.muddassir_92.hotmail.com.runtime.model.ServiceProvider;
import com.muddassir_92.hotmail.com.runtime.model.ServiceProvider_;
import com.muddassir_92.hotmail.com.runtime.model.ServiceToServiceProvider;
import com.muddassir_92.hotmail.com.runtime.model.ServiceToServiceProvider_;
import com.muddassir_92.hotmail.com.runtime.request.ServiceProviderFilter;
import com.wizzdi.flexicore.file.model.FileResource;
import com.wizzdi.flexicore.file.model.FileResource_;
import com.wizzdi.flexicore.security.data.BasicRepository;
import com.wizzdi.flexicore.security.data.SecuredBasicRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.persistence.metamodel.SingularAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ServiceProviderRepository {
  @PersistenceContext private EntityManager em;

  @Autowired private SecuredBasicRepository securedBasicRepository;

  /**
   * @param serviceProviderFilter Object Used to List ServiceProvider
   * @param securityContext
   * @return List of ServiceProvider
   */
  public List<ServiceProvider> listAllServiceProviders(
      ServiceProviderFilter serviceProviderFilter, SecurityContextBase securityContext) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<ServiceProvider> q = cb.createQuery(ServiceProvider.class);
    Root<ServiceProvider> r = q.from(ServiceProvider.class);
    List<Predicate> preds = new ArrayList<>();
    addServiceProviderPredicate(serviceProviderFilter, cb, q, r, preds, securityContext);
    q.select(r)
        .where(preds.toArray(new Predicate[0]))
        .orderBy(cb.desc(r.get(ServiceProvider_.creationDate)));
    TypedQuery<ServiceProvider> query = em.createQuery(q);

    BasicRepository.addPagination(serviceProviderFilter, query);

    return query.getResultList();
  }

  public <T extends ServiceProvider> void addServiceProviderPredicate(
      ServiceProviderFilter serviceProviderFilter,
      CriteriaBuilder cb,
      CommonAbstractCriteria q,
      From<?, T> r,
      List<Predicate> preds,
      SecurityContextBase securityContext) {

    this.securedBasicRepository.addSecuredBasicPredicates(
        serviceProviderFilter.getBasicPropertiesFilter(), cb, q, r, preds, securityContext);

    if (serviceProviderFilter.getProfilePictures() != null
        && !serviceProviderFilter.getProfilePictures().isEmpty()) {
      Set<String> ids =
          serviceProviderFilter.getProfilePictures().parallelStream()
              .map(f -> f.getId())
              .collect(Collectors.toSet());
      Join<T, FileResource> join = r.join(ServiceProvider_.profilePicture);
      preds.add(join.get(FileResource_.id).in(ids));
    }

    if (serviceProviderFilter.getBlock() != null && !serviceProviderFilter.getBlock().isEmpty()) {
      preds.add(r.get(ServiceProvider_.block).in(serviceProviderFilter.getBlock()));
    }

    if (serviceProviderFilter.getAddress() != null
        && !serviceProviderFilter.getAddress().isEmpty()) {
      preds.add(r.get(ServiceProvider_.address).in(serviceProviderFilter.getAddress()));
    }

    if (serviceProviderFilter.getGender() != null && !serviceProviderFilter.getGender().isEmpty()) {
      preds.add(r.get(ServiceProvider_.gender).in(serviceProviderFilter.getGender()));
    }

    if (serviceProviderFilter.getPhoneNumber() != null
        && !serviceProviderFilter.getPhoneNumber().isEmpty()) {
      preds.add(r.get(ServiceProvider_.phoneNumber).in(serviceProviderFilter.getPhoneNumber()));
    }

    if (serviceProviderFilter.getEmail() != null && !serviceProviderFilter.getEmail().isEmpty()) {
      preds.add(r.get(ServiceProvider_.email).in(serviceProviderFilter.getEmail()));
    }

    if (serviceProviderFilter.getServiceProviderServiceToServiceProviderses() != null
        && !serviceProviderFilter.getServiceProviderServiceToServiceProviderses().isEmpty()) {
      Set<String> ids =
          serviceProviderFilter.getServiceProviderServiceToServiceProviderses().parallelStream()
              .map(f -> f.getId())
              .collect(Collectors.toSet());
      Join<T, ServiceToServiceProvider> join =
          r.join(ServiceProvider_.serviceProviderServiceToServiceProviders);
      preds.add(join.get(ServiceToServiceProvider_.id).in(ids));
    }
  }

  /**
   * @param serviceProviderFilter Object Used to List ServiceProvider
   * @param securityContext
   * @return count of ServiceProvider
   */
  public Long countAllServiceProviders(
      ServiceProviderFilter serviceProviderFilter, SecurityContextBase securityContext) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Long> q = cb.createQuery(Long.class);
    Root<ServiceProvider> r = q.from(ServiceProvider.class);
    List<Predicate> preds = new ArrayList<>();
    addServiceProviderPredicate(serviceProviderFilter, cb, q, r, preds, securityContext);
    q.select(cb.count(r)).where(preds.toArray(new Predicate[0]));
    TypedQuery<Long> query = em.createQuery(q);
    return query.getSingleResult();
  }

  public <T extends Baseclass> List<T> listByIds(
      Class<T> c, Set<String> ids, SecurityContextBase securityContext) {
    return securedBasicRepository.listByIds(c, ids, securityContext);
  }

  public <T extends Baseclass> T getByIdOrNull(
      String id, Class<T> c, SecurityContextBase securityContext) {
    return securedBasicRepository.getByIdOrNull(id, c, securityContext);
  }

  public <D extends Basic, E extends Baseclass, T extends D> T getByIdOrNull(
      String id,
      Class<T> c,
      SingularAttribute<D, E> baseclassAttribute,
      SecurityContextBase securityContext) {
    return securedBasicRepository.getByIdOrNull(id, c, baseclassAttribute, securityContext);
  }

  public <D extends Basic, E extends Baseclass, T extends D> List<T> listByIds(
      Class<T> c,
      Set<String> ids,
      SingularAttribute<D, E> baseclassAttribute,
      SecurityContextBase securityContext) {
    return securedBasicRepository.listByIds(c, ids, baseclassAttribute, securityContext);
  }

  public <D extends Basic, T extends D> List<T> findByIds(
      Class<T> c, Set<String> ids, SingularAttribute<D, String> idAttribute) {
    return securedBasicRepository.findByIds(c, ids, idAttribute);
  }

  public <T extends Basic> List<T> findByIds(Class<T> c, Set<String> requested) {
    return securedBasicRepository.findByIds(c, requested);
  }

  public <T> T findByIdOrNull(Class<T> type, String id) {
    return securedBasicRepository.findByIdOrNull(type, id);
  }

  @Transactional
  public void merge(java.lang.Object base) {
    securedBasicRepository.merge(base);
  }

  @Transactional
  public void massMerge(List<?> toMerge) {
    securedBasicRepository.massMerge(toMerge);
  }
}
