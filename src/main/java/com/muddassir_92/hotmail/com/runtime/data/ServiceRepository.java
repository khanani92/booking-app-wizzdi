package com.muddassir_92.hotmail.com.runtime.data;

import com.flexicore.model.Baseclass;
import com.flexicore.model.Basic;
import com.flexicore.security.SecurityContextBase;
import com.muddassir_92.hotmail.com.runtime.model.Service;
import com.muddassir_92.hotmail.com.runtime.model.ServiceProvider;
import com.muddassir_92.hotmail.com.runtime.model.ServiceProvider_;
import com.muddassir_92.hotmail.com.runtime.model.Service_;
import com.muddassir_92.hotmail.com.runtime.request.ServiceFilter;
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
public class ServiceRepository {
  @PersistenceContext private EntityManager em;

  @Autowired private SecuredBasicRepository securedBasicRepository;

  /**
   * @param serviceFilter Object Used to List Service
   * @param securityContext
   * @return List of Service
   */
  public List<Service> listAllServices(
      ServiceFilter serviceFilter, SecurityContextBase securityContext) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Service> q = cb.createQuery(Service.class);
    Root<Service> r = q.from(Service.class);
    List<Predicate> preds = new ArrayList<>();
    addServicePredicate(serviceFilter, cb, q, r, preds, securityContext);
    q.select(r)
        .where(preds.toArray(new Predicate[0]))
        .orderBy(cb.desc(r.get(Service_.creationDate)));
    TypedQuery<Service> query = em.createQuery(q);

    BasicRepository.addPagination(serviceFilter, query);

    return query.getResultList();
  }

  public <T extends Service> void addServicePredicate(
      ServiceFilter serviceFilter,
      CriteriaBuilder cb,
      CommonAbstractCriteria q,
      From<?, T> r,
      List<Predicate> preds,
      SecurityContextBase securityContext) {

    this.securedBasicRepository.addSecuredBasicPredicates(
        serviceFilter.getBasicPropertiesFilter(), cb, q, r, preds, securityContext);

    if (serviceFilter.getServiceServiceProviderses() != null
        && !serviceFilter.getServiceServiceProviderses().isEmpty()) {
      Set<String> ids =
          serviceFilter.getServiceServiceProviderses().parallelStream()
              .map(f -> f.getId())
              .collect(Collectors.toSet());
      Join<T, ServiceProvider> join = r.join(Service_.serviceServiceProviders);
      preds.add(join.get(ServiceProvider_.id).in(ids));
    }

    if (serviceFilter.getPriceStart() != null) {
      preds.add(cb.greaterThanOrEqualTo(r.get(Service_.price), serviceFilter.getPriceStart()));
    }
    if (serviceFilter.getPriceEnd() != null) {
      preds.add(cb.lessThanOrEqualTo(r.get(Service_.price), serviceFilter.getPriceEnd()));
    }

    if (serviceFilter.getProfilePictures() != null
        && !serviceFilter.getProfilePictures().isEmpty()) {
      Set<String> ids =
          serviceFilter.getProfilePictures().parallelStream()
              .map(f -> f.getId())
              .collect(Collectors.toSet());
      Join<T, FileResource> join = r.join(Service_.profilePicture);
      preds.add(join.get(FileResource_.id).in(ids));
    }
  }

  /**
   * @param serviceFilter Object Used to List Service
   * @param securityContext
   * @return count of Service
   */
  public Long countAllServices(ServiceFilter serviceFilter, SecurityContextBase securityContext) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Long> q = cb.createQuery(Long.class);
    Root<Service> r = q.from(Service.class);
    List<Predicate> preds = new ArrayList<>();
    addServicePredicate(serviceFilter, cb, q, r, preds, securityContext);
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
