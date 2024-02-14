package com.denys_panasiuk.media_praha_sro.model.gpc;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Gpc")
@Data
@Getter
@Setter
public class DataObratovaPolozka {
    @Id
    private String id;
    private String typeOfEntity; // 1 - 3 | "075" = record type designation "Data - turnover item"
    private String assignedAccountNumber; // 4 – 19 | assigned account number 16 numeric characters with leading zeros
    private String accountNumber; // 20 – 35 | account number 16 numeric characters with leading zeros (or in the order of prefix + account number)
    private String documentNumber; // 36 – 48 | document number 13 numeric characters
    private String amountInPennies; // 49 – 60 | amount in pennies 12 numeric characters with leading zeros
    private String billingCode; // 61 | billing code related to the account number: // 1 = debit item, // 2 = credit item, // 4 = item cancellation debit, 5 = cancellation of credit item
    private String variableSymbol; // 62 – 71 | variable symbol 10 numeric characters with leading zeros
    private String constantSymbol; // 72 – 81 | constant symbol 10 numeric characters with leading zeros in BBBBKSYM format where: BBBB - bank code, KSYM - constant symbol
    private String specificSymbol; // 82 – 91 | specific symbol 10 numeric characters with leading zeros
    private String paymentDueDate; // 92 – 97 | "000000" = currency, payment in the Czech Republic - due date in DDMMRR format
    private String abbreviatedNameOfTheClient; // 98 – 117 | 20 alphanumeric characters of the abbreviated name of the client, supplemented by spaces on the right
    private String zeroChar; // 118 | "0"
    private String currencyCode; // 119 – 122 | "0203" = currency code for CZK
    private String dueDate; // 123 – 128 | due date in DDMMRR format
    private String terminationCharacters; // 129 - 130 | termination characters CR and LF
}
