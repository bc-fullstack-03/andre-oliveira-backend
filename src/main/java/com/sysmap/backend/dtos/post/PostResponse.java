package com.sysmap.backend.dtos.post;

import java.util.List;

import com.sysmap.backend.model.Comment;
import com.sysmap.backend.model.Like;
import com.sysmap.backend.model.Post;

public class PostResponse {
  private String id;
  private String userId;
  private String content;
  private List<Comment> comments;
  private List<Like> likes;

  public PostResponse(Post post) {
    this.id = post.getId();
    this.userId = post.getUserId();
    this.content = post.getContent();
    this.comments = post.getComments();
    this.likes = post.getLikes();
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
