package com.ekos.koopper.shared.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ekos.koopper.shared.exceptions.custom.ResourceConflictException;
import com.ekos.koopper.shared.exceptions.custom.ResourceNotFoundException;

record ResponseExceptionDTO(String message) {
    
}

@ControllerAdvice
public class RestExceptionHandler {

    // <----------------------->
    // <-------NOT FOUND------->
    // <----------------------->

    @ExceptionHandler({ResourceNotFoundException.class})
    private ResponseEntity<ResponseExceptionDTO> handleNotFound(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseExceptionDTO(exception.getMessage()));
    }

    // <----------------------->
    // <-------CONFLICT-------->
    // <----------------------->

    @ExceptionHandler({ResourceConflictException.class})
    private ResponseEntity<ResponseExceptionDTO> handleConflict(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseExceptionDTO(exception.getMessage()));
    }
}
