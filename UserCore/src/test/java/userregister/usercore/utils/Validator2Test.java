package userregister.usercore.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    private Validator validator;

    @BeforeEach
    void setUp(){
        this.validator = new Validator();
    }

    @Test
    void checkIfWorkingWhenHappyPathForValidator2(){
        assertTrue(validator.validatePhoneNumberAndCode("123456789", "123456"));
    }

    @Test
    public void checkIfNullWhenStringsAreNull(){
        assertFalse(validator.validatePhoneNumberAndCode(null, null));
    }

    @Test
    public void checkIfFalseWhenStringIsMoreDigits(){

        //when
        boolean result = validator.validatePhoneNumberAndCode("1234567899", "1234567");

        //then
        assertFalse(result);
    }
    @Test
    public void checkIfFalseWhenStringIsLessDigits(){

        //when
        boolean result = validator.validatePhoneNumberAndCode("12345678", "12345");
        //then
        assertFalse(result);
    }
}