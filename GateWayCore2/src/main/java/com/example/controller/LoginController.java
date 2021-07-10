package com.example.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@RestController
public class LoginController {

    @PostMapping("/getLetters")
    public String myLetters() {

        RestTemplate restTemplate = new RestTemplate();

        final String uri = "http://localhost:8081/login?number=123456789&freshToken=1234567899";
//        final String uri = "http://localhost:8081/login?number=" + phoneNumber + "&freshToken=" + token;

        ResponseEntity<LoginResponse> responseFromLogin = restTemplate.getForEntity(uri, LoginResponse.class);

        LoginResponse body = responseFromLogin.getBody();
        HttpHeaders headers = responseFromLogin.getHeaders();
        HttpStatus statusCode = responseFromLogin.getStatusCode();
        if (Objects.nonNull(body) && body.isLogged()) {
//        boolean logged = Objects.requireNonNull(responseFromLogin.getBody()).isLogged();
//        if (logged) {
            return "OK";
        }
        return "Bad";
    }

    @PostMapping("/getLetters2")
//    @PostMapping("/getLetters2/{phone}/{token}")
//    public String myLetters2(...) {
    public String myLetters2() {

        RestTemplate restTemplate = new RestTemplate();

        final String uri = "http://localhost:8081/login2/123456789/1234567899";

        ResponseEntity<LoginResponse> responseFromLogin = restTemplate.getForEntity(uri, LoginResponse.class);

        LoginResponse body = responseFromLogin.getBody();
        HttpHeaders headers = responseFromLogin.getHeaders();
        HttpStatus statusCode = responseFromLogin.getStatusCode();
        if (Objects.nonNull(body) && body.isLogged()) {
//        boolean logged = Objects.requireNonNull(responseFromLogin.getBody()).isLogged();
//        if (logged) {
            return "OK";
        }
        return "Bad";
    }

    @PostMapping("/getLetters3")
    public String myLetters3() {

        RestTemplate restTemplate = new RestTemplate();

        final String uri = "http://localhost:8081/login3";

        LoginParam request = new LoginParam();
        request.setNumberParam("123456789");
//        request.setNumberParam(request.getPhoneNumber());
        request.setFreshTokenParam("1234567899");

        ResponseEntity<LoginResponse> responseFromLogin = restTemplate.postForEntity(uri, request, LoginResponse.class);

        LoginResponse body = responseFromLogin.getBody();
        HttpHeaders headers = responseFromLogin.getHeaders();
        HttpStatus statusCode = responseFromLogin.getStatusCode();
        if (Objects.nonNull(body) && body.isLogged()) {
//        boolean logged = Objects.requireNonNull(responseFromLogin.getBody()).isLogged();
//        if (logged) {
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

