package com.muddassir_92.hotmail.com.runtime.controller;

import com.flexicore.model.SecurityTenant;
import com.flexicore.model.TenantToUser;
import com.flexicore.security.SecurityContextBase;
import com.muddassir_92.hotmail.com.runtime.model.AppUser;
import com.muddassir_92.hotmail.com.runtime.request.AppUserCreate;
import com.muddassir_92.hotmail.com.runtime.request.AppUserFilter;
import com.muddassir_92.hotmail.com.runtime.service.AppUserService;
import com.wizzdi.flexicore.security.request.TenantToUserCreate;
import com.wizzdi.flexicore.security.response.TenantAndUserInit;
import com.wizzdi.flexicore.security.service.TenantToUserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Tag(name = "RegisterApi")
@RestController
public class RegisterApi {

  private final AppUserService appUserService;

  private final TenantToUserService tenantToUserService;
  private final TenantAndUserInit tenantAndUserInit;
  private final SecurityContextBase adminSecurityContext;

  public RegisterApi(
      AppUserService appUserService,
      TenantToUserService tenantToUserService,
      @Lazy TenantAndUserInit tenantAndUserInit,
      @Lazy @Qualifier("adminSecurityContext") SecurityContextBase adminSecurityContext) {
    this.appUserService = appUserService;

    this.tenantToUserService = tenantToUserService;
    this.tenantAndUserInit = tenantAndUserInit;
    this.adminSecurityContext = adminSecurityContext;
  }

  @SecurityRequirements
  @PostMapping("register")
  public AppUser register(
      @RequestBody
          @org.springframework.validation.annotation.Validated(
              com.wizzdi.flexicore.security.validation.Create.class)
          AppUserCreate appUserCreate) {
    appUserService
        .listAllAppUsers(
            new AppUserFilter().setUsername(Collections.singleton(appUserCreate.getUsername())),
            null)
        .stream()
        .findAny()
        .ifPresent(
            f -> {
              throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username already exists");
            });
    AppUser appUser = appUserService.createAppUser(appUserCreate, null);

    SecurityTenant defaultTenant = tenantAndUserInit.getDefaultTenant();
    appUser.setCreator(appUser);
    appUser.setTenant(defaultTenant);
    appUserService.merge(appUser);
    TenantToUserCreate tenantToUserCreate =
        new TenantToUserCreate()
            .setDefaultTenant(true)
            .setSecurityUser(appUser)
            .setTenant(defaultTenant);
    TenantToUser link =
        tenantToUserService.createTenantToUserNoMerge(tenantToUserCreate, adminSecurityContext);
    link.setUser(appUser);
    tenantToUserService.merge(link);

    return appUser;
  }
}
