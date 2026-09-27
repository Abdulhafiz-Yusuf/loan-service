package com.aySmartTech.Loan_Service.services.impl;

import com.aySmartTech.Loan_Service.dtos.LoanRequestDto;
import com.aySmartTech.Loan_Service.dtos.LoanResponseDto;
import com.aySmartTech.Loan_Service.exceptions.AmountPaidGreaterThanLoanAmountException;
import com.aySmartTech.Loan_Service.exceptions.LoanAlreadyExistException;
import com.aySmartTech.Loan_Service.exceptions.ResourceNotFoundException;
import com.aySmartTech.Loan_Service.utils.LoanUtility;
import com.aySmartTech.Loan_Service.repositories.LoanRepository;
import com.aySmartTech.Loan_Service.entities.Loan;
import com.aySmartTech.Loan_Service.services.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class LoanServiceImp implements LoanService {

    private final LoanRepository loanRepository;

    @Override
    @Transactional
    public LoanResponseDto createLoan(LoanRequestDto loanRequestDto) {
        Loan loan = loanRepository.findByMobileNumber(loanRequestDto.getMobileNumber());
        if(loan != null){
            throw new LoanAlreadyExistException("Loan already exist");
        }
        if(loanRequestDto.getAmountPaid().compareTo(loanRequestDto.getTotalLoanAmount()) > 0 ){
            throw new AmountPaidGreaterThanLoanAmountException(
                    "Amount Paid can not be greater the Total Loan Amount");
        }

        Loan newLoan = new Loan();
        newLoan.setMobileNumber(loanRequestDto.getMobileNumber());
        newLoan.setLoanType(loanRequestDto.getLoanType());
        newLoan.setLoanNumber(LoanUtility.generateLoanNumber());
        newLoan.setTotalLoanAmount(loanRequestDto.getTotalLoanAmount());
        newLoan.setAmountPaid(loanRequestDto.getAmountPaid());

        newLoan.setOutstandingAmount(loanRequestDto.getTotalLoanAmount().subtract(loanRequestDto.getAmountPaid()));

        Loan savedLoan = loanRepository.save(newLoan);

        return LoanUtility.mapToLoanResponseDto(savedLoan);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LoanResponseDto> getAllLoans() {
        return loanRepository.findAll().stream()
                .map(LoanUtility::mapToLoanResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public LoanResponseDto getLoanById(Long Id) {
        return loanRepository.findById(Id)
                .map(LoanUtility::mapToLoanResponseDto)
                .orElse(null);
    }

    @Override
    @Transactional
    public LoanResponseDto updateLoan(Long id, LoanRequestDto loanRequestDto) {
        Loan loan = loanRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Loan with id:" + id + "not found"));

        if(loanRequestDto.getAmountPaid().compareTo(loanRequestDto.getTotalLoanAmount()) > 0 ){
            throw new AmountPaidGreaterThanLoanAmountException(
                    "Amount Paid can not be greater the Total Loan Amount");
        }

        loan.setLoanType(loanRequestDto.getLoanType());
        loan.setTotalLoanAmount(loanRequestDto.getTotalLoanAmount());
        loan.setAmountPaid(loanRequestDto.getAmountPaid());
        loan.setMobileNumber(loanRequestDto.getMobileNumber());

        Loan savedLoan = loanRepository.save(loan);

        return LoanUtility.mapToLoanResponseDto(savedLoan);
    }

    @Override
    @Transactional
    public void deleteLoan(Long id) {
        if(!loanRepository.existsById(id)){
            throw new ResourceNotFoundException("Loan with id:" + id + "not found");
        }
        loanRepository.deleteById(id);
    }

}
