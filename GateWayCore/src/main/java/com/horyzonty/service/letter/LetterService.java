package com.horyzonty.service.letter;

import com.horyzonty.api.endpoint.letter.response.Letters;
import com.horyzonty.service.letter.request.LetterCoreRequestByPhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

public class LetterService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String letterHostUrl;

    public LetterService( @Value("${hosts.letter-core-url}") String letterCoreUrl) {
        this.letterHostUrl = letterCoreUrl;
    }

    public Letters getLetters(String phoneNumber) {
        LetterCoreRequestByPhoneNumber request = new LetterCoreRequestByPhoneNumber();
        request.setPhoneNumber(phoneNumber);
        String url = letterHostUrl + "/byPhoneNumber";
        return (restTemplate.postForEntity(url, request, Letters.class)).getBody();
    }
}
