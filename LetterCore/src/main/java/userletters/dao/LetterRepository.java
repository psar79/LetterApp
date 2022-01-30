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

    Optional<Receiver> findByReceiverPhoneNumber(String number);

//    public Letter findByName(String name);
//    public Letter findByNameAndAge(String name, Integer age);
//    Letter findByPhoneNumber(String phoneNumber);

}
