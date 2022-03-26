package userletters.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import userletters.dao.entity.Letter;
import userletters.dao.entity.Receiver;
import userletters.dao.entity.Sender;

import java.util.List;
import java.util.Optional;

@Repository
public interface LetterRepository extends CrudRepository<Letter, Long> {

    static final String dom = "A";

    Optional<List<Letter>> findByReceiverPhoneNumber(String number);
    Optional<Letter> findByReceiverPhoneNumberAndSurname(String number, String surname);


//    public Letter findByName(String name);
//    public Letter findByNameAndAge(String name, Integer age);
//    Letter findByPhoneNumber(String phoneNumber);

}
