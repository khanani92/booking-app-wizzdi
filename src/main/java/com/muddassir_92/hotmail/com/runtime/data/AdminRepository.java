package com.muddassir_92.hotmail.com.runtime.data;

import com.flexicore.model.Baseclass;
import com.flexicore.model.Basic;
import com.flexicore.security.SecurityContextBase;
import com.muddassir_92.hotmail.com.runtime.model.Admin;
import com.muddassir_92.hotmail.com.runtime.model.Admin_;
import com.muddassir_92.hotmail.com.runtime.request.AdminFilter;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AdminRepository {
  @PersistenceContext private EntityManager em;

  @Autowired private SecuredBasicRepository securedBasicRepository;

  /**
   * @param adminFilter Object Used to List Admin
   * @param securityContext
   * @return List of Admin
   */
  public List<Admin> listAllAdmins(AdminFilter adminFilter, SecurityContextBase securityContext) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Admin> q = cb.createQuery(Admin.class);
    Root<Admin> r = q.from(Admin.class);
    List<Predicate> preds = new ArrayList<>();
    addAdminPredicate(adminFilter, cb, q, r, preds, securityContext);
    q.select(r).where(preds.toArray(new Predicate[0])).orderBy(cb.desc(r.get(Admin_.creationDate)));
    TypedQuery<Admin> query = em.createQuery(q);

    BasicRepository.addPagination(adminFilter, query);

    return query.getResultList();
  }

  public <T extends Admin> void addAdminPredicate(
      AdminFilter adminFilter,
      CriteriaBuilder cb,
      CommonAbstractCriteria q,
      From<?, T> r,
      List<Predicate> preds,
      SecurityContextBase securityContext) {

    this.securedBasicRepository.addSecuredBasicPredicates(
        adminFilter.getBasicPropertiesFilter(), cb, q, r, preds, securityContext);

    if (adminFilter.getGender() != null && !adminFilter.getGender().isEmpty()) {
      preds.add(r.get(Admin_.gender).in(adminFilter.getGender()));
    }

    if (adminFilter.getBlock() != null && !adminFilter.getBlock().isEmpty()) {
      preds.add(r.get(Admin_.block).in(adminFilter.getBlock()));
    }

    if (adminFilter.getEmail() != null && !adminFilter.getEmail().isEmpty()) {
      preds.add(r.get(Admin_.email).in(adminFilter.getEmail()));
    }
  }

  /**
   * @param adminFilter Object Used to List Admin
   * @param securityContext
   * @return count of Admin
   */
  public Long countAllAdmins(AdminFilter adminFilter, SecurityContextBase securityContext) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Long> q = cb.createQuery(Long.class);
    Root<Admin> r = q.from(Admin.class);
    List<Predicate> preds = new ArrayList<>();
    addAdminPredicate(adminFilter, cb, q, r, preds, securityContext);
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
