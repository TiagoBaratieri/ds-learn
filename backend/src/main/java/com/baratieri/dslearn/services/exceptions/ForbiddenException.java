package com.baratieri.dslearn.services.exceptions;

public class ForbiddenException extends RuntimeException{
    private static  final long serialVersionUID = 1l;

    public ForbiddenException(String msg){
        super(msg);
    }
}
