package com.sysmap.backend.dtos.comment;

import jakarta.validation.constraints.NotBlank;

public class CommentRequest {
  @NotBlank(message = "id do usuário não pode ser vazio")
  private String userId;
  @NotBlank(message = "conteudo do comentário não pode ser vazio")
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
