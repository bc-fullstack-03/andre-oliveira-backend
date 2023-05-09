package com.sysmap.backend.exceptions;

public class AlreadyCreatedException extends RuntimeException {
  public AlreadyCreatedException(String msg) {
    super(msg);
  }
}
