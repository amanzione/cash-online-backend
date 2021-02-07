package com.cashonline.examenbackend.service;

import com.cashonline.examenbackend.model.User;

public interface UserService {
    User save(User user);

    User get(Long userId);

    void delete(Long userId);
}
