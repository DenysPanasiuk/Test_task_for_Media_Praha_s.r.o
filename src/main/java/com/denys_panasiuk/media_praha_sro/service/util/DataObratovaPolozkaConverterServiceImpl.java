package com.denys_panasiuk.media_praha_sro.service.util;

import com.denys_panasiuk.media_praha_sro.model.gpc.DataObratovaPolozka;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class DataObratovaPolozkaConverterServiceImpl implements DataObratovaPolozkaConverterService{
    @Override
    public DataObratovaPolozka convertStringToDataObratovaPolozka(String dataObratovaPolozkaContent) {
        String typeOfEntity = dataObratovaPolozkaContent.substring(0, 3);
        if (typeOfEntity.equals("075")) {
            if (checkUrlForDataObratovaPolozka(dataObratovaPolozkaContent)) {
                return getDataObratovaPolozka(dataObratovaPolozkaContent);
            } else {
                return null;
            }
        } else {
            return null;
        }
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
