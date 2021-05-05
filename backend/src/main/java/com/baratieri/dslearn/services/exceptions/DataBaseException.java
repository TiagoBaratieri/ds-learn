package com.baratieri.dslearn.services.exceptions;

public class DataBaseException extends RuntimeException{
    private static  final long serialVersionUID = 1l;

    public DataBaseException(String msg){
        super(msg);
    }
}
