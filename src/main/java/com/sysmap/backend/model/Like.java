package com.sysmap.backend.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.sysmap.backend.dtos.like.LikeDTO;

@Document
public class Like {
  private String userId;

  public Like(LikeDTO like) {
    this.userId = like.getUserId();
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public Like() {
  }

}
