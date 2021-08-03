package com.example.service.login;

import com.example.api.endpoint.letter.request.LoginParam;
import com.example.service.login.request.LoginParameters;
import com.example.service.login.response.LoginResponse;
import com.example.api.endpoint.letter.response.LettersByPhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoginService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String userHostUrl;
    private final String letterCoreUrl;

    public LoginService(@Value("${hosts.user-core-url}") String userCoreUrl,
                        @Value("${hosts.letter-core-url}") String letterCoreUrl) {
        this.userHostUrl = userCoreUrl;
        this.letterCoreUrl = letterCoreUrl;
    }

    public ResponseEntity<LoginResponse> getLogin(String phoneNumber, String token) {
        String url = userHostUrl + "/login?number=" + phoneNumber + "&freshToken=" + token;
        return restTemplate.getForEntity(url, LoginResponse.class);
    }

    public ResponseEntity<LoginResponse> getLogin2(String number, String token) {
        String url = userHostUrl + "/login2/" + number + "/" + token;
        return restTemplate.getForEntity(url, LoginResponse.class);
    }

    public ResponseEntity<LoginResponse> getLogin3(String number, String token) {
        LoginParameters loginParameters = new LoginParameters();
        loginParameters.setPhoneNumber(number);
        loginParameters.setFreshTokenParam(token);
        String url = userHostUrl + "/login3";
        return restTemplate.postForEntity(url, loginParameters, LoginResponse.class);
    }

    //TODO: move this to LetterService
    public ResponseEntity<LettersByPhoneNumber> getLetters(String phoneNumber) {
        LetterCoreRequestByPhoneNumber request = new LetterCoreRequestByPhoneNumber();
        request.setPhoneNumber(phoneNumber);
        String url = letterCoreUrl + "/byPhoneNumber";
        return restTemplate.postForEntity(url, request, LettersByPhoneNumber.class);
    }
}
