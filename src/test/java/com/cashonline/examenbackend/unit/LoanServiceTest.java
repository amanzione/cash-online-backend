package com.cashonline.examenbackend.unit;

import com.cashonline.examenbackend.model.Loan;
import com.cashonline.examenbackend.repository.LoanRepository;
import com.cashonline.examenbackend.service.LoanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class LoanServiceTest {

    @Autowired
    private LoanService service;

    @MockBean
    private LoanRepository repository;

    @Test
    public void fetchingLoansWithPaginationShouldReturnPage(){
        when(repository.findAll(PageRequest.of(0,5))).thenReturn(Page.empty());

        Page<Loan> returned = service.getWithPagination(0, 5);

        assertThat(returned).isNotNull();
        assertThat(returned).isInstanceOf(Page.class);
    }

    @Test
    public void fetchingLoanByUserIdWithPaginationShouldReturnPage(){
        when(repository.findByUserId(PageRequest.of(0,5), 1L)).thenReturn(Page.empty());

        Page<Loan> returned = service.getByUserIdWithPagination(0, 5, 1L);

        assertThat(returned).isNotNull();
        assertThat(returned).isInstanceOf(Page.class);
    }
}
