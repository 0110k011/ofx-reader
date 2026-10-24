package dto;

import java.time.LocalDateTime;
import java.util.List;

public record AccountStatement(
        String organization,
        String currency,
        String bankCode,
        String branchCode,
        String accountCode,
        String accountType,
        LocalDateTime dateStart,
        LocalDateTime dateEnd,
        List<Transaction> transactions,
        Integer transactionQuantity
) {}
