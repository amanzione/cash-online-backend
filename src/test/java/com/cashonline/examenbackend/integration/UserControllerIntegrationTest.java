package com.cashonline.examenbackend.integration;

import com.cashonline.examenbackend.model.Loan;
import com.cashonline.examenbackend.model.User;
import com.cashonline.examenbackend.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private UserRepository repository;

    //Create
    @Test
    public void creatingAUserWithoutNameShouldReturn400() throws Exception {
        User sent = new User();

        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(sent)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message", is("User first name not provided")));

    }

    @Test
    public void creatingAUserWithoutLastNameShouldReturn400() throws Exception{
        User sent = new User();
        sent.setFirstName("anyName");

        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(sent)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message", is("User last name not provided")));
    }

    @Test
    public void creatingUserWithoutEmailShouldReturn400() throws Exception{
        User sent = new User();
        sent.setFirstName("anyName");
        sent.setLastName("anyLastName");

        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(sent)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message", is("User email not provided")));
    }

    @Test
    public void creatingAUserShouldReturn201AndTheUser() throws Exception{
        User sent = new User();
        sent.setFirstName("anyName");
        sent.setLastName("anyLastName");
        sent.setEmail("anyEmail@anyHost.com");
        ArrayList<Loan> loans = new ArrayList<>();
        Loan l = new Loan();
        l.setTotal(1000.5);
        loans.add(l);
        sent.setLoans(loans);

        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(sent)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("firstName", is("anyName")))
                .andExpect(jsonPath("lastName", is("anyLastName")))
                .andExpect(jsonPath("email", is("anyEmail@anyHost.com")))
                .andExpect(jsonPath("loans[0].total", is(1000.5)));

    }

    //Delete
    @Test
    public void deletingAUserShouldDeleteTheUserAndReturn200() throws Exception{
        User saved = new User();
        saved = repository.save(saved);

        mvc.perform(delete("/users/".concat(saved.getId().toString())))
                .andExpect(status().isOk());

        assertThat(repository.findById(saved.getId())).isEmpty();
    }

    //GET
    @Test
    public void gettingAUserShouldReturnTheUser() throws Exception{
        User sent = new User();
        sent.setFirstName("anyName");
        sent.setLastName("anyLastName");
        sent.setEmail("anyEmail@anyHost.com");
        User saved = repository.save(sent);
        System.out.println(repository.findById(saved.getId()).get());

        mvc.perform(get("/users/".concat(saved.getId().toString())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstName", is("anyName")))
                .andExpect(jsonPath("lastName", is("anyLastName")))
                .andExpect(jsonPath("email", is("anyEmail@anyHost.com")));
    }

    @Test
    public void gettingAUserThatDoesNotExistShouldReturn404() throws Exception{
        mvc.perform(get("/users/12345"))
                .andExpect(status().isNotFound());
    }

}
