package userregister.usercore.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import userregister.usercore.dao.entity.Register;
import userregister.usercore.dao.entity.RegisterRepository;

@Service
public class RegisterManager {

    private RegisterRepository registerRepository;

    @Autowired
    public RegisterManager(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    public Register registerUser(Register register){
        return registerRepository.save(register);
    }

//    public Register findByPhoneNumber(String phoneNumber){
//        return registerRepository.findByPhoneNumber(phoneNumber);
//    }
}
