package com.sysmap.backend.services.userservice;

import java.util.List;

import com.sysmap.backend.dtos.UserRequest;
import com.sysmap.backend.dtos.UserResponse;

public interface IUserService {
  UserResponse createUser(UserRequest user);

  List<UserResponse> getUsers();
}
