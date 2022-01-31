package com.adp.assignment.change.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.adp.assignment.change.model.ErrorDetails;

@RestControllerAdvice
public class ChangeServiceExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(InvalidBillException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Object> handleInvalidBillException(Exception ex, WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.value(),
        HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage(), LocalDateTime.now());
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InsufficientChangeException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<Object> handleInsufficientChangeException(Exception ex,
      WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(),
        HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getMessage(), LocalDateTime.now());
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }

  // @ExceptionHandler(ChangeException.class)
  // @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  // public ResponseEntity<Object> handleChangeException(Exception ex, WebRequest request) {
  // ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
  // HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage(), LocalDateTime.now());
  // return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  // }

  // Javax/Hibernate validator exception handler
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {

    ErrorDetails errorDetails =
        new ErrorDetails(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
            ex.getBindingResult().getAllErrors().get(0).getDefaultMessage(), LocalDateTime.now());
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {

    ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.value(),
        HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage(), LocalDateTime.now());
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage(), LocalDateTime.now());
    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
