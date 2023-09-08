package com.muddassir_92.hotmail.com.runtime.data;

import com.flexicore.model.Baseclass;
import com.flexicore.model.Basic;
import com.flexicore.security.SecurityContextBase;
import com.muddassir_92.hotmail.com.runtime.model.AppUser;
import com.muddassir_92.hotmail.com.runtime.model.AppUser_;
import com.muddassir_92.hotmail.com.runtime.request.AppUserFilter;
import com.wizzdi.flexicore.security.data.BaseclassRepository;
import com.wizzdi.flexicore.security.data.BasicRepository;
import com.wizzdi.flexicore.security.data.SecuredBasicRepository;
import com.wizzdi.flexicore.security.data.SecurityUserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.persistence.metamodel.SingularAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AppUserRepository {
  @PersistenceContext private EntityManager em;

  @Autowired private SecuredBasicRepository securedBasicRepository;

  @Autowired private BaseclassRepository baseclassRepository;

  @Autowired private SecurityUserRepository securityUserRepository;

  /**
   * @param appUserFilter Object Used to List AppUser
   * @param securityContext
   * @return List of AppUser
   */
  public List<AppUser> listAllAppUsers(
      AppUserFilter appUserFilter, SecurityContextBase securityContext) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<AppUser> q = cb.createQuery(AppUser.class);
    Root<AppUser> r = q.from(AppUser.class);
    List<Predicate> preds = new ArrayList<>();
    addAppUserPredicate(appUserFilter, cb, q, r, preds, securityContext);
    q.select(r)
        .where(preds.toArray(new Predicate[0]))
        .orderBy(cb.desc(r.get(AppUser_.creationDate)));
    TypedQuery<AppUser> query = em.createQuery(q);

    BasicRepository.addPagination(appUserFilter, query);

    return query.getResultList();
  }

  public <T extends AppUser> void addAppUserPredicate(
      AppUserFilter appUserFilter,
      CriteriaBuilder cb,
      CommonAbstractCriteria q,
      From<?, T> r,
      List<Predicate> preds,
      SecurityContextBase securityContext) {

    securityUserRepository.addSecurityUserPredicates(
        appUserFilter, cb, q, r, preds, securityContext);

    if (appUserFilter.getUsername() != null && !appUserFilter.getUsername().isEmpty()) {
      preds.add(r.get(AppUser_.username).in(appUserFilter.getUsername()));
    }
  }

  /**
   * @param appUserFilter Object Used to List AppUser
   * @param securityContext
   * @return count of AppUser
   */
  public Long countAllAppUsers(AppUserFilter appUserFilter, SecurityContextBase securityContext) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Long> q = cb.createQuery(Long.class);
    Root<AppUser> r = q.from(AppUser.class);
    List<Predicate> preds = new ArrayList<>();
    addAppUserPredicate(appUserFilter, cb, q, r, preds, securityContext);
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
