package com.cashonline.examenbackend.unit;

import com.cashonline.examenbackend.model.User;
import com.cashonline.examenbackend.repository.UserRepository;
import com.cashonline.examenbackend.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService service;

    @MockBean
    private UserRepository repository;

    @Test
    public void saveShouldReturnAUser(){
        User toSave = new User();
        toSave.setFirstName("anyName");
        toSave.setLastName("anyLastName");
        toSave.setEmail("anyEmail@anyHost.com");
        when(repository.save(toSave)).thenReturn(new User());

        User returned = service.save(toSave);

        assertThat(returned).isInstanceOf(User.class);
    }


    @Test
    public void gettingAUserThatExistsShouldReturnUser(){
        Optional<User> found = Optional.of(new User());
        when(repository.findById(1L)).thenReturn(found);

        User returned = service.get(1L);

        assertThat(returned).isNotNull();
        assertThat(returned).isInstanceOf(User.class);
    }


    @Test
    public void shouldBeAbleToDeleteUsersById(){
        Optional<User> found = Optional.of(new User());
        when(repository.findById(1L)).thenReturn(found);
        service.delete(1L);

        verify(repository, times(1)).delete(found.get());
    }
}
