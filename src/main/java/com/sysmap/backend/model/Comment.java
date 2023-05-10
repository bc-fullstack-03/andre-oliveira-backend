package com.sysmap.backend.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.sysmap.backend.dtos.comment.CommentRequest;

@Document
public class Comment {

  private UUID id;
  private String userId;
  private String content;
  private List<Like> likes;

  public Comment() {
  }

  public Comment(CommentRequest comment) {
    this.id = UUID.randomUUID();
    this.userId = comment.getUserId();
    this.content = comment.getContent();
    this.likes = new ArrayList<>();
  }

  public List<Like> getLikes() {
    return likes;
  }

  public void setLikes(List<Like> likes) {
    this.likes = likes;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

}
