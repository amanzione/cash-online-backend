package com.cashonline.examenbackend.service.impl;

import com.cashonline.examenbackend.exception.UserNotFoundException;
import com.cashonline.examenbackend.model.User;
import com.cashonline.examenbackend.repository.UserRepository;
import com.cashonline.examenbackend.service.UserService;
import com.cashonline.examenbackend.validator.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final Validator<User> validator;

    @Override
    public User save(User user) {
        validator.validate(user);
        return repository.save(user);
    }

    @Override
    public User get(Long userId) {
        return repository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public void delete(Long userId) {

        Optional<User> toDelete = repository.findById(userId);
        repository.delete(toDelete.orElseThrow(() -> new UserNotFoundException("User not found")));
    }
}
