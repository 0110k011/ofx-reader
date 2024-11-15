package com.ofxr;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OFXProcess {

    private static final Logger logger = Logger.getLogger(OFXProcess.class.getName());

    public void processOFX(File file) {
        try {

            String content = new String(Files.readAllBytes(file));

            // Remove linhas de cabeçalho antes da tag <OFX>
            int startIndex = content.indexOf("<OFX>");
            if (startIndex != -1) {
                content = content.substring(startIndex); // Mantém apenas o conteúdo XML
            } else {
                throw new Exception("Tag <OFX> não encontrada no arquivo.");
            }

            // Converter a string de conteúdo para um InputStream
            ByteArrayInputStream inputStream = new ByteArrayInputStream(content.getBytes());

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(file);
            document.getDocumentElement().normalize();

            NodeList transactions = document.getElementsByTagName("STMTTRN");
            for (int i = 0; i < transactions.getLength(); i++) {
                Node node = transactions.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("Transaction ID: " + getTagValue("FITID", element));
                    System.out.println("Amount: " + getTagValue("TRNAMT", element));
                    System.out.println("Date: " + getTagValue("DTPOSTED", element));
                }
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error: File can`t be processed!", e);
        }
    }

    private String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag);
        if (nodeList.getLength() > 0 && nodeList.item(0).getChildNodes().getLength() > 0) {
            return nodeList.item(0).getChildNodes().item(0).getNodeValue();
        }
        return null;
    }
}