package com.muddassir_92.hotmail.com.runtime.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.muddassir_92.hotmail.com.runtime.model.AppUser;
import com.wizzdi.flexicore.security.validation.IdValid;

/** Object Used to Update AppUser */
@com.wizzdi.flexicore.security.validation.IdValid.List({
  @IdValid(
      targetField = "appUser",
      field = "id",
      fieldType = com.muddassir_92.hotmail.com.runtime.model.AppUser.class,
      groups = {com.wizzdi.flexicore.security.validation.Update.class})
})
public class AppUserUpdate extends AppUserCreate {

  @JsonIgnore private AppUser appUser;

  private String id;

  /**
   * @return appUser
   */
  @JsonIgnore
  public AppUser getAppUser() {
    return this.appUser;
  }

  /**
   * @param appUser appUser to set
   * @return AppUserUpdate
   */
  public <T extends AppUserUpdate> T setAppUser(AppUser appUser) {
    this.appUser = appUser;
    return (T) this;
  }

  /**
   * @return id
   */
  public String getId() {
    return this.id;
  }

  /**
   * @param id id to set
   * @return AppUserUpdate
   */
  public <T extends AppUserUpdate> T setId(String id) {
    this.id = id;
    return (T) this;
  }
}
