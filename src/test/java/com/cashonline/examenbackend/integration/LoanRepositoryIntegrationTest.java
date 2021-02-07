package com.cashonline.examenbackend.integration;

import com.cashonline.examenbackend.model.Loan;
import com.cashonline.examenbackend.model.User;
import com.cashonline.examenbackend.repository.LoanRepository;
import com.cashonline.examenbackend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class LoanRepositoryIntegrationTest {

    private static int LOANS_FOR_USER_1 = 5;
    private static int OTHER_LOANS = 10;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private UserRepository userRepository;


    @Test
    public void shouldBeAbleToFetchLoansUsingPagination(){
        int page = 0;
        int pageSize = 5;

        //User needs to exist for loans to be created
        User user = new User();
        user = userRepository.save(user);

        saveLoansForUser(user, OTHER_LOANS);

        Pageable paging = PageRequest.of(page, pageSize);
        Page<Loan> loansPaged = loanRepository.findAll(paging);

        assertThat(loansPaged.getTotalElements()).isEqualTo(OTHER_LOANS);
        assertThat(loansPaged.getTotalPages()).isEqualTo(OTHER_LOANS/pageSize);
        assertThat(loansPaged.getContent().size()).isEqualTo(pageSize);
    }

    @Test
    public void shouldBeAbleToFetchLoansByUserIdUsingPagination(){
        //Create our main user
        User user1 = new User();
        user1 = userRepository.save(user1);

        //Create other users to generate noise
        User otherUser = new User();
        otherUser = userRepository.save(otherUser);

        //Insert loans for main user
        saveLoansForUser(user1, LOANS_FOR_USER_1);

        //Insert loans for other user to verify that i'm only fetching main user's loans
        saveLoansForUser(otherUser, OTHER_LOANS);

        //Page and size do not matter for this test
        Pageable paging = PageRequest.of(0, 5);
        Page<Loan> loansOfUser1Paged = loanRepository.findByUserId(paging, user1.getId());

        assertThat(loansOfUser1Paged.getTotalElements()).isEqualTo(LOANS_FOR_USER_1);
    }

    @Test
    public void askingForANonExistingPageNumberShouldReturnNoRecords(){
        int page = 10;
        int pageSize = 5;

        //User needs to exist for loans to be created
        User user = new User();
        user = userRepository.save(user);

        saveLoansForUser(user, OTHER_LOANS);

        Pageable paging = PageRequest.of(page, pageSize);
        Page<Loan> loansPaged = loanRepository.findAll(paging);

        assertThat(loansPaged.getContent().size()).isEqualTo(0);
    }


    private void saveLoansForUser(User user, int loans) {
        IntStream.range(0, loans).forEach( i -> {
            Loan l = new Loan();
            l.setUserId(user.getId());
            loanRepository.save(l);
        });
    }
}
