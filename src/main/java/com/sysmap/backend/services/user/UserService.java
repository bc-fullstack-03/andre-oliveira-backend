package com.sysmap.backend.services.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sysmap.backend.dtos.user.UserRequest;
import com.sysmap.backend.dtos.user.UserRequestPUT;
import com.sysmap.backend.dtos.user.UserResponse;
import com.sysmap.backend.exceptions.AlreadyCreatedException;
import com.sysmap.backend.exceptions.NotFoundException;
import com.sysmap.backend.model.UserApp;
import com.sysmap.backend.repositories.UserRepository;

@Service
public class UserService implements IUserService {

  private UserRepository repository;
  private PasswordEncoder encoder;

  public UserService(UserRepository repository, PasswordEncoder encoder) {
    this.repository = repository;
    this.encoder = encoder;
  }

  @Override
  public UserResponse createUser(UserRequest user) {
    if (repository.findByEmail(user.getEmail()).isPresent()) {
      throw new AlreadyCreatedException("email ja cadastrado");
    }
    UserApp newUser = new UserApp(user);
    newUser.setSenha(encoder.encode(newUser.getSenha()));
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

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserApp user = repository.findByEmail(username).orElseThrow(() -> new NotFoundException("Usuário não cadastrado"));
    return new User(user.getEmail(), user.getSenha(), new ArrayList<>());
  }

}
