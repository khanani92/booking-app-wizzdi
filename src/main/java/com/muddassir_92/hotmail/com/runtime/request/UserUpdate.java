package com.muddassir_92.hotmail.com.runtime.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.muddassir_92.hotmail.com.runtime.model.User;
import com.wizzdi.flexicore.security.validation.IdValid;

/** Object Used to Update User */
@com.wizzdi.flexicore.security.validation.IdValid.List({
  @IdValid(
      targetField = "user",
      field = "id",
      fieldType = com.muddassir_92.hotmail.com.runtime.model.User.class,
      groups = {com.wizzdi.flexicore.security.validation.Update.class})
})
public class UserUpdate extends UserCreate {

  private String id;

  @JsonIgnore private User user;

  /**
   * @return id
   */
  public String getId() {
    return this.id;
  }

  /**
   * @param id id to set
   * @return UserUpdate
   */
  public <T extends UserUpdate> T setId(String id) {
    this.id = id;
    return (T) this;
  }

  /**
   * @return user
   */
  @JsonIgnore
  public User getUser() {
    return this.user;
  }

  /**
   * @param user user to set
   * @return UserUpdate
   */
  public <T extends UserUpdate> T setUser(User user) {
    this.user = user;
    return (T) this;
  }
}
