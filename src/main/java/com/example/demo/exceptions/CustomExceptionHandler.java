package com.example.demo.exceptions;

import com.example.demo.domain.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorMessage> handleAllExceptions(Exception ex, HttpServletRequest request){
        ErrorMessage exMsg = new ErrorMessage(
                ex.getMessage(),
                request.getRequestURI(),
                String.valueOf(request.getServerPort()),
                request.getMethod()
        );
        return new ResponseEntity<>(exMsg, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<ErrorMessage> handleRuntimeExceptions(RuntimeException ex, HttpServletRequest request){
        ErrorMessage exMsg = new ErrorMessage(
                ex.getMessage(),
                request.getRequestURI(),
                String.valueOf(request.getServerPort()),
                request.getMethod()
        );
        return new ResponseEntity<>(exMsg, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ErrorMessage> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request){
        ErrorMessage exMsg = new ErrorMessage(
                ex.getMessage(),
                request.getRequestURI(),
                String.valueOf(request.getServerPort()),
                request.getMethod()
        );
        return new ResponseEntity<>(exMsg, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
