package com.sysmap.backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sysmap.backend.dtos.UserRequest;
import com.sysmap.backend.dtos.UserResponse;
import com.sysmap.backend.services.userservice.IUserService;

@RestController
@RequestMapping("api/v1/user")
public class UserControlle {

  private IUserService service;

  public UserControlle(IUserService service) {
    this.service = service;
  }

  @PostMapping
  public UserResponse create(@RequestBody UserRequest user) {
    return service.createUser(user);
  }

  @GetMapping
  public List<UserResponse> getUsers() {
    return service.getUsers();
  }
}
