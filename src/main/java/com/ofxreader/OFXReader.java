package com.ofxreader;

import com.ofxreader.dto.AccountStatement;
import com.ofxreader.dto.Transaction;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OFXReader {

    private static final Logger logger = Logger.getLogger(OFXReader.class.getName());
    private static final DateTimeFormatter OFX_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    public AccountStatement processOFX(InputStream inputStream) {
        try {
            String content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

            int startIndex = content.indexOf("<OFX>");
            if (startIndex == -1) {
                throw new IllegalArgumentException("Tag <OFX> não encontrada no arquivo.");
            }
            content = content.substring(startIndex);

            ByteArrayInputStream newInputStream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(newInputStream);
            document.getDocumentElement().normalize();

            String organization = getTagValueFromNodeList(document.getElementsByTagName("ORG"));
            String currency = getTagValueFromNodeList(document.getElementsByTagName("CURDEF"));
            String bankCode = getTagValueFromNodeList(document.getElementsByTagName("BANKID"));
            String branchCode = getTagValueFromNodeList(document.getElementsByTagName("BRANCHID"));
            String accountCode = getTagValueFromNodeList(document.getElementsByTagName("ACCTID"));
            String accountType = getTagValueFromNodeList(document.getElementsByTagName("ACCTTYPE"));

            LocalDateTime dateStart = getLocalDateTime(Objects.requireNonNull(getTagValueFromNodeList(document.getElementsByTagName("DTSTART")), "DTSTART não pode ser nulo"));
            LocalDateTime dateEnd = getLocalDateTime(Objects.requireNonNull(getTagValueFromNodeList(document.getElementsByTagName("DTEND")), "DTEND não pode ser nulo"));

            List<Transaction> transactionsList = new ArrayList<>();
            NodeList transactions = document.getElementsByTagName("STMTTRN");

            for (int i = 0; i < transactions.getLength(); i++) {
                Node node = transactions.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String transactionCode = getTagValue("FITID", element);
                    String description = getTagValue("MEMO", element);
                    String transactionType = getTagValue("TRNTYPE", element);
                    BigDecimal amount = new BigDecimal(Objects.requireNonNull(getTagValue("TRNAMT", element)));
                    LocalDateTime date = getLocalDateTime(Objects.requireNonNull(getTagValue("DTPOSTED", element)));

                    transactionsList.add(new Transaction(transactionCode, amount, date, description, transactionType));
                }
            }

            return new AccountStatement(
                    organization, currency, bankCode, branchCode, accountCode,
                    accountType, dateStart, dateEnd, transactionsList, transactions.getLength()
            );
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao processar o arquivo OFX", e);
            throw new RuntimeException("Falha ao processar o arquivo OFX", e);
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
        return LocalDateTime.parse(cleanedInput, OFX_DATE_FORMATTER);
    }
}