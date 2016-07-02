package com.dotin.main;

import com.dotin.bean.Deposit;
import com.dotin.business.XMLFileReader;
import com.dotin.exception.NegativeDepositBalanceException;
import com.dotin.exception.NegativeDurationInDaysException;
import com.dotin.exception.OtherDepositTypeException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void sortDeposits(List<Deposit> depositList){
        Collections.sort(depositList,(Deposit first, Deposit second)-> second.compareTo(first));
    }

    // Write in output File
    public static void writeFile(){
        List<Deposit> depositList = new ArrayList<>();
        Collections.sort(depositList, Collections.<Deposit>reverseOrder());
        File file = new File("D:/PayedInterestOutputFile.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            for (Deposit deposit1 : depositList) {
                fileWriter.write(deposit1.getCustomNumber() + "#" + deposit1.getPayedInterest() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws NegativeDurationInDaysException, NegativeDepositBalanceException, OtherDepositTypeException{

//      "D:/DepositsFile.xml"
        Deposit deposit = new Deposit();
        System.out.println("Please enter the XML file to compute \"Deposit interests\"");
        Scanner scan = new Scanner(System.in);
        String url = scan.nextLine();
        try {
            XMLFileReader.readXMLFile(url);
        } catch (NegativeDepositBalanceException e) {
            e.printStackTrace();
        } catch (NegativeDurationInDaysException e) {
            e.printStackTrace();
        }
        writeFile();
    }
}

