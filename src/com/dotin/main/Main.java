package com.dotin.main;

import com.dotin.bean.Deposit;
import com.dotin.business.DepositHandler;
import org.xml.sax.SAXException;
import java.io.*;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String args[]) throws IOException, SAXException {

        DepositHandler depositHandler = new DepositHandler();

            List<Deposit> depositList = depositHandler.readXMLFile();
            RandomAccessFile file = new RandomAccessFile("OutputFile.txt", "rw");
            Collections.sort(depositList);
            for (Deposit deposit : depositList) {
                file.writeBytes(deposit.getCustomNumber() + "#" + deposit.getPayedInterest() + "\n");
            }
            file.close();
    }
}
