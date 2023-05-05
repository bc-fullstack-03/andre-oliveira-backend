package com.sysmap.backend.exceptions;

public class Erro {
  private String msg;
  private String codigo;

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public Erro(String msg, String codigo) {
    this.msg = msg;
    this.codigo = codigo;
  }

}
