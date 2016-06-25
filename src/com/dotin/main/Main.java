package com.dotin.main;

import com.dotin.business.XMLFileReader;

import java.util.Scanner;

/**
 * Created by Dotin School1 on 6/22/2016.
 */
public class Main {

    public static void main(String argv[]){

//      "D:/DepositsFile.xml"
        System.out.println("Please enter the XML file to compute \"Deposit interests\"");
        Scanner scan = new Scanner(System.in);
        String url = scan.nextLine();
        XMLFileReader.readXMLFile(url);
    }

}
