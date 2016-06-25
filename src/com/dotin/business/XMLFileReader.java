package com.dotin.business;

import com.dotin.bean.Deposit;
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

public class XMLFileReader {

    public static void readXMLFile(String url){

        try {
            Class deposit = Deposit.class;
            Deposit depositt = (Deposit)deposit.newInstance();
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
                    depositt.setCustomNumber(Long.valueOf(element.getElementsByTagName("customerNumber").item(0).getTextContent()));
//                        file.setDepositBalance(element.getElementsByTagName("depositBalance").item(0).getTextContent());
                    depositt.setDurationInDays(Long.valueOf(element.getElementsByTagName("durationInDays").item(0).getTextContent()));
                        String str = element.getElementsByTagName("depositType").item(0).getTextContent();
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
        }
    }
}
