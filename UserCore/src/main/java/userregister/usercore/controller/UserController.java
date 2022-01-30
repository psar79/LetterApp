package userregister.usercore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import userregister.usercore.dao.entity.Register;
import userregister.usercore.dao.entity.User;
import userregister.usercore.manager.RegisterManager;
import userregister.usercore.mapper.RegisterMapper;
import userregister.usercore.mapper.UserMapper;
import userregister.usercore.request.RequestLogin;
import userregister.usercore.response.IsLoggedResponse;
import userregister.usercore.utils.CodeGenerator;
import userregister.usercore.utils.RefreshToken;
import userregister.usercore.utils.Validator;

import java.util.Objects;
import java.util.Optional;

@RestController
public class UserController {

    private final Validator validator;
    private final CodeGenerator codeGenerator;
    private final RegisterManager registerManager;
    private final RegisterMapper registerMapper;
    private final RefreshToken refreshToken;
    private final UserMapper userMapper;

    @Autowired
    public UserController(Validator validator, CodeGenerator codeGenerator, RegisterManager registerManager, RegisterMapper registerMapper, RefreshToken refreshToken, UserMapper userMapper) {
        this.validator = validator;
        this.codeGenerator = codeGenerator;
        this.registerManager = registerManager;
        this.registerMapper = registerMapper;
        this.refreshToken = refreshToken;
        this.userMapper = userMapper;
    }

    @PostMapping("/add")//Klient podaje nr telefonu (spradzamy czy 9-cyfrowy), jeżeli tak to generujemy cod(6-cyfr) oraz zapisujemy  telefon oraz cod do bazy Register. W Postman aby wywołać http://localhost:8081/add?number=123456789
    public ResponseEntity<String> userRegister(@RequestParam String number) {
        if (!validator.validatePhoneNumber(number) || Objects.nonNull(registerManager.findByPhoneNumber(number))) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please, give proper number");
        }
        String code = codeGenerator.code();
//        Optional.ofNullable(null);
//        Optional.of(null);

        Register register = registerMapper.setRegister(number, code);

        registerManager.addNewRegisterUser(register);
        return ResponseEntity.ok().body(code);
    }

    @PostMapping("/add2")//Klient podaje telfon i code jeżeli prawidłowa ilość znaków, to szukamy w nr telefonu w bazie Register, a po znalzezieniu generujemy token
    //ustawiamy obiekt User(telfon, token ) i zapisujemy obiekt User(telfonu, token) do bazy danych User,      W Postman aby wywołać localhost:8081/add2?number=123456789&code=123456
    public ResponseEntity<String> userRegister2(@RequestParam String number, @RequestParam String code) {
        if (!validator.validatePhoneNumberAndCode(number, code)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please, give proper number");
        }

        Register register = registerManager.findByPhoneNumber(number);

        if (Objects.nonNull(register) && register.getCode().equals(code)) {
            String token = refreshToken.tokenCreator();

            User user = userMapper.getUser(number, token);

            registerManager.addUser(user);
            return ResponseEntity.status(HttpStatus.OK).body(token);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    //localhost:8080/login?number=
    @GetMapping("/login")//LOGOWANIE: Klient podaje nr telefonu i token, szukamy takiego nr w bazie User, jak znajdziemy to proównujemy token w biekcie user z tym z rq
    //jak są takie same to ustawiamy obiekt na true(isLoggedResponse.setLogged(true)), a jak nie to zwracamy false (isLoggedResponse.setLogged(false))       W Postman Get'em dostaje status logged (true jeżeli wartoci podano ok) http://localhost:8081/login?number=502306418&freshToken=1234567899
    public ResponseEntity<IsLoggedResponse> userLogin(@RequestParam String number, @RequestParam String freshToken) {                                                                                                            //localhost:8082/letters?phoneNumber=123456789&token=1234567899
        User user = registerManager.findUserByPhoneNumber(number);
        IsLoggedResponse isLoggedResponse = new IsLoggedResponse();

        if (Objects.nonNull(number) && Objects.nonNull(freshToken) && Objects.nonNull(user)
                && user.getRefreshedToken().equals(freshToken)) {
            isLoggedResponse.setLogged(true);
            return ResponseEntity.ok().body(isLoggedResponse);
        }
        isLoggedResponse.setLogged(false);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(isLoggedResponse);
    }

    @GetMapping("/login2/{number}/{token}")
    public ResponseEntity<IsLoggedResponse> userLogin2(@PathVariable("number") String number, @PathVariable("token") String token) {
        User user = registerManager.findUserByPhoneNumber(number);
        IsLoggedResponse isLoggedResponse = new IsLoggedResponse();

        if (Objects.nonNull(number) && Objects.nonNull(token) && Objects.nonNull(user)
                && user.getRefreshedToken().equals(token)) {
            isLoggedResponse.setLogged(true);
            return ResponseEntity.ok().body(isLoggedResponse);
        }
        isLoggedResponse.setLogged(false);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(isLoggedResponse);
    }

    @PostMapping("/login3")
    public ResponseEntity<IsLoggedResponse> userLogin3(@RequestBody RequestLogin requestLogin) {
        User user = registerManager.findUserByPhoneNumber(requestLogin.getNumberParam());

        IsLoggedResponse isLoggedResponse = new IsLoggedResponse();

        if (Objects.nonNull(requestLogin.getNumberParam()) && Objects.nonNull(requestLogin.getFreshTokenParam())
                && Objects.nonNull(user)
                && user.getRefreshedToken().equals(requestLogin.getFreshTokenParam())) {
            isLoggedResponse.setLogged(true);
            return ResponseEntity.ok().body(isLoggedResponse);
        }
        isLoggedResponse.setLogged(false);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(isLoggedResponse);
    }
}




