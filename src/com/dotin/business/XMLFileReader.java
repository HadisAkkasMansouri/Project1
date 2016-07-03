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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class XMLFileReader {

    public static List<Deposit> readXMLFile() throws NegativeDepositBalanceException, NegativeDurationInDaysException, OtherDepositTypeException {

        try {
            List<Deposit> depositList = new ArrayList<>();
            File xmlfile = new File("DepositsFile.xml");
            DocumentBuilderFactory dbfactory = DocumentBuilderFactoryImpl.newInstance();
            DocumentBuilder dBuilder = dbfactory.newDocumentBuilder();
            Document document = dBuilder.parse(xmlfile);
            NodeList nodeList = document.getElementsByTagName("deposit");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                Deposit deposit = new Deposit();
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String depositTypeStr = element.getElementsByTagName("depositType").item(0).getTextContent();
                    Class depositType = Class.forName("com.dotin.bean." + depositTypeStr);
                    if (!(depositType.equals(depositTypeStr))){
                        throw new OtherDepositTypeException("This Deposit Type is not recognised!");
                    }
                    DepositType depositType1 = (DepositType) depositType.newInstance();
                    deposit.setDepositType(depositType1);

                    Long customerNumber = Long.valueOf(element.getElementsByTagName("customerNumber").item(0).getTextContent());
                    deposit.setCustomNumber(customerNumber);

                    BigDecimal depositBalance = new BigDecimal(element.getElementsByTagName("depositBalance").item(0).getTextContent());
                    if (depositBalance.compareTo(BigDecimal.ZERO) < 0) {
                        deposit.setDepositBalance(depositBalance);
                        throw new NegativeDepositBalanceException("Deposit Balance should be positive!");
                    }

                    Long durationInDays = Long.valueOf(element.getElementsByTagName("durationInDays").item(0).getTextContent());
                    if(durationInDays < 0){
                        deposit.setDurationInDays(durationInDays);
                        throw new NegativeDurationInDaysException("Duration in days should be positive!");
                    }
                    deposit.setPayedInterest(deposit.calculatePayedInterest(depositType1, depositBalance, durationInDays));
                }
                depositList.add(deposit);
            }
            return depositList;
        } catch(NegativeDepositBalanceException e){
            e.printStackTrace();
        }catch(NegativeDurationInDaysException e1){
            e1.printStackTrace();
        }catch(OtherDepositTypeException e2){
            e2.printStackTrace();
        }catch(Exception e3){
            e3.printStackTrace();
        }
        return null;
    }
}


