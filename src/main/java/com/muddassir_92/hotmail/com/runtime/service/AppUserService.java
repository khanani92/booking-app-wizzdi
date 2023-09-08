package com.muddassir_92.hotmail.com.runtime.service;

import com.flexicore.model.Baseclass;
import com.flexicore.model.Basic;
import com.flexicore.security.SecurityContextBase;
import com.muddassir_92.hotmail.com.runtime.data.AppUserRepository;
import com.muddassir_92.hotmail.com.runtime.model.AppUser;
import com.muddassir_92.hotmail.com.runtime.request.AppUserCreate;
import com.muddassir_92.hotmail.com.runtime.request.AppUserFilter;
import com.muddassir_92.hotmail.com.runtime.request.AppUserUpdate;
import com.wizzdi.flexicore.security.response.PaginationResponse;
import com.wizzdi.flexicore.security.service.SecurityUserService;
import jakarta.persistence.metamodel.SingularAttribute;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AppUserService {

  @Autowired private AppUserRepository repository;

  @Autowired private SecurityUserService securityUserService;

  @Autowired private PasswordEncoder passwordEncoder;

  /**
   * @param appUserCreate Object Used to Create AppUser
   * @param securityContext
   * @return created AppUser
   */
  public AppUser createAppUser(AppUserCreate appUserCreate, SecurityContextBase securityContext) {
    AppUser appUser = createAppUserNoMerge(appUserCreate, securityContext);
    this.repository.merge(appUser);
    return appUser;
  }

  /**
   * @param appUserCreate Object Used to Create AppUser
   * @param securityContext
   * @return created AppUser unmerged
   */
  public AppUser createAppUserNoMerge(
      AppUserCreate appUserCreate, SecurityContextBase securityContext) {
    AppUser appUser = new AppUser();
    appUser.setId(Baseclass.getBase64ID());
    updateAppUserNoMerge(appUser, appUserCreate);

    return appUser;
  }

  /**
   * @param appUserCreate Object Used to Create AppUser
   * @param appUser
   * @return if appUser was updated
   */
  public boolean updateAppUserNoMerge(AppUser appUser, AppUserCreate appUserCreate) {
    boolean update = securityUserService.updateSecurityUserNoMerge(appUserCreate, appUser);

    if (appUserCreate.getPassword() != null
        && (!passwordEncoder.matches(appUserCreate.getPassword(), appUser.getPassword()))) {
      appUser.setPassword(passwordEncoder.encode(appUserCreate.getPassword()));
      update = true;
    }

    if (appUserCreate.getUsername() != null
        && (!appUserCreate.getUsername().equals(appUser.getUsername()))) {
      appUser.setUsername(appUserCreate.getUsername());
      update = true;
    }

    return update;
  }

  /**
   * @param appUserUpdate
   * @param securityContext
   * @return appUser
   */
  public AppUser updateAppUser(AppUserUpdate appUserUpdate, SecurityContextBase securityContext) {
    AppUser appUser = appUserUpdate.getAppUser();
    if (updateAppUserNoMerge(appUser, appUserUpdate)) {
      this.repository.merge(appUser);
    }
    return appUser;
  }

  /**
   * @param appUserFilter Object Used to List AppUser
   * @param securityContext
   * @return PaginationResponse<AppUser> containing paging information for AppUser
   */
  public PaginationResponse<AppUser> getAllAppUsers(
      AppUserFilter appUserFilter, SecurityContextBase securityContext) {
    List<AppUser> list = listAllAppUsers(appUserFilter, securityContext);
    long count = this.repository.countAllAppUsers(appUserFilter, securityContext);
    return new PaginationResponse<>(list, appUserFilter.getPageSize(), count);
  }

  /**
   * @param appUserFilter Object Used to List AppUser
   * @param securityContext
   * @return List of AppUser
   */
  public List<AppUser> listAllAppUsers(
      AppUserFilter appUserFilter, SecurityContextBase securityContext) {
    return this.repository.listAllAppUsers(appUserFilter, securityContext);
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
