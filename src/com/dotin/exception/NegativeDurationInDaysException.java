package com.dotin.exception;

public class NegativeDurationInDaysException extends Exception {

    public NegativeDurationInDaysException(String message){
        super(message);
    }
}
