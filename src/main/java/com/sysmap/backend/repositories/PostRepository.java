package com.sysmap.backend.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sysmap.backend.model.Post;

public interface PostRepository extends MongoRepository<Post, String> {
  List<Post> findAllByUserId(String id);
}
