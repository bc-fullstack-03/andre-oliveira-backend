package com.sysmap.backend.dtos.like;

import com.sysmap.backend.model.Like;

import jakarta.validation.constraints.NotBlank;

public class LikeDTO {
  @NotBlank(message = "id do usuário não pode ser vazio")
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
