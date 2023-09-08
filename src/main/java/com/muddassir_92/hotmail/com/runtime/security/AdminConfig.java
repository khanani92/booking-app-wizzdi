package com.muddassir_92.hotmail.com.runtime.security;

import com.muddassir_92.hotmail.com.runtime.request.AppUserCreate;
import com.muddassir_92.hotmail.com.runtime.request.AppUserFilter;
import com.wizzdi.flexicore.security.interfaces.DefaultUserProvider;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class AdminConfig {

  @Autowired @Lazy
  private com.muddassir_92.hotmail.com.runtime.service.AppUserService appUserService;

  @Value("${admin.username:admin@health-flexicore.com}")
  private String username;

  @Value("${admin.password:#{T(java.util.UUID).randomUUID().toString()}}")
  private String password;

  @Bean
  public DefaultUserProvider<com.muddassir_92.hotmail.com.runtime.model.AppUser>
      defaultSecurityUserProvider() {
    return securityUserCreate ->
        appUserService
            .listAllAppUsers(new AppUserFilter().setUsername(Collections.singleton(username)), null)
            .stream()
            .findFirst()
            .orElseGet(
                () ->
                    appUserService.createAppUser(
                        new AppUserCreate()
                            .setUsername(username)
                            .setPassword(password)
                            .setTenant(securityUserCreate.getTenant())
                            .setIdForCreate(securityUserCreate.getIdForCreate())
                            .setName(securityUserCreate.getName()),
                        null));
  }
}
