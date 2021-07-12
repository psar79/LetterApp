package com.example.service.login;

import com.example.controller.LetterByPhoneNumberReceiver;
import com.example.controller.LettersByPhoneNumberReceiver;
import com.example.controller.LoginParam;
import com.example.controller.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoginService {

    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<LoginResponse> getLogin(String phoneNumber, String token) {
        String url = "http://localhost:8081/login?number=" + phoneNumber + "&freshToken=" + token;
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

    public ResponseEntity<LettersByPhoneNumberReceiver> getLetters(Long phone){

        LetterByPhoneNumberReceiver letterByPhoneNumberReceiver = new LetterByPhoneNumberReceiver();
        letterByPhoneNumberReceiver.setNumberResponse(phone);

        String url = "http://localhost:8080/byPhoneNumber";
        return restTemplate.postForEntity(url,letterByPhoneNumberReceiver, LettersByPhoneNumberReceiver.class);

    }
}
