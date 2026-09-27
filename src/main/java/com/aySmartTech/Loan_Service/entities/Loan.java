package com.aySmartTech.Loan_Service.entities;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Loan extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long loanId;
	private String mobileNumber;
	private String loanNumber;
	private String loanType;
	private BigDecimal totalLoanAmount;
	private BigDecimal amountPaid;
	private BigDecimal outstandingAmount;

}
