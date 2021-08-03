package com.example.api.endpoint.letter;

import com.example.api.endpoint.letter.request.LoginParam;
import com.example.service.login.response.LoginResponse;
import com.example.api.endpoint.letter.response.LettersByPhoneNumber;
import com.example.service.login.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class LetterController {

    private final LoginService loginService;

    public LetterController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/letters")
    public ResponseEntity<LettersByPhoneNumber> myLetters(@RequestParam String phoneNumber, @RequestParam String token) {
        //walidacja phoneNumber i token ->
        // czy phoneNumber jest 9 cyfrowy
        // czy token jest 10 cyfrowy
        ResponseEntity<LoginResponse> login = loginService.getLogin(phoneNumber, token);

        if (Objects.isNull(login)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        LoginResponse body = login.getBody();
        //login.getStatusCode(); -> 200 mówi Ci, że operacja poszłą ok
        if (Objects.nonNull(body) && body.isLogged()) {
            //TODO: returyn ResponsEntity....
            return loginService.getLetters(phoneNumber);
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