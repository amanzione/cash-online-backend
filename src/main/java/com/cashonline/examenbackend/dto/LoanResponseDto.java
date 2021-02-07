package com.cashonline.examenbackend.dto;

import com.cashonline.examenbackend.model.Loan;
import lombok.Data;

import java.util.List;

@Data
public class LoanResponseDto {

    private final List<Loan> items;

    private final PagingDto paging;
}

