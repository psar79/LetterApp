package com.horyzonty.service.login;

import com.horyzonty.service.login.request.LoginParameters;
import com.horyzonty.service.login.response.LoginResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoginService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String userHostUrl;

    public LoginService(@Value("${hosts.user-core-url}") String userCoreUrl){
        this.userHostUrl = userCoreUrl;
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

}
