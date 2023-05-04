package com.sysmap.backend.dtos;

import com.sysmap.backend.model.UserApp;

public class UserResponse {
  private String id;
  private String nome;
  private String senha;
  private String email;

  public UserResponse(String id, String nome, String senha, String email) {
    this.id = id;
    this.nome = nome;
    this.senha = senha;
    this.email = email;
  }

  public UserResponse(UserApp user) {
    this.id = user.getId();
    this.nome = user.getNome();
    this.senha = user.getSenha();
    this.email = user.getEmail();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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
