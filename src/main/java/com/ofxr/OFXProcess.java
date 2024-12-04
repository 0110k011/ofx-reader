package com.ofxr;

import com.ofxr.dtos.AccountStatementDto;
import com.ofxr.dtos.TransactionDto;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.ArrayList;
import java.util.List;


public class OFXProcess {

    private static final Logger logger = Logger.getLogger(OFXProcess.class.getName());

    public AccountStatementDto processOFX(InputStream inputStream) throws Exception {
        // Lê o conteúdo do InputStream para uma String
        String content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

        try {


            // Remove linhas de cabeçalho antes da tag <OFX>
            int startIndex = content.indexOf("<OFX>");
            if (startIndex != -1) {
                content = content.substring(startIndex); // Mantém apenas o conteúdo XML
            } else {
                throw new Exception("Tag <OFX> não encontrada no arquivo.");
            }

            // Converter a string de conteúdo para um InputStream
            ByteArrayInputStream newInputStream = new ByteArrayInputStream(content.getBytes());

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(newInputStream);
            document.getDocumentElement().normalize();

            String organization = getTagValueFromNodeList(document.getElementsByTagName("ORG"));
            String currency = getTagValueFromNodeList(document.getElementsByTagName("CURDEF"));
            String bankId = getTagValueFromNodeList(document.getElementsByTagName("BANKID"));
            String branchId = getTagValueFromNodeList(document.getElementsByTagName("BRANCHID"));
            String accountId = getTagValueFromNodeList(document.getElementsByTagName("ACCTID"));
            String accountType = getTagValueFromNodeList(document.getElementsByTagName("ACCTTYPE"));
            LocalDateTime dateStart = getLocalDateTime(Objects.requireNonNull(getTagValueFromNodeList(document.getElementsByTagName("DTSTART"))));
            LocalDateTime dateEnd = getLocalDateTime(Objects.requireNonNull(getTagValueFromNodeList(document.getElementsByTagName("DTEND"))));

            List<TransactionDto> transactionsList = new ArrayList<>();

            NodeList transactions = document.getElementsByTagName("STMTTRN");
            for (int i = 0; i < transactions.getLength(); i++) {
                Node node = transactions.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String transactionId = getTagValue("FITID", element);
                    BigDecimal amount = new BigDecimal(Objects.requireNonNull(getTagValue("TRNAMT", element)));
                    LocalDateTime date = getLocalDateTime(Objects.requireNonNull(getTagValue("DTPOSTED", element)));

                    transactionsList.add(new TransactionDto(transactionId, amount, date));
                }
            }

            return new AccountStatementDto(
                    organization, currency, bankId, branchId, accountId, accountType, dateStart, dateEnd, transactionsList
            );

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error: File can`t be processed!", e);
            return new AccountStatementDto();
        }
    }

    private String getTagValueFromNodeList(NodeList nodeList) {
        return (nodeList.getLength() > 0 && nodeList.item(0).getFirstChild() != null)
                ? nodeList.item(0).getFirstChild().getNodeValue()
                : null;
    }

    private String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag);
        if (nodeList.getLength() > 0 && nodeList.item(0).getChildNodes().getLength() > 0) {
            return nodeList.item(0).getChildNodes().item(0).getNodeValue();
        }
        return null;
    }

    private LocalDateTime getLocalDateTime(String stringDate) {
        String cleanedInput = stringDate.split("\\[")[0];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return LocalDateTime.parse(cleanedInput, formatter);
    }
}