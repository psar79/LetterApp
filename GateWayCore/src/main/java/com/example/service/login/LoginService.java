package com.example.service.login;

import com.example.controller.LoginParam;
import com.example.controller.LoginResponse;
import com.example.getLettersByReceiver.LetterByPhoneNumber;
import com.example.getLettersByReceiver.LettersByPhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoginService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String USER_CORE_URL = "http://localhost:8081";
    private final String userHostUrl;

    public LoginService(@Value("${hosts.user-core-url}") String userCoreUrl) {
        this.userHostUrl = userCoreUrl;
    }

    public ResponseEntity<LoginResponse> getLogin(String phoneNumber, String token) {
//        String url = "http://localhost:8081/login?number=" + phoneNumber + "&freshToken=" + token;
        String url = userHostUrl + "/login?number=" + phoneNumber + "&freshToken=" + token;
        return restTemplate.getForEntity(url, LoginResponse.class);
    }

    public ResponseEntity<LoginResponse> getLogin2(String number, String token) {
        String url = "http://localhost:8081/login2/" + number + "/" + token;
        return restTemplate.getForEntity(url, LoginResponse.class);
    }

    public ResponseEntity<LoginResponse> getLogin3(String number, String token) {

        LoginParam loginParam = new LoginParam();
        loginParam.setNumberParam(number);
        loginParam.setFreshTokenParam(token);

        String url = "http://localhost:8081/login3";
        return restTemplate.postForEntity(url, loginParam, LoginResponse.class);
    }

    public ResponseEntity<LettersByPhoneNumber> getLetters(String phoneNumberFromRequest) {

//        LetterByPhoneNumber letterByPhoneNumber = new LetterByPhoneNumber();
//        letterByPhoneNumber.setPhoneNumberReceiverResponse(phoneNumber);

        LetterCoreRequestByPhoneNumber request = new LetterCoreRequestByPhoneNumber();
        request.setPhoneNumber(phoneNumberFromRequest);

//        LetterByPhoneNumber letterByPhoneNumber = new LetterByPhoneNumber();
//        letterByPhoneNumber.setPhoneNumberReceiverResponse(phoneNumber);
        String url = "http://localhost:8080/byPhoneNumber";
        return restTemplate.postForEntity(url, request, LettersByPhoneNumber.class);
    }

//public ResponseEntity<LettersByPhoneNumber> getLetters(String phoneNumber) {
//
//        LetterByPhoneNumberReceiverResponse letterByPhoneNumberReceiverResponse = new LetterByPhoneNumberReceiverResponse();
//        letterByPhoneNumberReceiverResponse.setPhoneNumberReceiverResponse(phoneNumber);
//        LetterByPhoneNumber letterByPhoneNumber = new LetterByPhoneNumber();
//        letterByPhoneNumber.setLetterByPhoneNumberReceiverResponse(letterByPhoneNumberReceiverResponse);
//        String url = "http://localhost:8080/byPhoneNumber;
//        return restTemplate.postForEntity(url, letterByPhoneNumber, LettersByPhoneNumber.class);
//    }
}
