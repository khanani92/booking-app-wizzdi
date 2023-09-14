package com.muddassir_92.hotmail.com.runtime.controller;

import com.muddassir_92.hotmail.com.runtime.AppInit;
import com.muddassir_92.hotmail.com.runtime.model.ServiceProvider;
import com.muddassir_92.hotmail.com.runtime.request.LoginRequest;
import com.muddassir_92.hotmail.com.runtime.request.ServiceProviderCreate;
import com.muddassir_92.hotmail.com.runtime.request.ServiceProviderFilter;
import com.muddassir_92.hotmail.com.runtime.request.ServiceProviderUpdate;
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
public class ServiceProviderControllerTest {

  private ServiceProvider testServiceProvider;
  @Autowired private TestRestTemplate restTemplate;

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
  public void testServiceProviderCreate() {
    ServiceProviderCreate request = new ServiceProviderCreate();

    request.setBlock(true);

    request.setAddress("test-string");

    request.setGender("test-string");

    request.setPhoneNumber("test-string");

    request.setEmail("test-string");

    request.setPassword("test-string");

    ResponseEntity<ServiceProvider> response =
        this.restTemplate.postForEntity(
            "/ServiceProvider/createServiceProvider", request, ServiceProvider.class);
    Assertions.assertEquals(200, response.getStatusCodeValue());
    testServiceProvider = response.getBody();
    assertServiceProvider(request, testServiceProvider);
  }

  @Test
  @Order(2)
  public void testListAllServiceProviders() {
    ServiceProviderFilter request = new ServiceProviderFilter();
    ParameterizedTypeReference<PaginationResponse<ServiceProvider>> t =
        new ParameterizedTypeReference<>() {};

    ResponseEntity<PaginationResponse<ServiceProvider>> response =
        this.restTemplate.exchange(
            "/ServiceProvider/getAllServiceProviders",
            HttpMethod.POST,
            new HttpEntity<>(request),
            t);
    Assertions.assertEquals(200, response.getStatusCodeValue());
    PaginationResponse<ServiceProvider> body = response.getBody();
    Assertions.assertNotNull(body);
    List<ServiceProvider> ServiceProviders = body.getList();
    Assertions.assertNotEquals(0, ServiceProviders.size());
    Assertions.assertTrue(
        ServiceProviders.stream().anyMatch(f -> f.getId().equals(testServiceProvider.getId())));
  }

  public void assertServiceProvider(
      ServiceProviderCreate request, ServiceProvider testServiceProvider) {
    Assertions.assertNotNull(testServiceProvider);

    if (request.getBlock() != null) {
      Assertions.assertEquals(request.getBlock(), testServiceProvider.isBlock());
    }

    if (request.getAddress() != null) {
      Assertions.assertEquals(request.getAddress(), testServiceProvider.getAddress());
    }

    if (request.getGender() != null) {
      Assertions.assertEquals(request.getGender(), testServiceProvider.getGender());
    }

    if (request.getPhoneNumber() != null) {
      Assertions.assertEquals(request.getPhoneNumber(), testServiceProvider.getPhoneNumber());
    }

    if (request.getEmail() != null) {
      Assertions.assertEquals(request.getEmail(), testServiceProvider.getEmail());
    }

    if (request.getPassword() != null) {
      Assertions.assertEquals(request.getPassword(), testServiceProvider.getPassword());
    }
  }

  @Test
  @Order(3)
  public void testServiceProviderUpdate() {
    ServiceProviderUpdate request = new ServiceProviderUpdate().setId(testServiceProvider.getId());
    ResponseEntity<ServiceProvider> response =
        this.restTemplate.exchange(
            "/ServiceProvider/updateServiceProvider",
            HttpMethod.PUT,
            new HttpEntity<>(request),
            ServiceProvider.class);
    Assertions.assertEquals(200, response.getStatusCodeValue());
    testServiceProvider = response.getBody();
    assertServiceProvider(request, testServiceProvider);
  }
}
