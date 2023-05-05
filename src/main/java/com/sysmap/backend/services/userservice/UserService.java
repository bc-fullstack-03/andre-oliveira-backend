package com.sysmap.backend.services.userservice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sysmap.backend.dtos.UserRequest;
import com.sysmap.backend.dtos.UserRequestPUT;
import com.sysmap.backend.dtos.UserResponse;
import com.sysmap.backend.exceptions.NotFoundException;
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
      throw new NotFoundException("email ja cadastrado");
    }
    UserApp newUser = new UserApp(user);
    newUser = repository.save(newUser);
    return new UserResponse(newUser);
  }

  @Override
  public List<UserResponse> getUsers() {
    return repository.findAll().stream().map(UserResponse::new).collect(Collectors.toList());
  }

  @Override
  public void deleteUser(String id) {
    UserApp user = repository.findById(id).orElseThrow(() -> new NotFoundException("Usuário não cadastrado"));
    repository.delete(user);
  }

  @Override
  public UserResponse updateUser(UserRequestPUT user, String id) {
    UserApp userApp = repository.findById(id).orElseThrow(() -> new NotFoundException("Usuário não cadastrado"));
    if (!user.getNome().isBlank()) {
      userApp.setNome(user.getNome());
    }
    if (!user.getSenha().isBlank()) {
      userApp.setSenha(user.getSenha());
    }
    return new UserResponse(repository.save(userApp));
  }

  @Override
  public UserResponse getUser(String id) {
    UserApp user = repository.findById(id).orElseThrow(() -> new NotFoundException("Usuário não cadastrado"));
    return new UserResponse(user);
  }

}
