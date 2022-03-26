package userregister.usercore.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import userregister.usercore.dao.entity.Register;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RegisterMapperTest {

    private RegisterMapper registerMapper;

    @BeforeEach
    void setUP() {
        this.registerMapper = new RegisterMapper();
    }

    @Test
    void checkIfWorkingWhenHappyPath() {
        //given
        String number = "232";
        String code = "23333";

        //when
        Register register = registerMapper.setRegister(number, code);

        //then
        assertEquals("232", register.getPhoneNumber());
        assertEquals("23333", register.getCode());
    }

    @Test
    void shouldReturnNullWhenNumberIsNull() {

        //when
        Register result = registerMapper.setRegister(null, "wer");

        //then
        assertNull(result);
    }

    @Test
    void shouldReturnNullWhenCodeIsNull() {

        //when
//        Register result = registerMapper.getRegister("dsf", null);

        //then
//        assertEquals(null, result.getCode());

        //when
        Register result = registerMapper.setRegister("dsf", null);

        //then
        assertNull(result);
    }

    @Test
    void shouldReturnNullWhenNumberAndCodeIsNull() {

        //when
        Register result = registerMapper.setRegister(null, null);

        //then
        assertNull(result);
    }
}