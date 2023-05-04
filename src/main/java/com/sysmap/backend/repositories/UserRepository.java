package com.sysmap.backend.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sysmap.backend.model.UserApp;

@Repository
public interface UserRepository extends MongoRepository<UserApp, String> {

  Optional<UserApp> findByEmail(String email);
}
