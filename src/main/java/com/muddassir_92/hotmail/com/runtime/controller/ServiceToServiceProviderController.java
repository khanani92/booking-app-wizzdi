package com.muddassir_92.hotmail.com.runtime.controller;

import com.flexicore.annotations.OperationsInside;
import com.flexicore.security.SecurityContextBase;
import com.muddassir_92.hotmail.com.runtime.model.ServiceToServiceProvider;
import com.muddassir_92.hotmail.com.runtime.request.ServiceToServiceProviderCreate;
import com.muddassir_92.hotmail.com.runtime.request.ServiceToServiceProviderFilter;
import com.muddassir_92.hotmail.com.runtime.request.ServiceToServiceProviderUpdate;
import com.muddassir_92.hotmail.com.runtime.service.ServiceToServiceProviderService;
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
@RequestMapping("ServiceToServiceProvider")
@Tag(name = "ServiceToServiceProvider")
@OperationsInside
public class ServiceToServiceProviderController {

  @Autowired private ServiceToServiceProviderService serviceToServiceProviderService;

  @PostMapping("createServiceToServiceProvider")
  @Operation(
      summary = "createServiceToServiceProvider",
      description = "Creates ServiceToServiceProvider")
  public ServiceToServiceProvider createServiceToServiceProvider(
      @Validated(Create.class) @RequestBody
          ServiceToServiceProviderCreate serviceToServiceProviderCreate,
      @RequestAttribute SecurityContextBase securityContext) {

    return serviceToServiceProviderService.createServiceToServiceProvider(
        serviceToServiceProviderCreate, securityContext);
  }

  @PutMapping("updateServiceToServiceProvider")
  @Operation(
      summary = "updateServiceToServiceProvider",
      description = "Updates ServiceToServiceProvider")
  public ServiceToServiceProvider updateServiceToServiceProvider(
      @Validated(Update.class) @RequestBody
          ServiceToServiceProviderUpdate serviceToServiceProviderUpdate,
      @RequestAttribute SecurityContextBase securityContext) {

    return serviceToServiceProviderService.updateServiceToServiceProvider(
        serviceToServiceProviderUpdate, securityContext);
  }

  @PostMapping("getAllServiceToServiceProviders")
  @Operation(
      summary = "getAllServiceToServiceProviders",
      description = "lists ServiceToServiceProviders")
  public PaginationResponse<ServiceToServiceProvider> getAllServiceToServiceProviders(
      @Valid @RequestBody ServiceToServiceProviderFilter serviceToServiceProviderFilter,
      @RequestAttribute SecurityContextBase securityContext) {

    return serviceToServiceProviderService.getAllServiceToServiceProviders(
        serviceToServiceProviderFilter, securityContext);
  }
}
