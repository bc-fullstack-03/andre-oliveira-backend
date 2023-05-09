package com.sysmap.backend.dtos.post;

public class PostRequest {
  private String userId;
  private String content;

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
