package com.muddassir_92.hotmail.com.runtime.controller;

import com.flexicore.annotations.OperationsInside;
import com.flexicore.security.SecurityContextBase;
import com.muddassir_92.hotmail.com.runtime.model.Admin;
import com.muddassir_92.hotmail.com.runtime.request.AdminCreate;
import com.muddassir_92.hotmail.com.runtime.request.AdminFilter;
import com.muddassir_92.hotmail.com.runtime.request.AdminUpdate;
import com.muddassir_92.hotmail.com.runtime.service.AdminService;
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
@RequestMapping("Admin")
@Tag(name = "Admin")
@OperationsInside
public class AdminController {

  @Autowired private AdminService adminService;

  @PostMapping("createAdmin")
  @Operation(summary = "createAdmin", description = "Creates Admin")
  public Admin createAdmin(
      @Validated(Create.class) @RequestBody AdminCreate adminCreate,
      @RequestAttribute SecurityContextBase securityContext) {

    return adminService.createAdmin(adminCreate, securityContext);
  }

  @PutMapping("updateAdmin")
  @Operation(summary = "updateAdmin", description = "Updates Admin")
  public Admin updateAdmin(
      @Validated(Update.class) @RequestBody AdminUpdate adminUpdate,
      @RequestAttribute SecurityContextBase securityContext) {

    return adminService.updateAdmin(adminUpdate, securityContext);
  }

  @PostMapping("getAllAdmins")
  @Operation(summary = "getAllAdmins", description = "lists Admins")
  public PaginationResponse<Admin> getAllAdmins(
      @Valid @RequestBody AdminFilter adminFilter,
      @RequestAttribute SecurityContextBase securityContext) {

    return adminService.getAllAdmins(adminFilter, securityContext);
  }
}
