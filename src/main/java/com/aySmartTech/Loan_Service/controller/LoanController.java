package com.aySmartTech.Loan_Service.controller;

import com.aySmartTech.Loan_Service.dtos.LoanRequestDto;
import com.aySmartTech.Loan_Service.dtos.LoanResponseDto;
import com.aySmartTech.Loan_Service.services.impl.LoanServiceImp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanServiceImp loanServiceImp;

    @PostMapping("")
    public ResponseEntity<LoanResponseDto> createLoan(@Valid @RequestBody LoanRequestDto loanRequestDto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(loanServiceImp.createLoan(loanRequestDto));
    }

    @GetMapping("")
    public ResponseEntity<List<LoanResponseDto>> getAllLoans(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loanServiceImp.getAllLoans());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanResponseDto> getLoanById(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loanServiceImp.getLoanById(id));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<LoanResponseDto> updateLoan(
            @PathVariable Long id,
            @Valid @RequestBody LoanRequestDto loanRequestDto){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loanServiceImp.updateLoan(id, loanRequestDto));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id){
        loanServiceImp.deleteLoan(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
