package com.dotin.bean;

import java.math.BigDecimal;

public class Deposit implements Comparable<Deposit> {

    private BigDecimal depositBalance;
    private Long durationInDays ;
    private Long customNumber;
    DepositType depositType ;
    BigDecimal payedInterest;

    public BigDecimal getDepositBalance() {
        return depositBalance;
    }

    public void setDepositBalance(BigDecimal depositBalance){
        this.depositBalance = depositBalance;
    }

    public Long getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(Long durationInDays){
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

    public void setDepositType(DepositType depositType){
        this.depositType = depositType;
    }

    public BigDecimal getPayedInterest(){
         return payedInterest;
    }

    public void setPayedInterest(BigDecimal payedInterest){
        this.payedInterest = payedInterest;
    }

    public  BigDecimal calculatePayedInterest(DepositType depositType, BigDecimal depositBalance, Long durationInDays){
        BigDecimal calculatePayedInterest =
                (depositBalance.multiply(new BigDecimal(durationInDays).multiply(new BigDecimal(depositType.getInterestRate())))).divide(new BigDecimal("36500"), 2, BigDecimal.ROUND_HALF_UP);
        return calculatePayedInterest;
    }

    @Override
    public int compareTo(Deposit deposit) {
        return this.payedInterest.compareTo(deposit.payedInterest);
    }
}



