package com.cashonline.examenbackend.validator.impl;

import com.cashonline.examenbackend.exception.UserValidationException;
import com.cashonline.examenbackend.model.User;
import com.cashonline.examenbackend.validator.Validator;
import org.springframework.stereotype.Component;

@Component
public class UserValidator implements Validator<User> {

    @Override
    public void validate(User toValidate) {
        if(toValidate.getFirstName() == null || toValidate.getFirstName().isBlank()){
            throw new UserValidationException("User first name not provided");
        }
        if(toValidate.getLastName() == null || toValidate.getLastName().isBlank()){
            throw new UserValidationException("User last name not provided");
        }
        if(toValidate.getEmail() == null || toValidate.getEmail().isBlank()){
            throw new UserValidationException("User email not provided");
        }
    }
}
