package com.horyzonty.service.letter;

import com.horyzonty.api.endpoint.letter.response.Letters;
import com.horyzonty.service.letter.request.PhoneNumberRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class LetterService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String letterHostUrl;

    public LetterService( @Value("${hosts.letter-core-url}") String letterCoreUrl) {
        this.letterHostUrl = letterCoreUrl;
    }

    public ResponseEntity<Letters> getLetters(String phoneNumber) {
        PhoneNumberRequest request = new PhoneNumberRequest();
        request.setPhoneNumber(phoneNumber);
        String url = letterHostUrl + "/phoneNumber";
        return (restTemplate.postForEntity(url, request, Letters.class));
    }

    public ResponseEntity<Letters> getLetters(Long index){
        String url = letterHostUrl + "/id";
        return (restTemplate.getForEntity(url, Letters.class));
    }
}
