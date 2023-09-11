package com.muddassir_92.hotmail.com.runtime.controller;

import com.muddassir_92.hotmail.com.runtime.AppInit;
import com.muddassir_92.hotmail.com.runtime.model.User;
import com.muddassir_92.hotmail.com.runtime.request.LoginRequest;
import com.muddassir_92.hotmail.com.runtime.request.UserCreate;
import com.muddassir_92.hotmail.com.runtime.request.UserFilter;
import com.muddassir_92.hotmail.com.runtime.request.UserUpdate;
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
public class UserControllerTest {

  private User testUser;
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
  public void testUserCreate() {
    UserCreate request = new UserCreate();

    request.setPassword("test-string");

    request.setBlock(true);

    request.setGender("test-string");

    request.setEmail("test-string");

    ResponseEntity<User> response =
        this.restTemplate.postForEntity("/User/createUser", request, User.class);
    Assertions.assertEquals(200, response.getStatusCodeValue());
    testUser = response.getBody();
    assertUser(request, testUser);
  }

  @Test
  @Order(2)
  public void testListAllUsers() {
    UserFilter request = new UserFilter();
    ParameterizedTypeReference<PaginationResponse<User>> t = new ParameterizedTypeReference<>() {};

    ResponseEntity<PaginationResponse<User>> response =
        this.restTemplate.exchange(
            "/User/getAllUsers", HttpMethod.POST, new HttpEntity<>(request), t);
    Assertions.assertEquals(200, response.getStatusCodeValue());
    PaginationResponse<User> body = response.getBody();
    Assertions.assertNotNull(body);
    List<User> Users = body.getList();
    Assertions.assertNotEquals(0, Users.size());
    Assertions.assertTrue(Users.stream().anyMatch(f -> f.getId().equals(testUser.getId())));
  }

  public void assertUser(UserCreate request, User testUser) {
    Assertions.assertNotNull(testUser);

    if (request.getPassword() != null) {
      Assertions.assertEquals(request.getPassword(), testUser.getPassword());
    }

    if (request.getBlock() != null) {
      Assertions.assertEquals(request.getBlock(), testUser.isBlock());
    }

    if (request.getGender() != null) {
      Assertions.assertEquals(request.getGender(), testUser.getGender());
    }

    if (request.getEmail() != null) {
      Assertions.assertEquals(request.getEmail(), testUser.getEmail());
    }
  }

  @Test
  @Order(3)
  public void testUserUpdate() {
    UserUpdate request = new UserUpdate().setId(testUser.getId());
    ResponseEntity<User> response =
        this.restTemplate.exchange(
            "/User/updateUser", HttpMethod.PUT, new HttpEntity<>(request), User.class);
    Assertions.assertEquals(200, response.getStatusCodeValue());
    testUser = response.getBody();
    assertUser(request, testUser);
  }
}
