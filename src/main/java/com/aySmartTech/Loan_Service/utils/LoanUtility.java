package com.aySmartTech.Loan_Service.utils;

import com.aySmartTech.Loan_Service.dtos.ErrorResponseDto;
import com.aySmartTech.Loan_Service.dtos.LoanResponseDto;
import com.aySmartTech.Loan_Service.entities.Loan;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;


@Component
@RequiredArgsConstructor
public class LoanUtility {

    private Loan loan;

    public static LoanResponseDto mapToLoanResponseDto(Loan loan){
        return new LoanResponseDto(
                loan.getMobileNumber(),
                loan.getLoanNumber(),
                loan.getLoanType(),
                loan.getTotalLoanAmount(),
                loan.getAmountPaid(),
                loan.getOutstandingAmount()
        );
    }

    public static String generateLoanNumber(){
        return String.valueOf(1000000000L + new Random().nextLong(900000000));
    }

    // ---- Private helper: builds the response with the CORRECT status ----
    public static ResponseEntity<ErrorResponseDto> build(
            HttpStatus status, String message, HttpServletRequest req) {
        ErrorResponseDto body = new ErrorResponseDto(
                req.getRequestURI(),
                status,
                message,
                LocalDateTime.now()
        );
        return ResponseEntity.status(status).body(body);
    }
}
