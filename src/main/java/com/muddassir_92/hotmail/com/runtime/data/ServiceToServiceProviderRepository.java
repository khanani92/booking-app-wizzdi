package com.muddassir_92.hotmail.com.runtime.data;

import com.flexicore.model.Baseclass;
import com.flexicore.model.Basic;
import com.flexicore.security.SecurityContextBase;
import com.muddassir_92.hotmail.com.runtime.model.Service;
import com.muddassir_92.hotmail.com.runtime.model.ServiceProvider;
import com.muddassir_92.hotmail.com.runtime.model.ServiceProvider_;
import com.muddassir_92.hotmail.com.runtime.model.ServiceToServiceProvider;
import com.muddassir_92.hotmail.com.runtime.model.ServiceToServiceProvider_;
import com.muddassir_92.hotmail.com.runtime.model.Service_;
import com.muddassir_92.hotmail.com.runtime.request.ServiceToServiceProviderFilter;
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
public class ServiceToServiceProviderRepository {
  @PersistenceContext private EntityManager em;

  @Autowired private SecuredBasicRepository securedBasicRepository;

  /**
   * @param serviceToServiceProviderFilter Object Used to List ServiceToServiceProvider
   * @param securityContext
   * @return List of ServiceToServiceProvider
   */
  public List<ServiceToServiceProvider> listAllServiceToServiceProviders(
      ServiceToServiceProviderFilter serviceToServiceProviderFilter,
      SecurityContextBase securityContext) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<ServiceToServiceProvider> q = cb.createQuery(ServiceToServiceProvider.class);
    Root<ServiceToServiceProvider> r = q.from(ServiceToServiceProvider.class);
    List<Predicate> preds = new ArrayList<>();
    addServiceToServiceProviderPredicate(
        serviceToServiceProviderFilter, cb, q, r, preds, securityContext);
    q.select(r)
        .where(preds.toArray(new Predicate[0]))
        .orderBy(cb.desc(r.get(ServiceToServiceProvider_.creationDate)));
    TypedQuery<ServiceToServiceProvider> query = em.createQuery(q);

    BasicRepository.addPagination(serviceToServiceProviderFilter, query);

    return query.getResultList();
  }

  public <T extends ServiceToServiceProvider> void addServiceToServiceProviderPredicate(
      ServiceToServiceProviderFilter serviceToServiceProviderFilter,
      CriteriaBuilder cb,
      CommonAbstractCriteria q,
      From<?, T> r,
      List<Predicate> preds,
      SecurityContextBase securityContext) {

    this.securedBasicRepository.addSecuredBasicPredicates(
        serviceToServiceProviderFilter.getBasicPropertiesFilter(),
        cb,
        q,
        r,
        preds,
        securityContext);

    if (serviceToServiceProviderFilter.getServices() != null
        && !serviceToServiceProviderFilter.getServices().isEmpty()) {
      Set<String> ids =
          serviceToServiceProviderFilter.getServices().parallelStream()
              .map(f -> f.getId())
              .collect(Collectors.toSet());
      Join<T, Service> join = r.join(ServiceToServiceProvider_.service);
      preds.add(join.get(Service_.id).in(ids));
    }

    if (serviceToServiceProviderFilter.getServiceProviders() != null
        && !serviceToServiceProviderFilter.getServiceProviders().isEmpty()) {
      Set<String> ids =
          serviceToServiceProviderFilter.getServiceProviders().parallelStream()
              .map(f -> f.getId())
              .collect(Collectors.toSet());
      Join<T, ServiceProvider> join = r.join(ServiceToServiceProvider_.serviceProvider);
      preds.add(join.get(ServiceProvider_.id).in(ids));
    }
  }

  /**
   * @param serviceToServiceProviderFilter Object Used to List ServiceToServiceProvider
   * @param securityContext
   * @return count of ServiceToServiceProvider
   */
  public Long countAllServiceToServiceProviders(
      ServiceToServiceProviderFilter serviceToServiceProviderFilter,
      SecurityContextBase securityContext) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Long> q = cb.createQuery(Long.class);
    Root<ServiceToServiceProvider> r = q.from(ServiceToServiceProvider.class);
    List<Predicate> preds = new ArrayList<>();
    addServiceToServiceProviderPredicate(
        serviceToServiceProviderFilter, cb, q, r, preds, securityContext);
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
