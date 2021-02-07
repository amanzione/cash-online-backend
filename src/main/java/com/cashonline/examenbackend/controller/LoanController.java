package com.cashonline.examenbackend.controller;

import com.cashonline.examenbackend.dto.LoanResponseDto;
import com.cashonline.examenbackend.dto.PagingDto;
import com.cashonline.examenbackend.model.Loan;
import com.cashonline.examenbackend.service.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @GetMapping("/loans")
    @ResponseStatus(HttpStatus.OK)
    public LoanResponseDto getLoans(@RequestParam int page, @RequestParam int size, @RequestParam Optional<Long> user_id){
        return buildLoanResponse(user_id.isEmpty() ? loanService.getWithPagination(page, size) : loanService.getByUserIdWithPagination(page, size, user_id.get()));
    }

    private LoanResponseDto buildLoanResponse(Page<Loan> page) {
        PagingDto paging = new PagingDto(page.getPageable().getPageNumber(), page.getPageable().getPageSize(), page.getTotalElements());
        LoanResponseDto response = new LoanResponseDto(page.getContent(), paging);

        return response;
    }

}
