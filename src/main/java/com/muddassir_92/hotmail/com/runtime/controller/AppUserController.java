package com.muddassir_92.hotmail.com.runtime.controller;

import com.flexicore.annotations.OperationsInside;
import com.flexicore.security.SecurityContextBase;
import com.muddassir_92.hotmail.com.runtime.model.AppUser;
import com.muddassir_92.hotmail.com.runtime.request.AppUserCreate;
import com.muddassir_92.hotmail.com.runtime.request.AppUserFilter;
import com.muddassir_92.hotmail.com.runtime.request.AppUserUpdate;
import com.muddassir_92.hotmail.com.runtime.service.AppUserService;
import com.wizzdi.flexicore.security.response.PaginationResponse;
import com.wizzdi.flexicore.security.validation.Create;
import com.wizzdi.flexicore.security.validation.Update;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("AppUser")
@Tag(name = "AppUser")
@OperationsInside
public class AppUserController {

  @Autowired private AppUserService appUserService;

  @PostMapping("createAppUser")
  @Operation(summary = "createAppUser", description = "Creates AppUser")
  public AppUser createAppUser(
      @Validated(Create.class) @RequestBody AppUserCreate appUserCreate,
      @RequestAttribute SecurityContextBase securityContext) {

    return appUserService.createAppUser(appUserCreate, securityContext);
  }

  @PutMapping("updateAppUser")
  @Operation(summary = "updateAppUser", description = "Updates AppUser")
  public AppUser updateAppUser(
      @Validated(Update.class) @RequestBody AppUserUpdate appUserUpdate,
      @RequestAttribute SecurityContextBase securityContext) {

    return appUserService.updateAppUser(appUserUpdate, securityContext);
  }

  @PostMapping("getAllAppUsers")
  @Operation(summary = "getAllAppUsers", description = "lists AppUsers")
  public PaginationResponse<AppUser> getAllAppUsers(
      @Valid @RequestBody AppUserFilter appUserFilter,
      @RequestAttribute SecurityContextBase securityContext) {

    return appUserService.getAllAppUsers(appUserFilter, securityContext);
  }
}
