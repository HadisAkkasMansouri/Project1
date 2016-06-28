package com.dotin.main;

import com.dotin.bean.Deposit;
import com.dotin.business.XMLFileReader;
import com.dotin.exception.NegativeDepositBalanceException;
import com.dotin.exception.NegativeDurationInDaysException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
        List<Deposit> depositList = new ArrayList<>();
        Collections.sort(depositList, Collections.<Deposit>reverseOrder());
        try {
            XMLFileReader.readXMLFile(url);
            File file = new File("D:/PayedInterestOutputFile.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            for (Deposit deposits : depositList){
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(deposits.getCustomNumber() + "#" + deposit.calculatePayedInterest(deposit.calculatePayedInterest(depositType1, depositBalance, durationInDays), "\n");
            }
            fileWriter.close();
        } catch (NegativeDepositBalanceException e) {
            e.printStackTrace();
        } catch (NegativeDurationInDaysException e) {
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}

