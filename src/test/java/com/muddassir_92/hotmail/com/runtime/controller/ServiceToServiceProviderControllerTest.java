package com.muddassir_92.hotmail.com.runtime.controller;

import com.muddassir_92.hotmail.com.runtime.AppInit;
import com.muddassir_92.hotmail.com.runtime.model.Service;
import com.muddassir_92.hotmail.com.runtime.model.ServiceProvider;
import com.muddassir_92.hotmail.com.runtime.model.ServiceToServiceProvider;
import com.muddassir_92.hotmail.com.runtime.request.LoginRequest;
import com.muddassir_92.hotmail.com.runtime.request.ServiceToServiceProviderCreate;
import com.muddassir_92.hotmail.com.runtime.request.ServiceToServiceProviderFilter;
import com.muddassir_92.hotmail.com.runtime.request.ServiceToServiceProviderUpdate;
import com.wizzdi.flexicore.security.response.PaginationResponse;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.annotation.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AppInit.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
public class ServiceToServiceProviderControllerTest {

  private ServiceToServiceProvider testServiceToServiceProvider;
  @Autowired private TestRestTemplate restTemplate;

  @Autowired private ServiceProvider serviceProvider;

  @Autowired private Service service;

  @BeforeAll
  private void init() {
    ResponseEntity<Object> authenticationResponse =
        this.restTemplate.postForEntity(
            "/login",
            new LoginRequest().setUsername("admin@flexicore.com").setPassword("admin"),
            Object.class);
    String authenticationKey =
        authenticationResponse.getHeaders().get(HttpHeaders.AUTHORIZATION).stream()
            .findFirst()
            .orElse(null);
    restTemplate
        .getRestTemplate()
        .setInterceptors(
            Collections.singletonList(
                (request, body, execution) -> {
                  request.getHeaders().add("Authorization", "Bearer " + authenticationKey);
                  return execution.execute(request, body);
                }));
  }

  @Test
  @Order(1)
  public void testServiceToServiceProviderCreate() {
    ServiceToServiceProviderCreate request = new ServiceToServiceProviderCreate();

    request.setServiceId(this.service.getId());

    request.setServiceProviderId(this.serviceProvider.getId());

    ResponseEntity<ServiceToServiceProvider> response =
        this.restTemplate.postForEntity(
            "/ServiceToServiceProvider/createServiceToServiceProvider",
            request,
            ServiceToServiceProvider.class);
    Assertions.assertEquals(200, response.getStatusCodeValue());
    testServiceToServiceProvider = response.getBody();
    assertServiceToServiceProvider(request, testServiceToServiceProvider);
  }

  @Test
  @Order(2)
  public void testListAllServiceToServiceProviders() {
    ServiceToServiceProviderFilter request = new ServiceToServiceProviderFilter();
    ParameterizedTypeReference<PaginationResponse<ServiceToServiceProvider>> t =
        new ParameterizedTypeReference<>() {};

    ResponseEntity<PaginationResponse<ServiceToServiceProvider>> response =
        this.restTemplate.exchange(
            "/ServiceToServiceProvider/getAllServiceToServiceProviders",
            HttpMethod.POST,
            new HttpEntity<>(request),
            t);
    Assertions.assertEquals(200, response.getStatusCodeValue());
    PaginationResponse<ServiceToServiceProvider> body = response.getBody();
    Assertions.assertNotNull(body);
    List<ServiceToServiceProvider> ServiceToServiceProviders = body.getList();
    Assertions.assertNotEquals(0, ServiceToServiceProviders.size());
    Assertions.assertTrue(
        ServiceToServiceProviders.stream()
            .anyMatch(f -> f.getId().equals(testServiceToServiceProvider.getId())));
  }

  public void assertServiceToServiceProvider(
      ServiceToServiceProviderCreate request,
      ServiceToServiceProvider testServiceToServiceProvider) {
    Assertions.assertNotNull(testServiceToServiceProvider);

    if (request.getServiceId() != null) {

      Assertions.assertNotNull(testServiceToServiceProvider.getService());
      Assertions.assertEquals(
          request.getServiceId(), testServiceToServiceProvider.getService().getId());
    }

    if (request.getServiceProviderId() != null) {

      Assertions.assertNotNull(testServiceToServiceProvider.getServiceProvider());
      Assertions.assertEquals(
          request.getServiceProviderId(),
          testServiceToServiceProvider.getServiceProvider().getId());
    }
  }

  @Test
  @Order(3)
  public void testServiceToServiceProviderUpdate() {
    ServiceToServiceProviderUpdate request =
        new ServiceToServiceProviderUpdate().setId(testServiceToServiceProvider.getId());
    ResponseEntity<ServiceToServiceProvider> response =
        this.restTemplate.exchange(
            "/ServiceToServiceProvider/updateServiceToServiceProvider",
            HttpMethod.PUT,
            new HttpEntity<>(request),
            ServiceToServiceProvider.class);
    Assertions.assertEquals(200, response.getStatusCodeValue());
    testServiceToServiceProvider = response.getBody();
    assertServiceToServiceProvider(request, testServiceToServiceProvider);
  }
}
