package com.horyzonty.service.login;

import com.horyzonty.service.login.request.LoginParameters;
import com.horyzonty.service.login.response.CodeResponse;
import com.horyzonty.service.login.response.PhoneNumberAndCodeResponse;
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

    public ResponseEntity<CodeResponse> getLoginToken(String phoneNumber){
        String url = userHostUrl + "/add?number=" + phoneNumber;
        return restTemplate.getForEntity(url, CodeResponse.class);
    }


    public ResponseEntity<PhoneNumberAndCodeResponse> getLogin(String phoneNumber, String token) {
        String url = userHostUrl + "/login?number=" + phoneNumber + "&freshToken=" + token;
        return restTemplate.getForEntity(url, PhoneNumberAndCodeResponse.class);
    }

    public ResponseEntity<PhoneNumberAndCodeResponse> getLogin2(String number, String token) {
        String url = userHostUrl + "/login2/" + number + "/" + token;
        return restTemplate.getForEntity(url, PhoneNumberAndCodeResponse.class);
    }

    public ResponseEntity<PhoneNumberAndCodeResponse> getLogin3(String number, String token) {
        LoginParameters loginParameters = new LoginParameters();
        loginParameters.setPhoneNumber(number);
        loginParameters.setFreshTokenParam(token);
        String url = userHostUrl + "/login3";
        return restTemplate.postForEntity(url, loginParameters, PhoneNumberAndCodeResponse.class);
    }

}
