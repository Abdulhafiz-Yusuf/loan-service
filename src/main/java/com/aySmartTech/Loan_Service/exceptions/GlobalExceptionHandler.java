package com.aySmartTech.Loan_Service.exceptions;

import com.aySmartTech.Loan_Service.dtos.ErrorResponseDto;
import com.aySmartTech.Loan_Service.utils.LoanUtility;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFound(
            ResourceNotFoundException e, HttpServletRequest req) {
        return LoanUtility.build(HttpStatus.NOT_FOUND, e.getMessage(), req);
    }

    @ExceptionHandler(LoanAlreadyExistException.class)
    public ResponseEntity<ErrorResponseDto> handleLoanAlreadyExist(
            LoanAlreadyExistException e, HttpServletRequest req) {
        // 409 CONFLICT, not 200 OK
        return LoanUtility.build(HttpStatus.CONFLICT, e.getMessage(), req);
    }

    @ExceptionHandler(AmountPaidGreaterThanLoanAmountException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidAmountPaid(
            AmountPaidGreaterThanLoanAmountException e, HttpServletRequest req) {
        return LoanUtility.build(HttpStatus.BAD_REQUEST, e.getMessage(), req);
    }

    // ---- 400 Bad Request: Jakarta Validation failures ----
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidation(
            MethodArgumentNotValidException e, HttpServletRequest req) {

        String errorMessage = e.getBindingResult().getFieldErrors().stream()
                .map(fe -> fe.getDefaultMessage())
                .collect(Collectors.joining("; "));

        if (errorMessage.isBlank()) {
            errorMessage = "Validation failed";
        }

        return LoanUtility.build(HttpStatus.BAD_REQUEST, errorMessage, req);
    }

    // ---- Catch-all safety net ----
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGeneric(
            Exception e, HttpServletRequest req) {
        return LoanUtility.build(HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected error occurred", req);
    }

}
