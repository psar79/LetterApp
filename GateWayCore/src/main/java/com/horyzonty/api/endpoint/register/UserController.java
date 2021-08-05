package com.horyzonty.api.endpoint.register;

import com.horyzonty.service.login.LoginService;
import com.horyzonty.util.Validator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final Validator validator;
    private final LoginService loginService;

    public UserController(Validator validator, LoginService loginService) {
        this.validator = validator;
        this.loginService = loginService;
    }

    @PostMapping("/registerStepOne")
    public ResponseEntity<Void> registerStepOne(@RequestParam String number) {
        if (!validator.validatePhoneNumber(number)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


    }
}
