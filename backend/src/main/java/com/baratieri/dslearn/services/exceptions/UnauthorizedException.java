package com.baratieri.dslearn.services.exceptions;

public class UnauthorizedException extends RuntimeException{
    private static  final long serialVersionUID = 1l;

    public UnauthorizedException(String msg){
        super(msg);
    }
}
