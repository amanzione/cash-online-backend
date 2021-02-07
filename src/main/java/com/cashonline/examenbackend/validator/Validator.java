package com.cashonline.examenbackend.validator;

public interface Validator<T> {

    void validate(T toValidate);
}
