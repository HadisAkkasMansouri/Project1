package com.dotin.bean;

import com.dotin.exception.NegativeDepositBalanceException;
import com.dotin.exception.NegativeDurationInDaysException;

import java.io.Serializable;
import java.math.BigDecimal;

public class Deposit implements Serializable {

    private BigDecimal depositBalance;
    private Long durationInDays ;
    private Long customNumber;
    DepositType depositType ;

    public BigDecimal getDepositBalance() {
        return depositBalance;
    }

    public void setDepositBalance(BigDecimal depositBalance) throws NegativeDepositBalanceException {

        if(depositBalance.compareTo(BigDecimal.ZERO) < 0){
            throw new NegativeDepositBalanceException("Deposit Balance should be positive!");
        }
        this.depositBalance = depositBalance;
    }

    public Long getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(Long durationInDays) throws NegativeDurationInDaysException {
        if(durationInDays < 0){
            throw new NegativeDurationInDaysException("Duration in days should be positive!");
        }
        this.durationInDays = durationInDays;
    }

    public Long getCustomNumber() {
        return customNumber;
    }

    public void setCustomNumber(Long customNumber) {
        this.customNumber = customNumber;
    }

    public DepositType getDepositType(){
        return depositType;
    }

    public void seDepositType(DepositType depositType){
        this.depositType = depositType;
    }

    public  BigDecimal calculatePayedInterest(DepositType depositType, BigDecimal depositBalance, Long durationInDays){
        BigDecimal calculatePayedInterest =
                (depositBalance.multiply(new BigDecimal(durationInDays).multiply(new BigDecimal(depositType.getInterestRate()))));
        calculatePayedInterest.divide(new BigDecimal("36500"));
        return calculatePayedInterest;
    }
}



