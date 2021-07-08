package userregister.usercore.manager;

import org.springframework.stereotype.Service;
import userregister.usercore.dao.entity.Register;
import userregister.usercore.dao.entity.RegisterRepository;
import userregister.usercore.dao.entity.User;
import userregister.usercore.dao.entity.UserRepository;

@Service
public class RegisterManager {

    private RegisterRepository registerRepository;
    private UserRepository userRepository;

    public RegisterManager(RegisterRepository registerRepository, UserRepository userRepository) {
        this.registerRepository = registerRepository;
        this.userRepository = userRepository;
    }

    public Register registerUser(Register register) {
        return registerRepository.save(register);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Register findByPhoneNumber(String phoneNumber) {
        return registerRepository.findByPhoneNumber(phoneNumber);
    }
}
