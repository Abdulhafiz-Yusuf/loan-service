package com.aySmartTech.Loan_Service.exceptions;

public class AmountPaidGreaterThanLoanAmountException extends RuntimeException {
    public AmountPaidGreaterThanLoanAmountException(String message) {
        super(message);
    }
}
