package com.dotin.main;

import com.dotin.bean.Deposit;
import com.dotin.business.FileReaderWriter;
import com.dotin.exception.NegativeDepositBalanceException;
import com.dotin.exception.NegativeDurationInDaysException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String argv[]) {

//      "D:/DepositsFile.xml"
        Deposit deposit = new Deposit();
        System.out.println("Please enter the XML file to compute \"Deposit interests\"");
        Scanner scan = new Scanner(System.in);
        String url = scan.nextLine();
        try {
            FileReaderWriter.readXMLFile(url);

        } catch (NegativeDepositBalanceException e) {
            e.printStackTrace();
        } catch (NegativeDurationInDaysException e) {
            e.printStackTrace();
        }
    }
}

