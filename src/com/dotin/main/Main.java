package com.dotin.main;

import com.dotin.bean.Deposit;
import com.dotin.business.XMLFileReader;
import com.dotin.exception.NegativeDepositBalanceException;
import com.dotin.exception.NegativeDurationInDaysException;
import com.dotin.exception.OtherDepositTypeException;

import java.io.*;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String args[]) throws NegativeDurationInDaysException, NegativeDepositBalanceException, OtherDepositTypeException {

        XMLFileReader.readXMLFile();
        List<Deposit> depositList = XMLFileReader.readXMLFile();
        Collections.sort(depositList, Collections.<Deposit>reverseOrder());
        try {
            RandomAccessFile file = new RandomAccessFile("OutputFile.txt", "rw");
            for (Deposit deposit : depositList) {
                file.writeBytes(deposit.getCustomNumber() + "#" + deposit.getPayedInterest() + "\n");
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

