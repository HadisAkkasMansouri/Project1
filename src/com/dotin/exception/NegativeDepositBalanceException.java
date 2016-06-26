package com.dotin.exception;


public class NegativeDepositBalanceException extends Exception {

    public NegativeDepositBalanceException (String message){
       super(message);
    }

}
