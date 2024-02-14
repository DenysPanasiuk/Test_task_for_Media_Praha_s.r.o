package com.denys_panasiuk.media_praha_sro.model.gpc;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Gpc")
@Data
@Getter
@Setter
public class DataVypis {
    @Id
    private String id;
    private String typeOfEntity; // 1 - 3 | "074" = designation of record type "Data - statement in CZK"
    private String assignedAccountNumber; // 4 - 19 | assigned account number with leading zeros
    private String accountHolderName; // 20 - 39 | 20 alphanumeric characters of the abbreviated account name, supplemented with spaces from the right
    private String oldBalanceDate; // 40 - 45 | date of old balance in DDMMRR format
    private String oldBalanceInPennies; // 46 - 59 | old balance in pennies 14 numeric characters with leading zeros
    private String oldBalanceSign; // 60 | old balance sign, 1 "+" or "-" sign
    private String newBalanceInPennies; // 61 - 74 | new balance in pennies 14 numeric characters with leading zeros
    private String newBalanceSign; // 75 | new balance sign, 1 "+" or "-" sign
    private String debitTransactions; // 76 - 89 | turns debit (MD) in pennies 14 numeric characters with leading zeros
    private String debitReversalSign; // 90 | Debit turnover sign (MD), 1 character "0" or "-"
    private String turnoverCredit; // 91 - 104 | turns credit (D) in pennies 14 numeric characters with leading zeros
    private String creditTurnoverSign; // 105 | credit turns sign (D), 1 character "0" or "-"
    private String serialNumberOfTheStatement; // 106 - 108 | serial number of the statement
    private String billingDate; // 109 - 114 | posting date in DDMMRR format
    private String filledSpaceCharacters; // 115 - 128 (filled with 14 space characters due to the unification of the length of records)
    private String terminationCharacters; // 129 - 130 termination characters CR and LF
}
