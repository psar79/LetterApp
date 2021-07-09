package userregister.usercore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import userregister.usercore.dao.entity.Register;
import userregister.usercore.dao.entity.User;
import userregister.usercore.manager.RegisterManager;
import userregister.usercore.mapper.RegisterMapper;
import userregister.usercore.mapper.UserMapper;
import userregister.usercore.utils.CodeGenerator;
import userregister.usercore.utils.RefreshToken;
import userregister.usercore.utils.Validator;
import userregister.usercore.utils.Validator2;

import java.util.Objects;

@RestController
public class UserController {

    private final Validator validator;
    private final Validator2 validator2;
    private final CodeGenerator codeGenerator;
    private final RegisterManager registerManager;
    private final RegisterMapper registerMapper;
    private final RefreshToken refreshToken;
    private final UserMapper userMapper;

    @Autowired
    public UserController(Validator validator, Validator2 validator2, CodeGenerator codeGenerator, RegisterManager registerManager, RegisterMapper registerMapper, RefreshToken refreshToken, UserMapper userMapper) {
        this.validator = validator;
        this.validator2 = validator2;
        this.codeGenerator = codeGenerator;
        this.registerManager = registerManager;
        this.registerMapper = registerMapper;
        this.refreshToken = refreshToken;
        this.userMapper = userMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<String> userRegister(@RequestParam String number) {

        if (!validator.validatePhoneNumber(number)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please, give proper number");
        }

        String code = codeGenerator.code();

        Register register = registerMapper.getRegister(number, code);

        registerManager.addNewRegisterUser(register);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/add2")
    public ResponseEntity<String> userRegister2(@RequestParam String number, @RequestParam  String code) {

        if (!validator2.validatePhoneNumberAndCode(number, code)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please, give proper number");
        }

        Register register = registerManager.findByPhoneNumber(number);

        if (Objects.isNull(register) || !register.getCode().equals(code)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
//        if (register=null || !register.getCode().equals(code)) {
//            return ResponseEntity.status(HttpStatus.OK).build();
//        }

////        registerManager.registerUser(register);

        String token = refreshToken.tokenCreator();

        User user = userMapper.getUser(number, token);

        registerManager.addUser(user);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(token);
    }
}






