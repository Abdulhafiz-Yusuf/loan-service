package com.aySmartTech.Loan_Service.exceptions;

public class LoanAlreadyExistException extends RuntimeException {
    public LoanAlreadyExistException(String message) {
        super(message);
    }
}
