package com.denys_panasiuk.media_praha_sro.service.util;

import com.denys_panasiuk.media_praha_sro.model.gpc.DataObratovaPolozka;
import com.denys_panasiuk.media_praha_sro.model.gpc.DataVypis;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class GpcConvertServiceImpl implements GpcConvertService{
    @Override
    public List<Object> convertUrlToGpcDtoEntity(String url) {
        if (url != null) {
            List<Object> listOfGpcObjects = new ArrayList<>();
            try {
                if ((url.length() % 130) == 0) {
                    String urlRepresantation;
                    for (int i = 0; i < url.length() / 130; i++) {
                        urlRepresantation = url.substring((i * 130), (++i * 130));
                        Object gpcObject = convertStringToEntity(urlRepresantation);
                        if (gpcObject == null) return null;
                        else listOfGpcObjects.add(gpcObject);
                    }
                } else {
                    return null;
                }
            } catch (IndexOutOfBoundsException e) {
                return null;
            }
            return listOfGpcObjects;
        } else {
            return null;
        }
    }

    private Object convertStringToEntity(String url) {
        String typeOfEntity = url.substring(0, 3);
        if (typeOfEntity.equals("074")) {
            if (checkUrlForDataVypis(url)) {
                return getDataVypis(url);
            } else
                return null;
        } else if (typeOfEntity.equals("075")) {
            if (checkUrlForDataObratovaPolozka(url)) {
                return getDataObratovaPolozka(url);
            } else
                return null;
        } else {
            return null;
        }
    }

    private boolean checkUrlForDataVypis(String url) {
        boolean isAssignedAccountNumberValid = url.substring(3, 19).matches("\\d+");
        boolean idAccountHolderNameValid = url.substring(19, 39).matches("[a-zA-Z,\\s]+");
        boolean isOldBalanceDateValid = isValidDateDDMMRR(url.substring(39, 45));
        boolean isOlcBalanceInPenniesValid = url.substring(45, 59).matches("\\d+");
        boolean isOldBalanceSignValid = (url.substring(59, 60).contains(" ") || url.substring(59, 60).contains("-"));
        boolean isNewBalanceInPenniesValid = url.substring(60, 74).matches("\\d+");
        boolean isNewBalanceSignValid = (url.substring(74, 75).contains(" ") || url.substring(74, 75).contains("-"));
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

    private boolean checkUrlForDataObratovaPolozka(String url) {
        boolean isAssignedAccountNumberValid = url.substring(3, 19).matches("\\d+");
        boolean idAccountNumberValid = url.substring(19, 35).matches("\\d+");
        boolean isDocumentNumberValid = url.substring(35, 48).matches("\\d+");
        boolean isAmountInPenniesValid = url.substring(48, 60).matches("\\d+");
        boolean isBillingCodeValid = (url.substring(60, 61).contains("1")
                                    || url.substring(60, 61).contains("2")
                                    || url.substring(60, 61).contains("4")
                                    || url.substring(60, 61).contains("5"));

        boolean isVariableSymbolValid = url.substring(61, 71).matches("\\d+");
        boolean isConstantSymbolValid = url.substring(71, 81).matches("\\d+");
        boolean isSpecificSymbolValid = url.substring(81, 91).matches("\\d+");
        boolean isPaymentDueDateValid = isValidDateDDMMRR(url.substring(91, 97));
        boolean isAbbreviatedNameOfTheClientValid = url.substring(97, 117).matches("[a-zA-Z,\\s]+");
        boolean isZeroCharValid = url.substring(117, 118).contains("0");
        boolean isCurrencyCodeValid = url.startsWith("0203", 118);
        boolean isDueDateValid = isValidDateDDMMRR(url.substring(122, 128));
        boolean isTerminationCharactersValid = (url.substring(128, 130).contains("CR") || url.substring(128, 130).contains("LF"));

        return isAssignedAccountNumberValid & idAccountNumberValid
                & isDocumentNumberValid & isAmountInPenniesValid
                & isBillingCodeValid & isVariableSymbolValid
                & isConstantSymbolValid & isSpecificSymbolValid
                & isPaymentDueDateValid & isAbbreviatedNameOfTheClientValid
                & isZeroCharValid & isCurrencyCodeValid
                & isDueDateValid & isTerminationCharactersValid;
    }

    public static boolean isValidDateDDMMRR(String dateStr) {
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

    private DataObratovaPolozka getDataObratovaPolozka(String url) {
        DataObratovaPolozka dataObratovaPolozka = new DataObratovaPolozka();
        dataObratovaPolozka.setTypeOfEntity(url.substring(0, 3));
        dataObratovaPolozka.setAssignedAccountNumber(url.substring(3, 19));
        dataObratovaPolozka.setAccountNumber(url.substring(19, 35));
        dataObratovaPolozka.setDocumentNumber(url.substring(35, 48));
        dataObratovaPolozka.setAmountInPennies(url.substring(48, 60));
        dataObratovaPolozka.setBillingCode(url.substring(60, 61));
        dataObratovaPolozka.setVariableSymbol(url.substring(61, 71));
        dataObratovaPolozka.setConstantSymbol(url.substring(71, 81));
        dataObratovaPolozka.setSpecificSymbol(url.substring(81, 91));
        dataObratovaPolozka.setPaymentDueDate(url.substring(91, 97));
        dataObratovaPolozka.setAbbreviatedNameOfTheClient(url.substring(97, 117));
        dataObratovaPolozka.setZeroChar(url.substring(117, 118));
        dataObratovaPolozka.setCurrencyCode(url.substring(118, 122));
        dataObratovaPolozka.setDueDate(url.substring(122, 128));
        dataObratovaPolozka.setTerminationCharacters(url.substring(128, 130));
        return dataObratovaPolozka;
    }
}
