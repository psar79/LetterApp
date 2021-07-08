package userregister.usercore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import userregister.usercore.utils.CodeGenerator;
import userregister.usercore.utils.Validator;

@RestController
public class UserController {

    private Validator validator;
    private CodeGenerator codeGenerator;

    @Autowired
    public UserController(Validator validator, CodeGenerator codeGenerator) {
        this.validator = validator;
        this.codeGenerator = codeGenerator;
    }

    @PostMapping
    public ResponseEntity<String> userRegister(@RequestParam String number) {

        if (validator.validatePhoneNumber(number)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please, give proper number");
        }

        String code = codeGenerator.code();


        return null;

    }


}
