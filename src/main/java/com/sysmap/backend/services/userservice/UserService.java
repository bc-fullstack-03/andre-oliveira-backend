package com.sysmap.backend.services.userservice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sysmap.backend.dtos.UserRequest;
import com.sysmap.backend.dtos.UserResponse;
import com.sysmap.backend.model.UserApp;
import com.sysmap.backend.repositories.UserRepository;

@Service
public class UserService implements IUserService {

  private UserRepository repository;

  public UserService(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public UserResponse createUser(UserRequest user) {
    if (repository.findByEmail(user.getEmail()).isPresent()) {
      throw new RuntimeException("email ja cadastrado");
    }
    UserApp newUser = new UserApp(user);
    newUser = repository.save(newUser);
    return new UserResponse(newUser);
  }

  @Override
  public List<UserResponse> getUsers() {
    return repository.findAll().stream().map(UserResponse::new).collect(Collectors.toList());
  }

}
