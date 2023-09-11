package com.muddassir_92.hotmail.com.runtime.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.muddassir_92.hotmail.com.runtime.model.Admin;
import com.wizzdi.flexicore.security.validation.IdValid;

/** Object Used to Update Admin */
@com.wizzdi.flexicore.security.validation.IdValid.List({
  @IdValid(
      targetField = "admin",
      field = "id",
      fieldType = com.muddassir_92.hotmail.com.runtime.model.Admin.class,
      groups = {com.wizzdi.flexicore.security.validation.Update.class})
})
public class AdminUpdate extends AdminCreate {

  @JsonIgnore private Admin admin;

  private String id;

  /**
   * @return admin
   */
  @JsonIgnore
  public Admin getAdmin() {
    return this.admin;
  }

  /**
   * @param admin admin to set
   * @return AdminUpdate
   */
  public <T extends AdminUpdate> T setAdmin(Admin admin) {
    this.admin = admin;
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
   * @return AdminUpdate
   */
  public <T extends AdminUpdate> T setId(String id) {
    this.id = id;
    return (T) this;
  }
}
