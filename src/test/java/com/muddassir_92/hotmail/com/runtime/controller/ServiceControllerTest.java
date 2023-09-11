package com.muddassir_92.hotmail.com.runtime.controller;

import com.muddassir_92.hotmail.com.runtime.AppInit;
import com.muddassir_92.hotmail.com.runtime.model.Service;
import com.muddassir_92.hotmail.com.runtime.request.LoginRequest;
import com.muddassir_92.hotmail.com.runtime.request.ServiceCreate;
import com.muddassir_92.hotmail.com.runtime.request.ServiceFilter;
import com.muddassir_92.hotmail.com.runtime.request.ServiceUpdate;
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
public class ServiceControllerTest {

  private Service testService;
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
  public void testServiceCreate() {
    ServiceCreate request = new ServiceCreate();

    request.setPrice(10f);

    ResponseEntity<Service> response =
        this.restTemplate.postForEntity("/Service/createService", request, Service.class);
    Assertions.assertEquals(200, response.getStatusCodeValue());
    testService = response.getBody();
    assertService(request, testService);
  }

  @Test
  @Order(2)
  public void testListAllServices() {
    ServiceFilter request = new ServiceFilter();
    ParameterizedTypeReference<PaginationResponse<Service>> t =
        new ParameterizedTypeReference<>() {};

    ResponseEntity<PaginationResponse<Service>> response =
        this.restTemplate.exchange(
            "/Service/getAllServices", HttpMethod.POST, new HttpEntity<>(request), t);
    Assertions.assertEquals(200, response.getStatusCodeValue());
    PaginationResponse<Service> body = response.getBody();
    Assertions.assertNotNull(body);
    List<Service> Services = body.getList();
    Assertions.assertNotEquals(0, Services.size());
    Assertions.assertTrue(Services.stream().anyMatch(f -> f.getId().equals(testService.getId())));
  }

  public void assertService(ServiceCreate request, Service testService) {
    Assertions.assertNotNull(testService);

    if (request.getPrice() != null) {
      Assertions.assertEquals(request.getPrice(), testService.getPrice());
    }
  }

  @Test
  @Order(3)
  public void testServiceUpdate() {
    ServiceUpdate request = new ServiceUpdate().setId(testService.getId());
    ResponseEntity<Service> response =
        this.restTemplate.exchange(
            "/Service/updateService", HttpMethod.PUT, new HttpEntity<>(request), Service.class);
    Assertions.assertEquals(200, response.getStatusCodeValue());
    testService = response.getBody();
    assertService(request, testService);
  }
}
