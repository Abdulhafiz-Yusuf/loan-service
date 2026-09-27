package com.aySmartTech.Loan_Service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class LoanResponseDto {
    private String mobileNumber;
    private String loanNumber;
    private String loanType;
    private BigDecimal totalLoanAmount;
    private BigDecimal amountPaid;
    private BigDecimal outstandingAmount;
}
