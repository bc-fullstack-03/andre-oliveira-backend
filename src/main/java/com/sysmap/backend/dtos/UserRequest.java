package com.sysmap.backend.dtos;

import com.sysmap.backend.model.UserApp;

import jakarta.validation.constraints.NotBlank;

public class UserRequest {
  @NotBlank(message = "O nome precisa ser preenchido")
  private String nome;
  @NotBlank(message = "A senha precisa ser preenchida")
  private String senha;
  @NotBlank(message = "O email precisa ser preenchido")
  private String email;

  public UserRequest(@NotBlank String nome, @NotBlank String senha, @NotBlank String email) {
    this.nome = nome;
    this.senha = senha;
    this.email = email;
  }

  public UserRequest(UserApp user) {
    this.nome = user.getNome();
    this.senha = user.getSenha();
    this.email = user.getEmail();
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}
