package com.aySmartTech.Loan_Service.dtos;

import jakarta.validation.constraints.*;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.math.BigDecimal;

@Value
public class LoanRequestDto implements Serializable {

    @NotBlank(message = "mobileNumber can not be empty")
    @Pattern(regexp = "\\d{11}", message = "mobileNumber must be 11 digits")
    String mobileNumber;

    @NotBlank(message = "loanType can not be empty")
    String loanType;

    @Positive(message = "totalLoanAmount must be greater than zero")
    BigDecimal totalLoanAmount;

    @PositiveOrZero(message = "amountPaid must be zero or greater")
    BigDecimal amountPaid;
}
