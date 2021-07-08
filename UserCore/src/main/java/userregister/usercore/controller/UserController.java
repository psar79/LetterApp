package userregister.usercore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import userregister.usercore.dao.entity.Register;
import userregister.usercore.manager.RegisterManager;
import userregister.usercore.mapper.RegisterMapper;
import userregister.usercore.mapper.UserMapper;
import userregister.usercore.utils.CodeGenerator;
import userregister.usercore.utils.Validator;
import userregister.usercore.utils.Validator2;

@RestController
public class UserController {

    private Validator validator;
    private Validator2 validator2;
    private CodeGenerator codeGenerator;
    private RegisterManager registerManager;
    private RegisterMapper registerMapper;
    //    private RefreshToken refreshToken;
    private UserMapper userMapper;

//    @Autowired
//    public UserController(Validator validator, Validator2 validator2, CodeGenerator codeGenerator, RegisterManager registerManager, RegisterMapper registerMapper, RefreshToken refreshToken, UserMapper userMapper) {
//        this.validator = validator;
//        this.validator2 = validator2;
//        this.codeGenerator = codeGenerator;
//        this.registerManager = registerManager;
//        this.registerMapper = registerMapper;
//        this.refreshToken = refreshToken;
//        this.userMapper = userMapper;
//    }

    @Autowired
    public UserController(Validator validator, Validator2 validator2, CodeGenerator codeGenerator, RegisterManager registerManager, RegisterMapper registerMapper, UserMapper userMapper) {
        this.validator = validator;
        this.validator2 = validator2;
        this.codeGenerator = codeGenerator;
        this.registerManager = registerManager;
        this.registerMapper = registerMapper;
        this.userMapper = userMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<String> userRegister(@RequestParam String number) {

        if (!validator.validatePhoneNumber(number)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please, give proper number");
        }

        String code = codeGenerator.code();

        Register register = registerMapper.getRegister(number, code);

        registerManager.registerUser(register);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/add2")
    public ResponseEntity<String> userRegister2(@RequestParam String number, String code) {

        if (!validator2.validatePhoneNumberAndCode(number, code)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please, give proper number");
        }

        Register register = registerManager.findByPhoneNumber(number);
        if (register.getCode().equals(code)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return null;
////        registerManager.registerUser(register);
//
//        String token = refreshToken.tokenCreator();
//
//        User user = userMapper.getUser(number, token);
//
//        registerManager.saveUser(user);
//
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(token);
    }
}


/////////////////////

//        Register registerr = registerManager.findByPhoneNumber(phoneNumber);
//
//        registerr.getCode().equals(code)
//                jesli true, to wtedy zapisz do tebelki 'uzytkonwicy'
//
//
//        return ResponseEntity.ok().build();

///////////////////////

//    }



