package com.sysmap.backend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sysmap.backend.model.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {

}
