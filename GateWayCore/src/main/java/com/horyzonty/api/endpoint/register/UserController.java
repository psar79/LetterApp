package com.horyzonty.api.endpoint.register;

import com.horyzonty.service.login.LoginService;
import com.horyzonty.service.login.response.CodeResponse;
import com.horyzonty.service.login.response.PhoneNumberAndCodeResponse;
import com.horyzonty.util.Validator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class UserController {

    private final Validator validator;
    private final LoginService loginService;

    public UserController(Validator validator, LoginService loginService) {
        this.validator = validator;
        this.loginService = loginService;
    }

    @PostMapping("/registerStepOne")
    public ResponseEntity<CodeResponse> registerStepOne(@RequestParam String number) {
        if (!validator.validatePhoneNumber(number)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        ResponseEntity<CodeResponse> login = loginService.getLoginToken(number);
        if(Objects.isNull(login)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        CodeResponse body = login.getBody();
        if(Objects.isNull(body) || body.getCode().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(body);

    }
    @PostMapping("/registerStepTwo")
    public ResponseEntity<PhoneNumberAndCodeResponse> registerStepTwo(@RequestParam String number, @RequestParam String code){
        if (!validator.validateToken(code)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        ResponseEntity<PhoneNumberAndCodeResponse> login = loginService.getLogin(number, code);
        if(Objects.isNull(login)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        PhoneNumberAndCodeResponse body = login.getBody();
        if (Objects.isNull(body) || body.isLogged()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
