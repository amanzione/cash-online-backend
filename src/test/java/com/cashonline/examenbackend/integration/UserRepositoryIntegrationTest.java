package com.cashonline.examenbackend.integration;

import com.cashonline.examenbackend.model.Loan;
import com.cashonline.examenbackend.model.User;
import com.cashonline.examenbackend.repository.LoanRepository;
import com.cashonline.examenbackend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository repository;
    @Autowired
    private LoanRepository loanRepository;

    @Test
    public void shouldBeAbleToSaveUserAndFindItById(){
        User userSaved = repository.save(new User());
        User userFetched = repository.findById(userSaved.getId()).get();

        assertThat(userSaved.getId()).isEqualTo(userFetched.getId());
    }

    @Test
    public void fetchingAUserShouldAlsoGetHisLoans(){
        Loan loan1 = new Loan();
        Loan loan2 = new Loan();
        ArrayList<Loan> userLoans = new ArrayList<>();
        userLoans.add(loan1);
        userLoans.add(loan2);

        User userSaved = new User();
        userSaved.setLoans(userLoans);

        userSaved = repository.save(userSaved);
        User userFetched = repository.findById(userSaved.getId()).get();

        assertThat(userFetched.getLoans().size()).isEqualTo(userLoans.size());
    }

    @Test
    public void deletingAUserShouldDeleteHisLoans(){
        Loan loan1 = new Loan();
        Loan loan2 = new Loan();
        ArrayList<Loan> userLoans = new ArrayList<>();
        userLoans.add(loan1);
        userLoans.add(loan2);

        User userSaved = new User();
        userSaved.setLoans(userLoans);

        userSaved = repository.save(userSaved);

        Long loan1Id = userSaved.getLoans().get(0).getId();
        Loan foundFirstTime = loanRepository.findById(loan1Id).get();
        assertThat(foundFirstTime).isNotNull();

        repository.delete(userSaved);
        Optional<Loan> foundSecondTime = loanRepository.findById(loan1Id);
        assertThat(foundSecondTime).isEmpty();
    }

}
