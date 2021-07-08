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
import userregister.usercore.utils.CodeGenerator;
import userregister.usercore.utils.Validator;

@RestController
public class UserController {

    private Validator validator;
    private CodeGenerator codeGenerator;
    private RegisterManager registerManager;
    private RegisterMapper registerMapper;

    @Autowired
    public UserController(Validator validator, CodeGenerator codeGenerator, RegisterManager registerManager, RegisterMapper registerMapper) {
        this.validator = validator;
        this.codeGenerator = codeGenerator;
        this.registerManager = registerManager;
        this.registerMapper = registerMapper;
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

    public ResponseEntity<String> userRegister2(@RequestParam String number, String code){

        return null;

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


}
