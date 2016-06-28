package com.dotin.bean;

public class DepositType {

    private int interestRate;

    public DepositType() {
        interestRate = getInterestRate();
    }

    public int getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(int interestRate) {
        this.interestRate = interestRate;
    }
}
