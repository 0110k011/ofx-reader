package com.ofxreader.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Transaction(
        String transactionCode,
        BigDecimal amount,
        LocalDateTime date,
        String description,
        String transactionType
) {}