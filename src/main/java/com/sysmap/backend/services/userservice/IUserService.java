package com.sysmap.backend.services.userservice;

import java.util.List;

import com.sysmap.backend.dtos.UserRequest;
import com.sysmap.backend.dtos.UserRequestPUT;
import com.sysmap.backend.dtos.UserResponse;

public interface IUserService {
  UserResponse createUser(UserRequest user);

  List<UserResponse> getUsers();

  UserResponse getUser(String id);

  void deleteUser(String id);

  UserResponse updateUser(UserRequestPUT user, String id);
}
