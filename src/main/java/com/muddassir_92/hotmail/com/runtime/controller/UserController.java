package com.muddassir_92.hotmail.com.runtime.controller;

import com.flexicore.annotations.OperationsInside;
import com.flexicore.security.SecurityContextBase;
import com.muddassir_92.hotmail.com.runtime.model.User;
import com.muddassir_92.hotmail.com.runtime.request.UserCreate;
import com.muddassir_92.hotmail.com.runtime.request.UserFilter;
import com.muddassir_92.hotmail.com.runtime.request.UserUpdate;
import com.muddassir_92.hotmail.com.runtime.service.UserService;
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
@RequestMapping("User")
@Tag(name = "User")
@OperationsInside
public class UserController {

  @Autowired private UserService userService;

  @PostMapping("createUser")
  @Operation(summary = "createUser", description = "Creates User")
  public User createUser(
      @Validated(Create.class) @RequestBody UserCreate userCreate,
      @RequestAttribute SecurityContextBase securityContext) {

    return userService.createUser(userCreate, securityContext);
  }

  @PutMapping("updateUser")
  @Operation(summary = "updateUser", description = "Updates User")
  public User updateUser(
      @Validated(Update.class) @RequestBody UserUpdate userUpdate,
      @RequestAttribute SecurityContextBase securityContext) {

    return userService.updateUser(userUpdate, securityContext);
  }

  @PostMapping("getAllUsers")
  @Operation(summary = "getAllUsers", description = "lists Users")
  public PaginationResponse<User> getAllUsers(
      @Valid @RequestBody UserFilter userFilter,
      @RequestAttribute SecurityContextBase securityContext) {

    return userService.getAllUsers(userFilter, securityContext);
  }
}
