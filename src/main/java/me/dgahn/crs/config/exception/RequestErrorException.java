package me.dgahn.crs.config.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class RequestErrorException extends RuntimeException {

  private int httpStatusCode;
  private String message;

}
