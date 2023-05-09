package com.sysmap.backend.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sysmap.backend.dtos.user.UserRequest;
import com.sysmap.backend.dtos.user.UserRequestPUT;
import com.sysmap.backend.dtos.user.UserResponse;
import com.sysmap.backend.services.user.IUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/user")
public class UserControlle {

  private IUserService service;

  public UserControlle(IUserService service) {
    this.service = service;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserResponse create(@RequestBody @Valid UserRequest user) {
    return service.createUser(user);
  }

  @GetMapping
  public List<UserResponse> getUsers() {
    return service.getUsers();
  }

  @GetMapping("/{id}")
  public UserResponse getUser(@PathVariable String id) {
    return service.getUser(id);
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable String id) {
    service.deleteUser(id);
  }

  @PutMapping("/{id}")
  public UserResponse updateUser(@RequestBody UserRequestPUT user, @PathVariable String id) {
    return service.updateUser(user, id);
  }
}
