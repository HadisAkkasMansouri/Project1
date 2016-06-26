package com.dotin.business;

public class NegativeDurationInDaysException extends Exception {

    public NegativeDurationInDaysException(String message){
        super(message);
    }
}
