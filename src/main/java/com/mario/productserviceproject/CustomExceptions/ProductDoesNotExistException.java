package com.mario.productserviceproject.CustomExceptions;

public class ProductDoesNotExistException extends Exception{
    public ProductDoesNotExistException(String message){
        super(message);
    }
}
