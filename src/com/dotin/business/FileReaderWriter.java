package com.dotin.business;

import com.dotin.bean.Deposit;
import com.dotin.bean.DepositType;
import com.dotin.exception.NegativeDepositBalanceException;
import com.dotin.exception.NegativeDurationInDaysException;
import com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileReaderWriter {

    public static void readXMLFile(String url) throws NegativeDepositBalanceException, NegativeDurationInDaysException {

        try {

            File xmlfile = new File(url);
            DocumentBuilderFactory dbfactory = DocumentBuilderFactoryImpl.newInstance();
            DocumentBuilder dBuilder = dbfactory.newDocumentBuilder();
            Document document = dBuilder.parse(xmlfile);
            NodeList nodeList = document.getElementsByTagName("deposit");
            for (int i = 0; i<nodeList.getLength(); i++){
                 Node node = nodeList.item(i);
                if ( node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String depositTypeStr = element.getElementsByTagName("depositType").item(0).getTextContent();
                    Class depositType = Class.forName("com.dotin.bean." + depositTypeStr);
                    DepositType depositType1 = (DepositType)depositType.newInstance();
                    Deposit deposit = new Deposit();

                    Long customerNumber = Long.valueOf(element.getElementsByTagName("customerNumber").item(0).getTextContent());
                    deposit.setCustomNumber(customerNumber);

                    BigDecimal depositBalance = new BigDecimal(element.getElementsByTagName("depositBalance").item(0).getTextContent());
                    deposit.setDepositBalance(depositBalance);

                    Long durationInDays = Long.valueOf(element.getElementsByTagName("durationInDays").item(0).getTextContent());
                    deposit.setDurationInDays(durationInDays);

                    deposit.calculatePayedInterest(depositType1, depositBalance, durationInDays);


                    List<Deposit> depositList = new ArrayList<>();
                    Collections.sort(depositList, Collections.<Deposit>reverseOrder());
                    File file = new File("D:/PayedInterestOutputFile.txt");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
                    for (Deposit deposit1 : depositList){
                        fileWriter.write(deposit1.getCustomNumber() + "#" + deposit1.calculatePayedInterest(depositType1, depositBalance, durationInDays));
                    }
                    fileWriter.close();
                }
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
