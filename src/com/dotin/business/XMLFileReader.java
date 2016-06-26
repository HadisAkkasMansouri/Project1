package com.dotin.business;

import com.dotin.bean.Deposit;
import com.dotin.exception.NegativeDepositBalanceException;
import com.dotin.exception.NegativeDurationInDaysException;
import com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

public class XMLFileReader {

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

//                    System.out.println("customerNumber: " + element.getElementsByTagName("customerNumber").item(0).getTextContent());
//                    System.out.println("depositType: " + element.getElementsByTagName("depositType").item(0).getTextContent());
//                    System.out.println("depositBalance: " + element.getElementsByTagName("depositBalance").item(0).getTextContent());
//                    System.out.println("durationInDays: " + element.getElementsByTagName("durationInDays").item(0).getTextContent()+ "\n");

                    Class depositType = Class.forName("DepositType");
                    Deposit deposit = (Deposit)depositType.newInstance();
                    String str = element.getElementsByTagName("DepositType").item(0).getTextContent();
                    str = String.valueOf(depositType.cast(depositType));
                    System.out.println(str);

                    Long customerNumber = Long.valueOf(element.getElementsByTagName("customerNumber").item(0).getTextContent());
                    deposit.setCustomNumber(customerNumber);

                    BigDecimal depositBalance = new BigDecimal(element.getElementsByTagName("depositBalance").item(0).getTextContent());
                    deposit.setDepositBalance(depositBalance);

                    Long durationInDays = Long.valueOf(element.getElementsByTagName("durationInDays").item(0).getTextContent());
                    deposit.setDurationInDays(durationInDays);

//                    deposit.calculateInterest(depositType.cast(depositType),deposit.setDepositBalance(depositBalance), deposit.setDurationInDays(durationInDays));
                }
                }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
