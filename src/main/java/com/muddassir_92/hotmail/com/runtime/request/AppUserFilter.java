package com.muddassir_92.hotmail.com.runtime.request;

import com.wizzdi.flexicore.security.request.SecurityUserFilter;
import java.util.Set;

/** Object Used to List AppUser */
public class AppUserFilter extends SecurityUserFilter {

  private Set<String> username;

  /**
   * @return username
   */
  public Set<String> getUsername() {
    return this.username;
  }

  /**
   * @param username username to set
   * @return AppUserFilter
   */
  public <T extends AppUserFilter> T setUsername(Set<String> username) {
    this.username = username;
    return (T) this;
  }
}
