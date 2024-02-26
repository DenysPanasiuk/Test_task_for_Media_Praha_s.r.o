package com.denys_panasiuk.media_praha_sro.service.util;

import com.denys_panasiuk.media_praha_sro.model.gpc.DataVypis;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class DataVypisConverterServiceImpl implements DataVypisConverterService{
    @Override
    public DataVypis convertStringToDataVypis(String dataVypisContent) {
        String typeOfEntity = dataVypisContent.substring(0, 3);
        if (typeOfEntity.equals("074")) {
            if (checkUrlForDataVypis(dataVypisContent)) {
                return getDataVypis(dataVypisContent);
            } else {
                return null;
            }
        }
        return null;
    }

    private boolean checkUrlForDataVypis(String url) {
        boolean isAssignedAccountNumberValid = url.substring(3, 19).matches("\\d+");
        boolean idAccountHolderNameValid = url.substring(19, 39).matches("[a-zA-Z,\\s]+");
        boolean isOldBalanceDateValid = isValidDateDDMMRR(url.substring(39, 45));
        boolean isOlcBalanceInPenniesValid = url.substring(45, 59).matches("\\d+");
        boolean isOldBalanceSignValid = (url.substring(59, 60).contains("+") || url.substring(59, 60).contains("-"));
        boolean isNewBalanceInPenniesValid = url.substring(60, 74).matches("\\d+");
        boolean isNewBalanceSignValid = (url.substring(74, 75).contains("+") || url.substring(74, 75).contains("-"));
        boolean isDebitTransactionsValid = url.substring(75, 89).matches("\\d+");
        boolean isDebitReversalSignValid = (url.substring(89, 90).contains("0") || url.substring(89, 90).contains("-"));
        boolean isTurnoverCreditValid = url.substring(90, 104).matches("\\d+");
        boolean isCreditTurnoverSignValid = (url.substring(104, 105).contains("0") || url.substring(89, 90).contains("-"));
        boolean isSerialNumberOfTheStatementValid = url.substring(105, 108).matches("\\d+");
        boolean isBillingDateValid = isValidDateDDMMRR(url.substring(108, 114));
        boolean isFilledSpaceCharactersValid = url.substring(114, 128).trim().isEmpty();
        boolean isTerminationCharactersValid = (url.substring(128, 130).contains("CR") || url.substring(128, 130).contains("LF"));

        return isAssignedAccountNumberValid & idAccountHolderNameValid
                & isOldBalanceDateValid & isOlcBalanceInPenniesValid
                & isOldBalanceSignValid & isNewBalanceInPenniesValid
                & isNewBalanceSignValid & isDebitTransactionsValid
                & isDebitReversalSignValid & isTurnoverCreditValid
                & isCreditTurnoverSignValid & isSerialNumberOfTheStatementValid
                & isBillingDateValid & isFilledSpaceCharactersValid
                & isTerminationCharactersValid;
    }

    private boolean isValidDateDDMMRR(String dateStr) {
        if (dateStr == null || dateStr.length() != 6) {
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
        sdf.setLenient(false);

        try {
            sdf.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private DataVypis getDataVypis(String url) {
        DataVypis dataVypis = new DataVypis();
        dataVypis.setTypeOfEntity(url.substring(0, 3));
        dataVypis.setAssignedAccountNumber(url.substring(3, 19));
        dataVypis.setAccountHolderName(url.substring(19, 39));
        dataVypis.setOldBalanceDate(url.substring(39, 45));
        dataVypis.setOldBalanceInPennies(url.substring(45, 59));
        dataVypis.setOldBalanceSign(url.substring(59, 60));
        dataVypis.setNewBalanceInPennies(url.substring(60, 74));
        dataVypis.setNewBalanceSign(url.substring(74, 75));
        dataVypis.setDebitTransactions(url.substring(75, 75));
        dataVypis.setDebitReversalSign(url.substring(89, 90));
        dataVypis.setTurnoverCredit(url.substring(90, 104));
        dataVypis.setCreditTurnoverSign(url.substring(104, 105));
        dataVypis.setSerialNumberOfTheStatement(url.substring(105, 108));
        dataVypis.setBillingDate(url.substring(108, 114));
        dataVypis.setFilledSpaceCharacters(url.substring(114, 128));
        dataVypis.setTerminationCharacters(url.substring(128, 130));
        return dataVypis;
    }
}
