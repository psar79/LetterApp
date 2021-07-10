package com.example.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@RestController
public class LoginController {

    @GetMapping("/getLetters")
    public String myLetters() {

        RestTemplate restTemplate = new RestTemplate();

        final String uri = "http://localhost:8081/login?number=123456789&freshToken=1234567899";

        ResponseEntity<LoginResponse> responseFromLogin = restTemplate.getForEntity(uri, LoginResponse.class);

        boolean logged = Objects.requireNonNull(responseFromLogin.getBody()).isLogged();
        if (logged) {
            return "OK";
        }
        return "Bad";
    }

    //        LoginParam loginParam = new LoginParam();
//        loginParam.setNumberParam(number);
//        loginParam.setFreshTokenParam(freshToken);
//        LoginResponse body = responseFromLogin.getBody();
//        HttpHeaders headers = responseFromLogin.getHeaders();
//        HttpStatus statusCode = responseFromLogin.getStatusCode();

//        boolean logged = Objects.requireNonNull(responseFromLogin.getBody()).isLogged();

//        if(logged){
//            restTemplate.getForEntity("http//localhost:8080/add2/user")
//        }



//    @GetMapping("/checkResponse")
//    public String toCheck(){
//        RestTemplate restTemplate = new RestTemplate();
//
//        ResponseEntity<CheckResponse> forEntity = restTemplate.getForEntity("http://localhost:8081/check", CheckResponse.class);
//
//        return Objects.requireNonNull(forEntity.getBody()).getCheckFromResponse();
//    }

}

