package com.muddassir_92.hotmail.com.runtime.request;

import com.wizzdi.flexicore.security.request.BasicPropertiesFilter;
import com.wizzdi.flexicore.security.request.PaginationFilter;
import java.util.Set;

/** Object Used to List Admin */
public class AdminFilter extends PaginationFilter {

  private BasicPropertiesFilter basicPropertiesFilter;

  private Set<Boolean> block;

  private Set<String> email;

  private Set<String> gender;

  /**
   * @return basicPropertiesFilter
   */
  public BasicPropertiesFilter getBasicPropertiesFilter() {
    return this.basicPropertiesFilter;
  }

  /**
   * @param basicPropertiesFilter basicPropertiesFilter to set
   * @return AdminFilter
   */
  public <T extends AdminFilter> T setBasicPropertiesFilter(
      BasicPropertiesFilter basicPropertiesFilter) {
    this.basicPropertiesFilter = basicPropertiesFilter;
    return (T) this;
  }

  /**
   * @return block
   */
  public Set<Boolean> getBlock() {
    return this.block;
  }

  /**
   * @param block block to set
   * @return AdminFilter
   */
  public <T extends AdminFilter> T setBlock(Set<Boolean> block) {
    this.block = block;
    return (T) this;
  }

  /**
   * @return email
   */
  public Set<String> getEmail() {
    return this.email;
  }

  /**
   * @param email email to set
   * @return AdminFilter
   */
  public <T extends AdminFilter> T setEmail(Set<String> email) {
    this.email = email;
    return (T) this;
  }

  /**
   * @return gender
   */
  public Set<String> getGender() {
    return this.gender;
  }

  /**
   * @param gender gender to set
   * @return AdminFilter
   */
  public <T extends AdminFilter> T setGender(Set<String> gender) {
    this.gender = gender;
    return (T) this;
  }
}
