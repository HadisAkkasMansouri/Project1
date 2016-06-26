package com.dotin.main;

import com.dotin.business.XMLFileReader;
import com.dotin.exception.NegativeDepositBalanceException;
import com.dotin.exception.NegativeDurationInDaysException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String argv[]) {

//      "D:/DepositsFile.xml"
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
        try {
            File file = new File("D:/PayedInterestOutputFile.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.close();
            }catch(IOException e){
                    e.printStackTrace();
        }
    }
}

