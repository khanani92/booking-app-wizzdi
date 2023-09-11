package com.muddassir_92.hotmail.com.runtime.controller;

import com.flexicore.annotations.OperationsInside;
import com.flexicore.security.SecurityContextBase;
import com.muddassir_92.hotmail.com.runtime.model.Service;
import com.muddassir_92.hotmail.com.runtime.request.ServiceCreate;
import com.muddassir_92.hotmail.com.runtime.request.ServiceFilter;
import com.muddassir_92.hotmail.com.runtime.request.ServiceUpdate;
import com.muddassir_92.hotmail.com.runtime.service.ServiceService;
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
@RequestMapping("Service")
@Tag(name = "Service")
@OperationsInside
public class ServiceController {

  @Autowired private ServiceService serviceService;

  @PostMapping("createService")
  @Operation(summary = "createService", description = "Creates Service")
  public Service createService(
      @Validated(Create.class) @RequestBody ServiceCreate serviceCreate,
      @RequestAttribute SecurityContextBase securityContext) {

    return serviceService.createService(serviceCreate, securityContext);
  }

  @PutMapping("updateService")
  @Operation(summary = "updateService", description = "Updates Service")
  public Service updateService(
      @Validated(Update.class) @RequestBody ServiceUpdate serviceUpdate,
      @RequestAttribute SecurityContextBase securityContext) {

    return serviceService.updateService(serviceUpdate, securityContext);
  }

  @PostMapping("getAllServices")
  @Operation(summary = "getAllServices", description = "lists Services")
  public PaginationResponse<Service> getAllServices(
      @Valid @RequestBody ServiceFilter serviceFilter,
      @RequestAttribute SecurityContextBase securityContext) {

    return serviceService.getAllServices(serviceFilter, securityContext);
  }
}
