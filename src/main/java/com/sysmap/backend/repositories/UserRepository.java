package com.sysmap.backend.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sysmap.backend.model.UserApp;

public interface UserRepository extends MongoRepository<UserApp, String> {

  Optional<UserApp> findByEmail(String email);
}
