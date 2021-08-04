package com.horyzonty.util;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Validator {

    public boolean validatePhoneNumber(String number) {
        return Objects.nonNull(number) && number.length() == 9 && number.matches("\\d{9}");
    }

    public boolean validateToken(String token) {
        return (Objects.nonNull(token) && token.length() == 10 && token.matches("\\d{10}"));
    }
}
