package com.horyzonty.api.endpoint.letter;

import com.horyzonty.api.endpoint.letter.request.LoginParam;
import com.horyzonty.api.endpoint.letter.response.Letters;
import com.horyzonty.service.letter.LetterService;
import com.horyzonty.util.Validator;
import com.horyzonty.service.login.LoginService;
import com.horyzonty.service.login.response.LoginResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class LetterController {

    private final LetterService letterService;
    private final LoginService loginService;
    private final Validator validator;

    public LetterController(LetterService letterService, LoginService loginService, Validator validator) {
        this.letterService = letterService;
        this.loginService = loginService;
        this.validator = validator;
    }

    @GetMapping("/letters")
    public ResponseEntity<Letters> myLetters(@RequestParam String phoneNumber, @RequestParam String token) {
        if(!validator.validatePhoneNumber(phoneNumber) || !validator.validateToken(token)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        ResponseEntity<LoginResponse> login = loginService.getLogin(phoneNumber, token);

        if (Objects.isNull(login)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        LoginResponse body = login.getBody();
        if (Objects.nonNull(body) && body.isLogged()) {
                   return ResponseEntity.ok().body(letterService.getLetters(phoneNumber));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/getLetters2/{number}/{token}")
    public String myLetters2(@PathVariable("number") String number, @PathVariable("token") String token) {
        //TODO: add number and token validation
        ResponseEntity<LoginResponse> serviceLogin2 = loginService.getLogin2(number, token);

        LoginResponse body = serviceLogin2.getBody();
        if (Objects.nonNull(body) && body.isLogged()) {
            return "OK";
        }
        return "Bad";
    }

    @PostMapping("/getLetters3")
    public String myLetters3(@RequestBody LoginParam loginParam) {
        ResponseEntity<LoginResponse> serviceLogin3 = loginService.getLogin3(loginParam.getNumberParam(), loginParam.getFreshTokenParam());

        LoginResponse body = serviceLogin3.getBody();
        if (Objects.nonNull(body) && body.isLogged()) {
            return "OK";
        }
        return "Bad";
    }
}