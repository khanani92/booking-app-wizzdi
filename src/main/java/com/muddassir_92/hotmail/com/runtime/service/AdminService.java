package com.muddassir_92.hotmail.com.runtime.service;

import com.flexicore.model.Baseclass;
import com.flexicore.model.Basic;
import com.flexicore.security.SecurityContextBase;
import com.muddassir_92.hotmail.com.runtime.data.AdminRepository;
import com.muddassir_92.hotmail.com.runtime.model.Admin;
import com.muddassir_92.hotmail.com.runtime.request.AdminCreate;
import com.muddassir_92.hotmail.com.runtime.request.AdminFilter;
import com.muddassir_92.hotmail.com.runtime.request.AdminUpdate;
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
public class AdminService {

  @Autowired private AdminRepository repository;

  @Autowired private BasicService basicService;

  @Autowired private PasswordEncoder passwordEncoder;

  /**
   * @param adminCreate Object Used to Create Admin
   * @param securityContext
   * @return created Admin
   */
  public Admin createAdmin(AdminCreate adminCreate, SecurityContextBase securityContext) {
    Admin admin = createAdminNoMerge(adminCreate, securityContext);
    this.repository.merge(admin);
    return admin;
  }

  /**
   * @param adminCreate Object Used to Create Admin
   * @param securityContext
   * @return created Admin unmerged
   */
  public Admin createAdminNoMerge(AdminCreate adminCreate, SecurityContextBase securityContext) {
    Admin admin = new Admin();
    admin.setId(UUID.randomUUID().toString());
    updateAdminNoMerge(admin, adminCreate);

    BaseclassService.createSecurityObjectNoMerge(admin, securityContext);

    return admin;
  }

  /**
   * @param adminCreate Object Used to Create Admin
   * @param admin
   * @return if admin was updated
   */
  public boolean updateAdminNoMerge(Admin admin, AdminCreate adminCreate) {
    boolean update = basicService.updateBasicNoMerge(adminCreate, admin);

    if (adminCreate.getPassword() != null
        && (!passwordEncoder.matches(adminCreate.getPassword(), admin.getPassword()))) {
      admin.setPassword(passwordEncoder.encode(adminCreate.getPassword()));
      update = true;
    }

    if (adminCreate.getBlock() != null && (!adminCreate.getBlock().equals(admin.isBlock()))) {
      admin.setBlock(adminCreate.getBlock());
      update = true;
    }

    if (adminCreate.getEmail() != null && (!adminCreate.getEmail().equals(admin.getEmail()))) {
      admin.setEmail(adminCreate.getEmail());
      update = true;
    }

    if (adminCreate.getProfilePicture() != null
        && (admin.getProfilePicture() == null
            || !adminCreate
                .getProfilePicture()
                .getId()
                .equals(admin.getProfilePicture().getId()))) {
      admin.setProfilePicture(adminCreate.getProfilePicture());
      update = true;
    }

    return update;
  }

  /**
   * @param adminUpdate
   * @param securityContext
   * @return admin
   */
  public Admin updateAdmin(AdminUpdate adminUpdate, SecurityContextBase securityContext) {
    Admin admin = adminUpdate.getAdmin();
    if (updateAdminNoMerge(admin, adminUpdate)) {
      this.repository.merge(admin);
    }
    return admin;
  }

  /**
   * @param adminFilter Object Used to List Admin
   * @param securityContext
   * @return PaginationResponse<Admin> containing paging information for Admin
   */
  public PaginationResponse<Admin> getAllAdmins(
      AdminFilter adminFilter, SecurityContextBase securityContext) {
    List<Admin> list = listAllAdmins(adminFilter, securityContext);
    long count = this.repository.countAllAdmins(adminFilter, securityContext);
    return new PaginationResponse<>(list, adminFilter.getPageSize(), count);
  }

  /**
   * @param adminFilter Object Used to List Admin
   * @param securityContext
   * @return List of Admin
   */
  public List<Admin> listAllAdmins(AdminFilter adminFilter, SecurityContextBase securityContext) {
    return this.repository.listAllAdmins(adminFilter, securityContext);
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
