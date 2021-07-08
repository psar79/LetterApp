package userregister.usercore.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class UserController {

public ResponseEntity<String> userRegister(@RequestParam String number){
    if(Objects.isNull(number) || number.length()!=9){
        return  null;
    }
    return null;
}
}
