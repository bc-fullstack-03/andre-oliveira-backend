package com.sysmap.backend.services.user;

import java.util.List;

import com.sysmap.backend.dtos.user.UserRequest;
import com.sysmap.backend.dtos.user.UserRequestPUT;
import com.sysmap.backend.dtos.user.UserResponse;

public interface IUserService {
  UserResponse createUser(UserRequest user);

  List<UserResponse> getUsers();

  UserResponse getUser(String id);

  void deleteUser(String id);

  UserResponse updateUser(UserRequestPUT user, String id);
}
