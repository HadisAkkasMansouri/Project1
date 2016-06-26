package com.dotin.bean;

public class LongTermDeposit extends DepositType {

    @Override
    public int getInterestRate(){
        return 20;
    }

}
