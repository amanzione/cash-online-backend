package com.cashonline.examenbackend.unit;

import com.cashonline.examenbackend.exception.UserValidationException;
import com.cashonline.examenbackend.model.User;
import com.cashonline.examenbackend.validator.Validator;
import com.cashonline.examenbackend.validator.impl.UserValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

public class UserValidatorTest {

    private Validator validator = new UserValidator();

    @Test
    public void validateUserWithoutFirstNameShouldThrowValidationException(){
        Exception e = assertThrows(UserValidationException.class, () -> {
            User toValidate = new User();
            validator.validate(toValidate);
        });

        assertThat("User first name not provided").isEqualTo(e.getMessage());
    }

    @Test
    public void validateUserWithoutLastNameShouldThrowValidationException(){
        Exception e = assertThrows(UserValidationException.class, () -> {
            User toValidate = new User();
            toValidate.setFirstName("anyName");
            validator.validate(toValidate);
        });

        assertThat("User last name not provided").isEqualTo(e.getMessage());
    }

    @Test
    public void validateUserWithoutEmailShouldThrowValidationException(){
        Exception e = assertThrows(UserValidationException.class, () -> {
            User toValidate = new User();
            toValidate.setFirstName("anyName");
            toValidate.setLastName("anyLastName");
            validator.validate(toValidate);
        });

        assertThat("User email not provided").isEqualTo(e.getMessage());
    }
}
