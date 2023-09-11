package com.muddassir_92.hotmail.com.runtime.service;

import com.flexicore.model.Baseclass;
import com.flexicore.model.Basic;
import com.flexicore.security.SecurityContextBase;
import com.muddassir_92.hotmail.com.runtime.data.UserRepository;
import com.muddassir_92.hotmail.com.runtime.model.User;
import com.muddassir_92.hotmail.com.runtime.request.UserCreate;
import com.muddassir_92.hotmail.com.runtime.request.UserFilter;
import com.muddassir_92.hotmail.com.runtime.request.UserUpdate;
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
public class UserService {

  @Autowired private UserRepository repository;

  @Autowired private BasicService basicService;

  @Autowired private PasswordEncoder passwordEncoder;

  /**
   * @param userCreate Object Used to Create User
   * @param securityContext
   * @return created User
   */
  public User createUser(UserCreate userCreate, SecurityContextBase securityContext) {
    User user = createUserNoMerge(userCreate, securityContext);
    this.repository.merge(user);
    return user;
  }

  /**
   * @param userCreate Object Used to Create User
   * @param securityContext
   * @return created User unmerged
   */
  public User createUserNoMerge(UserCreate userCreate, SecurityContextBase securityContext) {
    User user = new User();
    user.setId(UUID.randomUUID().toString());
    updateUserNoMerge(user, userCreate);

    BaseclassService.createSecurityObjectNoMerge(user, securityContext);

    return user;
  }

  /**
   * @param userCreate Object Used to Create User
   * @param user
   * @return if user was updated
   */
  public boolean updateUserNoMerge(User user, UserCreate userCreate) {
    boolean update = basicService.updateBasicNoMerge(userCreate, user);

    if (userCreate.getPassword() != null
        && (!passwordEncoder.matches(userCreate.getPassword(), user.getPassword()))) {
      user.setPassword(passwordEncoder.encode(userCreate.getPassword()));
      update = true;
    }

    if (userCreate.getBlock() != null && (!userCreate.getBlock().equals(user.isBlock()))) {
      user.setBlock(userCreate.getBlock());
      update = true;
    }

    if (userCreate.getProfilePicture() != null
        && (user.getProfilePicture() == null
            || !userCreate.getProfilePicture().getId().equals(user.getProfilePicture().getId()))) {
      user.setProfilePicture(userCreate.getProfilePicture());
      update = true;
    }

    if (userCreate.getGender() != null && (!userCreate.getGender().equals(user.getGender()))) {
      user.setGender(userCreate.getGender());
      update = true;
    }

    if (userCreate.getEmail() != null && (!userCreate.getEmail().equals(user.getEmail()))) {
      user.setEmail(userCreate.getEmail());
      update = true;
    }

    return update;
  }

  /**
   * @param userUpdate
   * @param securityContext
   * @return user
   */
  public User updateUser(UserUpdate userUpdate, SecurityContextBase securityContext) {
    User user = userUpdate.getUser();
    if (updateUserNoMerge(user, userUpdate)) {
      this.repository.merge(user);
    }
    return user;
  }

  /**
   * @param userFilter Object Used to List User
   * @param securityContext
   * @return PaginationResponse<User> containing paging information for User
   */
  public PaginationResponse<User> getAllUsers(
      UserFilter userFilter, SecurityContextBase securityContext) {
    List<User> list = listAllUsers(userFilter, securityContext);
    long count = this.repository.countAllUsers(userFilter, securityContext);
    return new PaginationResponse<>(list, userFilter.getPageSize(), count);
  }

  /**
   * @param userFilter Object Used to List User
   * @param securityContext
   * @return List of User
   */
  public List<User> listAllUsers(UserFilter userFilter, SecurityContextBase securityContext) {
    return this.repository.listAllUsers(userFilter, securityContext);
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
