package com.dotin.business;

import com.dotin.bean.Deposit;
import com.dotin.bean.DepositType;
import com.dotin.exception.NegativeDepositBalanceException;
import com.dotin.exception.NegativeDurationInDaysException;
import com.dotin.exception.OtherDepositTypeException;
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
import java.util.ArrayList;
import java.util.List;

public class DepositHandler {

    public static List<Deposit> readXMLFile() throws IOException, SAXException{
        try {
            List<Deposit> depositList = new ArrayList<>();
            File xmlfile = new File("depositsFile.xml");
            DocumentBuilderFactory dbfactory = DocumentBuilderFactoryImpl.newInstance();
            DocumentBuilder dBuilder = dbfactory.newDocumentBuilder();
            Document document = dBuilder.parse(xmlfile);
            NodeList nodeList = document.getElementsByTagName("deposit");
            for (int i = 0; i < nodeList.getLength(); i++) {
                try {
                    Node node = nodeList.item(i);
                    Deposit deposit = new Deposit();
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        String depositTypeStr = element.getElementsByTagName("depositType").item(0).getTextContent();

                        DepositType depositType;

                        try {
                            depositType = (DepositType) Class.forName("com.dotin.bean." + depositTypeStr).newInstance();
                        } catch (ClassNotFoundException e) {
                            throw new OtherDepositTypeException("The deposit " + depositTypeStr +" is not recognised!");
                        }

                        deposit.setDepositType(depositType);
                        Long customerNumber = Long.valueOf(element.getElementsByTagName("customerNumber").item(0).getTextContent());
                        deposit.setCustomNumber(customerNumber);

                        BigDecimal depositBalance = new BigDecimal(element.getElementsByTagName("depositBalance").item(0).getTextContent());
                        if (depositBalance.compareTo(BigDecimal.ZERO) < 0) {
                            throw new NegativeDepositBalanceException("Deposit Balance should be positive!");
                        }
                        deposit.setDepositBalance(depositBalance);

                        Long durationInDays = Long.valueOf(element.getElementsByTagName("durationInDays").item(0).getTextContent());
                        if (durationInDays < 0) {
                            throw new NegativeDurationInDaysException("Duration in days should be positive!");
                        }
                        deposit.setDurationInDays(durationInDays);
                        deposit.calculatePayedInterest(depositType, depositBalance, durationInDays);
                    }
                    depositList.add(deposit);
                } catch (OtherDepositTypeException e) {
                    System.out.println(e.getMessage());
                }catch (NegativeDurationInDaysException e){
                    System.out.println(e.getMessage());
                }catch (NegativeDepositBalanceException e){
                    System.out.println(e.getMessage());
                }
            }
            return depositList;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}


