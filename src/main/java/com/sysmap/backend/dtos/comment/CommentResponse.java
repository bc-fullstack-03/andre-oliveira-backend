package com.sysmap.backend.dtos.comment;

import java.util.List;

import com.sysmap.backend.model.Comment;
import com.sysmap.backend.model.Like;

public class CommentResponse {
  private String id;
  private String userId;
  private String content;
  private List<Like> likes;

  public CommentResponse(Comment comment) {
    this.id = comment.getId();
    this.userId = comment.getUserId();
    this.content = comment.getContent();
    this.likes = comment.getLikes();
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

  public List<Like> getLikes() {
    return likes;
  }

  public void setLikes(List<Like> likes) {
    this.likes = likes;
  }

}
