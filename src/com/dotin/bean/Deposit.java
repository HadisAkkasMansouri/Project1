package com.dotin.bean;

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

    public void setDepositBalance(BigDecimal depositBalance) {
        this.depositBalance = depositBalance;
    }

    public Long getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(Long durationInDays) {
        this.durationInDays = durationInDays;
    }

    public Long getCustomNumber() {
        return customNumber;
    }

    public void setCustomNumber(Long customNumber) {
        this.customNumber = customNumber;
    }

    public  BigDecimal calculateInterest(BigDecimal depositBalance, Long durationInDays, DepositType depositType ){
        BigDecimal payedInterest =
                (depositBalance.multiply(new BigDecimal(durationInDays).multiply(new BigDecimal(depositType.getInterestRate())))).divide(new BigDecimal(36500));
        return payedInterest;
    }
}



