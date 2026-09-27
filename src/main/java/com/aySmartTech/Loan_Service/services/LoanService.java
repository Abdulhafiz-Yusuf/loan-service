package com.aySmartTech.Loan_Service.services;

import com.aySmartTech.Loan_Service.dtos.LoanRequestDto;
import com.aySmartTech.Loan_Service.dtos.LoanResponseDto;

import java.util.List;

public interface LoanService {

    //create a loan
    LoanResponseDto createLoan(LoanRequestDto loanRequestDto);

    // get loan list
    List<LoanResponseDto> getAllLoans();

    // get a specific loan
    LoanResponseDto getLoanById(Long Id);

    // update a specific loan
    LoanResponseDto updateLoan(Long id, LoanRequestDto loanRequestDto);

    // delete a loan
    void deleteLoan(Long Id);
}
