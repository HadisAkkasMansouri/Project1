package com.dotin.bean;

public class ShortTermDeposit extends DepositType {

    @Override
    public int getInterestRate(){
        return 10;
    }
}
