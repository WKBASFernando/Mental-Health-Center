package com.ijse.orm.mentalhealthcenter.bo.exeption;

public class DuplicateException extends RuntimeException{
    public DuplicateException(String message) {
        super(message);
    }
}
