package com.nanmol.sbr.restPractice.exceptions;

public class NoCustomersException extends RuntimeException{
    public NoCustomersException(String msg){
        super(msg);
    }
}
