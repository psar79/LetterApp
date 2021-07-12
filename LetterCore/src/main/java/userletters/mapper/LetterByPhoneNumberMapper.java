package userletters.mapper;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import userletters.controller.LettersByPhoneNumberResponse;
import userletters.controller.ReceiverLetter;
import userletters.dao.entity.Letter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class LetterByPhoneNumberMapper {

    public LettersByPhoneNumberResponse mapToLetterByPhoneNumberResponse (List<Letter> letters){

        if(CollectionUtils.isEmpty(letters)){
            return null;
        }
        List<ReceiverLetter> receiverLetters = letters.stream()
                .filter(Objects::nonNull)
                .map(this::dupa)
                .collect(Collectors.toList());

        LettersByPhoneNumberResponse response = new LettersByPhoneNumberResponse();
        response.setReceiverLetterList(receiverLetters);

        return response;

    }

    private ReceiverLetter dupa(Letter letter) {

        ReceiverLetter receiverLetter = new ReceiverLetter();

        receiverLetter.setNumber(letter.getId());

        return receiverLetter;

    }
}
