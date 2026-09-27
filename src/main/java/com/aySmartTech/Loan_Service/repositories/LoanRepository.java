package com.aySmartTech.Loan_Service.repositories;

import com.aySmartTech.Loan_Service.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    Loan findByMobileNumber(String mobileNumber);
}
