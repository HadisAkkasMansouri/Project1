package com.dotin.business;


public class NegativeDepositBalanceException extends Exception {

    public NegativeDepositBalanceException (String message){
       super(message);
    }

}
