package com.muddassir_92.hotmail.com.runtime.controller;

import com.muddassir_92.hotmail.com.runtime.AppInit;
import com.muddassir_92.hotmail.com.runtime.model.Admin;
import com.muddassir_92.hotmail.com.runtime.request.AdminCreate;
import com.muddassir_92.hotmail.com.runtime.request.AdminFilter;
import com.muddassir_92.hotmail.com.runtime.request.AdminUpdate;
import com.muddassir_92.hotmail.com.runtime.request.LoginRequest;
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
public class AdminControllerTest {

  private Admin testAdmin;
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
  public void testAdminCreate() {
    AdminCreate request = new AdminCreate();

    request.setPassword("test-string");

    request.setBlock(true);

    request.setEmail("test-string");

    ResponseEntity<Admin> response =
        this.restTemplate.postForEntity("/Admin/createAdmin", request, Admin.class);
    Assertions.assertEquals(200, response.getStatusCodeValue());
    testAdmin = response.getBody();
    assertAdmin(request, testAdmin);
  }

  @Test
  @Order(2)
  public void testListAllAdmins() {
    AdminFilter request = new AdminFilter();
    ParameterizedTypeReference<PaginationResponse<Admin>> t = new ParameterizedTypeReference<>() {};

    ResponseEntity<PaginationResponse<Admin>> response =
        this.restTemplate.exchange(
            "/Admin/getAllAdmins", HttpMethod.POST, new HttpEntity<>(request), t);
    Assertions.assertEquals(200, response.getStatusCodeValue());
    PaginationResponse<Admin> body = response.getBody();
    Assertions.assertNotNull(body);
    List<Admin> Admins = body.getList();
    Assertions.assertNotEquals(0, Admins.size());
    Assertions.assertTrue(Admins.stream().anyMatch(f -> f.getId().equals(testAdmin.getId())));
  }

  public void assertAdmin(AdminCreate request, Admin testAdmin) {
    Assertions.assertNotNull(testAdmin);

    if (request.getPassword() != null) {
      Assertions.assertEquals(request.getPassword(), testAdmin.getPassword());
    }

    if (request.getBlock() != null) {
      Assertions.assertEquals(request.getBlock(), testAdmin.isBlock());
    }

    if (request.getEmail() != null) {
      Assertions.assertEquals(request.getEmail(), testAdmin.getEmail());
    }
  }

  @Test
  @Order(3)
  public void testAdminUpdate() {
    AdminUpdate request = new AdminUpdate().setId(testAdmin.getId());
    ResponseEntity<Admin> response =
        this.restTemplate.exchange(
            "/Admin/updateAdmin", HttpMethod.PUT, new HttpEntity<>(request), Admin.class);
    Assertions.assertEquals(200, response.getStatusCodeValue());
    testAdmin = response.getBody();
    assertAdmin(request, testAdmin);
  }
}
