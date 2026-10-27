package com.example.demo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
   // public ResponseEntity<Map<String, Object>> handleResponseStatusException(
    public  ResponseEntity<String> handleResponseStatusException(
            ResponseStatusException ex) {

//        Map<String, Object> response = Map.of(
//              "status", ex.getStatusCode().value(),
//                "message",ex.getReason()
//        );
     //   return ResponseEntity.status(ex.getStatusCode()).body(response);
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
    }
}