package com.muddassir_92.hotmail.com.runtime.controller;

import com.flexicore.annotations.OperationsInside;
import com.flexicore.security.SecurityContextBase;
import com.muddassir_92.hotmail.com.runtime.model.ServiceProvider;
import com.muddassir_92.hotmail.com.runtime.request.ServiceProviderCreate;
import com.muddassir_92.hotmail.com.runtime.request.ServiceProviderFilter;
import com.muddassir_92.hotmail.com.runtime.request.ServiceProviderUpdate;
import com.muddassir_92.hotmail.com.runtime.service.ServiceProviderService;
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
@RequestMapping("ServiceProvider")
@Tag(name = "ServiceProvider")
@OperationsInside
public class ServiceProviderController {

  @Autowired private ServiceProviderService serviceProviderService;

  @PostMapping("createServiceProvider")
  @Operation(summary = "createServiceProvider", description = "Creates ServiceProvider")
  public ServiceProvider createServiceProvider(
      @Validated(Create.class) @RequestBody ServiceProviderCreate serviceProviderCreate,
      @RequestAttribute SecurityContextBase securityContext) {

    return serviceProviderService.createServiceProvider(serviceProviderCreate, securityContext);
  }

  @PutMapping("updateServiceProvider")
  @Operation(summary = "updateServiceProvider", description = "Updates ServiceProvider")
  public ServiceProvider updateServiceProvider(
      @Validated(Update.class) @RequestBody ServiceProviderUpdate serviceProviderUpdate,
      @RequestAttribute SecurityContextBase securityContext) {

    return serviceProviderService.updateServiceProvider(serviceProviderUpdate, securityContext);
  }

  @PostMapping("getAllServiceProviders")
  @Operation(summary = "getAllServiceProviders", description = "lists ServiceProviders")
  public PaginationResponse<ServiceProvider> getAllServiceProviders(
      @Valid @RequestBody ServiceProviderFilter serviceProviderFilter,
      @RequestAttribute SecurityContextBase securityContext) {

    return serviceProviderService.getAllServiceProviders(serviceProviderFilter, securityContext);
  }
}
