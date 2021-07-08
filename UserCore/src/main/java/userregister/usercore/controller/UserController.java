package userregister.usercore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import userregister.usercore.utils.Validator;

@RestController
public class UserController {

    private Validator validator;

    @Autowired
    public UserController(Validator validator) {
        this.validator = validator;
    }

    @PostMapping
    public ResponseEntity<String> userRegister(@RequestParam String number) {

        if (validator.validatePhoneNumber(number)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please, give proper number");
        }


        return null;

    }


}
