package com.sysmap.backend.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.sysmap.backend.dtos.post.PostRequest;

@Document
public class Post {

  @Id
  private String id;
  private String userId;
  private String content;
  private List<Comment> comments;
  private List<Like> likes;

  public Post() {
  }

  public Post(PostRequest post) {
    this.userId = post.getUserId();
    this.content = post.getContent();
    this.comments = new ArrayList<>();
    this.likes = new ArrayList<>();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
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

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  public List<Like> getLikes() {
    return likes;
  }

  public void setLikes(List<Like> likes) {
    this.likes = likes;
  }

}
