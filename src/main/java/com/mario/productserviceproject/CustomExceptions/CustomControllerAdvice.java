package com.mario.productserviceproject.CustomExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.mario.productserviceproject.DTOs.ExceptionDTO;

@ControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(ProductDoesNotExistException.class)
    public ResponseEntity<ExceptionDTO> handleProductDoesNotExistException(ProductDoesNotExistException e){
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setMessage(e.getMessage());
        exceptionDTO.setDetails("provide a valid product id");
        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }
}
