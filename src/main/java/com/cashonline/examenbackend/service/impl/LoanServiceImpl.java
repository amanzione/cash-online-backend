package com.cashonline.examenbackend.service.impl;

import com.cashonline.examenbackend.model.Loan;
import com.cashonline.examenbackend.repository.LoanRepository;
import com.cashonline.examenbackend.service.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoanRepository repository;

    @Override
    public Page<Loan> getWithPagination(int page, int pageSize) {
        return repository.findAll(PageRequest.of(page, pageSize));
    }

    @Override
    public Page<Loan> getByUserIdWithPagination(int page, int pageSize, Long userId) {
        return repository.findByUserId(PageRequest.of(page, pageSize), userId);
    }
}
