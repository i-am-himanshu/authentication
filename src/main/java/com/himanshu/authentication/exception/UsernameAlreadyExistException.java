package com.himanshu.authentication.exception;

public class UsernameAlreadyExistException extends RuntimeException{

    public UsernameAlreadyExistException(String message) {
        super(message);
    }
}
