package com.betrybe.museumfinder.util;

import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Handler error.
 */

@ControllerAdvice
public class HandlerExceptionController {

  /**
   * Tratamento do erro InvalidCoordinateException.
   *
   * @param exception erro recebido ao passar um coordinate invalido.
   */
  @ExceptionHandler(InvalidCoordinateException.class)

  public ResponseEntity<String> invalidCoordinateException(InvalidCoordinateException exception) {
    return ResponseEntity
        .badRequest()
        .body("Coordenada inválida!");
  }

  /**
   * Tratamento do erro museumNotFoundException.
   *
   * @param exception caso nenhum museum seja encontrado no bando de dados.
   */
  @ExceptionHandler(MuseumNotFoundException.class)

  public ResponseEntity<String> museumNotFoundException(MuseumNotFoundException exception) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body("Museu não encontrado!");
  }

  /**
   * Tratamento do erro handlerException.
   *
   * @param exception qualquer outra excessão que não tenha o tratamento personalisado.
   */
  @ExceptionHandler(Exception.class)

  public ResponseEntity<String> handlerException(Exception exception) {
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("Erro interno!");
  }
}
