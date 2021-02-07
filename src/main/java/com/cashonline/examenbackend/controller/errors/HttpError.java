package com.cashonline.examenbackend.controller.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class HttpError {
    private int code;
    private String message;
}
