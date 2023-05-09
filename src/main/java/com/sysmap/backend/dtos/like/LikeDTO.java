package com.sysmap.backend.dtos.like;

import com.sysmap.backend.model.Like;

public class LikeDTO {
  private String userId;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public LikeDTO(Like like) {
    this.userId = like.getUserId();
  }

  public LikeDTO() {
  }

}
