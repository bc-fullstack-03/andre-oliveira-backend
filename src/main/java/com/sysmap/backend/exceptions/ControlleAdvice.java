package com.sysmap.backend.exceptions;

import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ControlleAdvice {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Erro> error(MethodArgumentNotValidException e) {
    String msg = e.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
        .collect(Collectors.joining(","));
    Erro erro = new Erro(msg, HttpStatus.BAD_REQUEST.toString());
    return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<Erro> error(MethodArgumentTypeMismatchException e) {
    Erro erro = new Erro(e.getMessage(), HttpStatus.BAD_REQUEST.toString());
    return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<Erro> error(NotFoundException e) {
    Erro erro = new Erro(e.getMessage(), HttpStatus.NOT_FOUND.toString());
    return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
  }

}
