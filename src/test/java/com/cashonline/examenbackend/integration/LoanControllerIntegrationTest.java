package com.cashonline.examenbackend.integration;

import com.cashonline.examenbackend.model.Loan;
import com.cashonline.examenbackend.model.User;
import com.cashonline.examenbackend.repository.LoanRepository;
import com.cashonline.examenbackend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LoanControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoanRepository loanRepository;


    @Test
    public void gettingLoansShouldReturnLoansAndPaginationInfo() throws Exception{

        //There needs to be a user for loans to be saved
        User user = saveUser();
        saveLoanForUser(user);

        mvc.perform(get("/loans?page=0&size=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("items[0].total", is(1500.0)))
                .andExpect(jsonPath("paging.page", is(0)))
                .andExpect(jsonPath("paging.size", is(10)));


    }

    @Test
    public void gettingLoansByUserIdShouldReturnLoansAndPaginationInfo() throws Exception{
        User user = saveUser();
        saveLoanForUser(user);
        saveLoanForUser(user);

        mvc.perform(get("/loans?page=0&size=10&user_id=".concat(user.getId().toString())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("items[0].total", is(1500.0)))
                .andExpect(jsonPath("paging.page", is(0)))
                .andExpect(jsonPath("paging.size", is(10)))
                .andExpect(jsonPath("items[0].userId", is(user.getId().intValue())));
    }

    private User saveUser() {
        User user = new User();
        user.setFirstName("aName");
        user.setLastName("aLastName");
        user.setEmail("email@host.com");
        user = userRepository.save(user);
        return user;
    }

    private void saveLoanForUser(User user) {
        Loan l = new Loan();
        l.setTotal(1500.0);
        l.setUserId(user.getId());
        loanRepository.save(l);
    }

}
