package com.cashonline.examenbackend.service;

import com.cashonline.examenbackend.model.Loan;
import org.springframework.data.domain.Page;

public interface LoanService {
    Page<Loan> getWithPagination(int page, int pageSize);

    Page<Loan> getByUserIdWithPagination(int page, int pageSize, Long userId);
}
