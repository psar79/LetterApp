package userregister.usercore.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import userregister.usercore.dao.entity.UserRepository;

import java.util.Objects;

@Service
public class UserManager {

    public UserRepository userRepository;


    @Autowired
    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestParam String number) {

        if (Objects.isNull(number) || number.length() != 9) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please, give the proper phone number");
        }

        return null;
    }
}
