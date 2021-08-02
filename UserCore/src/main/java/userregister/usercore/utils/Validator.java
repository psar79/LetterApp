package userregister.usercore.utils;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Validator {

    public boolean validatePhoneNumber(String number) {
        return Objects.nonNull(number) && number.length() == 9 && number.matches("\\d{9}");
    }

    public boolean validatePhoneNumberAndCode(String phoneNumber, String code) {
        return (Objects.nonNull(phoneNumber) && Objects.nonNull(code)
                && phoneNumber.length() == 9 && phoneNumber.matches("\\d{9}")
                && code.length() == 6 && code.matches("\\d{6}"));
    }
}
