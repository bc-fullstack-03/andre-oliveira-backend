package com.sysmap.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.sysmap.backend.dtos.UserRequest;

@Document
public class UserApp {

  @Id
  private String id;
  private String nome;
  private String senha;
  private String email;

  public UserApp(String nome, String senha, String email) {
    this.nome = nome;
    this.senha = senha;
    this.email = email;
  }

  public UserApp(UserRequest user) {
    this.nome = user.getNome();
    this.senha = user.getSenha();
    this.email = user.getEmail();
  }

  public UserApp() {
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
